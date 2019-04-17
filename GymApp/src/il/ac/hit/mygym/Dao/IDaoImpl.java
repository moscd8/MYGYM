package il.ac.hit.mygym.Dao;

import il.ac.hit.mygym.Exception.*;
import il.ac.hit.mygym.Model.Activity;
import il.ac.hit.mygym.Model.User;
import il.ac.hit.mygym.Model.UserActivity;
import org.apache.log4j.*;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 * IDaoImpl implements the IDao , our interface for manage the MYGYM
 */
public class IDaoImpl implements IDao {
    /***
     * Singletone of the project
     */
    private static final IDaoImpl instance = null;

    /***
     * Log4j logger object
     */
    static Logger logger = Logger.getLogger(IDaoImpl.class.getName());

    /**
     * session factory
     */
    private  static SessionFactory factory = null;
    Session session = null;

    /***
     *  IDAo Implement c'tor
     */
    private IDaoImpl() {
        try {

            BasicConfigurator.configure();
            logger.addAppender(new FileAppender(new SimpleLayout(), "logs.txt"));
            }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *  this instance is function as Singleton Design Patterns
     * @return instance of  the singletone
     */
    public static IDaoImpl getInstance() {
        if (instance == null) {
            factory = new AnnotationConfiguration().configure().buildSessionFactory();

            return new IDaoImpl();
        }
        else
            return instance;
    }

    /**
     * working with the session
     * @return session
     */
    public Session openSession()
    {
        return factory.openSession();
    }



    /*****########   START
     /*****####################################################################################################
     //**  UserController Function
     /*****####################################################################################################
     ***/

    /***
     *  Login   function will check if user is Existing in the db & authentication .
     * @param loginUser   : user who want to login
     * @return 3 optional string code : unregister,authinticated ,authinticated;
     * unregister - user is Not exist int he db,  authinticated - user entered his own credential ,  authinticated- wrong password Or Email , Or username
     * @throws UserNotFoundException
     * @throws HibernameExceptionMyGym
     */
    public String login(User loginUser) throws UserNotFoundException, HibernameExceptionMyGym {
        boolean vaild=false,reg=false;
        String result="";
        logger.info("FUNC:login - user :"+loginUser.toString() + "is trying to login..");
        if(getUserByEmail(loginUser.getEmail()) == null  )  /** its mean he DOES NOT EXIST IN DB*/
        {
            logger.info("FUNC:login -user need to register first !");
            result= "unregister";
            return  result;
        }
        else
        {
            /**At this point user is exist in db and need to login */
            vaild= authnticate(loginUser); /**chcek  authentication with the DB*/
            if(vaild){ /** premisstion granted**/
                logger.info("FUNC:login -user statue change to online: " + loginUser.toString());
                result= "authnticate";
                logger.info("FUNC:login -user loging succsfuly");
                result="authinticated";
                return  result;
            }
            else if(vaild==false){  /***premisstion denaied */
                result= "unauthnticate";
                logger.info("FUNC:login -user is unauthnticated...");
                return result;
            }
        }
        return result;
    }


    /***
     * save user in the db
     * @param entity user to save
     * @throws HibernameExceptionMyGym
     */
    @Override
    public void saveUser(User entity) throws HibernameExceptionMyGym {
        Session session=null;
        Transaction tx=null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();

        }
        catch (HibernateException exception){
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();

            throw new HibernameExceptionMyGym("Error, failed to save user");

        }
        finally {
            if(session != null)
                session.close();
        }
    }


    //V ,  //VV_LOGIN

