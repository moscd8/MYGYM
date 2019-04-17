<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.io.IOException" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <title>Login </title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/login.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/fontawesome/css/all.css"  >



</head>
<body>

<header class="header">
    <nav class="navbar navbar-style">
        <div class="container-head">
            <div class="navbar-header">
                <h1><i class='fas fa-user'> </i> Login </h1>

            </div>
        </div>
    </nav>
</header>



<div class="container">
    <div class="row  ">
        <div id="form-horizontal">
            Wellcome  Please Login
        </div>


        <% String username , usernameCoocie ;
        username = (String) session.getAttribute("username");
            if(username != null){
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home.jsp");
                try {
                    requestDispatcher.forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else
                {
                    username="";
                Cookie[] vec = request.getCookies();
                for(int i=0; vec!=null && i<vec.length; i++)
                {
                    if(vec[i].getName().equals("username"))
                    {
                        username = vec[i].getValue();
                    }
                }

        }

        %>
        <br><%if(request.getAttribute("loginMsg")!=null){%>
        <%= request.getAttribute("loginMsg")%>
        <%}%>
        <br>


        <form class="form-horizontal"  method="post" action="${pageContext.request.contextPath}/mygym/user/login" >
            <div class="col-xs-10">

            username: <input type="text"  class="form-control" name="username" id="username" value="<%= username%>" required  >  <br/>
            email:    <input type="email"  class="form-control" name="email" id="email" required > <br/>
            password: <input type="password" class="form-control"  name="password" id="password" required > <br/>

            <br>

                <input type="submit" value="Login ">

            </div>

        </form >

        <form  class="form-horizontal"  method="post" action="${pageContext.request.contextPath}/mygym/user/login" >
            <div class="col-xs-10">
            <input type="submit" name="registerbt457556" value="Register Me  ">
            <input type="text" name="registerbt" value="registerme" hidden>
            </div>
        </form>

    </div>
</div>




<script src="${pageContext.request.contextPath}/jspLib/js/3.3.1-jquery.min.js"> </script>
<script src="${pageContext.request.contextPath}/jspLib/js/register.js" type="text/javascript"> </script>
<script src="${pageContext.request.contextPath}/jspLib/js/bootstrap.min.js"> </script>


</body>
</html>
