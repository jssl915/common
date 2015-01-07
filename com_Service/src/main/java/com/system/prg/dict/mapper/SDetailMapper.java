package com.system.prg.dict.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.system.prg.dict.entity.SDetail;

public interface SDetailMapper {

	public void insert(SDetail  record) ;

	public int deleteByCondition( Map<String,Object> condition) ;

	public int updateByCondition(SDetail  record) ;

	public  Integer  countByCondition(Map<String,Object> condititon) ;

	public  List<SDetail> selectByCondition(Map<String,Object> condititon ) ;

	public  List<SDetail> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds ) ;

	public int deleteByPrimaryKey(Long  detailId);

	public SDetail findByPrimaryKey(Long  detailId);

}
