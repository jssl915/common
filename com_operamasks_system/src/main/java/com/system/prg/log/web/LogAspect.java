package com.system.prg.log.web;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.system.prg.log.entity.SLog;
import com.system.prg.log.service.SLogService;
import com.system.prg.user.entity.SUser;
@Aspect
public class LogAspect {
	@Autowired
	private SLogService sLogService;
	@Pointcut("execution(* com..*Controller.*(..))")
	private void doLog() {}
	/**
	 * 注意拦截的请求第一个参数必须是request
	 * @param request
	 */
	@After("doLog() && args(request,..)")
	public void doAfter(HttpServletRequest request) {
		SUser sUser = (SUser)request.getSession().getAttribute("user");
		String url=request.getRequestURI();
		String resUrl = url.substring(url.indexOf("//") + 1);
		if(sUser!=null&&(resUrl.indexOf("getMenu")==-1)&&(resUrl.indexOf("list")==-1)){
			SLog sLog = new SLog();
			sLog.setUserId(sUser.getUserId());
			sLog.setUserName(sUser.getUserName());
			sLog.setActionUrl(resUrl);
			sLog.setUserIp(request.getRemoteAddr());
			sLogService.insert(sLog);
		}
	}
}
