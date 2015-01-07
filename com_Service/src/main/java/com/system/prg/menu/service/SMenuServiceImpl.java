package com.system.prg.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.prg.menu.entity.SMenu;
import com.system.prg.menu.mapper.SMenuMapper;
import com.system.prg.util.AjaxUtils;
import com.system.prg.util.BusinessException;
import com.system.prg.util.DateUtils;
import com.system.prg.util.PageObject;

@Service
@Transactional(rollbackFor=BusinessException.class)
public class SMenuServiceImpl implements SMenuService {
    
	@Autowired
	SMenuMapper mapper;
	
	@Override
	public void insert(SMenu record){
		record.setMenuStatus(1l);
		record.setCreateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.insert(record);
	}
	@Override
	public void deleteByCondition(Long menuId){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("pMenuId", menuId);
		mapper.deleteByCondition(condition);
	}
	@Override
	public void updateByCondition(SMenu record){
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.updateByCondition(record);
	}
	@Override
	public Integer countByCondition(Map<String, Object> condititon){
		return mapper.countByCondition(condititon);
	}
	@Override
	public List<SMenu> selectByCondition(Map<String, Object> condititon){
		return mapper.selectByCondition(condititon);
	}
	
	@Override
	public List<SMenu> pageList(PageObject po) {
		RowBounds rowBounds = new  RowBounds(po.getStart(),po.getPageSize()) ;
		return mapper.selectByCondition(po.getCondition(),rowBounds);
	}
	
	@Override
	public void deleteByPrimaryKey(Long menuId){
		mapper.deleteByPrimaryKey(menuId);
	}
	@Override
	public SMenu findByPrimaryKey(Long menuId){
		return mapper.findByPrimaryKey(menuId);
	}
	
	@Override
	public String listTree(boolean expanded){
		SMenu centerMenu = mapper.findByPrimaryKey(0l);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("menuStatus", 1);
		condition.put("orderByClause", "MENU_ORDER asc");
		List<SMenu>allMenuList = mapper.selectByCondition(condition);
		List<MenuTree> pttList = new ArrayList<MenuTree>();
		for (SMenu pt : allMenuList) {
			if(pt.getMenuId()!=0){
				pttList.add(new MenuTree(pt));
			}
		}
		Map<String, MenuTree> prodTypeTreeMap = new HashMap<String, MenuTree>();
		for (MenuTree ptt : pttList) {
			prodTypeTreeMap.put(ptt.getId(), ptt);
		}
		MenuTree root = new MenuTree();
		root.setId("0");
		root.setText(centerMenu.getMenuName());
		root.setExpanded(true);
		for (MenuTree ptt : pttList) {
			if (ptt.getPid().equals("0")) {
				root.addChildren(ptt, expanded);
			}else {
				MenuTree parentTree = prodTypeTreeMap.get(ptt.getPid());
				if (parentTree != null) {
					parentTree.addChildren(ptt, expanded);
				}
			}
		}
		List<MenuTree> tree = new ArrayList<MenuTree>();
		tree.add(root);
		return AjaxUtils.toJson(tree);
	}
	@Override
	public String listUserTree(Long userId,boolean expanded){
		SMenu centerMenu = mapper.findByPrimaryKey(0l);
		List<SMenu>allMenuList = mapper.getAllMenus(userId);
		List<MenuTree> pttList = new ArrayList<MenuTree>();
		for (SMenu pt : allMenuList) {
			if(pt.getMenuId()!=0){
				pttList.add(new MenuTree(pt));
			}
		}
		Map<String, MenuTree> prodTypeTreeMap = new HashMap<String, MenuTree>();
		for (MenuTree ptt : pttList) {
			prodTypeTreeMap.put(ptt.getId(), ptt);
		}
		MenuTree root = new MenuTree();
		root.setId("0");
		root.setText(centerMenu.getMenuName());
		root.setExpanded(true);
		for (MenuTree ptt : pttList) {
			if (ptt.getPid().equals("0")) {
				root.addChildren(ptt, expanded);
			}else {
				MenuTree parentTree = prodTypeTreeMap.get(ptt.getPid());
				if (parentTree != null) {
					parentTree.addChildren(ptt, expanded);
				}
			}
		}
		List<MenuTree> tree = new ArrayList<MenuTree>();
		tree.add(root);
		return AjaxUtils.toJson(tree);
	}
}
