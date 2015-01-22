package com.system.prg.menu.mapper;

import java.util.List;

import com.system.prg.menu.entity.SMenu;
import com.system.prg.util.BaseMapper;

public interface SMenuMapper extends BaseMapper<SMenu>{
	
	public  List<SMenu> getAllMenus(Long userId) ;

}
