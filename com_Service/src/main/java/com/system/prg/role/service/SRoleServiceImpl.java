package com.system.prg.role.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.prg.role.entity.SRole;
import com.system.prg.role.entity.SRoleMenu;
import com.system.prg.role.entity.SUserRole;
import com.system.prg.role.mapper.SRoleMapper;
import com.system.prg.role.mapper.SRoleMenuMapper;
import com.system.prg.role.mapper.SUserRoleMapper;
import com.system.prg.util.BusinessException;
import com.system.prg.util.DateUtils;
import com.system.prg.util.PageObject;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class SRoleServiceImpl implements SRoleService {

	@Autowired
	SRoleMapper mapper;
	@Autowired
	SRoleMenuMapper sRoleMenuMapper;
	@Autowired
	SUserRoleMapper sUserRoleMapper;

	@Override
	public SRole insert(SRole record) {
		record.setCreateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.insert(record);
		return record;
	}

	@Override
	public void deleteByCondition(Map<String, Object> condition) {
		mapper.deleteByCondition(condition);
	}

	@Override
	public void updateByCondition(SRole record) {
		mapper.updateByCondition(record);
	}

	@Override
	public Integer countByCondition(Map<String, Object> condititon) {
		return mapper.countByCondition(condititon);
	}

	@Override
	public List<SRole> selectByCondition(Map<String, Object> condititon) {
		return mapper.selectByCondition(condititon);
	}
	
	@Override
	public List<SRole> pageList(PageObject po) {
		RowBounds rowBounds = new  RowBounds(po.getStart(),po.getPageSize()) ;
		return mapper.selectByCondition(po.getCondition(),rowBounds);
	}

	@Override
	public void deleteByPrimaryKey(Long roleId) {
		mapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public SRole findByPrimaryKey(Long roleId) {
		return mapper.findByPrimaryKey(roleId);
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
