package com.system.prg.menu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.system.prg.menu.entity.SMenu;

public interface SMenuMapper {

	public void insert(SMenu  record) ;

	public int deleteByCondition( Map<String,Object> condition) ;

	public int updateByCondition(SMenu  record) ;

	public  Integer  countByCondition(Map<String,Object> condititon) ;

	public  List<SMenu> selectByCondition(Map<String,Object> condititon ) ;

	public  List<SMenu> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds ) ;

	public int deleteByPrimaryKey(Long  menuId);

	public SMenu findByPrimaryKey(Long  menuId);
	
	public  List<SMenu> getAllMenus(Long userId) ;

}
