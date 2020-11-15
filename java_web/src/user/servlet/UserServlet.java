package user.servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.jdbc.User;
import user.jdbc.UserDAO;

public class UserServlet extends HttpServlet{
	
	private static final long serialVersionUID = -2944273288812944241L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Path:" + req.getRequestURI());
		String uri = req.getRequestURI();
		UserDAO userDAO = new UserDAO();
		
		if(uri.contains("create-user")) {
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String gender = req.getParameter("gender");
			String age = req.getParameter("age");
			String password = req.getParameter("password");
			int ageInt = 0;
			try {
				ageInt = Integer.parseInt(age);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			User user = new User(id, name, gender, ageInt, password);
			try {
				int result = userDAO.insertUser(user);
				System.out.println(result + " user is created.");
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(uri.contains("get-user")) {
			String id = req.getParameter("id");
			try {
				User user = userDAO.getUserById(id);
				PrintWriter pw = res.getWriter();
				pw.println(user.getId());
				pw.println(user.getName());
				pw.println(user.getGender());
				pw.println(user.getAge());
				pw.flush();
				pw.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			List<User> userList = userDAO.getAllUsers();
			final String userPage = "../users.jsp";
			req.setAttribute("users",userList);
			RequestDispatcher requestDisp = req.getRequestDispatcher(userPage);
			requestDisp.forward(req, res);
			
			res.sendRedirect("../users.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		doGet(req, res);
	}
}
