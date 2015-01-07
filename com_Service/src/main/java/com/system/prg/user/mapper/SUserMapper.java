package com.system.prg.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.system.prg.user.entity.SUser;

public interface SUserMapper {

	public void insert(SUser  record) ;

	public int deleteByCondition( Map<String,Object> condition) ;

	public int updateByCondition(SUser  record) ;

	public  Integer  countByCondition(Map<String,Object> condititon) ;

	public  List<SUser> selectByCondition(Map<String,Object> condititon ) ;

	public  List<SUser> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds ) ;

	public int deleteByPrimaryKey(Long  userId);

	public SUser findByPrimaryKey(Long  userId);

}
