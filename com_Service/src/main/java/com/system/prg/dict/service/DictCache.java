package com.system.prg.dict.service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.system.prg.dict.entity.SDetail;
import com.system.prg.dict.entity.SDict;
import com.system.prg.dict.mapper.SDetailMapper;
import com.system.prg.dict.mapper.SDictMapper;
import com.system.prg.util.OscacheFactory;
import com.system.prg.util.SystemCache;
import com.system.util.ObjectFactory;

public class DictCache {
	private static DictCache instance = null;
	private DictCache() {}
	public static synchronized DictCache getInstance() {
		if (instance == null) {instance = new DictCache();}
		return instance;
	}

	@SuppressWarnings("unchecked")
	private static Hashtable<String, Map<String, SDetail>> getDictFromCache() {
		Hashtable<String, Map<String, SDetail>> dict = (Hashtable<String, Map<String, SDetail>>) OscacheFactory.getInstance().getObject(SystemCache.CACHE_DICT);
		return dict;
	}
	 
	public static synchronized void reloadData(){
		SystemCache.reCacheDict();
	}

	public Hashtable<String, Map<String, SDetail>>loadData(){
		SDictMapper sDictMapper = (SDictMapper) ObjectFactory.getBean(SDictMapper.class); 
		SDetailMapper sDetailMapper = (SDetailMapper) ObjectFactory.getBean(SDetailMapper.class); 
		Hashtable<String, Map<String, SDetail>> dict = new Hashtable<String, Map<String, SDetail>>();
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("detailStatus", 1);
		List<SDict>sDictList = sDictMapper.selectByCondition(null);
		List<SDetail> sDetailList = sDetailMapper.selectByCondition(para);
		Map<String, SDetail> dictDetailMap = null;
		for(SDict sd:sDictList){
			String dictId = sd.getDictId().toString();
			dictDetailMap = new HashMap<String, SDetail>();
			for (SDetail sDetail : sDetailList) {
				if(dictId.equals(sDetail.getDictId().toString())){
					dictDetailMap.put(sDetail.getDetailName(), sDetail);
				}
			}
			dict.put(sd.getDictName(), dictDetailMap);
		}
		return dict;
	}
	
	//根据dictName得到detail对应的明细map<name,value>
	public static Map<String, String> getDetailNameMapByDictName(String dictName){
		Hashtable<String, Map<String, SDetail>> dict = getDictFromCache();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		Map<String, String> params=new TreeMap<String, String>();
		Map<String, SDetail> dictDetailMap = dict.get(dictName);
		for (Map.Entry<String, SDetail> e : dictDetailMap.entrySet()) {  
			SDetail d = e.getValue();
			params.put(d.getDetailName(), d.getDetailValue().toString());
		}  
		return params;
	}
	//根据dictName得到detail对应的明细map<value,name>
	public static Map<String, String> getDetailValueMapByDictName(String dictName){
		Hashtable<String, Map<String, SDetail>> dict = getDictFromCache();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		Map<String, String> params=new TreeMap<String, String>();
		Map<String, SDetail> dictDetailMap = dict.get(dictName);
		for (Map.Entry<String, SDetail> e : dictDetailMap.entrySet()) {  
			SDetail d = e.getValue();
			params.put(d.getDetailValue().toString(),d.getDetailName());
		}  
		return params;
	}
	
	//根据dictName和detailName得到detail对应的value
	public static String getDetailValue(String dictName, String detailName){
		Hashtable<String, Map<String, SDetail>> dict = getDictFromCache();
		if (dict == null || dict.size() == 0) {
			return null;
		}
		SDetail dictDetail = dict.get(dictName).get(detailName);
		return dictDetail.getDetailValue().toString();
	}

}