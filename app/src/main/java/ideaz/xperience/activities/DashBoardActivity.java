package ideaz.xperience.activities;

import android.app.Activity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ideaz.xperience.R;
import ideaz.xperience.util.CustomToast;

public class DashboardActivity extends Activity {
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_dashboard);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        CustomToast.displayMessage(getApplicationContext(), String.format("User is %s", currentUser.getDisplayName()));
    }
}
