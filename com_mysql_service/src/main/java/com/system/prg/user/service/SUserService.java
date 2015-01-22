package com.system.prg.user.service;

import com.system.prg.user.entity.SUser;
import com.system.prg.util.BaseService;

public interface SUserService extends BaseService<SUser>{
	
	public void updateUserPwd(SUser record);
	
}
