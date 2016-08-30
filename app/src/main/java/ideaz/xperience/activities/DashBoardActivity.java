package ideaz.xperience.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ideaz.xperience.R;

public class DashboardActivity extends Activity {
    private FirebaseUser mFirebaseUser;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            setContentView(R.layout.activity_dashboard);
            registerButtonListeners();
            fadeInWelcomeMessage();
        }
    }

    private void fadeInWelcomeMessage() {
        final TextView welcomeTextView = (TextView) findViewById(R.id.welcome_text);
        String welcomeMessage = String.format("Welcome to Xperience %s%s", System.getProperty("line.separator"), mFirebaseUser.getDisplayName());
        welcomeTextView.setText(welcomeMessage);
        welcomeTextView.setAlpha(0);
        welcomeTextView.animate().alpha(1).setDuration(4000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                welcomeTextView.setVisibility(View.GONE);
            }
        });
    }

    private void registerButtonListeners() {
        registerCreateExperienceButton();
        registerBrowseExperiencesButton();
    }

    private void registerCreateExperienceButton() {
        View createExperienceButton = findViewById(R.id.create_experience_button);
        createExperienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreateXperienceActivity.class));
            }
        });
    }

    private void registerBrowseExperiencesButton() {
        View browseExperiencesButton = findViewById(R.id.browse_experience_button);
        browseExperiencesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BrowseXperiencesActivity.class));
            }
        });
    }

}
