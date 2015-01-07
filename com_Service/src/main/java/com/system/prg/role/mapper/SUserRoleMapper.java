package com.system.prg.role.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.system.prg.role.entity.SUserRole;

public interface SUserRoleMapper {

	public void insert(SUserRole  record) ;

	public int deleteByCondition( Map<String,Object> condition) ;

	public int updateByCondition(SUserRole  record) ;

	public  Integer  countByCondition(Map<String,Object> condititon) ;

	public  List<SUserRole> selectByCondition(Map<String,Object> condititon ) ;

	public  List<SUserRole> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds ) ;

}
