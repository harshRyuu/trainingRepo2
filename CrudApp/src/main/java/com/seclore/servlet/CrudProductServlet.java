package com.seclore.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seclore.dao.ProductDao;
import com.seclore.entity.Product;

/**
 * Servlet implementation class CrudProductServlet
 */

@WebServlet("/CrudProductServlet")
public class CrudProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ProductDao dao = new ProductDao();
		
		if(action.equals("fetchAll")) {
			List<Product> list = dao.fetchAll();
			
			request.setAttribute("listOfProducts", list);
			request.getRequestDispatcher("/viewProducts.jsp").forward(request, response);
		}
		else if(action.equals("add")) {
			String name = request.getParameter("name");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			
			Product product = new Product();
			product.setName(name);
			product.setPrice(price);
			product.setQuantity(quantity);
			
			int id=dao.add(product);
			request.setAttribute("productId", id);
			request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
		}
		else if(action.equals("delete")) {
			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			dao.delete(id);
			
			request.getRequestDispatcher("/CrudProductServlet?action=fetchAll").forward(request, response);
		}
		else if(action.equals("sort")) {
			String order = request.getParameter("val");
			System.out.println(order);
			List<Product> list = dao.fetchAll();
			if(order.equals("asc")) {
				list.sort(Comparator.comparing(Product::getPrice));
			}
			else {
				list.sort(Comparator.comparing(Product::getPrice).reversed());
			}
			
			request.setAttribute("listOfProducts", list);
			request.getRequestDispatcher("/viewProducts.jsp").forward(request, response);
		}
		else if(action.equals("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			Product product = dao.fetchById(id);
			request.setAttribute("product", product);
			request.getRequestDispatcher("/editProduct.jsp").forward(request, response);
		}
		else if(action.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			
			Product product = new Product();
			product.setId(id);
			product.setName(name);
			product.setPrice(price);
			product.setQuantity(quantity);
			
			dao.update(product);
//			request.setAttribute("productId", id);
			request.getRequestDispatcher("/CrudProductServlet?action=fetchAll").forward(request, response);
		}
	}

}
