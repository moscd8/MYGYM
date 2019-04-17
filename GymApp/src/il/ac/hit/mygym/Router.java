package il.ac.hit.mygym;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

/**
 * The router is a serverlet that rout requests and foward them to the relvenet  controoler
 */
@WebServlet("/mygym/*")
public class Router extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** empty c'tor */
    public Router(){
        // TODO Auto-generated
        super();
    }

    /**
     * doPost will take care the doPost and doGet , and will route with the request by using reflection
     * its allow us use more controllers as we wand in the futer
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        try {

            /** get the full path of the request */
            StringBuffer sb = request.getRequestURL();

            StringTokenizer tokenizer = new StringTokenizer(sb.toString(), "/");
            /** count the  part of the request  */
            int numberOfTokens = tokenizer.countTokens();
            //  http://localhost:8080/GymApp_war_exploded/mygym/user/login
            /** searching the controller name  path and action **/
            tokenizer.nextToken();
            tokenizer.nextToken();
            tokenizer.nextToken();
            tokenizer.nextToken();
            String controller = tokenizer.nextToken(); /** geting the controller name (for now its Admin Or controller)*/
            String action = tokenizer.nextToken();     /** action : relvenet  function and page **/

            controller = controller.substring(0, 1).toUpperCase()+controller.substring(1)+"Controller";  // convert the controller as the same name of his class name
            System.out.println("controller is :" +controller + " Action is :"+ action );

            /** creating the required controoleer object and invoke his action function dynmacliy **/
            Class clazz = Class.forName("il.ac.hit.mygym.controller." + controller);
            Object ob = clazz.newInstance();
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            /** invoke the function */
            method.invoke(ob, request, response);

            System.out.println("after invoke");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mygym/user/error");
            dispatcher.forward(request, response);
        }
    }

    /***
     *  the do get will be foward to doPost FUnction
      * @param request  request
     * @param response   response
     * @throws ServletException
     * @throws IOException
     */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet.. ");
        doPost(request,response);
    }
}
