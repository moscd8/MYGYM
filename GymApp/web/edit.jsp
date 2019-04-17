<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="il.ac.hit.mygym.Model.Activity" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">

    <title>Manage Youre Activity</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/login.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jspLib/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jspLib/css/fontawesome/css/all.css"  >

   <!-- <script>

        window.onload = function (){
            $("#edit-form").hide();
            $("#edit-form1").hide();

        };




//edit -> copy to form test..
function edit_OLD(no){
    $("#edit-form").show();
    alert("edit1");
    $(document).ready(function() {
        // $('#copy').click(function() {
        console.log("inside");
        // $('#target').val($('#original').val());
        // $('#id').val(no);


        $('#aname').val($('#activityName'+no).val());

        $('#aid').val($('#activityId'+no).val());  /// hidden

        $('#nsets').val($('#activitySet'+no).val());
        $('#nrep').val($('#activityRepeats'+no).val());
        $('#ndur').val($('#activityDur'+no).val());
        console.log("finish1");
        // update(no);
        // var x= $('#id').val();
        // console.log("before " + x );
        // x= $('#id').val(no);
        // console.log("after " + x );


    })
};




<%--function save_row(no)--%>
        <%--{--%>
            <%--var name_val=document.getElementById("activityName"+no).value;--%>
            <%--// var country_val=document.getElementById("country_text"+no).value;--%>
            <%--// var age_val=document.getElementById("age_text"+no).value;--%>
            <%--alert(name_val);--%>




            <%--document.getElementById("activityName"+no).innerHTML=name_val;--%>
            <%--// document.getElementById("country_row"+no).innerHTML=country_val;--%>
            <%--// document.getElementById("age_row"+no).innerHTML=age_val;--%>

            <%--document.getElementById("edit_button"+no).style.display="block";--%>
            <%--document.getElementById("save_button"+no).style.display="none";--%>

        <%--}--%>


        function delete_row(no)
        {
            // document.getElementsByClassName("delete").style.display = none;
            $(".edit").prop('disabled', true);
            $(".delete").prop('disabled', true);

            var activityId= $('#activityId'+no).val();
            var form =document.createElement("form");
            var elemem1= document.createElement("input");
            form.name= "formToDelete";
            form.method="POST";
            form.action= "http://localhost:8080/GymApp_war_exploded/mygym/admin/delete";
            elemem1.type= "number hidden";
            elemem1.name= "activityId";
            var activityidValue=  $('#activityId'+no).val();
            console.log(activityidValue);
            elemem1.value =activityidValue ;
            form.appendChild(elemem1);
            document.body.appendChild(form);
            form.submit();

            elemem1.hidden=true;

            document.getElementById("row"+no+"").outerHTML="";
            $("#edit-form").hide();

            $(".edit").prop('disabled', false);
            $(".delete").prop('disabled', false);
        };
        //
        <%--// function add_row()--%>
        <%--// {--%>
        <%--//  var new_name=document.getElementById("new_name").value;--%>
        <%--//  var new_country=document.getElementById("new_country").value;--%>
        <%--//  var new_age=document.getElementById("new_age").value;--%>
        <%--//--%>
        <%--//  var table=document.getElementById("data_table");--%>
        <%--//  var table_len=(table.rows.length)-1;--%>
        <%--//  var row = table.insertRow(table_len).outerHTML="<tr id='row"+table_len+"'><td id='name_row"+table_len+"'>"+new_name+"</td><td id='country_row"+table_len+"'>"+new_country+"</td><td id='age_row"+table_len+"'>"+new_age+"</td><td><input type='button' id='edit_button"+table_len+"' value='Edit' class='edit' onclick='edit_row("+table_len+")'> <input type='button' id='save_button"+table_len+"' value='Save' class='save' onclick='save_row("+table_len+")'> <input type='button' value='Delete' class='delete' onclick='delete_row("+table_len+")'></td></tr>";--%>
        <%--//--%>
        <%--//  document.getElementById("new_name").value="";--%>
        <%--//  document.getElementById("new_country").value="";--%>
        <%--//  document.getElementById("new_age").value="";--%>
        <%--// }--%>





        function cancel() {
            $('#aname').val("");
            $('#aid').val("");

            $('#nsets').val("");
            $('#nrep').val("");
            $('#ndur').val("");
            $('#id').val("");
            $("#edit-form1").hide();

            $('.edit').prop('disabled', false);
            $('.delete').prop('disabled', false);
        };





        <%--// function add_row()--%>
        <%--// {--%>
        <%--//     var table = document.getElementById("data_tabe") ;--%>
        <%--//     var rowNum = document.getElementById("data_table").rows.length  ;--%>
        <%--//     var activityname=document.getElementById("activityNameTemp").value;--%>
        <%--//     var activityset=document.getElementById("activitySetTemp").value;--%>
        <%--//     var activityrep=document.getElementById("activityRepeatsTemp").value;--%>
        <%--//     var activitydur=document.getElementById("activityDurTemp").value;--%>
        <%--//     var row = table.insertRow(3);--%>
        <%--//     // table.insertRow(rowNum).outerHTML= "<tr><td>!@#</td> </tr>";--%>
        <%--//     // table.insertRow(rowNum).outerHTML= " <tr id='row'"+rowNum+">" +--%>
        <%--//     //     "<td><input  id='activityName'" +rowNum+ "value="+activityName+rowNum + "class='form-control border-0'  readonly> </td> "--%>
        <%--//     //     + " <td><input  id='activitySet'"+rowNum +"value="+activitySet+rowNum + "class='form-control border-0'  readonly> </td>"--%>
        <%--//     //         + "<td><input  id='activityRepeats'"+rowNum +"value="+activityRepeats+rowNum + "class='form-control border-0'  readonly> </td>"--%>
        <%--//     //         +" <td><input  id='activityDur'"+rowNum +"value="+activityDur+rowNum + "class='form-control border-0'  readonly> </td>"--%>
        <%--//     //         +"<td>"--%>
        <%--//     //         +"<input type='button' id='edit_button2' value='Edit' class='edit' onclick='edit('"+rowNum+"')'>"--%>
        <%--//     //         +"<input type='button' id='save_button2' value='Update' class='save' onclick='update('"+rowNum+"')'>"--%>
        <%--//     //         +"<input type='button' id='Delete' value='delete' class='delete' onclick='delete_row('"+rowNum+"')'>"--%>
        <%--//     //         + "</td> </tr>" ;--%>
        <%--//--%>
        <%--//--%>
        <%--//--%>
        <%--//--%>
        <%--//--%>
        <%--// //--%>
        <%--// //  var table=document.getElementById("data_table");--%>
        <%--// //  var table_len=(table.rows.length)-1;--%>
        <%--// //  var row = table.insertRow(table_len).outerHTML="<tr id='row"+table_len+"'><td id='name_row"+table_len+"'>"+new_name+"</td><td id='country_row"+table_len+"'>"+new_country+"</td><td id='age_row"+table_len+"'>"+new_age+"</td><td><input type='button' id='edit_button"+table_len+"' value='Edit' class='edit' onclick='edit_row("+table_len+")'> <input type='button' id='save_button"+table_len+"' value='Save' class='save' onclick='save_row("+table_len+")'> <input type='button' value='Delete' class='delete' onclick='delete_row("+table_len+")'></td></tr>";--%>
        <%--// //--%>
        <%--// //  document.getElementById("new_name").value="";--%>
        <%--// //  document.getElementById("new_country").value="";--%>
        <%--// //  document.getElementById("new_age").value="";--%>
        <%--// };--%>
