package com.cck.service.system.impl;

import com.cck.common.Utils.RedisUtil;
import com.cck.mapper.system.SysUsersMapper;
import com.cck.model.system.SysUsers;
import com.cck.service.system.UserService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AAS on 2018/3/18.
 */
@Service("userService")
@CacheConfig(cacheNames = "sysUsers")
public class UserServiceImpl extends BaseServiceImpl<SysUsers, SysUsersMapper> implements UserService {


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysUsersMapper sysUsersMapper;

    @Override
    public boolean isValidUserByIdAndPsd(String user_id, String password) {
        String uId = sysUsersMapper.loginByUserIdAndPsd(user_id, password);
        return StringUtil.isNotEmpty(uId) ? true : false;
    }

    @Cacheable(value = "sysUsers",key = "'usersWithRoles'")
    @Override
    public List<SysUsers> getUsersWithRoles() {
        return sysUsersMapper.getUsersWithRoles();
    }

    @CacheEvict(key = "'usersWithRoles'")
    @Override
    public int insertSelective(SysUsers o) {
        return super.insertSelective(o);
    }

    @CacheEvict(key = "'usersWithRoles'")
    @Override
    public int updateSelective(SysUsers sysUser) {

        redisUtil.del("sysUsers::"+sysUser.getId());

        return super.updateByPrimaryKeySelective(sysUser);
    }


    @Cacheable(key = "#id",condition = "#id != 'eee'")
    @Override
    public SysUsers selectByPrimaryKey(String id) {
        return super.selectByPrimaryKey(id);
    }
}