    /***
     * getUserByEmail is the funtion  to get the user from the db , the email is functon as the key for the user
     * @param email1  email Address
     * @return
     * @throws UserNotFoundException
     * @throws HibernateException
     */
    public User getUserByEmail(String email1) throws UserNotFoundException, HibernateException {     //WORKING
       Session session=null;
        Transaction tx=null;
        List users=null;
        User tempUser=null;
        try{
            session = this.openSession();
            session.beginTransaction();

            Query query1 = session.createQuery("from User where email=:emailVar" );
            query1.setParameter("emailVar",email1);
             users = query1.list();
             tempUser= (User) query1.uniqueResult();
            logger.info("FUNC:getUserByEmail -The Number of Users in email address= " + email1 + "is:" + users.size()  );
            session.getTransaction().commit();
            if(users.size()==0){  //if the size is 0 ,  no row was effected
                return null;
            }
            return tempUser;   //return Unique User
        }catch (HibernateException exception){
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();

            throw new UserNotFoundException(" error during find user ,please try again \n "+exception.getMessage(), exception);

        }
        finally {
            if(session != null)
                session.close();

        }

    }



    /***
     *
     * @param checkUser   user to who want the authnticate
     * @returntrue or false , depand if there was match with his account
     * @throws UserNotFoundException
     * @throws HibernameExceptionMyGym
     */
    public boolean authnticate(User checkUser ) throws UserNotFoundException ,HibernameExceptionMyGym{
        boolean valid=false;
        User userFromDb=null;
        Session session=null;
        Transaction tx=null;
        try{
            session = this.openSession();
            session.beginTransaction();
            userFromDb= getUserByEmail(checkUser.getEmail());

            if(userFromDb == null){ /**  user not exist .. he must register first **/
                valid=false;
                return valid;
            }

            /***
             * //check match with username , email, password from checkUser with the actual user from the DB
             */
            if((userFromDb.getUsername().toLowerCase().equals(checkUser.getUsername().toLowerCase()))
                    && userFromDb.getPassword().equals(checkUser.getPassword())
                    && userFromDb.getEmail().toLowerCase().equals(checkUser.getEmail().toLowerCase()))
            {
                System.out.println("user name and passwotd match ! ");
                valid = true;
                return  valid;
            }

            return valid;

        }catch (HibernateException exception){
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();


            throw new HibernameExceptionMyGym(" error during authnticate   please try again, \n "+exception.getMessage(), exception);

        }

        finally {
           if(session != null)
                session.close();
        }
    }


    /*** To Keep **/

    /***
     *
     * @param newUser  user to Register
     * @return boolean restult if the registertion finish successfully
     * @throws HibernameExceptionMyGym
     * @throws UserNotFoundException
     */
    public boolean  Register(User newUser) throws HibernameExceptionMyGym, UserNotFoundException {
        if (newUser.getUsername().length() > 0 && newUser.getPassword().length() > 0 && !newUser.getUsername().isEmpty()) {
            User temp = getUserByEmail(newUser.getEmail());
            if (temp != null) { /**  User is Already Exist He need to  login */
                return false;
            } else {
                saveUser(newUser);
                return true; /** User  Registred Succsfully */
            }
        }  return false;
    }


    /***
     * checking if user already exist in the db
     * @param user user to search
     * @return  boolean  result
     * @throws UserNotFoundException
     */
    public boolean  isUserExist(User user) throws UserNotFoundException{
        boolean exist=false;
        Session session=null;
        Transaction tx=null;
        try{
            session = this.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from User where username=:name" );
            query.setParameter("name",user.getUsername());
            List users = query.list();
            User temp= (User) query.uniqueResult();
            session.getTransaction().commit();
            if(users.size() < 1 ){
                exist=false;
                return exist;

            }else{
                if(temp.getUsername().equals(user.getUsername()) ){ /** check match  */
                    exist= true;
                    return exist;
                }
            }
        }catch (HibernateException exception){
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();
            throw new UserNotFoundException(" error ,please try again \n "+exception.getMessage(), exception);

        }
        finally {
            if(session != null)
                session.close();

            return exist;
        }
    }


    /***
     * deleteuser from the db
     * @param entity user to delete
     * @throws HibernameExceptionMyGym
     */
    @Override
    public void deleteUser(User entity) throws HibernameExceptionMyGym{
        Session session=null;
        Transaction tx=null;

        try {
            session = this.openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();

        }catch (HibernateException exception){

            tx = session.getTransaction();
            if(tx.isActive()) {
                tx.rollback();
            }


            throw new HibernameExceptionMyGym(" error during delete user failed , \n "+exception.getMessage(), exception);

        }  finally {

            if(session != null)
                session.close();
        }


    }


