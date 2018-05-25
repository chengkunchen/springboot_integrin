package com.cck.controller.charts;

import com.cck.common.result.Result;
import com.cck.controller.BaseController;
import com.cck.model.TableInfo;
import com.cck.service.charts.impl.AsdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CCK on 2018/4/4.
 */
@RestController
@RequestMapping("/asd")
public class AsdController extends BaseController{
    @Autowired
    private AsdServiceImpl asdService;
    @GetMapping("/getList")
    public Result getList(){
        //T_DIM_ASD
        TableInfo tableInfo = new TableInfo("T_DIM_ASD");
        List list = asdService.findListByAsd(tableInfo);

        return successResult(list);
    }
}
