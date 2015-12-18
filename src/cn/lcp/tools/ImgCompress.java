package cn.lcp.tools;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImgCompress {
    private Image img;
    private int width;
    private int height;

    public ImgCompress(File file) throws Exception{
        img = ImageIO.read(file);//����image����
        width = img.getWidth(null);//�õ�ͼ���ԭ���
        height = img.getHeight(null);//�õ�ͼ���ԭ�߶�
    }

    //���տ�Ȼ��߸߶Ƚ���ѹ��
    public String resizeFix(int w, int h,String username,String filename) throws IOException {
        if (width / height > w / h) {
            return resizeByWidth(w,username,filename);
        } else {
            return	resizeByHeight(h,username,filename);
        }
    }
    // �Կ��Ϊ��׼���ȱ�������ͼƬ
    public String resizeByWidth(int w,String username,String filename) throws IOException {
        int h = (int) (height * w / width);
        return resize(w, h,username,filename);
    }
    //�Ը߶�Ϊ��׼���ȱ�������ͼƬ
    public String resizeByHeight(int h,String username,String filename) throws IOException {
        int w = (int) (width * h / height);
        return resize(w, h, username,filename);
    }
    //ǿ��ѹ��/�Ŵ�ͼƬ���̶��Ĵ�С�� w�¿�ȣ�h�¸߶ȣ�username��Ҫ�޸ĵ�ͼƬ���ƣ�pnameԴ�ļ���ͼƬ  ������
    public String resize(int w, int h,String pictureName,String pname) throws IOException {
        // SCALE_SMOOTH �������㷨 ��������ͼƬ��ƽ���ȵ� ���ȼ����ٶȸ� ���ɵ�ͼƬ�����ȽϺ� ���ٶ���
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // ������С���ͼ

        //����ϴ�·��
        String uploadPath = ServletActionContext.getServletContext().getRealPath("/upload/firmuserPicture");
        //�޸��ϴ��ļ�����
        int i = pname.lastIndexOf(".");// ԭ�����ﵹ����һ��"."������
        String ext = pname.substring(i + 1);// ȡ�ú�׺����"."������ַ�

        Date nowtime=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");//Сд��mm��ʾ���Ƿ���
        String filename = pictureName +"_"+df.format(nowtime)+ "." + ext;// �ϴ����ļ��ı�����


        File destFile = new File(uploadPath, filename);//��������ļ�
        FileOutputStream out = new FileOutputStream(destFile); // ������ļ���

        System.out.println("success update picture !");
        // ��������ʵ��bmp��png��gifתjpg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image); // JPEG����
        out.close();

        String url="upload/firmuserPicture/"+filename;

        return url;
    }
}
