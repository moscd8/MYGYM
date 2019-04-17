package il.ac.hit.mygym.Model;

/***
 * This class repesent an Workout for MYGYM useer .
 * this class is mapped to "Activities" table in the db (Hibernate)
 *
 */

public class Activity {

    /**
     * id of the activity
     */
    private long activityId;

    /**
     * name of the activity
     */
    private String name;

    /**
     * type  of the activity
     */
    private String type;

    /**
     * muscleGroup of the activity
     */
    private String muscleGroup;

    /**
     * duration of the activity
     */
    private double duration;

    /**
     * numOfSets of the activity
     */
    private int numOfSets;

    /**
     * numOfReps of the activity
     */
    private int numOfReps;

    /**
     * flag for basic activity (in order to save multilines of basic activitys in the table)
     */
    private  boolean defaultActivity;

    /**
     * estimated  amaount  of calories in  activity
     */
    private int calories;

    /***
     * non defualt c'tor, it will use  the setters of the object in order to assign only valid data
     * @param name activity Name
     * @param type Aerobi or AnAerobi
     * @param muscleGroup type of muscle Group
     * @param duration duration
     * @param numOfSets numOfSets
     * @param numOfReps numOfReps
     */
    public Activity( String name, String type, String muscleGroup, double duration, int numOfSets, int numOfReps) {
        setName(name);
        setType(type);
        setMuscleGroup(muscleGroup);
        setDuration(duration);
        setNumOfSets(numOfSets);
        setNumOfReps(numOfReps);

        this.defaultActivity= false;

    }

    /**
     * defualt ctor of activity , hibernate  requirement Ematy c'tot
     */
    public Activity() {
        super();
    }



    /***
     *
     * @return     return the Id of activity
     */
    public long getId() {
        return activityId;
    }

    /***
     *
     * @param id     set the Id of activity
     */
    public void setId(long id) {
        if(id >=0){
            this.activityId = id;
        }

    }


    /***
     *
     * @return  return the Name of activity
     */
    public String getName() {

        return name;
    }

    /***
     *
     * @param name update the Id of activity
     */
    public void setName(String name) {
        if(name !=null){
            this.name = name;
        }
    }

    /***
     *
     * @return return the Type  of activity (aerobi OR anaerobi)
     */
    public String getType() {
        return type;
    }
    //

    /***
     *
     * @param type update the Type  of activity (aerobi OR anaerobi)
     */
    public void setType(String type) {
        if(type !=null){
        this.type = type;
        }
    }

    //

    /***
     *
     * @return get the MuscleGroup
     */
    public String getMuscleGroup() {

        return muscleGroup;
    }

    /***
     *
     * @param muscleGroup update the MuscleGroup
     */
    public void setMuscleGroup(String muscleGroup) {
        if(muscleGroup!=null){
            this.muscleGroup = muscleGroup;
        }
    }

    /***
     *
     * @return get duration of activity
     */
    public double getDuration() {
        return duration;
    }

    /***
     *
     * @param duration set duration of activity
     */
    public void setDuration(double duration) {
        if(duration>=0){
            this.duration = duration;
        }

    }

    /***
     *
     * @return  get num of sets of workout
     */
    public int getNumOfSets() {
        return numOfSets;
    }

    /***
     *
     * @param numOfSets  set num of sets of workout
     */
    public void setNumOfSets(int numOfSets) {
        if(numOfSets >=0){
            this.numOfSets = numOfSets;
        }
    }

    /***
     *
     * @return  get num of repeats  of workout
     */
    public int getNumOfReps() {
        return numOfReps;
    }

    /***
     *
     * @param numOfReps set num of repeats  of workout
     */
    public void setNumOfReps(int numOfReps) {
        if(numOfReps>=0){
            this.numOfReps = numOfReps;
        }
    }

    /***
     *
     * @param defaultActivity  check if its DefaultActivity (created my the GYM  Mangers / trainers)
     */
    public void setDefaultActivity(boolean defaultActivity) {
        this.defaultActivity = defaultActivity;
    }

    /***
     *
     * @return return of activity is DefaultActivity
     */
    public boolean getDefaultActivity() {
        return defaultActivity;
    }


    /***
     *
     * @return custome toString of Activity Class
     */
    @Override
    public String toString() {
        return "Activity{" +
                "id=" + activityId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", duration=" + duration +
                ", numOfSets=" + numOfSets +
                ", numOfReps=" + numOfReps +
                '}';
    }

}
