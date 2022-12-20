<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="practice_4.SelectReq"%>
<html>
<form>
<head>
    <title>Start Page</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false"%>
</head>
<body>
<div style="font-size: 20px; padding: 30px; margin: 50px; border: 1px solid black; border-radius: 5px; display: flex; flex-direction: column; justify-content: center;">
<ur>
<%
    String[] commands = new String[] {"Create_student"," Create_textbook", "Remove_textbook","Remove_student"};
    request.setAttribute("urls", commands);
%>
<div style="text-align: center;">
      <c:forEach var = "url" items="${urls}">
            <a href="<c:url value='  ${url}.jsp  ' />"><strong>${url}</strong></a>
      </c:forEach>
</div>
</ur>
<%
    List<SelectReq> selectReq = (List) request.getAttribute("selectReq");
%>
<div style="text-align: center;">
<table style="display: inline-block;  font-size: 20px;">
    <thead>
    <tr>
        <th>ID student</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>ID Textbook</th>
        <th>Textbook</th>
    </tr>
    </thead>
    <tbody>
          <% for(SelectReq sel : selectReq) { %>
             <tr>
                <td style="text-align:center"> <%= sel.getIdStudent() %> </td>
                <td style="text-align:center"> <%= sel.getFirstName() %> </td>
                <td style="text-align:center"> <%= sel.getLastName() %> </td>
                <td style="text-align:center"> <%= sel.getIdTextBook() %> </td>
                <td style="text-align:center"> <%= sel.getTextBook() %> </td>
             <tr>
          <% } %>
    </tbody>
</table>
</div>
</div>
</body>
</form>
</html>
