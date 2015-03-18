package com.just4me.apple.testing;

import android.util.Log;
import android.app.Application;

import com.podio.sdk.Session;
import com.podio.sdk.Podio;
import android.content.*;



/**
 * Created by apple on 3/8/2015.
 */
public class MyPodioApp extends Application {
    private static final String TAG = "MyPodioApp";
    private static final String CLIENT_ID = "mypodio-vgupqn";
    private static final String CLIENT_SECRET = "f9m3ZpLGRIdKcil1Rficmvbe1JvoCZLPNy0Jpd3iEIbVpGWyXmu1vE1tuQgFGhwJ";

    private static Session session;
    private String authToken;
    private String refreshToken;
    private long expires;
	private String email;
	private final String SECRETS = "secrets";
	private SharedPreferences prefs; 
	
    public void setAuthToken(String token){
		prefs.edit().putString("authToken", token).apply();
		authToken=token;}
		public String getAuthToken(){
			authToken=prefs.getString("authToken", null);
			return authToken;
		}
		
    public void setRefreshToken(String token){
		prefs.edit().putString("refreshToken", token).apply();
		refreshToken=token;}
		public String getRefreshToken(){
			refreshToken=prefs.getString("refreshToken",null);
			return refreshToken;
		}
		
    public void setExpires(long t){
	prefs.edit().putLong("expires", t).apply();
	expires=t;
	}
	public long getExpires(){
		expires = prefs.getLong("expires",0);
		return expires;
	}
	
	public void setEmail(String e){
		prefs.edit().putString("email",e).apply();
		email = e;
		Toaster t = new Toaster(this);
		t.make("set "+e);
		
	}
	public String getEmail(){
		
		email= prefs.getString("email",null);
		if (email!=null){
			
			Toaster t = new Toaster(this);
			t.make("retreved " + email);
			
		}
		return email;
		
	}
	
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
		prefs = this.getSharedPreferences(
			SECRETS, Context.MODE_PRIVATE);
        Log.d(TAG, "onCreate start");
        // Setup the Podio SDK runtime environment. Failing to call
        // this method will cause any and all subsequent calls to
        // throw exceptions.
        try {
            Podio.setup(this, CLIENT_ID, CLIENT_SECRET);
            Log.d(TAG, "onCreate after Podio.setup");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

}
