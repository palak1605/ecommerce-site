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

import com.ecommerce.model.Product;
import com.ecommerce.model.User;

@WebServlet("/dashboard")
public class dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public dashboard() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Configuration config = new Configuration().configure().addAnnotatedClass(Product.class);
		SessionFactory sf = config.buildSessionFactory();
		Session SQLsession = sf.openSession();

		HttpSession session = request.getSession();

		if (session.getAttribute("username") != null) {
			User user = (User) SQLsession.get(User.class, (String) session.getAttribute("username"));
			session.setAttribute("user", user);
			response.sendRedirect("views/dashboard.jsp");
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
