<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="il.ac.hit.mygym.Model.Activity" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Welcome to the Gym</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/home.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/fontawesome/css/all.css"  >


</head>

<body>
<header class="header">
    <nav class="navbar navbar-style">
        <div class="container">
            <div class="navbar-header">

                 <h1>
                     <i class='fas fa-user'> </i><%=session.getAttribute("username")%>
                </h1>
               <button><a href="${pageContext.request.contextPath}/mygym/user/logout"><i class='fas fa-home'></i> Logout</a> </button>
               <button><a href="${pageContext.request.contextPath}/mygym/user/home"><i class='fas fa-home'></i> Home</a> </button>
                <hr/>
              </div>
        </div>
    </nav>
</header>

<div id="load">
    <% if(request.getAttribute("msg") != null) {%>
     <p>   <%= request.getAttribute("msg")%>  </p>
       <%}%>

        <% String defaultCreated= (String) session.getAttribute("defualtCreated");%>
        <% if(defaultCreated == "yes" && session.getAttribute("defaultActivityTable")!= null){%>
         <%= session.getAttribute("defaultActivityTable")%>
        <% }else { %>
             <%--<%=   session.getAttribute("username")%>--%>
            Opps , something went wrong please try again
            <%}%>




        <br/>



</div>

<script src="${pageContext.request.contextPath}/jspLib/js/3.3.1-jquery.min.js"> </script>
<script src="${pageContext.request.contextPath}/jspLib/js/bootstrap.min.js"> </script>
</body>


</html>