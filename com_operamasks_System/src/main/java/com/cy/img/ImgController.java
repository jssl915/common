package com.cy.img;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.system.prg.util.JsonMapper;
import com.system.util.BaseController;

@Controller
@Scope("session")
@RequestMapping(value = "/cy/img")
public class ImgController extends BaseController {
	Logger log = LoggerFactory.getLogger(ImgController.class);

	@RequestMapping(value = "photowall")
	public String photowall(HttpServletRequest request){
		return "cy/img/photowall";
	}
	@RequestMapping(value = "upload")
	public String upload(HttpServletRequest request){
		return "cy/img/upload";
	}
	@RequestMapping(value = "uploadDo")
	public void uploadDo(HttpServletRequest request,HttpServletResponse response){
		String url = System.getProperty("user.dir")+"/src/main/webapp/static/img";
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multiRequest.getFile("Filedata");
		String fileName = multipartFile.getOriginalFilename();
		File targetFile = new File(url, fileName);
		try {
			multipartFile.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileUrl = fileName;
		writeToPage(JsonMapper.buildNormalMapper().toJson(fileUrl), response);
	}
}
