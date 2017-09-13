package com.demand.site.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demand.site.common.annotation.AdminRequired;
import com.demand.site.common.annotation.LoginRequired;
import com.demand.site.common.entity.User;
import com.demand.site.common.flag.UserFlag;

public class LoginCheckByUserLevelInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String context = request.getContextPath();
		String loginPageUrl = context + "/login";

		LoginRequired loginRequired = ((HandlerMethod) handler).getMethodAnnotation(LoginRequired.class);
		AdminRequired adminRequired = ((HandlerMethod) handler).getMethodAnnotation(AdminRequired.class);

		if (loginRequired != null) {
			HttpSession session = request.getSession(false);

			User user = (User) session.getAttribute("user");

			if (user == null) {
				response.sendRedirect(loginPageUrl);
				return false;
			}
		}

		if (adminRequired != null) {
			HttpSession session = request.getSession(false);

			User user = (User) session.getAttribute("user");

			if (user != null) {
				int level = user.getLevel();

				if (level != UserFlag.ADMIN_LEVEL) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				}
			} else {
				response.sendRedirect(loginPageUrl);
				return false;
			}
		}

		return true;
	}

}