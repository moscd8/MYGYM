<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="il.ac.hit.mygym.Model.Activity" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">

    <title>MY Statistics </title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/home.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/fontawesome/css/all.css"  >
    <script type="text/javascript" src="${pageContext.request.contextPath}/jspLib/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jspLib/js/canvasjs.min.js"></script>


</head>

<script type="text/javascript">
  window.onload = function () {
        var chart = new CanvasJS.Chart("chartContainer1",
            {
                title:{
                    text: "Num of Repeates in each  Activity ",
                    fontWeight: "bolder",
                    fontColor: "#008B8B",
                    fontfamily: "tahoma",
                    padding: 10,
                },
                axisX:{
                    title : "Activity "
                },
                axisY:{
                    title : "Repeats"
                },

                data: [
                    {
                        // legendText: "{indexLabel}",
                        dataPoints: [

                            // <!--{ x: "error", y: "error", label: "error"}
                            // { label: "apple",  y: 10  }
                            <% String msgRepeats= (String) request.getAttribute("repeatsMsg"); %>
                            <% if(msgRepeats!=null){%>
                            <%=msgRepeats%>
                            <%} %>
                            <!--
                            <%--<%="Opps, No Activity was founded "%>--%>
                            <%--<%= "{ x: 'error', y: 'error', label: 'error'}"%>--%>
                            <%--<%}%>--%>
                            -->

                        ]
                    }
                ]
            });
        var chart2 = new CanvasJS.Chart("chartContainer2",
            {
                title: {
                    text: "Calories in Activity"
                },
                legend: {
                    maxWidth: 350,
                    itemWidth: 120
                },
                data: [
                    {
                        type: "pie",
                        showInLegend: true,
                        legendText: "{indexLabel}",
                        dataPoints: [
                            <% String msgCalorie= (String) request.getAttribute("caloriesMsg"); %>
                            <% if(msgCalorie!=null){%>
                            <%=msgCalorie%>
                            <%}%>
                            <%--<%} else{ %>--%>
                            <%--<%="Opps, No Activity was founded "%>--%>
                            <%--<%= "{ y: 'error', indexLable: 'error'}"%>--%>
                            <%--<%}%>--%>

                        ]
                    }
                ]
            });
        chart.render();
        chart2.render();

    }
</script>

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


  <div class="container">

    <br/><h1> Here some statistics we made for you</h1>

     <div id="userStatistics" >
            <% String msg= (String) request.getAttribute("statisticsMsg"); %>
            <% if(msg!=null){%>
            <%=msg%>
            <%request.setAttribute("statisticsMsg","");}%>

     </div>

    <div id="chartContainer1" style="height: 300px; width: 80%;">
    </div>

    <div id="chartContainer2" style="height: 300px; width: 80%;">
    </div>

</div>

</div>


<script src="${pageContext.request.contextPath}/jspLib/js/3.3.1-jquery.min.js"> </script>
<script src="${pageContext.request.contextPath}/jspLib/js/bootstrap.min.js"> </script>
</body>


</html>