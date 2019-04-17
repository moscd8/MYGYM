package il.ac.hit.mygym.Exception;

/**
 * Hibernate Exception
 */
public class HibernameExceptionMyGym extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     *  Exception c'tor with msg
     *  @param message  hibernate Exception msg
     */
    public HibernameExceptionMyGym(String message) {
        super(message);
    }

    /***
     *
     * @param message  hibernate Exception msg
     * @param cause the Throwable cause
     */
    public HibernameExceptionMyGym(String message, Throwable cause) {
        super(message, cause);
    }
}
