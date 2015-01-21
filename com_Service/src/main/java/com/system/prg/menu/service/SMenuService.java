package com.system.prg.menu.service;

import com.system.prg.menu.entity.SMenu;
import com.system.prg.util.BaseService;

public interface SMenuService extends BaseService<SMenu>{

	public String listTree(boolean expanded);
	
	public String listUserTree(Long userId,boolean expanded);

	void deleteByCondition(Long menuId);
}
