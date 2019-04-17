package il.ac.hit.mygym.Exception;

/**
 * UserNot  Exception
 */
public class UserNotFoundException  extends Exception  {
    private static final long serialVersionUID = 1L;

    /**
     *  Exception c'tor with msg
     *  @param message  hibernate Exception msg
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /***
     *
     * @param message  hibernate Exception msg
     * @param cause the Throwable cause
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
