package login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.jdbc.User;
import user.jdbc.UserDAO;
import util.SHAUtil;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {	
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("In filter...");	
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;

		String url = httpRequest.getRequestURL().toString();
		System.out.println("Requested url: " + url);
		
		String userId = httpRequest.getParameter("userId");
		String password = httpRequest.getParameter("password");
		
		HttpSession session = httpRequest.getSession(true);
		
		User user = (User)session.getAttribute("user");
		
		Object login = session.getAttribute("login");
		
		if(url.contains("logout")) {
			// 5. 用户退出，将session失效.
			session.invalidate();
			httpResponse.sendRedirect("http://localhost:8088/java_web/login.html");
		} else if(login != null && ((Boolean)login).equals(Boolean.TRUE)) {
			// 3. 如果用户已经登录了，直接放行，跳转到用户想去的地址。
			System.out.println("This is a logged in user");
			chain.doFilter(httpRequest, httpResponse);
		} else if(userId != null && password != null) {
			// 2. 验证从login.html输入的用户名和密码。如果验证成功，将用户信息放入session。
			
			try {
				String shaPassword = SHAUtil.process(password); 
				UserDAO userDAO = new UserDAO();
				User userInDB = userDAO.getUserById(userId);	
				shaPassword.equals(userInDB.getPassword()); 
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			session.setAttribute("login", Boolean.TRUE);
			session.setAttribute("login-id", userId);
			
			String lastUrl = (String)(session.getAttribute("lastUrl"));
			RequestDispatcher rd = null;
			if(lastUrl != null) {
				// 去用户指定的地址
				httpResponse.sendRedirect(lastUrl);
			} else {
				// 去登录后默认的地址。这里我们的默认地址users.jsp
				rd = httpRequest.getRequestDispatcher("../users.jsp");
				rd.forward(httpRequest, httpResponse);
			}
		} else if(url.contains("login.html")) {
			// 1. 用户第一次正常访问login.html地址, 直接去login页面

			chain.doFilter(httpRequest, httpResponse);
		} else {
			// 4. 用户未登录，直接访问了某个地址，记录下来用户想访问的地址，然后让用户先去登录
			session.setAttribute("lastUrl", url);
			httpResponse.sendRedirect("http://localhost:8088/java_web/login.html");
		}
	}
}
