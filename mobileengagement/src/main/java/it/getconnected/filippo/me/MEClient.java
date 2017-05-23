package it.getconnected.filippo.me;

import java.lang.reflect.Method;

import it.getconnected.filippo.me.services.MEService;
import it.getconnected.filippo.me.services.MEServiceModule;
import it.getconnected.filippo.me.utils.OptModule;
import it.getconnected.filippo.me.utils.OptionalModule;

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

    /**
     * Allows you to get a {@code ServiceModule} optionally provided by the current
     * Engagement Service from its class descriptor. This method works even if the module
     * you are looking for does not appear in the {@link MEService} interface, though
     * you can use it to retrieve standard engagement services as well.
     * <p>It uses reflection to invoke, if present, a method of the current Service
     * with no parameters and with the given return type. In all the other cases
     * (including the one of no Service being set), it returns an empty {@link OptionalModule}.</p>
     * <p>By not providing the requested ServiceModule directly, it forces the user
     * to check the returned value ({@link OptionalModule#isPresent()} before using it.
     * This is a great improvement from the dangerous and confusing use of null pointers
     * to indicate the absence of a particular service module.</p>
     *
     * @param type the class descriptor of the requested ServiceModule
     * @param <T> the type of the returned ServiceModule
     * @return an OptionalModule containing the requested service or an empty one if
     * the module or the Service itself were not available
     */
    public static <T extends MEServiceModule> OptionalModule<T> getServiceModule(Class<T> type) {
        if(!isInitialized())
            return OptModule.empty();

        T result = null;
        try {
            for(Method m : serviceInstance.getClass().getDeclaredMethods()) {
                // m.getParameterCount() -> yet to be added...
                if(m.getParameterTypes().length==0 && m.getReturnType().equals(type)) {
                    result = type.cast(m.invoke(serviceInstance));
                    break;
                }
            }
        } catch(Exception e) { }

        if(result == null)
            return OptModule.empty();

        return new OptModule<>(result);
    }

}
