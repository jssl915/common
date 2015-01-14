package com.cy.img;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.util.BaseController;

@Controller
@Scope("session")
@RequestMapping(value = "/cy/img")
public class ImgController extends BaseController {
	Logger log = LoggerFactory.getLogger(ImgController.class);

	@RequestMapping(value = "photowall")
	public String init(HttpServletRequest request){
		System.out.println(123);
		return "cy/img/photowall";
	}
}
