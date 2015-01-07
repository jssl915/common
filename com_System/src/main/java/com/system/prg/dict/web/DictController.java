package com.system.prg.dict.web;

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

import com.system.prg.dict.entity.SDict;
import com.system.prg.dict.service.SDictService;
import com.system.prg.util.AjaxUtils;
import com.system.prg.util.BaseController;
import com.system.prg.util.BusinessException;

@Controller
@Scope("session")
@RequestMapping(value = "/system/prg/dict")
public class DictController extends BaseController {
	Logger log = LoggerFactory.getLogger(DictController.class);
	@Autowired
	private SDictService sDictService;

	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		Map<String,String>statusMap = sDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", AjaxUtils.toJson(statusMap));
		return "system/prg/dict/init";
	}
	
	@RequestMapping(value = "list")
	public void list(HttpServletRequest request, HttpServletResponse response,@ModelAttribute SDict sDict) throws BusinessException{
		log.debug("method: list() ");
		try {
			po = getPageObject(request,"UPDATE_TIME desc");
			if (po.isExeQuery()) {
				po.getCondition().put("dictNameLike", sDict.getDictName());
				po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
				po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
				po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
				po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
				List<SDict> modelList = sDictService.pageList(po);
				Integer totalCount = sDictService.countByCondition(po.getCondition());
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
		return "system/prg/dict/add";
	}
	
	@RequestMapping(value = "insert")
	public String insert(HttpServletRequest request,@ModelAttribute SDict sDict){
		sDictService.insert(sDict);
		return "success";
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long dictId = Long.valueOf(request.getParameter("dictId"));
		SDict sDict = sDictService.findByPrimaryKey(dictId);
		request.setAttribute("sDict", sDict);
		Map<String,String>statusMap = sDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", statusMap);
		return "system/prg/dict/edit";
	}
	
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request,@ModelAttribute SDict sDict){
		sDictService.updateByCondition(sDict);
		return "success";
	}
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		log.debug("method: delete() ");
		try {
			String ids = request.getParameter("ids");
			String[] aDeleteId = ids.split(",");
			for(String id:aDeleteId){
				sDictService.deleteByPrimaryKey(Long.valueOf(id));
			}
			writeToPage(AjaxUtils.makeJsonResponse(true), response);
		} catch (Exception e) {
			writeToPage(AjaxUtils.makeJsonResponse(false, "系统发生异常！"), response);
		}
	}
	@RequestMapping(value = "checkDictName")
	public void checkDictName(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SDict dict) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("dictName", dict.getDictName());
		condititon.put("nodDictId", dict.getDictId());
		int flag = sDictService.countByCondition(condititon);
		writeToPage(flag>0?"false":"true", response);
	}
}