function validateMyForm() {
    // alert('Handler for validateMyForm called.');

    var name= document.getElementById("activitynameAjax").value;
    var sets = document.getElementById("activitysetsAjaxn").value;
    var rep = document.getElementById("activityrepsAjaxn").value;
    var dur = document.getElementById("activitydurAjaxn").value;

    if(name.length >0  && name.length <25)
        if(sets >0 && sets <30)
            if(rep  >0 && rep  <30)
                if(dur>0 && dur<100)
                {
                    updAjax();
                }else{
                    alert("Error, please check the input ");
                    return false;
                }


//    alert("after fun finish  ...: "+$('#activityidAjax').val());
//                 var numRow=$('#idOfRowNum').val();
//                 $('#activityId'+numRow).val($('#activityidAjax').val());
//    alert("id from table  : "+$('#activityId'+numRow).val());


    // var sets = document.getElementById("activitysetsAjaxn").value;
    //                $('#activitySet'+numRow).val(document.getElementById("activitysetsAjaxn").value);
  //  alert($('#activitySet'+numRow).val());
    // var rep = document.getElementById("activityrepsAjaxn").value;
    //            $('#activityRepeats'+numRow).val(document.getElementById("activityrepsAjaxn").value);
  //  alert($('#activityRepeats'+numRow).val());
    // var dur = document.getElementById("activitydurAjaxn").value;
    //            $('#activityDur'+numRow).val(document.getElementById("activitydurAjaxn").value);
//    alert($('#activityDur'+numRow).val());


};
        function updAjax2(){
            var xhr = new XMLHttpRequest();
            $.ajax({

                url : "${pageContext.request.contextPath}/mygym/admin/updateAjax?activityName="+$('#activitynameAjax').val() + "&activityid=" + $('#activityidAjax').val()  + "&activitysets=" + $('#activitysetsAjaxn').val() + "&activityrepeats=" + $('#activityrepsAjaxn').val() + "&activitydur=" + $('#activitydurAjaxn').val() ,
                type: 'GET',
                success : function( resp ) {
                    console.log( resp.people );
                    alert( resp.people );
                },error: function( req, status, err ) {
                    console.log( 'something went wrong', status, err );
                    alert( "something went wrong " + status + err );
                }
            });
        }


