package it.getconnected.filippo.me.services;

import it.getconnected.filippo.me.configuration.MEConfiguration;

/**
 * Abstraction of a generic Mobile Engagement Service. It follows the facade design pattern,
 * standing between the client and all the classes that provide the actual services.
 * <p>Services are grouped in smaller and more flexible interfaces according to the
 * interface-segregation principle (ISP).</p>
 */
public interface MEService extends
                                MEActivityTracker,
                                MEApplicationHelper,
                                MEEventsSender,
                                MEAppInfoSender {

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
}
