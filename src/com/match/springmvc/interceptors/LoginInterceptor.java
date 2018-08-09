package com.match.springmvc.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.match.springmvc.entities.Admin;
import com.match.springmvc.entities.Users;
import com.match.springmvc.exception.WebAuthException;
import com.match.springmvc.exception.WebInfoException;

public class LoginInterceptor implements HandlerInterceptor {
	
	private List<String> excludedUrl0;
	
	private List<String> includedUrls;
	
	private List<String> excludedUrl;
	
	public void setExcludeUrl0(List<String> excludeUrl0) {
		this.excludedUrl0 = excludeUrl0;
	}
	
	public void setIncludeUrls(List<String> includeUrls) {
		this.includedUrls = includeUrls;
	}
	
	public void setExcludeUrl(List<String> excludeUrl) {
		this.excludedUrl = excludeUrl;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String requestUri = request.getRequestURI(); // 获取请求
		for( String url0 : excludedUrl0) {
			if(requestUri.endsWith(url0)) {
				return true;
			}
		}
		HttpSession session = request.getSession(); // 获取session
		
		if ((Admin)session.getAttribute("admin")==null) { // 是管理员？
			
			if((Users)session.getAttribute("user")==null) { // 是用户？
				throw new WebAuthException();
			}else { // 是用户！
				for( String url1 : excludedUrl) {
					if(requestUri.endsWith(url1)) {
						return true;
					}
				}
				return false;
//				throw new WebInfoException();
			}
		}else { // 是 管理员！
			for( String url : includedUrls) {
				if(requestUri.endsWith(url)) {
					throw new WebInfoException();
				}
			}
			return true;
		}
	}
	
}
