package it.getconnected.filippo.me.helpers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import it.getconnected.filippo.me.BuildConfig;
import it.getconnected.filippo.me.MEClient;
import it.getconnected.filippo.me.services.MEActivityTracker;
import it.getconnected.filippo.me.services.MEService;

/**
 * Utility abstract class you can extend with your own activities to make them
 * be automatically tracked by the Mobile Engagement Service.
 * Activities are considered ended when the {@code onPause} callback gets called
 * and they are restarted at {@code onResume}.
 * <p>If you want to send additional information attached to the activity you have to
 * override {@link #getEngagementActivityExtra()}, whose default implementation
 * simply returns null.</p>
 * <p>You can do without this class by using the corresponding services yourself:
 * {@link MEService#startActivity(Activity, Bundle)} and {@link MEService#endActivity(Activity)}.
 * Remember to do so within the {@code onResume} and {@code onPause} methods and to always consider
 * the chance of no Service being set: your application has to run smoothly in either case.</p>
 */
public abstract class MEActivity extends AppCompatActivity {

    private MEActivityTracker getActivityTracker() {
        return MEClient.getService();
    }

    /**
     * Gets the bundle containing additional information about the activity.
     * Override this method if you want a returned value different from null.
     *
     * @return a bundle with info about the activity or null if not redefined by a sub-class
     */
    public Bundle getEngagementActivityExtra() {
        return null;
    }

    @Override
    public void onResume() {
        if(MEClient.isInitialized()) {
            MEActivityTracker tracker = getActivityTracker();
            tracker.startActivity(this, getEngagementActivityExtra());

            //region DEBUG
            if(BuildConfig.DEBUG) {
                Log.d(getLocalClassName(),
                        "onResume: MEActivityTracker.startActivity extras=" + getEngagementActivityExtra());
            }
            //endregion
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if(MEClient.isInitialized()) {
            MEActivityTracker tracker = getActivityTracker();
            tracker.endActivity(this);

            //region DEBUG
            if(BuildConfig.DEBUG) {
                Log.d(getLocalClassName(), "onPause: MEActivityTracker.endActivity");
            }
            //endregion
        }
        super.onPause();
    }
}
