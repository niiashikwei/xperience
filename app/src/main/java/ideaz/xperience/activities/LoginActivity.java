package ideaz.xperience.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import ideaz.xperience.R;
import ideaz.xperience.util.CustomToast;

public class LoginActivity extends Activity {
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        } else {
            AccessToken currentFBAccessToken = AccessToken.getCurrentAccessToken();
            if(currentFBAccessToken != null && !currentFBAccessToken.isExpired()){
                CustomToast.displayMessage(getApplicationContext(), "User already logged into facebook.");
                handleFacebookAccessToken(currentFBAccessToken);
            } else {
                registerFBLoginCallbacks();
                setContentView(R.layout.activity_login);
                LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
                loginButton.setReadPermissions("email", "public_profile");
            }
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        CustomToast.displayMessage(getApplicationContext(), "handleFacebookAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Task<AuthResult> authResultTask = firebaseAuth.signInWithCredential(credential);
        authResultTask.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                CustomToast.displayMessage(getApplicationContext(), "signInWithCredential:onComplete:" + task.isSuccessful());

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    CustomToast.displayMessage(getApplicationContext(), "signInWithCredential" + task.getException());
                    CustomToast.displayMessage(getApplicationContext(), "Authentication failed.");
                } else {
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    finish();
                }
            }
        });
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

