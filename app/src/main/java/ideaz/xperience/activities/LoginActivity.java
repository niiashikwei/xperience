package ideaz.xperience.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import ideaz.xperience.R;
import ideaz.xperience.util.CustomToast;

public class LoginActivity extends Activity{
    private CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AccessToken currentFBAccessToken = AccessToken.getCurrentAccessToken();
        if(currentFBAccessToken != null && !currentFBAccessToken.isExpired()){
            CustomToast.displayMessage(getApplicationContext(), "User already logged in!");
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

        } else {
            registerFBLoginCallbacks();
            setContentView(R.layout.activity_login);
            LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions("email", "public_profile");
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void registerFBLoginCallbacks() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    }

                    @Override
                    public void onCancel() {
                        CustomToast.displayMessage(getApplicationContext(), "onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        CustomToast.displayMessage(getApplicationContext(), "onError");
                    }
                });
    }

}

