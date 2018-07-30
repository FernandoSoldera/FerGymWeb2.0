package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import model.User;
import util.HibernateUtil;

public class SignUpServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		JSONObject json = new JSONObject();
		Session session = null;
		
		try
		{
			if(name != null && !name.equals(""))
			{
				if(email != null && !email.equals(""))
				{
					if(password != null && !password.equals("") && password.length() > 5)
					{
						session = HibernateUtil.getSessionFactory().openSession();
			            Transaction transaction = session.beginTransaction();
			            
			            User user = new User();
			            user.setName(name);
			            user.setEmail(email);
			            user.setPassword(password);
			            
			            session.save(user);
			            transaction.commit();
			            json.put("msg", "ok");
					}
					else { json.put("msg", "Sua senha precisa ter mais de 5 caracteres"); }
				}
				else { json.put("msg", "Por favor, digite um e-mail válido"); }
			}
			else { json.put("msg", "Por favor, digite um nome"); }
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if(session!=null) { session.close(); } }
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json.toString());
	}
}
