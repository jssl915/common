package com.cy.chart;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.util.BaseController;

@Controller
@Scope("session")
@RequestMapping(value = "/cy/chart")
public class ChartController extends BaseController {
	Logger log = LoggerFactory.getLogger(ChartController.class);

	@RequestMapping(value = "baseline")
	public String photowall(HttpServletRequest request){
		return "cy/chart/baseline";
	}
	@RequestMapping(value = "basepie")
	public String basepie(HttpServletRequest request){
		return "cy/chart/basepie";
	}
	@RequestMapping(value = "basecolumn")
	public String basecolumn(HttpServletRequest request){
		return "cy/chart/basecolumn";
	}
}
