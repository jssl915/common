package com.system.prg.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.prg.menu.service.SMenuService;
import com.system.prg.user.entity.SUser;
import com.system.prg.user.service.SUserService;
import com.system.prg.util.AjaxUtils;
import com.system.prg.util.MD5Encoder;
import com.system.util.BaseController;
@Controller
@Scope("session")
@RequestMapping(value = "/index")
public class IndexController extends BaseController {
	@Autowired
	private SMenuService sMenuService;
	@Autowired
	private SUserService sUserService;
	@RequestMapping(value = "tree")
	public void index(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		SUser sUser = (SUser)session.getAttribute("user");
		String menuTree = sMenuService.listUserTree(sUser.getUserId(),false);
		writeToPage(menuTree, response);
	}
	@RequestMapping(value = "password")
	public String password(){
		return "password";
	}
	@RequestMapping(value = "checkUserPwd")
	public void checkUserPwd(HttpServletRequest request, HttpServletResponse response){
		SUser sUser = (SUser)request.getSession().getAttribute("user");
		String password = MD5Encoder.encode(request.getParameter("password"));
		if(password.equals(sUser.getUserPwd())){
			writeToPage(AjaxUtils.toJson(true), response);
		}else{
			writeToPage(AjaxUtils.toJson(false), response);
		}
	}
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request){
		String userPwd = request.getParameter("newPwd");
		SUser sUser = (SUser)request.getSession().getAttribute("user");
		sUser.setUserPwd(userPwd);
		sUserService.updateUserPwd(sUser);
		return "success";
	}
}
