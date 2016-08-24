package ideaz.xperience.activities;

import android.app.Activity;
import android.os.Bundle;

import ideaz.xperience.R;
import ideaz.xperience.util.CustomToast;

public class DashboardActivity extends Activity{
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_dashboard);
        CustomToast.displayMessage(getApplicationContext(), "logged in successfully!");
    }
}