    /***
     * get user with his id
     * @param id for user
     * @return User from the db
     * @throws UserNotFoundException
     */
    @Override
    public User getUser(long id) throws UserNotFoundException {
        Session session=null;
        Transaction tx=null;
        User entity=null;

        try {
            session = this.openSession();
            session.beginTransaction();
            entity = (User) session.get(User.class, id);
            session.getTransaction().commit();

        }catch (HibernateException exception){

            tx = session.getTransaction();
            if(tx.isActive()) {
                tx.rollback();
            }

            throw  new UserNotFoundException(" error , User Not Found , \n "+exception.getMessage(), exception);

        }  finally {

            if(session != null)
                session.close();
        }

        return  entity;
    }


     /**  ########   END              ############################################################################# */
     /*****########################################################################################################
     //**  UserController Function
     /*****#######################################################################################################**/



    /*****########   START         #############################################################################
     /*****####################################################################################################
     //**  AdminController Function
     /*****####################################################################################################
     ***/



    /***
     *  getActivity Function  return  Activity by given unique id
     * @param id for Activity
     * @return Activity from the db
     * @throws ActivityNotFoundException
     */
    @Override
    public Activity getActivity(long id) throws ActivityNotFoundException{
        Activity entity=null;
        Session session=null;
        Transaction tx=null;
        try {
            session = this.openSession();
            session.beginTransaction();

            entity = (Activity) session.get(Activity.class,id);
            session.getTransaction().commit();

            if(entity!= null){ /** if not null the activity is valid and will return it */
                return entity;
            }
            else {
                /**the activity was not faound in the db  */
                return  null;
            }

        }
        catch ( HibernateException exception ){
            tx = session.getTransaction();
            if(tx.isActive()) {
                tx.rollback();
            }
            /** throw exception :getActivity */
            throw new ActivityNotFoundException(" Error please try again , \n "+exception.getMessage(), exception);
        }
        finally {
            if(session!=null)
                session.close();

        }
    }


    /**
     * getDefualtActivity Function search the default Activity and return them to user
     * the default  activity is the first 6's activities
     * @return  arraylist of Defualy Activities  (Basic Activities for Starter Users)
     * @throws HibernameExceptionMyGym
     */
    public ArrayList  getDefualtActivity()  throws  HibernameExceptionMyGym{
        ArrayList<Activity> activities=null;
        Session session=null;
        Transaction tx=null;
        try {
            session = this.openSession();
            session.beginTransaction();
            /**each activity has a  flag wheter the activity is defulat or not  **/
            Query query = session.createQuery("from Activity where defaultActivity=true");
            activities = (ArrayList) query.list();
            return activities;
        } catch (HibernateException  exception) {
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();
            throw new HibernameExceptionMyGym(" Error during get activity please try again , \n "+exception.getMessage(), exception);
        }
        finally {
            if(session != null)
                session.close();

        }

    }


    /***
     *  getUserActivities Function  create query from the db and return list of userActivity
     * @param user
     * @return
     */

    /***
     *  getUserActivities Function  create query from the db and return list of userActivity
     * @param user  user to search for him his own activitys
     * @return Arraylist of user Activities
     * @throws UserActivityException
     */
    @Override
    public ArrayList  getUserActivities(User user) throws UserActivityException{
        Activity entity=null;
        Session session=null;
        Transaction tx=null;
        try {
            session = this.openSession();
            session.beginTransaction();

            Query query = session.createQuery("from UserActivity where userId = :id ");
            query.setParameter("id",user.getUserId());
            ArrayList<UserActivity>  arrayList= (ArrayList) query.list();
            session.getTransaction().commit();
            if(arrayList!= null){
                return arrayList;
            }
            else {
                /**no activities for the user */
                return  null;
            }
        }
        catch (HibernateException exception){
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();
            throw new UserActivityException(" error during save user failed , \n "+exception.getMessage(), exception);
        }
        finally {
            if(session != null)
                session.close();

        }



    }


