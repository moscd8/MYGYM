package il.ac.hit.mygym.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import il.ac.hit.mygym.Model.Activity;
import il.ac.hit.mygym.Model.User;
import il.ac.hit.mygym.Model.UserActivity;
import il.ac.hit.mygym.Dao.IDaoImpl;
import netscape.javascript.JSObject;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class AdminController extends HttpServlet {
    private static int DB_ID =3000;
    private RequestDispatcher rd=null;
    private HttpSession httpSession=null;
    private PrintWriter out;

    //FUNCTION WILL ADD THE ACTIVITY TO USER
    // THIS FUNCTION COME AFTER defaultactivity

    /***
     * addactivity Function will Add Defualt Activity to the user,and before that will check if the activity is aleardy exist in the user-Activity
     * the defualt activity means = its basic activity for starter, and save alot of place in the db  when alot of user will use the basic activity
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void addactivity(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Activity  activitiy=new Activity();
        activitiy.setName("TEST");
        String tempname,sets,repeat;
        String outmsg;
        long idToAdd;
        httpSession = request.getSession(true);
        User temp=null;
        try {


          tempname = request.getParameter("activityName");
          sets = request.getParameter("activitySet");
          repeat = request.getParameter("activityRepeats");
           idToAdd = Long.parseLong(request.getParameter("activityId"));
          temp = IDaoImpl.getInstance().getUserByEmail((String) httpSession.getAttribute("email"));
          ArrayList<UserActivity> userActivities= IDaoImpl.getInstance().getUserActivities(temp);

          if(userActivities.size() == 0) {
              if (IDaoImpl.getInstance().saveUserActivity(temp.getUserId(), idToAdd)) {


                  outmsg =  "Activity: '"+IDaoImpl.getInstance().getActivity(idToAdd).getName() + "' succssfuly Added ";
                  request.setAttribute("msg",outmsg);
              }
              /***
               * there is more than 1 activity to the user, now we need to check if the activity is already existed in userActivities
               */
          }else{
                  /** Searching in all  user activities */
                  int count=0;
                  boolean flag=false;
                  long idInDb;
                  for(UserActivity activity :userActivities) {
                      idInDb=activity.getActivityId();
                      count++;
                      if(idInDb==idToAdd){ /** check if useractivities contain the require new activity who he want to addd */
                          outmsg = "AddActivity Failed \n Activity: '"+IDaoImpl.getInstance().getActivity(idToAdd).getName() +"' already exist..Please choose something else ";
                          request.setAttribute("msg",outmsg);
                          flag=true;
                      }
                  }
                  /**idToAdd is not exist in userActivities**/
                  if(flag==false){
                      if (IDaoImpl.getInstance().saveUserActivity(temp.getUserId(), idToAdd)){
                          temp.addActivityToCache(IDaoImpl.getInstance().getActivity(idToAdd));
                          outmsg =  "Activity: '"+IDaoImpl.getInstance().getActivity(idToAdd).getName() + "' succssfuly Added ";
                          request.setAttribute("msg",outmsg);
                      }
                  }
          }
      }catch (Exception e ){
            /**Error */
            rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
      }

      finally {
          try {
              httpSession.setAttribute("userActivitiesp",temp.getActivites());
              rd = request.getRequestDispatcher("/defualtactivity.jsp");
              rd.forward(request, response);
          } catch (ServletException | IOException e ) {
              System.out.println(e);
              e.printStackTrace();
          }
      }
    }


    /**
     * defualtactivity Functiona  WILL get DEFUALT ACTIVITIES from DB
     * After that , create dynamic html table and will display it in defualtactivity.jsp page
     * and if the user add activity , the request will post to  "addactivity " functio above
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void defualtactivity(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

     try {
       httpSession = request.getSession(true);

        /**Checking defaultActivityTable is strore in the sesstion value
         * we prefome this check inorder to save time  */
        if (httpSession.getAttribute("defualtCreated") != "yes"){
                //session editActivityTable is   NULL
            String usermail = (String) httpSession.getAttribute("email");
            String maintable="";
            String tempTableRow;
            if (usermail != null) {  //user exist..
                System.out.println("email is ");
                User temp = IDaoImpl.getInstance().getUserByEmail(usermail);
                /***
                 * createing dynamclly the html table
                 */
                tempTableRow = " <div class='anaerobi-div contents'> " +
                            "<p>Please Check Which exricese you wand to add </p>" +
                            "  <strong>MYGYM basic Activities </strong> " +
                            " <table id='addactivity-table' class='table table-bordered table-sm'>" +
                            " <thead class=thead-dark>  <tr> " +
                            "   <th>Excricise Name</th>'" +
                            "       <th>num set</th>" +
                            "       <th>repeats</th>" +
                            "       <th>Duration </th>" +
                            "       <th> </th>" +
                            "   </tr></thead>" +
                            "  <tbody>";
                maintable += tempTableRow;

            ArrayList<Activity> getactivity = IDaoImpl.getInstance().getDefualtActivity();  //return array of Defualt activitys

                /**looping  all defualt  activities  and adding them to the body*/
                tempTableRow="";
                int i=1;
                String te="";
                for (Activity a : getactivity) {
                        tempTableRow+= "<tr id='row"+i+"'>";

                        tempTableRow += "<form class='form-horizontal'  method='post' action='http://10.0.2.2:8080/GymApp_war_exploded/mygym/admin/addactivity'>";
//                    tempTableRow += "<form class='form-horizontal'  method='post' action='http://localhost:8080/GymApp_war_exploded/mygym/admin/addactivity'>";
                        tempTableRow +=  "<td><input name='activityName' id=activityName value="+ a.getName() +" class='form-control border-0' readonly>";
                        tempTableRow +=  "<input type='number' name='activityId' hidden id=activityId value='"+a.getId()+"'/> </td>";
                        tempTableRow += "<td><input id=activitySet name='activitySet' value="+ a.getNumOfSets()+" class='form-control border-0' readonly></td>";
                        tempTableRow +=  "<td><input id=activityRepeats  name='activityRepeats' value="+ a.getNumOfReps()+" class='form-control border-0' readonly></td>";
                        tempTableRow +=  "<td><input id=activityDur  name='activityDur'  value="+ a.getDuration()+" class='form-control border-0' readonly></td>";
                        tempTableRow += "<td><input type='submit' value='Add Activity'>";
                                i++;
                        tempTableRow += "</form></td> </tr>";
                }
                maintable+=tempTableRow;
                maintable += "</tbody> " + "</table>";

                httpSession.setAttribute("defaultActivityTable", maintable);
                httpSession.setAttribute("defualtCreated", "yes");  //Defualt activitys loaded
                httpSession.setMaxInactiveInterval(60*60*2);

                rd = request.getRequestDispatcher("/defualtactivity.jsp");
                rd.forward(request, response);

            } else { // user dont exist
                  httpSession.setAttribute("defualtCreated", "no");
                  rd = request.getRequestDispatcher("/error.jsp");
                  rd.forward(request, response);
               }

        } else {
                System.out.println("session editActivityTable is NOT NULL , its :" + httpSession.getAttribute("defaultActivityTable"));
                rd = request.getRequestDispatcher("/defualtactivity.jsp");
                rd.forward(request, response);
                }
      }
        catch (Exception e ){
        System.out.println("error in edit...");
        rd = request.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
    }

    }


    /***
     * edit Function will display the user Activities  and upaded them
     * @param request
     * @param response
     * @throws IOException
     */
    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        httpSession=request.getSession(true);

      try {
             String usermail = (String) httpSession.getAttribute("email");
             String maintable = "";
             String temptable;
             if (usermail != null)
             {  //user exist..
                System.out.println("email is " + usermail.toString());
                User temp = IDaoImpl.getInstance().getUserByEmail(usermail);
                temptable = "   <strong>My Activities </strong> " +
                        "                  <table id='editable' class='table table-bordered table-sm'>" +
                        "                      <thead class=thead-dark>" +
                        "                      <tr >" +
                        "                          <th> Excricise name  </th>" +
                        "                          <th> num sets   </th>" +
                        "                          <th> repeats   </th>" +
                        "                          <th> Duration   </th>" +
                        "                          <th>   </th>" +
                        "                      </tr>" +
                        "                      </thead>" +
                        "                      <tbody> ";
                maintable += temptable;

                ///start
                ArrayList<UserActivity> userActivitieesArray = IDaoImpl.getInstance().getUserActivities(temp);  //return array of activitys of user
                ArrayList<Activity> activitesArray = new ArrayList<Activity>();
                Activity activity = null;
                int j = 0;

                System.out.println("printing the activity per user ");
                for (UserActivity tempUserAactivity : userActivitieesArray) {
                    //part1 get the activity id
                    long idact = tempUserAactivity.getActivityId();

                    //part2 find the activity by id and add to activity list
                    activity = IDaoImpl.getInstance().getActivity(idact);
                    if (activity != null) {
                        activitesArray.add(activity);
                        System.out.println("Activity " + activity.toString() + " inserted to userActivities List...");
                    } else System.out.println("error");

                }

                System.out.println("printing all activities for user:  " + temp.toString());
                temptable = "";

                if(activitesArray.size() == 0 ){
                    httpSession.setAttribute("noOfUserActivity", activitesArray.size());

                }

                int i = 1;
                for (Activity a : activitesArray) {
                    System.out.println(a.toString());
                    temptable += "<tr id='row" + i + "'>";

                    temptable += "<td><input name='activityName" + i + "' id=activityName" + i + " value=" + a.getName() + " class='form-control border-0' readonly>";
                    temptable += "<input type='number' name='activityId" + i + "' hidden id=activityId" + i + " value='" + a.getId() + "'/> </td>";
                    temptable += "<td><input id=activitySet" + i + " name='activitySet" + i + "' value=" + a.getNumOfSets() + " class='form-control border-0' readonly></td>";
                    temptable += "<td><input id=activityRepeats" + i + "  name='activityRepeats" + i + "' value=" + a.getNumOfReps() + " class='form-control border-0' readonly></td>";
                    temptable += "<td><input id=activityDur" + i + "  name='activityDur" + i + "'  value=" + a.getDuration() + " class='form-control border-0' readonly></td>";
                    temptable += "<td> <input type='button' id='edit_button" + i + "' value=Edit class='edit' onclick='edit(" + i + ")'>";
                    temptable += "  <input type='button' id='delete_button" + i + "' value=Delete class='delete' onclick='delete_row(" + i + ")'>";
                    temptable += "</td> </tr>";
                    i++;

                }
                maintable += temptable;

                maintable += "</tbody> " + "</table>";

                httpSession.setAttribute("editActivityTable", maintable);

        } else {

            System.out.println("user not exist ... ");
        }

    }
    catch (Exception e) {
            System.out.println("error in edit...");
            System.out.println(e.toString());
        }
        finally {
            try {
                rd = request.getRequestDispatcher("/edit.jsp");
                rd.forward(request, response);
            } catch (ServletException | IOException e ) {
                System.out.println(e);
                e.printStackTrace();
            }
        }

    }


    /***
     *   This Function called by "Edit Activity" ,
     *   The function is update existing activitys after modification that done by the user
     *   if the activity is defulat activity then we  need to the delete the basic activity fron user and create new
     *   if its not defualt , just update
     *
     * @param request request to update content of activity fron edit.jsp
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void updateAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    try{
        response.setContentType("test/html");
        PrintWriter out = response.getWriter();
        httpSession = request.getSession(true);


        String tempname = request.getParameter("activityName");
        int sets = Integer.parseInt(request.getParameter("activitysets"));
        int repeat = Integer.parseInt(request.getParameter("activityrepeats"));
        long tempId = Long.parseLong(request.getParameter("activityid"));
        double dura = Double.parseDouble(request.getParameter("activitydur"));


        /** validation  */
        String validMsg = "";
        boolean valid=true;
        String result="";
        String newID="";
        if(tempname.length() <=0 ||  tempname.length()>20 ) {
            validMsg+= "\n Error ! inValid activity Name, please enter string only or name leangth is bigger than 20 \n";;
            valid=false;
        }


        if(sets <0 || sets>=9 ){
            validMsg+="\n Error ! inValid activity Set, please enter positive numbers only and the max is 8 sets \n" ;
            valid=false;
        }

        if(repeat <0 || repeat>=32 )
        {  validMsg+="\n Error ! inValid activity Repeats, please enter positive numbers only\n \n";
            valid=false;

        }


        if(valid == false ){
            result = "<p style='color:red;'>" + validMsg + " .</p> <br>";
            request.setAttribute("validForm", result);
            response.sendRedirect("/edit.jsp");
            return;
        }

        Activity activityfromSrv= IDaoImpl.getInstance().getActivity(tempId);


        httpSession.setAttribute("updateMsg","Updating ... ");

        String email= (String) httpSession.getAttribute("email");
        User tempUser = IDaoImpl.getInstance().getUserByEmail(email);

        if(activityfromSrv ==null || tempname==null){
            out.print("error activity or username dont exist");
            System.out.println("error activity dont exist");

        }else {
            Activity tempActivityToUpdate;
            boolean isNewActivity = false;
            if (activityfromSrv.getDefaultActivity() == true) {
                /**          create new**/
                isNewActivity = true; // its defualt activity so we need to  create new Activity!
                tempActivityToUpdate = new Activity();
                tempActivityToUpdate.setId(DB_ID);

            } else {
                /**its NOT defualt activity , so we continue with this activity id , */
                isNewActivity = false;
                tempActivityToUpdate = new Activity();
                tempActivityToUpdate.setId(activityfromSrv.getId());

            }

            tempActivityToUpdate.setName(tempname);
            tempActivityToUpdate.setNumOfReps(repeat);
            tempActivityToUpdate.setNumOfSets(sets);
            tempActivityToUpdate.setDuration(dura);
            tempActivityToUpdate.setMuscleGroup(activityfromSrv.getMuscleGroup());
            tempActivityToUpdate.setType(activityfromSrv.getType());

            System.out.println("old activity: " + activityfromSrv.toString() + " \n new activity: " + tempActivityToUpdate.toString());

            if (!isNewActivity) { //isNewActivity == false
                //its mean  ,its exist in the db and  we need to update the content
                IDaoImpl.getInstance().updateActivity(tempActivityToUpdate);

                /**  this is the update of the new id of the new Activity */
                out.print(tempActivityToUpdate.getId());
                httpSession.setAttribute("updateMsg","<br><p style='color:green;'> Activity: <br>'" + tempActivityToUpdate.toString() + "'<br> was UPDTATED In User Activity </p> <br>");
                System.out.println("the Activity was UPDTATED..");
            } else { //its not existing in the db right now, And we need to delete the old and save the New


                // delete the OLD Activity from the user
                if (IDaoImpl.getInstance().deleteUserActivity(tempUser, activityfromSrv)) {
                    //Save the new Activity
                    tempActivityToUpdate=  IDaoImpl.getInstance().saveActivityWithID(tempActivityToUpdate);
                    long newId=tempActivityToUpdate.getId();
                    IDaoImpl.getInstance().saveUserActivity(tempUser.getUserId(), tempActivityToUpdate.getId()); //save the updateed Activity in USERActivity
                    System.out.println("old Activiry was deleted , and new Activity was saved..");
                    httpSession.setAttribute("updateMsg","<br><p style='color:green;'>  Activiry: <br>'" + tempActivityToUpdate.toString() + "'<br> was UPDTATED In User Activity </p> <br>");
                    out.print(tempActivityToUpdate.getId());
                    rd = request.getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);

                }

                else{
                    request.setAttribute("errorMsg","Error during update Activity "+ activityfromSrv.toString() + "please try again");
                    rd = request.getRequestDispatcher("/error.jsp");
                    rd.forward(request, response);
                }
            }

            }
    }catch (Exception e  ){
        request.setAttribute("errorMsg","General Error during update Activity ");
        rd = request.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
    }
    }



    /***
     * This function will delete Activity  from user Activitys .
     * @param request
     * @param response
     * @throws IOException
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("delete activity ... ");
        String msg;
        try {

            httpSession = request.getSession(true);
            long idToDelete = Long.parseLong(request.getParameter("activityId"));
            String usermail = (String) httpSession.getAttribute("email");

            User tempuser=IDaoImpl.getInstance().getUserByEmail(usermail);
            if (tempuser != null) {  //user exist..
                Activity activity = IDaoImpl.getInstance().getActivity(idToDelete);
                if(activity!=null){
                  boolean result=false;
                  result=  IDaoImpl.getInstance().deleteUserActivity(tempuser,activity);
                  IDaoImpl.getInstance().deleteActivity(activity);

                  if(result){  // return true if cativity was deleted
                      msg="delete sucssfully";
                      httpSession.setAttribute("updateMsg", msg);

                  }else{
                      msg="Error , delete activity  failed ";
                      httpSession.setAttribute("updateMsg", msg);
                  }
                }
            }
        }catch (Exception e ){
          System.out.println(e.toString());
        }
        finally {
           try{
                 rd = request.getRequestDispatcher("/home.jsp");
                 rd.forward(request, response);
           }
               catch (Exception e )
               {
                   System.out.println(e.toString());
               }
        }
    }


    /***
     * the statisctic function create info, and statistics about the  user
     * and will display graph
     * @param request
     * @param response
     * @throws IOException
     */
    public void statistics(HttpServletRequest request, HttpServletResponse response) throws IOException{
        httpSession = request.getSession(true);

        int tNumSets=0;
        int tNumRep=0;
        int tNumActivities;
        String caloriesGraph="";
        String repeatsGraph="";
        try{
            User user1 = IDaoImpl.getInstance().getUserByEmail((String) httpSession.getAttribute("email"));
            if(user1 != null){
                String statisticsMSG= "<h5><br>User Name : " + user1.getUsername() + "<br> Weight: " + user1.getWeight() + "<br>" +
                        " BMI : "+ 24.2 + "<br> Protein recommendation per day : "+ user1.getWeight()*1.8 + "(gram) <br> </h5>";

                ArrayList<UserActivity> userActivities= IDaoImpl.getInstance().getUserActivities(user1);
                Activity tempActivity;
                ArrayList<Activity> activitesArray = new ArrayList<Activity>();
                for (UserActivity tempUserAactivity : userActivities) {
                    //part1 finding the id

                    long idact = tempUserAactivity.getActivityId();
                    tempActivity = IDaoImpl.getInstance().getActivity(idact);
                    if (tempActivity != null) {
                        activitesArray.add(tempActivity);
                    } else System.out.println("erorr");
                }


                /**
                 * calorie, and NumOfRepeats  grapghs will create dynamcly , only  the data about  user info gett from here
                 */
                if(activitesArray.size() != 0){
                for(int i=0;i<activitesArray.size();i++){
                   tNumRep += activitesArray.get(i).getNumOfReps();
                   tNumSets += activitesArray.get(i).getNumOfSets();
                    if((i+1) != activitesArray.size()){
                        if(activitesArray.get(i).getDuration()!=0 ){

                            caloriesGraph+= "{y: "+ activitesArray.get(i).getDuration()*2 + ", indexLabel: '" + activitesArray.get(i).getName()+"'},";
                        }else{
                            caloriesGraph+= "{y: "+ 10*2 + ", indexLabel: '" + activitesArray.get(i).getName()+"'},";
                        }
//Example= { x: 1, y: 13, label: "activityname "},
                        repeatsGraph+=  "{x: "+ (i+1) + ", y: " + activitesArray.get(i).getNumOfReps()  + ", label:'" + activitesArray.get(i).getName() + "'}, ";
                    }else
                        {
                            if(activitesArray.get(i).getDuration()!=0){
                                caloriesGraph += "{y: "+ activitesArray.get(i).getDuration()*2 + ", indexLabel: '" + activitesArray.get(i).getName()+"'}";
                            }else
                                caloriesGraph += "{y: "+ 10*2 + ", indexLabel: '" + activitesArray.get(i).getName()+"'}";

                            repeatsGraph +=  "{x: "+ (i+1) + ", y: " + activitesArray.get(i).getNumOfReps()+ ", label:'" + activitesArray.get(i).getName() + "'} ";
                        }

                }
                }

                tNumActivities=activitesArray.size();
                statisticsMSG+= " <br><h5>Total Num Of Repeats : " + tNumRep +"<br>";
                statisticsMSG+= " Total Num Of Set :" + tNumSets+" <br> ";
                statisticsMSG+= " Total Num Of Activitis : " + tNumActivities+ " <br> </h5> ";

                request.setAttribute("statisticsMsg", statisticsMSG);
                request.setAttribute("caloriesMsg", caloriesGraph);
                request.setAttribute("repeatsMsg", repeatsGraph);

                System.out.println("rep: "+ repeatsGraph);
                System.out.println("cal: "+ caloriesGraph);

            }

        }
        catch (Exception e ){
            System.out.println(e.getStackTrace());
        }

        finally {
            try{

                rd = request.getRequestDispatcher("/statistics.jsp");
                rd.forward(request, response);
            }
            catch (Exception e )
            {
                System.out.println(e.toString());
            }
        }
    }

}

