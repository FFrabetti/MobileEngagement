package it.getconnected.filippo.me.configuration;

/**
 * It provides a uniform way to access and modify settings, being either developer or user defined.
 */
public interface MESettings {

    public String getString(String key);
    public int getInt(String key);
    public boolean getBoolean(String key);

    public void setString(String key, String value);
    public void setInt(String key, int value);
    public void setBoolean(String key, boolean value);

    public SettingsListener getSettingsListener();
    public void setSettingsListener(SettingsListener listener);

    /**
     * Event handler for any settings change.
     */
    public interface SettingsListener {
        public <T> void onSettingsChanged(String key, T value);
//        public void onStringSettingChanged(String key, String value);
//        public void onIntSettingChanged(String key, int value);
//        public void onBooleanSettingChanged(String key, boolean value);
    }
}
