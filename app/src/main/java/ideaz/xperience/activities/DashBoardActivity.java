package ideaz.xperience.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ideaz.xperience.R;

public class DashboardActivity extends Activity {

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_dashboard);
        fadeInWelcomeMessage();
    }

    private void fadeInWelcomeMessage() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final TextView welcomeTextView = (TextView) findViewById(R.id.welcome_text);
        String welcomeMessage = String.format("Welcome to Xperience %s%s", System.getProperty("line.separator"), currentUser.getDisplayName());
        welcomeTextView.setText(welcomeMessage);
        welcomeTextView.setAlpha(0);
        welcomeTextView.animate().alpha(1).setDuration(4000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                welcomeTextView.setVisibility(View.GONE);
            }
        });
    }
}
