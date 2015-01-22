package com.system.prg.role.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.prg.role.entity.SRole;
import com.system.prg.role.entity.SRoleMenu;
import com.system.prg.role.entity.SUserRole;
import com.system.prg.role.mapper.SRoleMapper;
import com.system.prg.role.mapper.SRoleMenuMapper;
import com.system.prg.role.mapper.SUserRoleMapper;
import com.system.prg.util.BaseServiceImpl;
import com.system.prg.util.BusinessException;
import com.system.prg.util.DateUtils;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class SRoleServiceImpl extends BaseServiceImpl<SRole> implements SRoleService {
	@Autowired
	SRoleMapper mapper;
	@Autowired
	SRoleMenuMapper sRoleMenuMapper;
	@Autowired
	SUserRoleMapper sUserRoleMapper;
	@Autowired
	public void setMapper(SRoleMapper mapper) {	
		super.setMapper(mapper);//对base里的mapper进行动态注入
	}
	
	@Override
	public SRole insert(SRole record) {
		record.setCreateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.insert(record);
		return record;
	}
	
	@Override
	public List<SRoleMenu> selectRoleMenuByCondition(Long roleId){
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("roleId", roleId);
		return sRoleMenuMapper.selectByCondition(condititon);
	}
	
	@Override
	public SRoleMenu insertSRoleMenu(SRoleMenu record){
		sRoleMenuMapper.insert(record);
		return record;
	}
	
	@Override
	public void deleteSRoleMenu(Long roleId){
		Map<String, Object>condititon = new HashMap<String, Object>();
		condititon.put("roleId", roleId);
		sRoleMenuMapper.deleteByCondition(condititon);
	}
	@Override
	public List<SUserRole> selectSUserRoleByCondition(Long roleId){
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("roleId", roleId);
		return sUserRoleMapper.selectByCondition(condititon);
	}
	@Override
	public SUserRole insertSUserRole(SUserRole record){
		sUserRoleMapper.insert(record);
		return record;
	}
	@Override
	public void deleteSUserRole(Long roleId){
		Map<String, Object>condititon = new HashMap<String, Object>();
		condititon.put("roleId", roleId);
		sUserRoleMapper.deleteByCondition(condititon);
	}
}
