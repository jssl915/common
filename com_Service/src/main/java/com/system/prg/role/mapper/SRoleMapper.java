package com.system.prg.role.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.system.prg.role.entity.SRole;

public interface SRoleMapper {

	public void insert(SRole  record) ;

	public int deleteByCondition(Map<String,Object> condition) ;

	public int updateByCondition(SRole  record) ;

	public  Integer  countByCondition(Map<String,Object> condititon) ;

	public  List<SRole> selectByCondition(Map<String,Object> condititon ) ;

	public  List<SRole> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds ) ;

	public int deleteByPrimaryKey(Long  roleId);

	public SRole findByPrimaryKey(Long  roleId);

}
