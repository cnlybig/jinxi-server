package com.jinxi.star.jxserver.config.jwt;

import com.alibaba.fastjson.JSON;
import com.jinxi.star.jxserver.model.common.ApiResult;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Shiz
 * @date: 2020/6/30 15:54
 */
public class JwtFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURL().toString().endsWith("/login/wechatLogin")) {
            filterChain.doFilter(request, response);
        } else {
            String token = request.getHeader("authorization"); //获取请求传来的token
            Claims claims = JwtHelper.verifyJwt(token); //验证token
            if (claims == null) {
                response.getWriter().write(JSON.toJSONString(ApiResult.fail(-1, "token is invalid")));
            } else {
                request.setAttribute("claims", claims);
                filterChain.doFilter(request, response);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
