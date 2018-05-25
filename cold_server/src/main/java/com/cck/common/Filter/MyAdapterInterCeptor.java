package com.cck.common.Filter;

import com.cck.common.Utils.JwtUtil;
import com.cck.common.consts.GlobDefine;
import com.cck.common.enums.ResultCode;
import com.cck.common.result.Result;
import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AAS on 2018/3/21.
 */
public class MyAdapterInterCeptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(MyAdapterInterCeptor.class);

    //TODO
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();




        logger.info(request.getRequestURI() + "......start");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cahce-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("text/json; charset=utf-8");

        //options
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, x-requested-with, X-Custom-Header, Authorization");


        if ("OPTIONS".equals(method))
            return true;


        //反射型跨站脚本漏洞的检查
        String queryStr = request.getQueryString();
        if (StringUtil.isNotEmpty(queryStr)) {
            if (hasDanger(queryStr)) {
                request.setAttribute("errorMsgInfo", "非法请求!!");
                response.setStatus(403);
                return false;//检测出含有特殊字符引起的漏洞
            }
            //解码后再次判断
            String decodeQueryStr = java.net.URLDecoder.decode(queryStr);
            if (decodeQueryStr != null && hasDanger(decodeQueryStr)) {
                request.setAttribute("errorMsgInfo", "非法请求!!");
                response.setStatus(403);
                return false;//检测出含有特殊字符引起的漏洞
            }
        }

        try {
            String token = request.getHeader("Authorization");
            //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
            JwtUtil.validateToken(token);
        } catch (Exception e) {
            Result result = new Result(ResultCode.NO_TOKEN_ERROR);
            result.setDetail(e.getMessage());
            response.getWriter().print(GlobDefine.gson.toJson(result));
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return false;
        }

        return true;

    }

   /* @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

      //postHandle：后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
    }*/

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        if ("OPTIONS".equals(request.getMethod()))
            return;
        //运行结束
        logger.info(request.getRequestURI() + "......end");

    }


    /**
     * 是否含有特殊字符引起的漏洞
     *
     * @param queryStr
     * @return
     */
    private static boolean hasDanger(String queryStr) {
        boolean has = false;
        String[] dangerChar = new String[]{";", "'", "\"", "<>", "<", ">", "()", "(", ")"};
        for (String danger : dangerChar) {
            if (queryStr.contains(danger)) {
                has = true;
                break;
            }
        }
        return has;
    }
}
