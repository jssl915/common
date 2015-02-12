package com.common.index.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
	@RequestMapping(value = "init")
	public String init(){
		System.out.println(123);
		return "index";
	}
}
