package com.ecommerce;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import com.ecommerce.model.Product;
import com.ecommerce.model.User;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("views/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Configuration config = new Configuration().configure().addAnnotatedClass(User.class);
		SessionFactory sf = config.buildSessionFactory();
		Session SQLsession = sf.openSession();

		User user = (User) SQLsession.get(User.class, username);

		HttpSession session = request.getSession();
		
		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute("user", user);
			session.setAttribute("username", user.getUsername());

			response.sendRedirect("dashboard");
			
		} else {
			System.out.println("Incorrect Login Credentials!");
		}
	}
}
