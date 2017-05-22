package it.getconnected.filippo.me.configuration;

import java.util.Set;

/**
 * Manages developer-defined and user settings to determine the
 * configuration options for the Mobile Engagement Service.
 *
 * @see MESettings
 * @see MEUserSettings
 */
public interface MEConfiguration {

    /**
     * Initializes the configuration for the Engagement Service.
     */
    public void init();

    /**
     * Gets the keys of all the configuration options available.
     *
     * @return a string set with all the keys
     */
    public Set<String> getKeySet();

    /**
     * Gets the permissions required by the Service for the current configuration.
     * Already granted permissions are not included, however you should keep in mind
     * that the user can revoke permissions at any time and without any notice
     * given to the apps that need them.
     *
     * @return the permissions needed for the Engagement Service but not already granted
     */
    public String[] getRequiredPermissions();

    /**
     * Gets the current developer-defined settings.
     *
     * @return developer-defined settings
     */
    public MESettings getSettings();

    /**
     * Gets the current user settings, usually stored in a SharedPreferences file.
     *
     * @return user settings
     */
    public MEUserSettings getUserSettings();

    public ConfigurationListener getConfigurationListener();
    public void setConfigurationListener(ConfigurationListener listener);

    /**
     * Event handler for every change in the configuration of the Engagement Service.
     */
    public interface ConfigurationListener {
        public void onConfigurationChanged();
    }
}
