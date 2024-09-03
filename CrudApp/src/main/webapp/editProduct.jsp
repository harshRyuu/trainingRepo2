<%@page import="com.seclore.entity.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		<%	
			Product product = (Product) request.getAttribute("product");
		 %>
		
		<form action="CrudProductServlet">
			<input type="hidden" name="action" value="update"/>
			Product id : <input type="text" name="id" value="<%= product.getId() %>" readonly/>
			Enter product name : <input type="text" name="name" value="<%= product.getName() %>"/> <br/>
			Enter product price : <input type="number" name="price" value="<%= product.getPrice() %>"/> <br/>
			Enter product quant : <input type="number" name="quantity" value="<%= product.getQuantity() %>"/> <br/>
			
			<button type="submit">Edit Product</button>
		</form>
	</body>
</html>