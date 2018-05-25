package com.cck.controller.system;

import com.cck.common.result.Result;
import com.cck.controller.BaseController;
import com.cck.mapper.system.SysRoleMapper;
import com.cck.model.system.SysRole;
import com.cck.service.system.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CCK on 2018/4/8.
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController{


    @Autowired
    private RoleServiceImpl roleService;


    @GetMapping(value = "rolesPageList")
    public Result getRoles(@RequestParam(value = "page", required = false, defaultValue = "1") int num,
                           @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        return roleService.findListByPage(num, search);
    }




    @Service("roleService")
    public class  RoleServiceImpl extends BaseServiceImpl<SysRole,SysRoleMapper>{

    }


}
