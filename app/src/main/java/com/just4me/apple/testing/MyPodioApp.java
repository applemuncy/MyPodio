package com.just4me.apple.testing;
import android.util.Log;
import android.app.Application;
import com.podio.sdk.*;


/**
 * Created by apple on 3/8/2015.
 */
public class MyPodioApp extends Application {
    private static final String TAG="MyPodioApp";
    private static final String CLIENT_ID = "mypodio-vgupqn";
    private static final String CLIENT_SECRET = "f9m3ZpLGRIdKcil1Rficmvbe1JvoCZLPNy0Jpd3iEIbVpGWyXmu1vE1tuQgFGhwJ";

    private static Session session;

    public static boolean hasSession() {
        if (MyPodioApp.session != null 
	//	&& MyPodioApp.session.isAuthorized()
		) {
		
		
            return true;
        } else {
            saveSession(null);
            return false;
        }
    }

    public static void saveSession(Session session) {
        MyPodioApp.session = session;
    }
	
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate start");
        // Setup the Podio SDK runtime environment. Failing to call
        // this method will cause any and all subsequent calls to
        // throw exceptions.
        try {
            Podio.setup(this, CLIENT_ID, CLIENT_SECRET);
            Log.d(TAG, "onCreate after Podio.setup");
        }catch (Exception e )
        {
            Log.d (TAG, e.toString());
        }
    }

}
