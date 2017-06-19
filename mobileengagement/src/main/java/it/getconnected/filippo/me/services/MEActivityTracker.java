package it.getconnected.filippo.me.services;

import android.app.Activity;
import android.os.Bundle;

/**
 * Interface that handles user's navigation among activities and use-paths.
 */
public interface MEActivityTracker extends MEServiceModule {

    /**
     * Sends the indication of an activity being started,
     * optionally along with some additional information.
     * This method should be called by the {@code onResume} callback.
     *
     * @param activity the activity being started
     * @param extras additional information attached to the activity
     */
    public void startActivity(Activity activity, Bundle extras);

    /**
     * Notifies the end of an activity. Unused activities are paused,
     * therefore this method should be called by the {@code onPause} callback.
     *
     * @param activity the activity being left
     */
    public void endActivity(Activity activity);

}
