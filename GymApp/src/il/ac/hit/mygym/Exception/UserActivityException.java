package il.ac.hit.mygym.Exception;

public class UserActivityException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     *  Exception c'tor with msg
     *  @param message  UserActivity Exception msg
     */
    public UserActivityException(String message) {
        super(message);
    }

    /***
     *
     * @param message  UserActivity Exception msg
     * @param cause the Throwable cause
     */
    public UserActivityException(String message, Throwable cause) {
        super(message, cause);
    }
}