function updAjax(){
    // $("#edit-form").hide();
  // var x=  $(".edit");
  // x.prop("disable")
  //   $(".edit").prop("disabled", true);
  //   $(".delete").prop("disabled", true);


    // if(validateMyForm() == true )
    // validateMyForm();

    alert("Update Your Activity please wait ...")
    var xhr = new XMLHttpRequest();
    xhr.open("GET","${pageContext.request.contextPath}/mygym/admin/updateAjax?activityName="+$('#activitynameAjax').val() + "&activityid=" + $('#activityidAjax').val()  + "&activitysets=" + $('#activitysetsAjaxn').val() + "&activityrepeats=" + $('#activityrepsAjaxn').val() + "&activitydur=" + $('#activitydurAjaxn').val()   ,true);
    xhr.onreadystatechange = function()
    {
        if((xhr.readyState==4) && (xhr.status = 200))
        {

            var node = document.getElementById("result");
            // node.innerHTML=xhr.responseText; //return  new id from srv
            // var  node2=xhr.responseText; //return  new id from srv

            // $idOfRowNum
            // node.innerHTML= xhr.responseText;
//                      alert(node.innerHTML);
            // alert(node2.innerHTML);
            <%--var newId= <%= request.getParameter("newactivityId")%>;--%>
            // alert(newId);

            var rowNum= $('#idOfRowNum').val(); //rowNumber in Table , need for update content

            // alert("num is : "+rowNum);
            // var name= document.getElementById("activitynameAjax").value;

            // $('#activityName'+rowNum).val(document.getElementById("activitynameAjax").value);
            // alert($('#activityName'+rowNum).val());

            if(node.innerHTML!=null){
                // $('#activityidAjax').val(node.innerHTML);
                $('#activityidAjax').val(xhr.responseText);
                // alert("new id from server  is: "+ $('#activityidAjax').val());
                $('#activityId'+rowNum).val(node.innerHTML);
            }
            // alert($('#activityId'+rowNum).val());

            // var sets = document.getElementById("activitysetsAjaxn").value;
            $('#activitySet'+rowNum).val(document.getElementById("activitysetsAjaxn").value);
            // alert($('#activitySet'+rowNum).val());
            // var rep = document.getElementById("activityrepsAjaxn").value;
            $('#activityRepeats'+rowNum).val(document.getElementById("activityrepsAjaxn").value);
            //alert($('#activityRepeats'+rowNum).val());
            // var dur = document.getElementById("activitydurAjaxn").value;
            $('#activityDur'+rowNum).val(document.getElementById("activitydurAjaxn").value);
            //alert($('#activityDur'+rowNum).val());

        }
    };
    xhr.send();

    // alert("after ...: "+$('#activityidAjax').val());
    // var numRow=$('#idOfRowNum').val();
    // $('#activityId'+numRow).val($('#activityidAjax').val());
    //
    // alert("after ...: "+$('#activityId'+numRow).val());


    $("#edit-form1").hide();
    $(".edit").prop("disabled", false);

    $(".delete").prop("disabled", false);


}


