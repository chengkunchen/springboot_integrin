package com.cck.mapper.system;

import com.cck.model.system.SysUsers;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysUsersMapper extends BaseMapper<SysUsers>{
     List<SysUsers> getUsersWithRoles();

     String loginByUserIdAndPsd(@Param("user_id") String user_id,@Param("password") String password);
}