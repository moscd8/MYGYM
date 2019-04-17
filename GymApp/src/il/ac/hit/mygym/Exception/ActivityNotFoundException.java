package il.ac.hit.mygym.Exception;

/**
 * Activity Not Found Exception
 */
public class ActivityNotFoundException  extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     *  Exception c'tor with msg
     *  @param message  hibernate Exception msg
     */
    public ActivityNotFoundException(String message) {
        super(message);
    }

    /***
     *
     * @param message  hibernate Exception msg
     * @param cause the Throwable cause
     */
    public ActivityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
