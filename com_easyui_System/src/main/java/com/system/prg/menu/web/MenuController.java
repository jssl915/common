package com.system.prg.menu.web;

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
import com.system.prg.menu.entity.SMenu;
import com.system.prg.menu.service.SMenuService;
import com.system.prg.user.service.SUserService;
import com.system.prg.util.AjaxUtils;
import com.system.prg.util.BusinessException;
import com.system.util.BaseController;
import com.system.util.ComUtil;

@Controller
@Scope("session")
@RequestMapping(value = "/system/prg/menu")
public class MenuController extends BaseController {
	Logger log = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private SUserService sUserService;
	@Autowired
	private SMenuService sMenuService;
	@Autowired
	private SDictService sDictService;

	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		Map<String,String>statusMap = sDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", AjaxUtils.toJson(statusMap));
		request.setAttribute("statusCombo", ComUtil.toCombo(statusMap));
		return "system/prg/menu/init";
	}
	
	@RequestMapping(value = "list")
	public void list(HttpServletRequest request, HttpServletResponse response,@ModelAttribute SMenu sMenu) throws BusinessException{
		log.debug("method: list() ");
		try {
			po = getPageObject(request,"MENU_ORDER asc");
			if (po.isExeQuery()) {
				po.getCondition().put("pMenuId", sMenu.getMenuId());
				po.getCondition().put("menuNameLike", sMenu.getMenuName());
				po.getCondition().put("menuLevel", sMenu.getMenuLevel());
				po.getCondition().put("menuStatus", sMenu.getMenuStatus());
				po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
				po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
				List<SMenu> modelList = sMenuService.pageList(po);
				Integer totalCount = sMenuService.countByCondition(po.getCondition());
				po.setTotalCount(totalCount);
				super.list(modelList, po, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			writeToPage(AjaxUtils.makeJsonResponse(false, "数据错误"), response);
		}
	}
	
	@RequestMapping(value = "showAdd")
	public String showAdd(HttpServletRequest request,HttpServletResponse response){
		Long menuPid = Long.valueOf(request.getParameter("menuPid"));
		SMenu sMenuP = sMenuService.findByPrimaryKey(menuPid);
		request.setAttribute("sMenuP", sMenuP);
		return "system/prg/menu/add";
	}
	
	@RequestMapping(value = "insert")
	public String insert(HttpServletRequest request,@ModelAttribute SMenu sMenu){
		Long menuPid = Long.valueOf(request.getParameter("menuPid"));
		Long menuLevel = sMenu.getMenuLevel()+1;
		sMenu.setMenuPid(menuPid);
		sMenu.setMenuLevel(menuLevel);
		sMenuService.insert(sMenu);
		return "success";
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long menuId = Long.valueOf(request.getParameter("menuId"));
		SMenu sMenu = sMenuService.findByPrimaryKey(menuId);
		SMenu sMenuP = sMenuService.findByPrimaryKey(sMenu.getMenuPid());
		request.setAttribute("sMenu", sMenu);
		request.setAttribute("sMenuP", sMenuP);
		Map<String,String>statusMap = sDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", statusMap);
		return "system/prg/menu/edit";
	}
	
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request,@ModelAttribute SMenu sMenu){
		sMenuService.updateByCondition(sMenu);
		return "success";
	}
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		log.debug("method: delete() ");
		try {
			String ids = request.getParameter("ids");
			String[] aDeleteId = ids.split(",");
			for(String id:aDeleteId){
				sMenuService.deleteByCondition(Long.valueOf(id));
			}
			writeToPage(AjaxUtils.makeJsonResponse(true), response);
		} catch (Exception e) {
			writeToPage(AjaxUtils.makeJsonResponse(false, "系统发生异常！"), response);
		}
	}
	
	@RequestMapping(value = "getMenu")
	public void getMenu(HttpServletRequest request, HttpServletResponse response){
		Long menuId = Long.valueOf(request.getParameter("menuId"));
		SMenu sMenu = sMenuService.findByPrimaryKey(menuId);
		writeToPage(AjaxUtils.toJson(sMenu), response);
	}
	
	@RequestMapping(value = "tree")
	public void tree(HttpServletRequest request, HttpServletResponse response){
		String resourceTree = sMenuService.listTree(true);
		writeToPage(resourceTree, response);
	}
	
	@RequestMapping(value = "checkMenuName")
	public void checkMenuName(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SMenu menu) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("menuName", menu.getMenuName());
		condititon.put("nodMenuId", menu.getMenuId());
		int flag = sMenuService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
}
