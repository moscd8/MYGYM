<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">

    <title>Help </title>
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
    <p> Help </p>

    <h1> #TODO , <br/> put here some info about workout , nutrition and general manuel info fo the trainers </h1>
</div>

<script src="${pageContext.request.contextPath}/jspLib/js/3.3.1-jquery.min.js"> </script>
<script src="${pageContext.request.contextPath}/jspLib/js/bootstrap.min.js"> </script>


</body>
</html>
