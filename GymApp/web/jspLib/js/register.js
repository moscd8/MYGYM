function validateMyForm() {
    // alert('Handler for validateMyForm called.');
    var name= document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var password1 = document.getElementById("password").value;
    var password2 = document.getElementById("password2").value;

    var weight = document.getElementById("weight").value;
    var height = document.getElementById("height").value;

    if(name.length >0  && name.length <25)
        if(email.length >0 && email.length <30)
            if(password1 == password2)
                if(weight>=30 && height>=30)
                {
                    alert("OK, checking your credential ");
                     postValid(name,email,password1,weight,height);

                }else{
                    alert("Error, please check your credential");
                    return false;
                 }

};




function postValid(username,email,password,weight,height)
{
    // alert('Handler for validateMyForm called.');
    var form =document.createElement("form");
    var elememUsername= document.createElement("input");
    var elememEmail= document.createElement("input");
    var elememPass= document.createElement("input");
    var elememWeight= document.createElement("input");
    var elememHeight= document.createElement("input");

    form.name= "formToDelete";
    form.method="POST";

    form.action= "http://10.0.2.2:8080/GymApp_war_exploded/mygym/user/register";
    // form.action= "http://localhost:8080/GymApp_war_exploded/mygym/user/register";
    elememUsername.name= "username";
    elememEmail.name= "email";
    elememPass.name= "password";
    elememWeight.name= "weight";
    elememHeight.name= "height";

    elememUsername.value=username;
    elememEmail.value=email;
    elememPass.value=password;
    elememWeight.value=weight;
    elememHeight.value=height;


    form.hidden=true;
    elememUsername.hidden=true;
    elememEmail.hidden=true;
    elememPass.hidden=true;
    elememWeight.hidden=true;
    elememHeight.hidden=true;

    form.appendChild(elememUsername);
    form.appendChild(elememEmail);
    form.appendChild(elememPass);
    form.appendChild(elememWeight);
    form.appendChild(elememHeight);
    document.body.appendChild(form);
    form.submit();


};
