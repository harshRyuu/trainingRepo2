<%@page import="com.seclore.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	
	<body>
		<a href="CrudProductServlet?action=sort&val=asc">low -> high</a>
		<a href="CrudProductServlet?action=sort&val=desc">high -> low</a>
		<%
			List<Product> list = (List<Product>) request.getAttribute("listOfProducts");
			for(Product product : list) {
		%>
			<div>
				id : <%=product.getId() %> <br/> 
				Name : <%=product.getName() %> <br/> 
				Price : <%=product.getPrice()%> <br/> 
				Quantity : <%=product.getQuantity()%> <br/> 
				<a href="CrudProductServlet?action=edit&id=<%= product.getId() %>">Edit</a>
				<a href="CrudProductServlet?action=delete&id=<%= product.getId() %>">Delete</a>
			</div>
		<%
			}
		%>
	</body>
</html>