function cancel2() {

    $('#aname1').val("");
    $('#aid1').val("");

    $('#nsets1').val("");
    $('#nrep1').val("");
    $('#ndur1').val("");
    $('#id1').val("");
    $("#edit-form1").hide();
    $(".edit").prop('disabled', false);
    $(".delete").prop('disabled', false);
};


//edit -> copy to form test..
function edit(no){
    $("#edit-form1").show();
    $(document).ready(function() {
        console.log("inside");

        $('#activitynameAjax').val($('#activityName'+no).val());

        $('#activityidAjax').val($('#activityId'+no).val());  /// hidden

        $('#activitysetsAjaxn').val($('#activitySet'+no).val());
        $('#activityrepsAjaxn').val($('#activityRepeats'+no).val());
        $('#activitydurAjaxn').val($('#activityDur'+no).val());

        $("#idOfRowNum").val(no);

        $('.edit').prop('disabled', false);
        $('.delete').prop('disabled', false);
    })
};


    </script> -->

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


<div class="container">
    <div class="row">

        <div  id="edit-form1" class="editform1" >
            <form>
                Activity Name :  <input type="text"  name ="activityName"  id="activitynameAjax"  minlength="2" maxlength="20" required  >
                <input type="text" name="activityid"   id="activityidAjax" hidden ><br><br>
                <input type="hidden" id="idOfRowNum"   >
                NumOfSets:       <input type="number" name="activitysets" id="activitysetsAjaxn" min="1" max="8"   minlength="1" maxlength="8" required  ><br><br>
                NumOfRepets:     <input type="number" name="activityrepeats" id="activityrepsAjaxn"  min="1" max="31" minlength="1" maxlength="31" required ><br><br>
                NumOfDuration:   <input type="number" name="activitydur"   id="activitydurAjaxn"  min="0" max="90" minlength="0" maxlength="90" required ><br><br>
                <input type="submit" value="Update" onclick='validateMyForm();' >  <br/>
                <input type="reset" value="Cancel" onclick="cancel2();"/>

            </form>
        </div>


    </div>

    <div id="result">

        <% String msg= (String) session.getAttribute("validForm"); %>
        <% if(msg!=null){%>
        <%=msg%>
        <%session.setAttribute("validForm","");}%>
        <% String updatemsg= (String) session.getAttribute("updateMsg"); %>
        <% if(updatemsg!=null){%>
        <%=updatemsg%>
        <% session.setAttribute("updateMsg","");}%>

    </div>


     <div class="row" id="edit-div">
        <!-- the user Activity -->
        <%= session.getAttribute("editActivityTable")%>
    </div>

</div>


<script src="${pageContext.request.contextPath}/jspLib/js/3.3.1-jquery.min.js"> </script>
<script src="${pageContext.request.contextPath}/jspLib/js/edit.js"> </script>
<script src="${pageContext.request.contextPath}/jspLib/js/bootstrap.min.js"> </script>

</body>
</html>