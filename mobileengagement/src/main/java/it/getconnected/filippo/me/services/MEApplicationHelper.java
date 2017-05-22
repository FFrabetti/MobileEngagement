package it.getconnected.filippo.me.services;

import android.content.Context;

import it.getconnected.filippo.me.helpers.MEApplication;

/**
 * Helper interface for managing custom classes that extend Application.
 * The methods of your Application sub-class are used as callbacks by every process of
 * your application, including services, but you may not want your redefined methods
 * to be called by the Engagement process, for instance: that's why you need an easy way
 * to know whether the current process is the one running the Engagement Service or not.
 *
 * @see MEApplication
 */
public interface MEApplicationHelper {

    /**
     * Checks whether the current process is the one running the Engagement service or not.
     *
     * @param context the application context
     * @return true if executing in the Engagement process, false otherwise
     */
    public boolean isInEngagementProcess(Context context);
}
