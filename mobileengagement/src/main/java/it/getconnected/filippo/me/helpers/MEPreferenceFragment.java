package it.getconnected.filippo.me.helpers;

import android.preference.PreferenceFragment;
import android.util.Log;

import it.getconnected.filippo.me.BuildConfig;
import it.getconnected.filippo.me.MEClient;

public class MEPreferenceFragment extends PreferenceFragment {

    @Override
    public void onResume() {
        super.onResume();

        if(MEClient.isInitialized()) {
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(MEClient.getService().getConfiguration().getUserSettings());

            //region DEBUG
            if(BuildConfig.DEBUG) {
                Log.d(getClass().getSimpleName(), "onResume: registerSP UserSettings");
            }
            //endregion
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(MEClient.isInitialized()) {
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(MEClient.getService().getConfiguration().getUserSettings());

            //region DEBUG
            if(BuildConfig.DEBUG) {
                Log.d(getClass().getSimpleName(), "onPause: unregisterSP");
            }
            //endregion
        }
    }
}
