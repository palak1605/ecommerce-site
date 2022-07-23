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

@WebServlet("/editProduct")
public class editProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Configuration config = new Configuration().configure().addAnnotatedClass(Product.class);
	SessionFactory sf = config.buildSessionFactory();
	Session SQLsession = sf.openSession();
	
	Product product;
	int id=-1;

	public editProduct() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		product = (Product) SQLsession.get(Product.class, Integer.parseInt(request.getParameter("id")));
		id = product.getId();
		response.sendRedirect("views/editProduct.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int size = Integer.parseInt(request.getParameter("size"));
		String image = request.getParameter("image");

		Transaction trans = SQLsession.beginTransaction();

//		product = SQLsession.get(Product.class, id);

		product.setTitle(title);
		product.setQuantity(quantity);
		product.setSize(size);
		product.setImage(image);
		
		SQLsession.update(product);
		HttpSession session = request.getSession();
		
		trans.commit();
		User user = (User) SQLsession.get(User.class, (String)session.getAttribute("username"));
		session.setAttribute("user", user);
		
		response.sendRedirect("dashboard");
	}

}