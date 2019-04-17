package il.ac.hit.mygym.Model;


/***
 * This class repesent an UserActivity for MYGYM  .
 * this class is mapped to "ActivitiesPerUser" table in the db (Hibernate)
 * this table will containa User id and his own Activity
 *
 */

public class UserActivity {
    /**
     * id of the row
     */
    private long parId;
    /**
     * id of the activity
     */
    private long userId;
    /**
     * id of the activity
     */

    private String email;

    /**
     * id of the activity
     */
    private long activityId;


    /**
     *
     * @param userID  userID (key)
     * @param activityId activityId (key)
     */
    public UserActivity(long userID, long activityId) {
        this.userId = userID;
        this.activityId = activityId;
    }


    /**
     * defualt ctor of UserActivity , hibernate  requirement Ematy c'tot
     */
    public UserActivity() {
    }

    /***
     *
     * @return email of user
     */
    public String getEmail() {
        return email;
    }

    /***
     *
     * @param email set the useremail
     */
    public void setEmail(String email) {
        if(email!= null && email.length()>0)
            this.email = email;
    }

    /***
     *
     * @return row num
     */
    public long getParId() {
        return parId;
    }

    /***
     *
     * @param parId set the row num
     */
    public void setParId(long parId) {
        if(parId>0)
            this.parId = parId;
    }

    /**
     *
     * @return user Id
     */
    public long getUserId() {
        return userId;
    }

    /***
     *
     * @param userId  set  the user id
     */
    public void setUserId(long userId) {
        if(userId>0)
            this.userId = userId;
    }

    /**
     *
     * @return activity id
     */
    public long getActivityId() {
        return activityId;
    }

    /**
     *
     * @param activityId update the activity id
     */
    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }



    /***
     *
     * @return custome toString of UserActivity Class
     */
    @Override
    public String toString() {
        return "UserActivity{" +
                "Userid=" + userId +
                ", activityid='" + activityId +
                '}';
    }
}
