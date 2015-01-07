package com.system.prg.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.system.prg.user.entity.SUser;
import com.system.prg.user.service.SUserService;
import com.system.prg.util.AjaxUtils;
import com.system.prg.util.BaseController;
import com.system.util.SysProperties;
@Controller
@Scope("session")
@RequestMapping(value = "/system")
public class SecurityController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(SecurityController.class);
	private static Map<String, String> signedOnUserMap = new ConcurrentHashMap<String, String>();
	@Autowired
	private SUserService sUserService;
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLoginForm(HttpServletRequest request) {
		return "login";
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public void login(HttpServletRequest request,HttpServletResponse response,@ModelAttribute LoginCommand command,HttpSession session,BindingResult errors) {
		String userName = command.getUserName();
		String passWord = command.getPassWord();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);
		SecurityUtils.getSubject().getSession().setAttribute("currentLoginIP", request.getRemoteAddr());
		String msg = "";
		try {
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			logger.debug("Error authenticating.", e);
			if (e instanceof org.apache.shiro.authc.ConcurrentAccessException) {
				msg = "用户已登录！";
			} else if (e instanceof org.apache.shiro.authc.AccountException) {
				msg = "未知帐号错误或用户状态异常！";
			} else if (e instanceof org.apache.shiro.authc.IncorrectCredentialsException) {
				msg = "用户名密码错误！";
			} else if (e instanceof org.apache.shiro.authc.AuthenticationException) {
				msg = "认证失败！";
			}
			errors.reject("error.invalidLogin", "The username or password was not correct.");
		}

		if (errors.hasErrors()) {
			writeToPage(AjaxUtils.toJson(msg), response);
		} else {
			signedOnUserMap.put(userName,  request.getRemoteAddr());
			Map<String, Object> condititon = new HashMap<String, Object>();
			condititon.put("userName", token.getUsername());
			List<SUser> userList = sUserService.selectByCondition(condititon);
			SUser user = userList.get(0);
			session.setAttribute("user", user);
			String pageRows = SysProperties.getProperty("PAGE_ROWS");
			String initPwd = SysProperties.getProperty("INIT_PWD");
			session.setAttribute("pageRows", pageRows);
			session.setAttribute("initPwd", initPwd);
			writeToPage(AjaxUtils.toJson(true), response);
		}
	}

	public static void logout(String loginName) {
		signedOnUserMap.remove(loginName);
	}

	public static boolean isLogin(String loginName) {
		return signedOnUserMap.get(loginName) != null;
	}
	
	public static String getLastLoginIP(String loginName) {
		String ip = signedOnUserMap.get(loginName);
		return ip== null ? "": ip;
	}
	
	public static void login(String loginName, String ip) {
		signedOnUserMap.put(loginName, ip);
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			signedOnUserMap.remove(session.getAttribute("user"));
			SecurityUtils.getSubject().getSession().stop();
		} catch (Exception e) {
			//do nothing
		}
		return "redirect:/";
	}
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
}
