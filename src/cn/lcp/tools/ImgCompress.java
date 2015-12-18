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
        img = ImageIO.read(file);//构造image对象
        width = img.getWidth(null);//得到图像的原宽度
        height = img.getHeight(null);//得到图像的原高度
    }

    //按照宽度或者高度进行压缩
    public String resizeFix(int w, int h,String username,String filename) throws IOException {
        if (width / height > w / h) {
            return resizeByWidth(w,username,filename);
        } else {
            return	resizeByHeight(h,username,filename);
        }
    }
    // 以宽度为基准，等比例放缩图片
    public String resizeByWidth(int w,String username,String filename) throws IOException {
        int h = (int) (height * w / width);
        return resize(w, h,username,filename);
    }
    //以高度为基准，等比例缩放图片
    public String resizeByHeight(int h,String username,String filename) throws IOException {
        int w = (int) (width * h / height);
        return resize(w, h, username,filename);
    }
    //强制压缩/放大图片到固定的大小， w新宽度，h新高度，username需要修改的图片名称，pname源文件（图片  ）名称
    public String resize(int w, int h,String pictureName,String pname) throws IOException {
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图

        //获得上传路径
        String uploadPath = ServletActionContext.getServletContext().getRealPath("/upload/firmuserPicture");
        //修改上传文件名称
        int i = pname.lastIndexOf(".");// 原名称里倒数第一个"."在哪里
        String ext = pname.substring(i + 1);// 取得后缀，及"."后面的字符

        Date nowtime=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");//小写的mm表示的是分钟
        String filename = pictureName +"_"+df.format(nowtime)+ "." + ext;// 上传后文件的保存名


        File destFile = new File(uploadPath, filename);//生成输出文件
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流

        System.out.println("success update picture !");
        // 可以正常实现bmp、png、gif转jpg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image); // JPEG编码
        out.close();

        String url="upload/firmuserPicture/"+filename;

        return url;
    }
}
