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
			if(email != null && !email.equals(""))
			{
				if(password != null && !password.equals(""))
				{
					session = HibernateUtil.getSessionFactory().openSession();
		            session.beginTransaction();
		            User user = new UserDAO().getUserByLogin(session, email, password);
		            
		            if(user != null)
		            {
		            	json.put("msg", "ok");
			            json.put("email", user.getEmail());
			            json.put("name", user.getName());
		            }
		            else { json.put("msg", "Invalid email or password"); }
				}
				else { json.put("msg", "Invalid email or password"); }
			}
			else { json.put("msg", "Invalid email or password"); }
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if(session!=null) { session.close(); } }
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json.toString());
	}
}
