<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <title>Register</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/register.css"/>
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/jspLib/css/login.css">--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/fontawesome/css/all.css"  >



</head>
<body>

<header class="header">
    <nav class="navbar navbar-style">
        <div class="container-head">
            <div class="navbar-header">
                <i class="fas fa-user"></i> Register</h1>
            </div>
        </div>
    </nav>
</header>

<div class="container">
    <div class="row  ">
        <br>
        <%--<%= session.getAttribute("loginMsg")%>--%>
        <p>
            <%if(request.getAttribute("loginMsg")!=null){%>
            <%= request.getAttribute("loginMsg")%>
            <%}%>

            <%request.setAttribute("loginMsg","");%>
        </p>
        <br>



       <form class="form-horizontal" action="${pageContext.request.contextPath}/mygym/user/register" method="post">
            <div class="col-xs-10">
                    Username: <input type="text"  class="form-control" name="username" id="username" placeholder="Enter your First Name"  minlength="2" maxlength="25" required > <br/>
            </div>

            <div class="col-xs-10">
                 Email :    <input type="email" class="form-control" id="email" name="email" placeholder="Enter email"  minlength="2" maxlength="25"    required> <br/>
            </div>
            <div class="col-xm-10">
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password"  minlength="3" maxlength="30"  required> <br/>
            </div>
            <div class="col-xm-10">
                    password:    <input type="password" class="form-control" id="password2" placeholder="repeat"  minlength="3" maxlength="30" required> <br/>
            </div>
            <div class="col-xm-10">
                weight:    <input type="number" class="form-control" id="weight" name="weight" placeholder="You weight in kg "  min="30" max="200"  minlength="30" maxlength="200" required> <br/>
            </div>
            <div class="col-xm-10">
                    height:    <input type="number" class="form-control" id="height"  name="height" placeholder="You height in cm "  min="30" max="250"  minlength="30" maxlength="250" required> <br/>
            </div>





            <div class="col-xs-offset-2 col-sm-10">
                <input type="submit" id= "sub-btn" value="Save " onclick="validateMyForm();"/>
            </div>

        </form>

    </div>
</div>

<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/jspLib/js/3.3.1-jquery.min.js"> </script>
<script src="${pageContext.request.contextPath}/jspLib/js/register.js" type="text/javascript"> </script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>



</body>
</html>