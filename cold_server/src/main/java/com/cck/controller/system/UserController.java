package com.cck.controller.system;

import com.cck.common.Utils.DateUtils;
import com.cck.common.Utils.JwtUtil;
import com.cck.common.consts.GlobDefine;
import com.cck.common.consts.MyProps;
import com.cck.common.enums.ResultCode;
import com.cck.common.result.Result;
import com.cck.controller.BaseController;
import com.cck.model.TableInfo;
import com.cck.model.system.SysUsers;
import com.cck.service.system.UserService;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by AAS on 2018/3/17.
 */
@Api("用户信息")
@RestController
@RequestMapping(value = "/sysUsers")
public class UserController extends BaseController {

    @Autowired
    private MyProps myProps;

    @Autowired
    private UserService userService;


    @ApiOperation(value = "test")
    @GetMapping(value = "usersPageList")
    public Result getUsers(@RequestParam(value = "page", required = false, defaultValue = "1") int num,
                           @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        return userService.findListByPage(num, search);
    }


    @GetMapping(value = "userRoles")
    public Result getUsersWithRoles() {
        List<SysUsers> list = userService.getUsersWithRoles();
        return successResult(list);
    }

   /* @GetMapping(value = "test")

    public Result test(){
        List<LinkedCaseInsensitiveMap> users = userService.findAllUser();
        return successResult(users);
    }
*/

    //@Transactional//事务 最好写在server层
    @PostMapping(value = "addUser")
    public Result addUser(SysUsers user) {
        if (StringUtil.isEmpty(user.getId()))
            user.setId(String.valueOf(DateUtils.currentTimeMillis()));
        int result = userService.insertSelective(user);

        return saveOrUpdateResult(result);
    }



    @GetMapping(value = "getUserById")
    public Result getUserById(String id) {

        SysUsers sysUsers = userService.selectByPrimaryKey(id);

        if (sysUsers == null){
            return new Result(ResultCode.nodata,"");
        }

        return successResult(sysUsers);
    }


    @PostMapping(value = "updateUser")
    public Result getUserById(SysUsers sysUsers) {

       int result = userService.updateSelective(sysUsers);

        return saveOrUpdateResult(result);
    }

    @PostMapping("/login")
    public Result login(@RequestParam(value = "user_id") String user_id,
                        @RequestParam(value = "password") String password, HttpServletResponse response) {


        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cahce-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("text/json; charset=utf-8");


        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, x-requested-with, X-Custom-Header, Authorization");

        TableInfo tableInfo = new TableInfo("xxx");
        tableInfo.getFilters().add(getSelectFilter("user_id", GlobDefine.FilterType.EQUAL, user_id));
        tableInfo.setSelectColumns(new String[]{"", ""});



        if (userService.isValidUserByIdAndPsd(user_id, password)) {
            String jwt = JwtUtil.generateToken(user_id);
            return successResult(jwt);
        } else {
            return new Result(ResultCode.PASSWORD_ERROR);
        }
    }
}
