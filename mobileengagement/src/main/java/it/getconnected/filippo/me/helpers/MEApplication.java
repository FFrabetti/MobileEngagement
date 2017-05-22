package it.getconnected.filippo.me.helpers;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import it.getconnected.filippo.me.BuildConfig;
import it.getconnected.filippo.me.MEClient;
import it.getconnected.filippo.me.services.MEApplicationHelper;
import it.getconnected.filippo.me.services.MEService;

/**
 * Utility abstract class you can extend with your own Application sub-class to avoid having
 * to deal with the Mobile Engagement Service directly.
 * <p>It provides two methods for each callback, which are called accordingly to the returned
 * value of {@link MEApplicationHelper#isInEngagementProcess(Context)}.</p>
 * <p>The names of the methods follow this pattern:
 * <br><code>onXXX --> onApplicationXXX, onEngagementProcessXXX</code>
 * <br>Where {@code XXX} is:
 * <ul>
 *     <li>Create ({@code onCreate()})</li>
 *     <li>Terminate ({@code onTerminate()})</li>
 *     <li>LowMemory ({@code onLowMemory()})</li>
 *     <li>ConfigurationChanged ({@code onConfigurationChanged(Configuration newConfig)})</li>
 * </ul></p>
 * <p>Alternatively, you can do without {@code MEApplication} by checking yourself what kind of process
 * is running with the help of {@link MEService#isInEngagementProcess(Context)}.</p>
 *
 * @see MEApplicationHelper
 */
public abstract class MEApplication extends Application {

    private boolean isInEngagementProcess() {
        MEApplicationHelper helper = MEClient.getSService();
        return helper!=null && helper.isInEngagementProcess(this);
    }

    @Override
    public final void onCreate() {
        super.onCreate();

        //region DEBUG
        if(BuildConfig.DEBUG) {
            Log.d(getClass().getSimpleName(), "onCreate: isInEngagementProcess()=" + isInEngagementProcess());
        }
        //endregion

        if(isInEngagementProcess())
            onEngagementProcessCreate();
        else
            onApplicationCreate();
    }

    public void onApplicationCreate() {}
    public void onEngagementProcessCreate() {}

    @Override
    public final void onTerminate() {
        super.onTerminate();

        //region DEBUG
        if(BuildConfig.DEBUG) {
            Log.d(getClass().getSimpleName(), "onTerminate: isInEngagementProcess()=" + isInEngagementProcess());
        }
        //endregion

        if(isInEngagementProcess())
            onEngagementProcessTerminate();
        else
            onApplicationTerminate();
    }

    public void onApplicationTerminate() {}
    public void onEngagementProcessTerminate() {}

    @Override
    public final void onLowMemory() {
        super.onLowMemory();

        //region DEBUG
        if(BuildConfig.DEBUG) {
            Log.d(getClass().getSimpleName(), "onLowMemory: isInEngagementProcess()=" + isInEngagementProcess());
        }
        //endregion

        if(isInEngagementProcess())
            onEngagementProcessLowMemory();
        else
            onApplicationLowMemory();
    }

    public void onApplicationLowMemory() {}
    public void onEngagementProcessLowMemory() {}

    @Override
    public final void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //region DEBUG
        if(BuildConfig.DEBUG) {
            Log.d(getClass().getSimpleName(), "onConfigurationChanged: isInEngagementProcess()=" + isInEngagementProcess());
        }
        //endregion

        if(isInEngagementProcess())
            onEngagementProcessConfigurationChanged(newConfig);
        else
            onApplicationConfigurationChanged(newConfig);
    }

    public void onApplicationConfigurationChanged(Configuration newConfig) {}
    public void onEngagementProcessConfigurationChanged(Configuration newConfig) {}
}