    /***
     * saveUserActivity Function  save  userActivity in  "usetActivity" table and return BOOLEAN  result of saving
     * @param usernameID
     * @param id
     * @return
     */
    public boolean saveUserActivity(long usernameID , long id  ){
        boolean result=false;
        Session session=null;
        Transaction tx=null;
        try {
            session = this.openSession();
            session.beginTransaction();
            session.save(new UserActivity(usernameID, id));

            result=true;
            session.getTransaction().commit();
        }
        catch (HibernateException exception){
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();
            /** throw  UserActivityException */
            throw new UserActivityException(" error during save user activity  ,please try again  \n "+exception.getMessage(), exception);

        }
        finally {
            if(session != null)
                session.close();

            return result;
        }

    }



    /***
     * deleteUserActivity Function delete  UserActivity row from UserActivity table by using query
     * return  boolean result at the end of  delete
     * @param user  user that want to delete  activity
     * @param activity activity to delete
     * @return boolean result at the end of  delete
     * @throws UserActivityException
     */
    @Override
    public boolean deleteUserActivity(User user,Activity activity) throws UserActivityException{
        boolean deleted=false;
        Session session=null;
        Transaction tx=null;
        try{
            session = this.openSession();
            session.beginTransaction();
            /** the query wll search row fron activityPerUser  that equal to given userID and ActivityID*/
            Query query1 = session.createQuery("DELETE from UserActivity where userId=:var1 and activityId=:var2" );
            query1.setParameter("var1",user.getUserId());
            query1.setParameter("var2",activity.getId());
            int result = query1.executeUpdate();
            /**result is The number of entities(row) updated or deleted */
            session.getTransaction().commit();
            if(result ==1 ){
                /**delete query was succsfuyll  */
                deleted=true;
            }else{
                /**delete query failed  */
                deleted=false;
            }

            return deleted;
        }
        catch ( HibernateException exception){
            /***Error during FUNC :deleteActivity  **/
            tx = session.getTransaction();
            if(tx.isActive()) {
                tx.rollback();
            }

            throw new UserActivityException(" Error during please try Again , \n "+exception.getMessage(), exception);
        }
        finally {
            if(session != null)
                session.close();

        }

    }


    /***
     * save activity with spacifg id , and not  auto generated
     * @param entity activity
     * @return the saved activity
     */
    public Activity saveActivityWithID(Activity entity)  {
        boolean saveResult=false;
        Activity resultActivity=null ;
        Session session=null;
        Transaction tx=null;
        try {
            /** Inorder  to avoid auto assignment gernerted id from the hibernate */
            entity.setId(-1);
            session = this.openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            /** copy the saved activity */
            resultActivity= entity;
            if(resultActivity!=null && resultActivity.getId()!= -1){
                saveResult=true;
            }

        } catch (HibernateException exception){
            /**  error... Failed to save .. */
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();


            throw new HibernameExceptionMyGym(" error during save user failed , \n "+exception.getMessage(), exception);

        }

        finally {
            System.out.println("result status is : "+ saveResult);
            if(session != null)
                session.close();

            return  resultActivity;
        }
    }



    /***
     *  Normal save activty overRide the funtion from the interface
     * @param entity activity to save
     * @throws HibernameExceptionMyGym
     */
    @Override
    public void saveActivity(Activity entity) throws HibernameExceptionMyGym {
        boolean saveResult=false;
        Session session=null;
        Transaction tx=null;
        try {

            session = this.openSession();
            session.beginTransaction();

            session.save(entity);
            session.getTransaction().commit();
            saveResult=true;

        }catch (HibernateException exception){
            /**  error... Failed to save .. */
            tx = session.getTransaction();
            if(tx.isActive()) {
                tx.rollback();
            }
            throw new HibernameExceptionMyGym(" error during save activity please try again , \n "+exception.getMessage(), exception);

        }
        finally {
            if(session != null)
                session.close();
        }

    }


