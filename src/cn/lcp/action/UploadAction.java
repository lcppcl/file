package cn.lcp.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import cn.lcp.tools.ImgCompress;

public class UploadAction extends ActionSupport {
	/** 文件对象 */
	private List<File> Filedata;
	/** 文件名 */
	private List<String> FiledataFileName;
	/** 文件内容类型 */
	private List<String> FiledataContentType;

	public List<File> getFiledata() {
		return Filedata;
	}

	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}

	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}

	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}

	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}

	public String shangchuan() throws Exception{
		if (Filedata.size() != 0) {
			for (int i = 0; i < Filedata.size(); i++) {
				File uploadFile = Filedata.get(i);
				// 对图片进行压缩上传
				ImgCompress imgCom = new ImgCompress(uploadFile);
				// 压缩图片 宽，高，图片名称，原图片名称
				Long date = new Date().getTime();
				String filename = imgCom.resize(130, 150, date.toString(), this.getFiledataFileName().get(i));
				System.out.println(filename);
			}
		}
		return "success";
	}
}
