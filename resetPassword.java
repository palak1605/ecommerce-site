package com.ecommerce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import com.ecommerce.model.User;

@WebServlet("/resetPassword")
public class resetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public resetPassword() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("views/resetPassword.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		Configuration config = new Configuration().configure().addAnnotatedClass(User.class);
		SessionFactory sf = config.buildSessionFactory();
		Session SQLsession = sf.openSession();
		Transaction trans = SQLsession.beginTransaction();

		if (password.equals(confirmPassword)) {
			User user = SQLsession.get(User.class, username);
			if (user != null) {
				user.setPassword(password);
				SQLsession.update(user);
				trans.commit();
				response.sendRedirect("login");
			}
			else {
				System.out.println("Incorrect Username!");
			}
		} else {
			System.out.println("Confirm password doesnot match!");
		}
	}
}
