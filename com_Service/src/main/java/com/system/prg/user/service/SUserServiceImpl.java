package com.system.prg.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.prg.role.mapper.SUserRoleMapper;
import com.system.prg.user.entity.SUser;
import com.system.prg.user.mapper.SUserMapper;
import com.system.prg.util.BaseServiceImpl;
import com.system.prg.util.BusinessException;
import com.system.prg.util.DateUtils;
import com.system.prg.util.MD5Encoder;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class SUserServiceImpl extends BaseServiceImpl<SUser> implements SUserService {
	@Autowired
	SUserMapper mapper;
	@Autowired
	SUserRoleMapper sUserRoleMapper;
	@Autowired
	public void setMapper(SUserMapper mapper) {	
		super.setMapper(mapper);//对base里的mapper进行动态注入
	}
	@Override
	public SUser insert(SUser record) {
		record.setUserStatus(1l);
		record.setUserType(1l);
		record.setCreateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		record.setUserPwd(MD5Encoder.encode(record.getUserPwd()));
		mapper.insert(record);
		return record;
	}
	@Override
	public void updateByCondition(SUser record) {
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.updateByCondition(record);
	}
	@Override
	public void updateUserPwd(SUser record){
		record.setUserPwd(MD5Encoder.encode(record.getUserPwd()));
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.updateByCondition(record);
	}
	@Override
	public void deleteByPrimaryKey(Long userId) {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("userId", userId);
		sUserRoleMapper.deleteByCondition(condition);
		mapper.deleteByPrimaryKey(userId);
	}
}
