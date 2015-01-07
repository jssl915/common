package com.system.prg.user.service;

import java.util.List;
import java.util.Map;

import com.system.prg.user.entity.SUser;
import com.system.prg.util.PageObject;

public interface SUserService {

	public SUser insert(SUser record);

	public void deleteByCondition(Map<String, Object> condition);

	public void updateByCondition(SUser record);
	
	public List<SUser> selectByCondition(Map<String, Object> condititon);
	
	public Integer countByCondition(Map<String, Object> condititon);

	public List<SUser> pageList(PageObject po);

	public void deleteByPrimaryKey(Long userId);

	public SUser findByPrimaryKey(Long userId);
	
	public void updateUserPwd(SUser record);

}
