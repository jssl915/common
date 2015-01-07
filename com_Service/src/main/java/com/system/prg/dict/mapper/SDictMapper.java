package com.system.prg.dict.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.system.prg.dict.entity.SDict;

public interface SDictMapper {

	public void insert(SDict  record) ;

	public int deleteByCondition( Map<String,Object> condition) ;

	public int updateByCondition(SDict  record) ;

	public  Integer  countByCondition(Map<String,Object> condititon) ;

	public  List<SDict> selectByCondition(Map<String,Object> condititon ) ;

	public  List<SDict> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds ) ;

	public int deleteByPrimaryKey(Long  dictId);

	public SDict findByPrimaryKey(Long  dictId);

}
