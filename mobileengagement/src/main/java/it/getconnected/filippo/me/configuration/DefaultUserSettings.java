package it.getconnected.filippo.me.configuration;

import android.content.SharedPreferences;
import android.util.Log;

import it.getconnected.filippo.me.BuildConfig;

public abstract class DefaultUserSettings extends DefaultSettings implements MEUserSettings {

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SettingsListener listener = getSettingsListener();
        Object value = sharedPreferences.getAll().get(key);

        if(listener != null) {
            listener.onSettingsChanged(key, value);

            //region DEBUG
            if(BuildConfig.DEBUG) {
                Log.d(getClass().getSimpleName(),
                        "onSharedPreferenceChanged: onSettingsChanged " + key + " -> " + value);
            }
            //endregion
        }
    }

}
