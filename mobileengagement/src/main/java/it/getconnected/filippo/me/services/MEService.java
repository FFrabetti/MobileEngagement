package it.getconnected.filippo.me.services;

import it.getconnected.filippo.me.MEClient;
import it.getconnected.filippo.me.configuration.MEConfiguration;

/**
 * Abstraction of a generic Mobile Engagement Service. It follows the facade design pattern,
 * standing between the client and all the classes that provide the actual services.
 * <p>Services are grouped in smaller and more flexible interfaces according to the
 * interface-segregation principle (ISP).</p>
 */
public interface MEService {

    /**
     * Invokes all the one-time operations that need to be done when starting the Service.
     * <p>You should not call this method directly, as it is done at the appropriate time
     * by {@link MEClient#newService(MEService)} and by {@link MEClient#start()}</p>
     */
    public void startService();

    /**
     * Does clean-up operations when the Service is shut down.
     * <p>Method called by {@link MEClient#newService(MEService)} before starting a new Service
     * and by {@link MEClient#stop()}.</p>
     */
    public void stopService();

    /**
     * Gets the configuration of the Engagement Service.
     *
     * @return the current configuration
     */
    public MEConfiguration getConfiguration();

    /**
     * Forces the re-execution of all the setup operations in order
     * to take into account newly made changes in the Engagement configuration.
     */
    public void refreshConfiguration();

    //region Engagement services (MEServiceModule)

    public MEActivityTracker getActivityTracker();

    public MEApplicationHelper getApplicationHelper();

    public MEEventsSender getEventsSender();

    public MEAppInfoSender getAppInfoSender();

    //endregion
}
