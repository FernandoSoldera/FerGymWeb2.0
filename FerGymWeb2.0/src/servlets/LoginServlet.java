package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.json.JSONObject;

import DAO.UserDAO;
import model.User;
import util.HibernateUtil;

public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		JSONObject json = new JSONObject();
		Session session = null;
		
		try
		{
			if(email != null && email != "")
			{
				if(password != null && password != "")
				{
					session = HibernateUtil.getSessionFactory().openSession();
		            session.beginTransaction();
		            User user = new UserDAO().getUserByLogin(session, email, password);
		            
		            json.put("msg", "ok");
		            json.put("email", user.getEmail());
		            json.put("name", user.getName());
				}
				else { json.put("msg", "Incorrect password"); }
			}
			else { json.put("msg", "Incorrect E-mail"); }
		}
		catch (Exception e) { e.printStackTrace(); }
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json.toString());
	}
}
