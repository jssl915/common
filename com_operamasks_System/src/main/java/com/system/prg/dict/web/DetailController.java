package com.system.prg.dict.web;

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

import com.system.prg.dict.entity.SDetail;
import com.system.prg.dict.service.SDetailService;
import com.system.prg.dict.service.SDictService;
import com.system.prg.util.AjaxUtils;
import com.system.prg.util.BusinessException;
import com.system.util.BaseController;

@Controller
@Scope("session")
@RequestMapping(value = "/system/prg/detail")
public class DetailController extends BaseController {
	Logger log = LoggerFactory.getLogger(DetailController.class);
	@Autowired
	private SDetailService sDetailService;
	@Autowired
	private SDictService sDictService;

	@RequestMapping(value = "init")
	public String init(HttpServletRequest request){
		Map<String,String>statusMap = sDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", AjaxUtils.toJson(statusMap));
		return "system/prg/Detail/init";
	}
	
	@RequestMapping(value = "list")
	public void list(HttpServletRequest request, HttpServletResponse response,@ModelAttribute SDetail sDetail) throws BusinessException{
		log.debug("method: list() ");
		try {
			po = getPageObject(request,"UPDATE_TIME desc");
			if (po.isExeQuery()) {
				po.getCondition().put("dictId", sDetail.getDictId());
				po.getCondition().put("detailNameLike", sDetail.getDetailName());
				po.getCondition().put("createTimeStart",request.getParameter("createTimeStart"));
				po.getCondition().put("createTimeEnd",request.getParameter("createTimeEnd"));
				po.getCondition().put("updateTimeStart",request.getParameter("updateTimeStart"));
				po.getCondition().put("updateTimeEnd",request.getParameter("updateTimeEnd"));
				List<SDetail> modelList = sDetailService.pageList(po);
				Integer totalCount = sDetailService.countByCondition(po.getCondition());
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
		Long dictId = Long.valueOf(request.getParameter("dictId"));
		request.setAttribute("dictId", dictId);
		return "system/prg/detail/add";
	}
	
	@RequestMapping(value = "insert")
	public void insert(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SDetail sDetail){
		sDetailService.insert(sDetail);
		writeToPage(AjaxUtils.makeJsonResponse(true), response);
	}
	
	@RequestMapping(value = "showEdit")
	public String showEdit(HttpServletRequest request){
		Long detailId = Long.valueOf(request.getParameter("detailId"));
		SDetail sDetail = sDetailService.findByPrimaryKey(detailId);
		request.setAttribute("sDetail", sDetail);
		Map<String,String>statusMap = sDictService.getDetailValueMap("状态");
		request.setAttribute("statusMap", statusMap);
		return "system/prg/detail/edit";
	}
	
	@RequestMapping(value = "update")
	public String update(HttpServletRequest request,@ModelAttribute SDetail sDetail){
		sDetailService.updateByCondition(sDetail);
		return "success";
	}
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		log.debug("method: delete() ");
		try {
			String ids = request.getParameter("ids");
			String[] aDeleteId = ids.split(",");
			for(String id:aDeleteId){
				sDetailService.deleteByPrimaryKey(Long.valueOf(id));
			}
			writeToPage(AjaxUtils.makeJsonResponse(true), response);
		} catch (Exception e) {
			writeToPage(AjaxUtils.makeJsonResponse(false, "系统发生异常！"), response);
		}
	}
	
}
