package it.getconnected.filippo.me.configuration;

import android.util.Log;

import it.getconnected.filippo.me.BuildConfig;

/**
 * Helper abstract class for dealing with recurring operations with the Mobile Engagement settings.
 * Specifically, it manages registration/de-registration of a {@link it.getconnected.filippo.me.configuration.MESettings.SettingsListener}
 * and the related callback event when an option gets changed.
 */
public abstract class DefaultSettings implements MESettings {

    private SettingsListener listener;

    @Override
    public SettingsListener getSettingsListener() {
        return listener;
    }

    @Override
    public void setSettingsListener(SettingsListener listener) {
        if(listener == null)
            throw new IllegalArgumentException("listener");
        this.listener = listener;
    }

    private <T> void onSettingsChanged(String key, T value) {
        if(listener != null) {
            listener.onSettingsChanged(key, value);

            //region DEBUG
            if(BuildConfig.DEBUG) {
                Log.d(getClass().getSimpleName(), "SettingsListener.onSettingsChanged: " + key + " -> " + value);
            }
            //endregion
        }
    }

    @Override
    public void setString(String key, String value) {
        if(!getString(key).equals(value))
            onSettingsChanged(key, value);
    }

    @Override
    public void setInt(String key, int value) {
        if(getInt(key) != value)
            onSettingsChanged(key, value);
    }

    @Override
    public void setBoolean(String key, boolean value) {
        if(getBoolean(key) != value)
            onSettingsChanged(key, value);
    }
}
