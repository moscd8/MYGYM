package il.ac.hit.mygym.Model;

import il.ac.hit.mygym.Dao.IDaoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * This calss is repesent an User  in  MYGYM  users  .
 * this class is mapped to "Usersdb" table in the db (Hibernate)
 *
 */
public class User {
    /**
     * id of the user
     */
    private long userId ;
    /**
     * username of the user
     */
    private  String username;
    /**
     * password of the user
     */
    private  String password;
    /**
     * email  of the user
     */
    private  String email;
    /**
     * weight  of the user in KG
     */
    private  double weight;
    /**
     * height  of the user in CM
     */
    private double height;
    /**
     * bmi   of the user , bmi =weight/ (height*height)
     */
    private  double bmi;
    /***
     * cache of user activities
     */
    private List<Activity> activities;

    /***
     * non defualt c'tor, it will use  the setters of the object in order to assign only valid data
     * @param email User Email , its user key
     * @param username User Name
     * @param password User Passsword
     * @param weight User weight
     * @param height User height
     */
    public User(String email, String username, String password, double weight, double height) {
         setEmail(email);
         setUsername(username);
         setPassword(password);
         setWeight(weight);
         setHeight(height);
        /**
         * //if height and weight are not valid , defualtb bmi is 22.
         */
        setBmi(22);
    }

    /**
     * defualt ctor of User , hibernate  requirement Ematy c'tot
     */
    public User() {

    }



    /***
     *
     * @return  return the Id of user
     */
    public long getUserId() {
        return userId;
    }


    /***
     *
     * @param userId update the Id of user
     */
    public void setUserId(long userId) {
         if(userId>=0)
            this.userId = userId;
    }


    /***
     *
     * @return return the username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username Set the username of user
     */
    public void setUsername(String username) {
        if(username!=null)
            this.username = username;
    }

    /***
     *
     * @return return the password of user
     */
    public String getPassword() {
        return password;
    }

    /***
     *
     * @param password set the password of user
     */
    public void setPassword(String password) {
        if(password != null )
            this.password = password;
    }


    /***
     *
     * @return get the weight of user
     */
    public double getWeight() {
        return weight;
    }


    /***
     *
     * @param weight set the weight of user
     */
    public void setWeight(double weight) {
        if(weight>=0)
            this.weight = weight;
    }


    /****
     *
     * @return get the height of user
     */
    public double getHeight() {
        return height;
    }

    /***
     *
     *  @param height set the height of user
     */
    public void setHeight(double height) {
        if(height>=0 )
            this.height = height;
    }


    /***
     *
     * @return get the bmi of user
     */
    public double getBmi() {
        return bmi;
    }


    /***
     *
     * @param bmi set the bmi of user
     */
    public void setBmi(double bmi) {
        if(this.height != 0 && this.weight!=0){
            this.bmi= (this.weight / ((this.height*this.height)/100) );
        }else
            this.bmi=1;

    }



    /***
     *
     * @return return email of user
     */
    public String getEmail() {
        return email;
    }



    /***
     *
     * @param email set email of user
     */
    public void setEmail(String email) {
        if(email!=null){
            this.email = email;
        }
    }

    /***
     * todo
     * @return return List of activities of user from the cache
     */
    public List<Activity> getActivites() {
        return activities;
    }

    /***
     *   todo
     * @param activites  update  activities of user
     */
    public void setActivites(List<Activity> activites) {
        this.activities = activites;
    }


    /***
     *  todo
     * @param activity add single activitya to chahe
     */
    public void addActivityToCache(Activity activity){
        if(this.activities==null){
            activities=new ArrayList<Activity>();
        }else{this.activities.add(activity);}
        }


    /***
     *
     * @return custom toString of Activity Class
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username='" + username  +
                ", password='" + password  +
                ", weight=" + weight +
                ", height=" + height +
                ", bmi=" + bmi +
                ", Email=" + email +
                '}';
    }
}
