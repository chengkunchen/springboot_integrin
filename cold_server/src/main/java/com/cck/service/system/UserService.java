package com.cck.service.system;

import com.cck.mapper.system.SysUsersMapper;
import com.cck.model.system.SysUsers;

import java.util.List;

/**
 * Created by AAS on 2018/3/17.
 */
public interface UserService extends BaseService<SysUsers,SysUsersMapper>{

     boolean isValidUserByIdAndPsd(String user_id,String password);

     List<SysUsers> getUsersWithRoles();

     int updateSelective(SysUsers sysUser);
}
