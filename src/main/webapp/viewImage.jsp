

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <b>View | <a href="index.jsp">Upload</a></b><br/><br/>
        
		<%
		String id = request.getParameter("id");
		%>
		<video width="400" controls>
        <source src="getImage.jsp?id=<%=id%>" type="video/mp4">
        
    </video>
    </body>
</html>