package com.system.prg.log.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;

import com.system.prg.log.entity.SLog;

public interface SLogMapper {

	public void insert(SLog  record) ;

	public int deleteByCondition( Map<String,Object> condition) ;

	public int updateByCondition(SLog  record) ;

	public  Integer  countByCondition(Map<String,Object> condititon) ;

	public  List<SLog> selectByCondition(Map<String,Object> condititon ) ;

	public  List<SLog> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds ) ;

	public int deleteByPrimaryKey(Long  logId);

	public SLog findByPrimaryKey(Long  logId);

}
