package it.getconnected.filippo.me.services;

import android.os.Bundle;

/**
 * It sends user-specific key/value pairs that are kept server-side until a new value
 * for the same key is provided.
 * <br>App-info are mainly used for personalizing notifications content.
 */
public interface MEAppInfoSender {

    public void sendAppInfo(Bundle appInfo);
}
