package com.system.prg.menu.service;

import java.util.List;
import java.util.Map;

import com.system.prg.menu.entity.SMenu;
import com.system.prg.util.PageObject;

public interface SMenuService {

	public void insert(SMenu record);

	public void deleteByCondition(Long menuId);

	public void updateByCondition(SMenu record);

	public Integer countByCondition(Map<String, Object> condititon);

	public List<SMenu> selectByCondition(Map<String, Object> condititon);
	
	public List<SMenu> pageList(PageObject po);

	public void deleteByPrimaryKey(Long menuId);

	public SMenu findByPrimaryKey(Long menuId);

	public String listTree(boolean expanded);
	
	public String listUserTree(Long userId,boolean expanded);
}
