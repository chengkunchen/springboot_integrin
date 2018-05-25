package com.cck.controller;

import com.cck.common.consts.GlobDefine;
import com.cck.common.enums.ResultCode;
import com.cck.common.result.Result;
import com.cck.model.SelectFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by AAS on 2018/3/20.
 */
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public Result handler(HttpServletRequest req, Exception e) {

        String url = req.getRequestURI() + "===";

        logger.info(url + " Http请求发生异常...");

        String erMsg = e.getMessage();

        Result result = new Result();

        logger.error(url + erMsg, e);

        int status = 500;//res.getStatus()
        if (e instanceof MissingServletRequestParameterException) {
            return result.fail(status, "参数错误", erMsg);
        } else if (e instanceof NullPointerException) {
            return result.fail(status, "发生空指针异常", erMsg);
        } else if (e instanceof IllegalArgumentException) {
            return result.fail(status, "数据类型不匹配", erMsg);
        } else if (e instanceof SQLException || e instanceof BadSqlGrammarException) {
            return result.fail(status, "数据库访问异常", erMsg);
        } else {
            return result.fail(status, "服务器代码发生异常,请联系管理员", erMsg);
        }
    }

    public Result successResult() {
        return this.successResult(null);
    }

    public Result successResult(Object o) {
        Result result = new Result(ResultCode.SUCCESS, o);
        return result;
    }

    public Result failResult() {
        Result result = new Result(ResultCode.SAVEFAIL);
        return result;
    }


    public Result saveOrUpdateResult(int result) {
        //TODO
        if (result > 0)
            return successResult();
        else
            return failResult();
    }


    /**
     * 项目路径
     * @param request
     * @return
     */
    /*protected String getServerRealPath(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("/");
        if (StringUtils.isBlank(serverRealPath)) {
            try {
                serverRealPath = application.getResource("/").getPath();
            } catch (MalformedURLException e) {
                logger.error("获得项目路径异常", e);
            }
        }
        if (!serverRealPath.endsWith(File.separator)) {
            serverRealPath = serverRealPath + File.separator;
        }
        return serverRealPath;
    }*/



    /**
     * 解析查询条件
     *
     * @return
     */
    public  SelectFilter getSelectFilter(String Column, GlobDefine.FilterType type, String param) {
        SelectFilter selectFilter = new SelectFilter();
        selectFilter.setColumnName(Column);
        selectFilter.setFilterType(type.getType());
        switch (type) {
            case IN:
                String[] ins = param.split(";");
                String inValue = "";
                for (String value : ins[0].split(",")) {
                    if (value != null && !"".equals(value)) {
                        inValue += "'" + value + "',";
                    }
                }
                selectFilter.setFilterValue(inValue.substring(0, inValue.length() - 1));
                break;
            case LIKE:
                selectFilter.setFilterValue("%" + param + "%");
                break;
            default:
                selectFilter.setFilterValue(param);
                break;
        }
        return selectFilter;
    }

}
