package com.leaf.service.system;

import com.leaf.service.system.domain.SystemUser;

public interface SystemUserService {

	SystemUser getByUsername(String username);
}
