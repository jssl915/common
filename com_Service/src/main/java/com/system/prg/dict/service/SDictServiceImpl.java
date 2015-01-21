package com.system.prg.dict.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.prg.dict.entity.SDict;
import com.system.prg.dict.mapper.SDetailMapper;
import com.system.prg.dict.mapper.SDictMapper;
import com.system.prg.util.BaseServiceImpl;
import com.system.prg.util.BusinessException;
import com.system.prg.util.DateUtils;
import com.system.prg.util.SystemCache;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class SDictServiceImpl extends BaseServiceImpl<SDict> implements SDictService {

	@Autowired
	SDictMapper mapper;
	@Autowired
	SDetailMapper sDetailMapper;
	@Autowired
	public void setMapper(SDictMapper mapper) {	
		super.setMapper(mapper);//对base里的mapper进行动态注入
	}
	@Override
	public SDict insert(SDict record) {
		record.setDictStatus(1l);
		record.setCreateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.insert(record);
		SystemCache.reCacheDict();
		return record;
	}

	@Override
	public void deleteByCondition(Map<String, Object> condition) {
		mapper.deleteByCondition(condition);
		SystemCache.reCacheDict();
	}

	@Override
	public void updateByCondition(SDict record) {
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.updateByCondition(record);
		SystemCache.reCacheDict();
	}

	@Override
	public void deleteByPrimaryKey(Long dictId) {
		Map<String, Object> condititon = new HashMap<String, Object>();
		condititon.put("dictId", dictId);
		sDetailMapper.deleteByCondition(condititon);
		mapper.deleteByPrimaryKey(dictId);
		SystemCache.reCacheDict();
	}
	
	@Override
	public Map<String, String> getDetailNameMap(String dictName){
		return DictCache.getDetailNameMapByDictName(dictName);
	}
	@Override
	public Map<String, String> getDetailValueMap(String dictName){
		return DictCache.getDetailValueMapByDictName(dictName);
	}
	
	@Override
	public String getDetailValue(String dictName,String detailName){
		return DictCache.getDetailValue(dictName,detailName);
	}
}
