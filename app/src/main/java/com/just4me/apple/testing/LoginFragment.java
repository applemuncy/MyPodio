package com.just4me.apple.testing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.*;

import java.util.Date;
import android.content.*;



/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment
{

    String TAG = "LoginFragment";
	private final String SECRETS = "secrets";

    public LoginFragment()
	{
        // Required empty public constructor
    }

    public static interface LoginListener
	{
        public void onLoginPerformed(Session session);
    }
    public static LoginFragment newInstance(LoginListener loginListener)
	{
        LoginFragment fragment = new LoginFragment();
        fragment.loginListener = loginListener;

        return fragment;
    }
    private EditText email;
    private EditText password;
    private Button go;
	private TextView error;

    private LoginListener loginListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
	{
        TAG = getClass().getName();
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_login2, container, false);

        email = (EditText) contentView.findViewById(R.id.email);

		MyPodioApp app= (MyPodioApp)getActivity().getApplication();
		String e = app.getEmail();
		
	
		if (e != null)
		{

			email.setText(e);
			Toaster t = new Toaster(getActivity().getApplicationContext());
			t.make("email " + e);
		}

        password = (EditText) contentView.findViewById(R.id.password);
        go = (Button) contentView.findViewById(R.id.go);
		error = (TextView) contentView.findViewById(R.id.login_error);
        go.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view)
				{
					Log.d(TAG, "onClick received");
					try
					{
						performLogin();
					}
					catch (Exception e)
					{
						Log.d(TAG, e.toString());
					}
				}

			});
        return contentView;
    }

    private void performLogin()
	{
        // Request a login action with the user provided credentials.
        String usr = email.getText().toString();
		MyPodioApp app= (MyPodioApp)getActivity().getApplication();
		app.setEmail(usr);

        String pwd = password.getText().toString();

        // Yup, this is the only Podio SDK call we need to do.
        Podio.client
        	.authenticateWithUserCredentials(usr, pwd)
			.withSessionListener(new Request.SessionListener() {
				@Override
				public boolean onSessionChanged(String authTokenT, String refreshTokenT, long expiresT)
				{
					Log.d(TAG, "onSessionChanged");
					Date d = new Date(expiresT * 1000);
					error.setText("Good login " + d.toString());
					MyPodioApp app = (MyPodioApp) getActivity().getApplication();
					app.setAuthToken(authTokenT);
					app.setRefreshToken(refreshTokenT);
					app.setExpires(expiresT);

					return false;
				}
			})
			.withErrorListener(new Request.ErrorListener() {

				@Override
				public boolean onErrorOccured(Throwable cause)
				{
					// Oh no! I couldn't log in...
					Log.d(TAG, "onError");
					error.setText("login falled. try again.");
					password.setText("");

					return false;
				}

			});

    }
	public void onLoginPerformed(Session session)
	{
		Log.d(TAG, "Login performed");
		Toaster t = new Toaster(getActivity().getApplicationContext());
		t.make(R.string.LoggedIn);
	}

}

