package com.system.prg.role.service;

import java.util.List;
import java.util.Map;

import com.system.prg.role.entity.SRole;
import com.system.prg.role.entity.SRoleMenu;
import com.system.prg.role.entity.SUserRole;
import com.system.prg.util.PageObject;

public interface SRoleService {

	public SRole insert(SRole record);

	public void deleteByCondition(Map<String, Object> condition);

	public void updateByCondition(SRole record);
	
	public List<SRole> selectByCondition(Map<String, Object> condititon);
	
	public Integer countByCondition(Map<String, Object> condititon);

	public List<SRole> pageList(PageObject po);

	public void deleteByPrimaryKey(Long roleId);

	public SRole findByPrimaryKey(Long roleId);
	
	public List<SRoleMenu> selectRoleMenuByCondition(Long roleId);
	
	public SRoleMenu insertSRoleMenu(SRoleMenu record);
	
	public void deleteSRoleMenu(Long roleId);
	
	public List<SUserRole> selectSUserRoleByCondition(Long roleId);
	
	public SUserRole insertSUserRole(SUserRole record);
	
	public void deleteSUserRole(Long roleId);

}