    /**
     * saveUserActivity save in the db  , (NOT return nothing )
     * @param user  user that want to save  activity
     * @param activity activity
     * @throws UserActivityException
     */
    @Override
    public void saveUserActivity (User user, Activity activity) throws UserActivityException {
        Session session=null;
        Transaction tx=null;
        try{
            session=this.openSession();
            session.beginTransaction();
            session.save(new UserActivity(user.getUserId(), activity.getId()));
            session.getTransaction().commit();
        }catch (HibernateException exception){
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();

            /**error during save user activity */
            throw new UserActivityException(" Error ,please try again \n "+exception.getMessage(), exception);
        }

        finally {
            if(session != null)
                session.close();
        }

    }



    /**
     * update content of Activity
     * @param activity
     * @throws HibernameExceptionMyGym
     */
    public void updateActivity (Activity activity) throws HibernameExceptionMyGym {
        Session session=null;
        Transaction tx=null;
        try {
            session = this.openSession();
            session.beginTransaction();
            session.update(activity);
            session.getTransaction().commit();
            /** update activity:  finish sessufully  */
        }
        catch (HibernateException exception){
            /** Error during update activity */
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();
            throw new HibernameExceptionMyGym(" Error ,please try again, \n "+exception.getMessage(), exception);
        }

        finally {
            if(session != null){
                session.close();
            }
        }
    }


    /***
     * delete activity from the db
     * @param entity activity to delete
     * @throws HibernameExceptionMyGym
     */
    @Override
    public void deleteActivity(Activity entity) throws HibernameExceptionMyGym{
        Session session=null;
        Transaction tx=null;
        try {
            session = this.openSession();
            session.beginTransaction();
            Activity temp=IDaoImpl.getInstance().getActivity(entity.getId());
            if( temp!= null)
            {
                /** first to check if the activity is defualt */
                boolean res= IDaoImpl.getInstance().checkIfActivityIsDefaultActivitiy(entity.getId());
                if (res) {
                    System.out.println("activity " + entity.toString() + " cannot be deleted");

                } else {

                    session.delete(entity);
                    session.getTransaction().commit();
                    if (IDaoImpl.getInstance().getActivity(entity.getId()) == null) {
                        System.out.println("Deleted finished");
                    } else {
                        System.out.println("Deleted failed");
                    }
                }
            }
            else{
                System.out.println("the activity dont exist in DB ");
            }
        }
        catch (HibernateException | ActivityNotFoundException exception){
            System.out.println("Error during FUNC :deleteActivity ");
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();

            throw new HibernameExceptionMyGym(" Error please try again  , \n "+exception.getMessage(), exception);

        }
        finally {
            session.close();
        }
    }



    /***
     * check if given id is defualt id
     * @param activityTofind
     * @return booleat result
     * @throws HibernameExceptionMyGym
     */
    public boolean  checkIfActivityIsDefaultActivitiy(long activityTofind) throws HibernameExceptionMyGym{

        boolean exist=false;
        Session session=null;
        Transaction tx=null;
        Activity activityToCheck=null;

        try {

            session = this.openSession();
            session.beginTransaction();

            activityToCheck= getActivity(activityTofind);
            session.getTransaction().commit();

            if(activityToCheck!=null){
                if(activityToCheck.getDefaultActivity()==true)
                    exist=true;
            }else {
                exist=false;
            }

            return exist;
        } catch (HibernateException | ActivityNotFoundException exception) {
            tx = session.getTransaction();
            if(tx.isActive())
                tx.rollback();

            /**Error during get activity */
            throw new HibernameExceptionMyGym(" Error  please try again , \n "+exception.getMessage(), exception);
        }
        finally {
            if(session != null)
                session.close();

        }
    }





    /**  ########   END              ############################################################################# */
     /*****########################################################################################################
     //**  AdminController Function
     /*****#######################################################################################################**/




}