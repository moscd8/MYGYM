window.onload = function (){
    $("#edit-form").hide();
    $("#edit-form1").hide();

};

function delete_row(no)
{

    $(".edit").prop('disabled', true);
    $(".delete").prop('disabled', true);

    var activityId= $('#activityId'+no).val();
    var form =document.createElement("form");
    var elemem1= document.createElement("input");
    form.name= "formToDelete";
    form.method="POST";

    form.action= "http://10.0.2.2:8080/GymApp_war_exploded/mygym/admin/delete";
    // form.action= "http://localhost:8080/GymApp_war_exploded/mygym/admin/delete";
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


};


function updAjax(){

    alert("Update Your Activity please wait ...")
    var xhr = new XMLHttpRequest();
    xhr.open("GET","${pageContext.request.contextPath}/mygym/admin/updateAjax?activityName="+$('#activitynameAjax').val() + "&activityid=" + $('#activityidAjax').val()  + "&activitysets=" + $('#activitysetsAjaxn').val() + "&activityrepeats=" + $('#activityrepsAjaxn').val() + "&activitydur=" + $('#activitydurAjaxn').val()   ,true);
    xhr.onreadystatechange = function()
    {
        if((xhr.readyState==4) && (xhr.status = 200))
        {

            var node = document.getElementById("result");
            var rowNum= $('#idOfRowNum').val(); //get the rowNumber in the Table , needed for update content
            if(node.innerHTML!=null){
                $('#activityidAjax').val(xhr.responseText);
                $('#activityId'+rowNum).val(node.innerHTML);
            }

            $('#activitySet'+rowNum).val(document.getElementById("activitysetsAjaxn").value);
            $('#activityRepeats'+rowNum).val(document.getElementById("activityrepsAjaxn").value);
            $('#activityDur'+rowNum).val(document.getElementById("activitydurAjaxn").value);
        }
    };
    xhr.send();


    $("#edit-form1").hide();
    $(".edit").prop("disabled", false);
    $(".delete").prop("disabled", false);

}


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
