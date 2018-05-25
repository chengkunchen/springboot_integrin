package com.cck.common.Filter;

import com.cck.common.Utils.JwtUtil;
import com.cck.common.enums.ResultCode;
import com.cck.common.result.Result;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.Gson;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by AAS on 2018/3/26.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //反射型跨站脚本漏洞的检查
        String queryStr = request.getQueryString();
        if (StringUtil.isNotEmpty(queryStr)) {
            if (hasDanger(queryStr)) {
                request.setAttribute("errorMsgInfo", "非法请求!!");
                response.setStatus(403);
                return;//检测出含有特殊字符引起的漏洞
            }
            //解码后再次判断
            String decodeQueryStr = java.net.URLDecoder.decode(queryStr);
            if (decodeQueryStr != null && hasDanger(decodeQueryStr)) {
                request.setAttribute("errorMsgInfo", "非法请求!!");
                response.setStatus(403);
                return;//检测出含有特殊字符引起的漏洞
            }
        }


     /*   String alreadyFilteredAttributeName = getAlreadyFilteredAttributeName();
        //防止多次执行，防止错误配置
        //如果当前的filter已经执行过则直接放行不再重复执行。
        if (request.getAttribute(alreadyFilteredAttributeName) == null || StringUtil.isEmpty(request.getAttribute(alreadyFilteredAttributeName).toString())) {*/
        try {
            String token = request.getHeader("Authorization");
            //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
            JwtUtil.validateToken(token);

        } catch (Exception e) {
            Result result = new Result(ResultCode.NO_TOKEN_ERROR);
            result.setDetail(e.getMessage());
            Gson gson = new Gson();
            response.getWriter().print(gson.toJson(result));
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
    //}
        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        filterChain.doFilter(request, response);
    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        boolean fTrue = pathMatcher.match("/login", request.getServletPath());

        return fTrue;
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

    //我们只对地址 /api 开头的api检查jwt. 不然的话登录/login也需要jwt
   /* private boolean isProtectedUrl(HttpServletRequest request) {
        return pathMatcher.match("/api*//**", request.getServletPath());
     }
     */
}
