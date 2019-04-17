<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="il.ac.hit.mygym.Model.Activity" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">

    <title>MYGym Tracer Home  </title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/home.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/fontawesome/css/all.css"  >

</head>
<body >

<header>
    <a href="home${pageContext.request.contextPath}/mygym/user/home" class="header-name">  MyGym</a>
    <nav> <!--  LINKS-->
        <ul>
            <li>  <a href="${pageContext.request.contextPath}/mygym/user/about"><i class='fas fa-home'></i> About  </a> </li>
            <li> <a href="${pageContext.request.contextPath}/mygym/user/help"><i class='fas fa-home'></i> Help   </a> </li>
            <li> <a href="${pageContext.request.contextPath}/mygym/user/logout"><i class="fas fa-sign-out-alt"></i></i> Logout   </a> </li>
        </ul>
        <div class="welcome-div">
            <br/> <h1><i class='fas fa-user'> </i>
            <!-- option  to show username from session
                <%--Wellcome username Test--%>
                 <%--Wellcome      <%=session.getAttribute("username")%>--%>
            -->
                <%  String username =null;
                    Cookie[] vec = request.getCookies();
                    username= (String) session.getAttribute("username");
                    for(int i=0; vec!=null && i<vec.length; i++)
                    {
                        if(vec[i].getName().equals("username"))
                        {
                            username = vec[i].getValue();
                        }
                    }
                %>
                <!-- Wellcome Name With Cookie-->
                Wellcome :   <%=username%>
                <br>
                <%if(request.getAttribute("loginMsg")!=null){%>
                <%= request.getAttribute("loginMsg")%>
                <%}%>

                <%request.setAttribute("loginMsg","");%>
            </h1>
        </div>
    </nav>


</header>

<main style="background-image: url('<%=request.getContextPath()%>/web/picture/1.jpg')" >
    <section class="index-banner" style="background-image: url('${pageContext.request.contextPath}/jspLib/picture/1.jpg')  " >
        <div class="vertical-center">
            <h2>
                Welcome To MYGYM , The best GYM Tracking App
            </h2>
            <h1> In our gym you can login and add Activities and Manage them and get Statistics </h1>

        </div>
    </section>
    <section class="index-links">
        <a href="${pageContext.request.contextPath}/mygym/admin/defualtactivity">
            <div class="index-boxlink-1">
                <h3>  <i class='far fa-calendar-alt '> </i> Add Activity </h3>
            </div>
        </a>



        <a href="${pageContext.request.contextPath}/mygym/admin/edit">
            <div class="index-boxlink-1">
                <h3><i class="fas fa-edit"></i>  Edit Activity </h3>
            </div>
        </a>

        <a href="${pageContext.request.contextPath}/mygym/admin/statistics">
            <div class="index-boxlink-1">
                <h3><i class="fas fa-chart-bar"></i> Statistics </h3>
            </div>
        </a>
    </section>



    <div class="container">
        <h3> Here some Example from our Activities   </h3>
        <table class="table table-bordered table-sm">
            <thead class="thead-dark">
            <tr>
                <th> Excricise name  </th>
                <th> num euqitment     </th>
                <th> Duration   </th>
                <th> </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td> elliptical </td>
                <td> 11 </td>
                <td> 30 min </td>
            </tr>
            <tr>
                <td> jogging </td>
                <td> 22 </td>
                <td> 40 min  </td>
            </tr>
            <tr>
                <td> cycling </td>
                <td> 33   </td>
                <td> 60 min </td>
            </tr>
            </tbody>

        </table>
    </div>





</main>
<footer>
    <h3><i class="far fa-copyright"></i> All Right reserved </h3>
</footer>


<script src="${pageContext.request.contextPath}/jspLib/js/3.3.1-jquery.min.js"> </script>
<script src="${pageContext.request.contextPath}/jspLib/js/bootstrap.min.js"> </script>


</body>

</html>

