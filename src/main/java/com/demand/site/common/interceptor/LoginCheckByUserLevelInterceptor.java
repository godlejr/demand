package com.demand.site.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demand.site.common.annotation.AdminRequired;
import com.demand.site.common.annotation.EmployeeRequired;
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
		EmployeeRequired employeeRequired = ((HandlerMethod) handler).getMethodAnnotation(EmployeeRequired.class);

		if (loginRequired != null) {
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("user") == null) {
				response.sendRedirect(loginPageUrl);
				return false;
			}
		}

		if (adminRequired != null) {
			HttpSession session = request.getSession(false);

			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");

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

		if (employeeRequired != null) {
			HttpSession session = request.getSession(false);

			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				int level = user.getLevel();

				if (level != UserFlag.EMPLOYEE_LEVEL && level != UserFlag.ADMIN_LEVEL) {
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