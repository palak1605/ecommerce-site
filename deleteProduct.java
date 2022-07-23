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

@WebServlet("/deleteProduct")
public class deleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Configuration config = new Configuration().configure().addAnnotatedClass(Product.class);
	SessionFactory sf = config.buildSessionFactory();
	Session SQLsession = sf.openSession();
	
	Product product;
	int id=-1;
       

	public deleteProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Transaction trans = SQLsession.beginTransaction();
		HttpSession session = request.getSession();
		product = (Product) SQLsession.get(Product.class, Integer.parseInt(request.getParameter("id")));
		id = product.getId();
		SQLsession.delete(product);
		trans.commit();
		User user  = (User) SQLsession.get(User.class, (String) session.getAttribute("username"));
		
		
		session.setAttribute("user", user);
		response.sendRedirect("dashboard");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
