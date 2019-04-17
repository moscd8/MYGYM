<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">

    <title>About MYGym Tracer  </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/home.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/fontawesome/css/all.css"  >

</head>
<body>
<header class="header">
    <nav class="navbar navbar-style">
        <div class="container">
            <div class="navbar-header">
                <h1><i class='fas fa-user'> </i>
                    <%=session.getAttribute("username")%>
                </h1>
                <button><a href="${pageContext.request.contextPath}/mygym/user/logout"><i class='fas fa-home'></i> Logout</a> </button>
                <button><a href="${pageContext.request.contextPath}/mygym/user/home"><i class='fas fa-home'></i> Home</a> </button>

            </div>
        </div>
    </nav>
</header>

<div id="about-msg">
<p> about MYGYM  site</p>

<p> MYGYM  created during the Course of Java-EE and Android  development  by  the lecture Mr. Haim Michael </p>
</div>
<script src="${pageContext.request.contextPath}/jspLib/js/3.3.1-jquery.min.js"> </script>
<script src="${pageContext.request.contextPath}/jspLib/js/bootstrap.min.js"> </script>


</body>
</html>
