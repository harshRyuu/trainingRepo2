package com.seclore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seclore.entity.Product;

public class ProductDao {
	
	public List<Product> fetch(int from, int rows) {
		Connection conn = null;
		List<Product> products = new ArrayList<Product>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "root");
			
			String sql = "select * from product limit ?, ?";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, from);
			st.setInt(2, rows);
			
			ResultSet rs = st.executeQuery();	
			
			while(rs.next()) {
				Product product = new Product();
				String name = rs.getString(1);
				double price = rs.getDouble(2);
				int quantity = rs.getInt(3);
				int id = rs.getInt(4);
				
				product.setId(id);
				product.setName(name);
				product.setPrice(price);
				product.setQuantity(quantity);
				
				products.add(product);
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
		
		return products;
	}
	
	public List<Product> fetchAll() {
		Connection conn = null;
		List<Product> products = new ArrayList<Product>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "root");
			
			String sql = "select * from product";
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();	
			
			while(rs.next()) {
				Product product = new Product();
				String name = rs.getString(1);
				double price = rs.getDouble(2);
				int quantity = rs.getInt(3);
				int id = rs.getInt(4);
				
				product.setId(id);
				product.setName(name);
				product.setPrice(price);
				product.setQuantity(quantity);
				
				products.add(product);
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
		
		return products;
	}

	public int add(Product product) {
		Connection conn=null;
		int generatedId = -1;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "root");
			
			String sql = "INSERT INTO product (name, price, quantity) VALUES (?, ?, ?)";
	        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        
	        String name = product.getName();
	        double price = product.getPrice();
	        int quantity = product.getQuantity();
	        
			stmt.setString(1, name);
			stmt.setDouble(2, price);
			stmt.setInt(3, quantity);
			int affectedRows = stmt.executeUpdate();
	        
			if (affectedRows > 0) {
	            ResultSet generatedKeys = stmt.getGeneratedKeys();

	            if (generatedKeys.next()) {
	                generatedId = generatedKeys.getInt(1);
	            }
	        }
			return generatedId;
		}
		catch( SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return generatedId;
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}

	public void delete(int id) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "root");
			
			String sql = "delete from product where id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, id);
			int rs= st.executeUpdate();	
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}

	public Product fetchById(int id) {
		Connection conn = null;
		Product p = new Product();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "root");
			
			String sql = "select * from product where id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();	
			
			if(rs.next()) {
				String name = rs.getString(1);
				double price = rs.getDouble(2);
				int quantity = rs.getInt(3);
				
				p.setId(id);
				p.setName(name);
				p.setPrice(price);
				p.setQuantity(quantity);
			}
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
		
		return p;
	}
	
	public void update(Product product) {
		Connection conn = null;
		Product p = new Product();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "root");
			
			String sql = "update product set name = ?, price=?, quantity=? where id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			
			int id = product.getId();
			String name = product.getName();
	        double price = product.getPrice();
	        int quantity = product.getQuantity();
	        
			st.setString(1, name);
			st.setDouble(2, price);
			st.setInt(3, quantity);
			st.setInt(4, id);
			
			int val = st.executeUpdate();
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}
}
