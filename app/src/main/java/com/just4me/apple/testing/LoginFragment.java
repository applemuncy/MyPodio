package com.just4me.apple.testing;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.podio.sdk.Request;
import com.podio.sdk.Session;
import com.podio.sdk.Request.ResultListener;
import com.podio.sdk.Podio;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    String TAG = "LoginFragment";

    public LoginFragment() {
        // Required empty public constructor
    }

    public static interface LoginListener {
        public void onLoginPerformed(Session session);
    }

    private EditText email;
    private EditText password;
    private Button go;

    private LoginListener loginListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TAG = getClass().getName();
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_login2, container, false);
        email = (EditText) contentView.findViewById(R.id.email);
        password = (EditText) contentView.findViewById(R.id.password);
        go = (Button) contentView.findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick received");
                try {
                    performLogin();
                }catch (Exception e){
                    Log.d( TAG, e.toString());
                }
            }

        });
        return contentView;
    }

    private void performLogin() {
        // Request a login action with the user provided credentials.
        String usr = email.getText().toString();
        String pwd = password.getText().toString();

        // Yup, this is the only Podio SDK call we need to do.
        Podio.client
                .authenticateWithUserCredentials(usr, pwd).withSessionListener(new Request.SessionListener() {
            @Override
            public boolean onSessionChanged(String authToken, String refreshToken, long expires) {
                return false;
            }
        })


                .withErrorListener(new Request.ErrorListener() {

                    @Override
                    public boolean onErrorOccured(Throwable cause) {
                        // Oh no! I couldn't log in...
                        return false;
                    }

                });

    }


}

