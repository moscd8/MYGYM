package il.ac.hit.mygym.Dao;

import il.ac.hit.mygym.Exception.*;
import il.ac.hit.mygym.Model.Activity;
import il.ac.hit.mygym.Model.User;

import java.util.ArrayList;
/***
 * The IDAO interface (Data Access Object ) for  the MYGYM
 *
 */
public interface IDao {

  /**
   *
   * @param entity user to save
   * @throws HibernameExceptionMyGym
   */
  public void saveUser(User entity) throws HibernameExceptionMyGym;

  /***
   *
   * @param entity activity to save
   * @throws HibernameExceptionMyGym
   */
  public void saveActivity(Activity entity) throws HibernameExceptionMyGym;

  /***
   *
   * @param entity user to delete
   * @throws HibernameExceptionMyGym
   */
  public void deleteUser(User entity) throws HibernameExceptionMyGym;

  /***
   *
   * @param entity activity to delete
   * @throws HibernameExceptionMyGym
   */
  public void deleteActivity(Activity entity) throws HibernameExceptionMyGym;

  /***
   *
   * @param id for user
   * @return
   * @throws UserNotFoundException
   */
  public User getUser(long id) throws UserNotFoundException;

  /***
   *
   * @param id for Activity
   * @return Activity from the db
   * @throws ActivityNotFoundException
   */
  public Activity getActivity(long id) throws ActivityNotFoundException;

  /***
   *
   * @param user  user that want to save  activity
   * @param activity activity
   * @throws UserActivityException
   */
  public void saveUserActivity(User user, Activity activity) throws UserActivityException;

  /***
   *
   * @param user  user that want to delete  activity
   * @param activity activity to delete
   * @return
   * @throws UserActivityException
   */
  public boolean deleteUserActivity(User user, Activity activity) throws UserActivityException;

  /***
   *
   * @param user  user
   * @return ArrayList that contain user Activities
   * @throws UserActivityException
   */
  public ArrayList getUserActivities(User user) throws UserActivityException ;

}
