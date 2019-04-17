package il.ac.hit.mygym.controller;
import il.ac.hit.mygym.Exception.HibernameExceptionMyGym;
import il.ac.hit.mygym.Exception.UserNotFoundException;
import il.ac.hit.mygym.Model.User;
import il.ac.hit.mygym.Dao.IDaoImpl;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**User Controller will  take care the  requests that releted to user login,register, logout  Home,and  errors jsp  */
public class UserController extends HttpServlet {
    private RequestDispatcher rd=null;

    public void about(HttpServletRequest request, HttpServletResponse response) {
        try {

                rd=request.getRequestDispatcher("/about.jsp");
                rd.forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void help(HttpServletRequest request, HttpServletResponse response) {
        try {

            rd=request.getRequestDispatcher("/help.jsp");
            rd.forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * homepage of the user
     * will check if the user is login and if so, it will show him the homePage
     * if not - he will foward to login page
     * @param request req
     * @param response response
     */
    public void home(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession(true);
            System.out.println("In HOME- FUNC");
           String userSession= (String) session.getAttribute("email");
            if(userSession == null){ // user dont exist in the session
               rd=request.getRequestDispatcher("/login.jsp");
               rd.forward(request,response);
           }else{
               rd=request.getRequestDispatcher("/home.jsp");
               rd.forward(request,response);
           }

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /***
     * Login funtion check if user Exist and if so , he will autinticate and if premission granted he will be foward to home and user will save in session
     * if user dont exist -> foward register page
     * if access denied   -> foward back to login page
     *
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request, HttpServletResponse response)   {
        HttpSession session = request.getSession(true);
        System.out.println("In Login- IN POST");
        try {
            String registerMe;
            registerMe = request.getParameter("registerbt");
                if(registerMe!=null ){
                    if(registerMe.equals("registerme")){
                        rd = request.getRequestDispatcher("/register.jsp");
                        rd.forward(request, response);
                    }
                }
            else
                {
                User userToLogin=new User();
                userToLogin.setUsername(request.getParameter("username"));
                userToLogin.setPassword(request.getParameter("password"));
                userToLogin.setEmail(request.getParameter("email"));

                /** the result from login will tell us the status of the user who want to login */
                String result= IDaoImpl.getInstance().login(userToLogin);

                String msg="";

                 if (result.equals("authinticated")) {  //authinticated..
                     if (IDaoImpl.getInstance().getUserByEmail(userToLogin.getEmail()) != null) {

                        session.setAttribute("username", userToLogin.getUsername());
                        session.setAttribute("password", userToLogin.getPassword());
                        session.setAttribute("email", userToLogin.getEmail());
                        session.setMaxInactiveInterval(60*60*3); // valid for 3 hours

                        Cookie username=new Cookie("usernmae",userToLogin.getUsername());
                        username.setMaxAge(60*60*24*7); // 1 week
                        response.addCookie(username);

                        msg= "user "+userToLogin.getUsername()+" is now login :) ";
                        request.setAttribute("loginMsg",msg);
                        rd = request.getRequestDispatcher("/home.jsp");
                        rd.forward(request, response);
                    }
                 } else if (result.equals("unregister")) {
                        /** LOGIN FAILED ,user is not exist in db*/
                         msg= "user is not registered , you been fowared to register page..";
                         request.setAttribute("loginMsg",msg);
                         rd = request.getRequestDispatcher("/register.jsp");
                         rd.forward(request, response);
                } else if (result.equals("unauthnticate")) {
                         /**LOGIN FAILED ,unauthnticate  user and password*/
                         msg= "LOGIN FAILED ,unauthnticate  user and password";
                        request.setAttribute("loginMsg",msg);
                        rd = request.getRequestDispatcher("/login.jsp");
                        rd.forward(request, response);

                } else {
                     msg= "LOGIN FAILED ,General Error";
                     request.setAttribute("errorMsg",msg);
                     rd = request.getRequestDispatcher("/error.jsp");
                     rd.forward(request, response);
                    return;
                }
            }
        }
        catch (ServletException | IOException | HibernateException | UserNotFoundException | HibernameExceptionMyGym e  ){
            try {
                request.setAttribute("errorMsg",e.getMessage());
                rd = request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
            } catch (ServletException e1) {
                request.setAttribute("errorMsg",e1.getMessage());
                e1.printStackTrace();
            } catch (IOException e1) {

                e1.printStackTrace();
            }
            }
    }


    /***
     *  Register Function will save user in the db
     *  and after that foward to login page
     * @param request
     * @param response
     */
    public void register(HttpServletRequest request, HttpServletResponse response) {

        String msg="";
        HttpSession session = request.getSession(true);
        try {

                String username2 = request.getParameter("username");
                String password2 = request.getParameter("password");
                String email2 = request.getParameter("email");
                double weight = Double.parseDouble(request.getParameter("weight"));
                double height = Double.parseDouble(request.getParameter("height"));

                User test=new User();
                test.setUsername(username2);
                test.setPassword(password2);
                test.setEmail(email2);
                test.setWeight(weight);
                test.setHeight(height);


                if (IDaoImpl.getInstance().isUserExist(test)) {
                    /**User is Alreaady EXIST ,  Need To  Login **/
                    msg= "User: '"+test.getUsername()+"' is Alreaady EXIST ,  Please Login";
                    request.setAttribute("loginMsg",msg);
                    rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                } else {
                    /**User is not exist ...  **/
                    Boolean res = IDaoImpl.getInstance().Register(test);
                    if (res) {
                        msg= "User: '"+test.getUsername()+"' register successfully ";
                        request.setAttribute("loginMsg",msg);
                        rd = request.getRequestDispatcher("/login.jsp");
                        rd.forward(request, response);
                    }
                }
        }
        catch (ServletException | IOException | HibernateException | HibernameExceptionMyGym | UserNotFoundException e  ){
            try {
                rd = request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }


    /***
     * Logout Function will  logout user and close  his session
     * And at the End he will forward to login page
     */
    public void logout(HttpServletRequest request, HttpServletResponse response)   {

        HttpSession session = request.getSession(false);
        System.out.println("In logout- IN POST");

        try {
            if(session.getAttribute("username")!= null){
                session.invalidate();
                System.out.println("user logout succsfully");
                rd= request.getRequestDispatcher("/login.jsp");
                rd.forward(request,response);
            }
        }


        catch (Exception e ){
            e.printStackTrace();
        }

    }

    /***
     * error function show the user the error page
     * @param request
     * @param response
     */
    public void error(HttpServletRequest request, HttpServletResponse response) {

        try {
        HttpSession session = request.getSession(true);
        System.out.println("Error ! ");

        String errorMsgToDisplay =  " Opps somthing went wrong   ";

        session.setAttribute("errorMsg", errorMsgToDisplay);
        rd=request.getRequestDispatcher("/error.jsp");

        rd.forward(request,response);
        } catch (ServletException | IOException  e) {
            e.printStackTrace();
        }
    }


}