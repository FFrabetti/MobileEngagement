package it.getconnected.filippo.me;

import it.getconnected.filippo.me.services.MEService;

/**
 * Static class used as a global access point for the current Mobile Engagement Service (MEService).
 * It handles a reference to a class implementing the {@code MEService} interface, making it
 * available everywhere within the application.
 * <p>Being a static class, all its methods are static and the only constructor is private (you cannot
 * instantiate a MEClient object).</p>
 *
 * @see MEService
 */
public class MEClient {

    private static MEService serviceInstance = null;

    /**
     * Sets a new MEService to be used from now on and to be stored as a static field.
     *
     * @param service the new Mobile Engagement Service
     * @return the newly set Mobile Engagement Service
     * @throws IllegalArgumentException if a null pointer is given
     */
    public static MEService newService(MEService service) {
        if(service == null)
            throw new IllegalArgumentException("service");

        serviceInstance = service;
        return serviceInstance;
    }

    /**
     * Checks whether a MEService has been set or not. A call to this method should always be done
     * before calling {@link #getSService()} in order to prevent the use of a null reference that
     * would result in a {@code NullPointerException} to be thrown.
     *
     * @return true if a MEService has been set, false otherwise
     */
    public static boolean isInitialized() {
        return serviceInstance != null;
    }

    /**
     * Returns the current MEService or throws an exception if none is set.
     *
     * @return the current MEService
     * @throws IllegalStateException if no MEService is set
     * @see #getSService()
     */
    public static MEService getService() {
        if(isInitialized())
            return serviceInstance;
        else
            throw new IllegalStateException("no service is set");
    }

    /**
     * Returns the current value of the static MEService field without performing any checks.
     * Safe variant of {@code getService()}: if no instance has been set it simply returns null
     * without throwing an exception. For this reason you should always check the returned value
     * or call {@link #isInitialized()} first.
     *
     * @return the current MEService or null if none is set
     * @see #getService()
     */
    public static MEService getSService() {
        return serviceInstance;
    }

    private MEClient() {
        /* It prevents initialization */
    }
}
