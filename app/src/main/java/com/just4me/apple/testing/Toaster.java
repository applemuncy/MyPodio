package com.just4me.apple.testing;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by apple on 3/10/2015.
 */
public class Toaster {
    private static String TAG;
    private static int TOAST_DURATION = Toast.LENGTH_LONG;
    private Context context;

    public Toaster(){
        TAG=getClass().getName();
    }

    public Toaster(Context context){
        TAG=getClass().getName();
        this.context= context;
    }

    public void make(String resource){
        Log.d(TAG, "make()");
        Toast toast= Toast.makeText(context, resource, TOAST_DURATION);
        toast.show();

    }
	
	public void make(int resource)
		
	{
        Log.d(TAG, "make()");
        Toast toast= Toast.makeText(context, resource, TOAST_DURATION);
        toast.show();

    }

}
