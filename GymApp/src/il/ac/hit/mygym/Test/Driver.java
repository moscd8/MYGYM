package il.ac.hit.mygym.Test;

import il.ac.hit.mygym.Exception.HibernameExceptionMyGym;
import il.ac.hit.mygym.Exception.UserActivityException;
import il.ac.hit.mygym.Exception.UserNotFoundException;
import il.ac.hit.mygym.Model.Activity;
import il.ac.hit.mygym.Model.User;
import il.ac.hit.mygym.Dao.IDaoImpl;

public class Driver {

    public static void main(String args[]) throws UserNotFoundException, UserActivityException, HibernameExceptionMyGym {

        IDaoImpl dao = IDaoImpl.getInstance();
        //new user
        User user1 = new User();
        user1.setUsername("test");
        user1.setPassword("1234");
        user1.setEmail("test@test");
        user1.setUserId(1111);
        user1.setWeight(70);
        user1.setHeight(170);
        //new user2
        User user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("1234");
        user2.setEmail("test2@test");
        user1.setUserId(1112);
        user2.setWeight(72);
        user2.setHeight(172);

        //new Activity
        Activity activity1 =new Activity();
        activity1.setName("test1");
        activity1.setType("Aerobi");
        activity1.setNumOfReps(10);
        activity1.setMuscleGroup("full body");

        //save Activity and user in useractivity
        dao.saveActivity(activity1);
        dao.saveUser(user1);
        dao.saveUser(user2);
        dao.saveUserActivity(user1.getUserId(),activity1.getId());
        dao.saveUserActivity(user2.getUserId(),activity1.getId());



        //     dao.saveUser(new User(1,"a","aa","aaa",12,12));
//        ArrayList mylist= dao.getUser(1).selectUserActivities();
//        System.out.println(mylist.size());

//      Activity activity=new Activity(2,"a","aa","aaa",12,12,12);
//      Activity activity1=new Activity(3,"Push Up","aa","aaa",12,12,12);
//      User u=  new User(2,"a","aa","aaa",12,12);
//      User u2=  new User(4,"a","aa","aaa",12,12);
//      User u3=  new User(5,"a","aa","aaa",12,12);

        // dao.saveUser(u);
     //dao.deleteUser(u);
     //dao.Register(u);
//     dao.Register(u2);
//     dao.deleteUser(u2);
  //   dao.deleteUser(u3);
//        dao.Register(u3);
//     dao.login(u3);
//     dao.saveUserActivity(u3,activity1);

//        System.out.println(    dao.getUserActivities(u3));
   //  dao.logout(u3);
//        ArrayList temp = dao.getUserActivities(u3);
//        Iterator itr= temp.iterator();

//        User u5=new User();
//        u.setEmail("t3a");
//        IDaoImpl.getInstance().Register(u5);

//        Activity activity3=new Activity();
//        activity3.setId(20077);
////        IDaoImpl.getInstance().deleteActivity(activity3);
//        User temp = IDaoImpl.getInstance().getUserByEmail("ta");
//        ArrayList<UserActivity> getactivity= IDaoImpl.getInstance().getUserActivities(temp);
        //boolean  re=IDaoImpl.getInstance().findActivityInUserActivities(1003,getactivity);
        //System.out.println(re);
//        boolean deleteActivity= IDaoImpl.getInstance().deleteUserActivity(temp,activity3);

//        IDaoImpl.getInstance().saveUserActivity(14,2007);

//        if (deleteActivity)
//            System.out.println("deleted..");
//        else
//                System.out.println("Not deleted..");
}



    }

