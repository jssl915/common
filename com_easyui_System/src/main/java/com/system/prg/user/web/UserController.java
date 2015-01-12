package com.system.prg.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.prg.dict.service.SDictService;
import com.system.prg.user.entity.SUser;
import com.system.prg.user.service.SUserService;
import com.system.prg.util.AjaxUtils;
import com.system.prg.util.BusinessException;
import com.system.util.BaseController;

@Controller
@Scope("session")
@RequestMapping(value = "/system/prg/user")
public class UserController extends BaseController {
	Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private SUserService sUserService;
	@Autowired
	private SDictService sDictService;

	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		request.setAttribute("statusMap", AjaxUtils.toJson(sDictService.getDetailValueMap("状态")));
		return "system/prg/user/init";
	}
	
	@RequestMapping(value = "list")
	public void list(HttpServletRequest request, HttpServletResponse response,@ModelAttribute SUser user){
		log.debug("method: list() ");
		try {
			po = getPageObject(request,"USER_ORDER asc");
			if (po.isExeQuery()) {
				po.getCondition().put("userNameLike", user.getUserName());
				po.getCondition().put("realName", user.getRealName());
				po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
				po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
				System.out.println(request.getParameter("createTimeStart")+" "+request.getParameter("createTimeEnd"));
				po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
				po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
				List<SUser> modelList = sUserService.pageList(po);
				Integer totalCount = sUserService.countByCondition(po.getCondition());
				po.setTotalCount(totalCount);
				super.list(modelList, po, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			writeToPage(AjaxUtils.makeJsonResponse(false, "数据错误"), response);
		}
	}
	
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request){
		return "system/prg/user/add";
	}
	
	@RequestMapping(value = "insert")
	public String insert(HttpServletRequest request,@ModelAttribute SUser user){
		sUserService.insert(user);
		return "success";
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long userId = Long.valueOf(request.getParameter("userId"));
		SUser sUser = sUserService.findByPrimaryKey(userId);
		request.setAttribute("sUser", sUser);
		Map<String,String>statusMap = sDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", statusMap);
		return "system/prg/user/edit";
	}
	
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request,@ModelAttribute SUser user){
		sUserService.updateByCondition(user);
		return "success";
	}
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		log.debug("method: delete() ");
		try {
			String ids = request.getParameter("ids");
			String[] aDeleteId = ids.split(",");
			for(String id:aDeleteId){
				sUserService.deleteByPrimaryKey(Long.valueOf(id));
			}
			writeToPage(AjaxUtils.makeJsonResponse(true), response);
		} catch (Exception e) {
			writeToPage(AjaxUtils.makeJsonResponse(false, "系统发生异常！"), response);
		}
	}
	
	@RequestMapping(value = "checkUserName")
	public void checkUserName(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SUser user) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("userName", user.getUserName());
		condititon.put("nodUserId", user.getUserId());
		int flag = sUserService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
	
	@RequestMapping(value = "initPwd")
	public void initPwd(HttpServletRequest request,HttpServletResponse response){
		try {
			String userPwd = (String)request.getSession().getAttribute("initPwd");
			String ids = request.getParameter("ids");
			String[] aId = ids.split(",");
			for(String id:aId){
				SUser sUser = new SUser();
				sUser.setUserId(Long.valueOf(id));
				sUser.setUserPwd(userPwd);
				sUserService.updateUserPwd(sUser);
			}
			writeToPage(AjaxUtils.makeJsonResponse(true), response);
		} catch (Exception e) {
			writeToPage(AjaxUtils.makeJsonResponse(false, "系统发生异常！"), response);
		}
	}
}
