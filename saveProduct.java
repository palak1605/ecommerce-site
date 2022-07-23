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
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

@WebServlet("/saveProduct")
public class saveProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Configuration config = new Configuration().configure().addAnnotatedClass(Product.class);
	SessionFactory sf = config.buildSessionFactory();
	Session SQLsession = sf.openSession();

	Product product = new Product();

	public saveProduct() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("dashboard");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int size = Integer.parseInt(request.getParameter("size"));
		String image = request.getParameter("image");

		product.setTitle(title);
		product.setQuantity(quantity);
		product.setSize(size);
		product.setImage(image);
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		User user = (User) SQLsession.get(User.class, username);
		user.getProduct().add(product);
//		session.setAttribute("user", user);
		product.setUser(user);
		
		Transaction trans = SQLsession.beginTransaction();
		SQLsession.save(product);
		SQLsession.update(user);
		trans.commit();

		response.sendRedirect("dashboard");
	}

}
