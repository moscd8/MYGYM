<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  %>
<%@page  isErrorPage="true"%>
<%@ page import="il.ac.hit.mygym.Model.Activity" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">

    <title>Welcome to the Gym</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/fontawesome/css/all.css"  >
    <link rel="stylesheet" href="<%=request.getContextPath()%>/jspLib/css/home.css">



</head>

<body>
<header class="header">

</header>



<%--<script src="<%=request.getContextPath()%>/jspLib/js/home.js"> </script>--%>


<div class="error">

    <%if(request.getAttribute("errorMsg")!=null){%>
    Error message(from server )  :   <%=request.getAttribute("errorMsg")%>
    <%}%>


    <br>
    <% if(response.getStatus() == 500){ %>
   <p> Error: <%=exception.getMessage() %><br> </p>
    <%}%>

    <%--<br>--%>
    <%--<h1> Hi There, error code is <%=response.getStatus() %><br>  </h1>--%>

    <h2> Opps something went wrong ,  Please go to <a href="${pageContext.request.contextPath}/mygym/user/home">Home page</a>  </h2>

</div>


<script src="${pageContext.request.contextPath}/jspLib/js/3.3.1-jquery.min.js"> </script>
<%--<script src="${pageContext.request.contextPath}/jspLib/js/home.js"> </script>--%>
<script src="${pageContext.request.contextPath}/jspLib/js/bootstrap.min.js"> </script>

</body>


</html>