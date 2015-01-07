package com.system.prg.role.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.system.prg.role.entity.SRoleMenu;

public interface SRoleMenuMapper {

	public void insert(SRoleMenu  record) ;

	public int deleteByCondition( Map<String,Object> condition) ;

	public int updateByCondition(SRoleMenu  record) ;

	public  Integer  countByCondition(Map<String,Object> condititon) ;

	public  List<SRoleMenu> selectByCondition(Map<String,Object> condititon ) ;

	public  List<SRoleMenu> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds ) ;

}
