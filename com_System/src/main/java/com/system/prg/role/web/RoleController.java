package com.system.prg.role.web;

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

import com.system.prg.menu.service.SMenuService;
import com.system.prg.role.entity.SRole;
import com.system.prg.role.entity.SRoleMenu;
import com.system.prg.role.entity.SUserRole;
import com.system.prg.role.service.SRoleService;
import com.system.prg.user.entity.SUser;
import com.system.prg.user.service.SUserService;
import com.system.prg.util.AjaxUtils;
import com.system.prg.util.BusinessException;
import com.system.util.BaseController;

@Controller
@Scope("session")
@RequestMapping(value = "/system/prg/role")
public class RoleController extends BaseController {
	Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private SRoleService sRoleService;
	@Autowired
	private SMenuService sMenuService;
	@Autowired
	private SUserService sUserService;

	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		return "system/prg/role/init";
	}
	
	@RequestMapping(value = "list")
	public void list(HttpServletRequest request, HttpServletResponse response) throws BusinessException{
		log.debug("method: list() ");
		try {
			po = getPageObject(request,"ROLE_ORDER asc");
			if (po.isExeQuery()) {
				po.getCondition().put("roleNameLike", request.getParameter("roleName"));
				po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
				po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
				po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
				po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
				List<SRole> modelList = sRoleService.pageList(po);
				Integer totalCount = sRoleService.countByCondition(po.getCondition());
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
		return "system/prg/role/add";
	}
	
	@RequestMapping(value = "insert")
	public String insert(HttpServletRequest request,@ModelAttribute SRole sRole){
		sRoleService.insert(sRole);
		return "success";
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		SRole sRole = sRoleService.findByPrimaryKey(roleId);
		request.setAttribute("sRole", sRole);
		return "system/prg/role/edit";
	}
	
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request,@ModelAttribute SRole sRole){
		sRoleService.updateByCondition(sRole);
		return "success";
	}
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		log.debug("method: delete() ");
		try {
			String ids = request.getParameter("ids");
			String[] aDeleteId = ids.split(",");
			for(String id:aDeleteId){
				sRoleService.deleteByPrimaryKey(Long.valueOf(id));
			}
			writeToPage(AjaxUtils.makeJsonResponse(true), response);
		} catch (Exception e) {
			writeToPage(AjaxUtils.makeJsonResponse(false, "系统发生异常！"), response);
		}
	}
	@RequestMapping(value = "menuTree")
	public String menuTree(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		List<SRoleMenu> SRoleMenu = sRoleService.selectRoleMenuByCondition(roleId);
		request.setAttribute("roleId", roleId);
		request.setAttribute("SRoleMenuJson", AjaxUtils.toJson(SRoleMenu));
		String resourceTree = sMenuService.listTree(true);
		request.setAttribute("resourceTree", resourceTree);
		return "system/prg/role/menuTree";
	}
	@RequestMapping(value = "bindMenu")
	public String bindMenu(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		sRoleService.deleteSRoleMenu(roleId);
		String menuIds = request.getParameter("menuIds");
		if(menuIds!=""){
			String aMenuIds[] = menuIds.split(",");
			for(int i=0;i<aMenuIds.length;i++){
				SRoleMenu sRoleMenu = new SRoleMenu();
				sRoleMenu.setRoleId(roleId);
				sRoleMenu.setMenuId(Long.valueOf(aMenuIds[i]));
				sRoleService.insertSRoleMenu(sRoleMenu);
			}
		}
		return "success";
	}
	@RequestMapping(value = "userDialog")
	public String userDialog(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		List<SUserRole> sUserRoleList = sRoleService.selectSUserRoleByCondition(roleId);
		List<SUser>sUserList = sUserService.selectByCondition(null);
		request.setAttribute("roleId", roleId);
		request.setAttribute("sUserRoleList", sUserRoleList);
		request.setAttribute("sUserList", sUserList);
		return "system/prg/role/roleUser";
	}
	@RequestMapping(value = "bindUser")
	public String bindUser(HttpServletRequest request){
		Long roleId = Long.valueOf(request.getParameter("roleId"));
		String userIds = request.getParameter("userIds");
		sRoleService.deleteSUserRole(roleId);
		if(userIds!=null){
			String aUserIds[] = userIds.split(",");
			for(int i=0;i<aUserIds.length;i++){
				SUserRole sUserRole = new SUserRole();
				sUserRole.setRoleId(roleId);
				sUserRole.setUserId(Long.valueOf(aUserIds[i]));
				sRoleService.insertSUserRole(sUserRole);
			}
		}
		return "success";
	}
	
	@RequestMapping(value = "checkRoleName")
	public void checkRoleName(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SRole role) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("roleName", role.getRoleName());
		condititon.put("nodRoleId", role.getRoleId());
		int flag = sRoleService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
}
