package com.system.prg.role.service;

import java.util.List;

import com.system.prg.role.entity.SRole;
import com.system.prg.role.entity.SRoleMenu;
import com.system.prg.role.entity.SUserRole;
import com.system.prg.util.BaseService;

public interface SRoleService extends BaseService<SRole> {
	
	public List<SRoleMenu> selectRoleMenuByCondition(Long roleId);
	
	public SRoleMenu insertSRoleMenu(SRoleMenu record);
	
	public void deleteSRoleMenu(Long roleId);
	
	public List<SUserRole> selectSUserRoleByCondition(Long roleId);
	
	public SUserRole insertSUserRole(SUserRole record);
	
	public void deleteSUserRole(Long roleId);

}
