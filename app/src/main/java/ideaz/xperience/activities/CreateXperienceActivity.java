package ideaz.xperience.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ideaz.xperience.R;
import ideaz.xperience.core.Xperience;

public class CreateXperienceActivity extends Activity {
    private static final String XPERIENCES_CHILD = "xperiences";
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.create_xperience_activity);
        View postExperienceButton = findViewById(R.id.post_experience_button);
        postExperienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText xperienceName = (EditText) findViewById(R.id.xperience_name);
                EditText xperienceDescription = (EditText) findViewById(R.id.xperience_description);
                postExperience(xperienceName.getText().toString(), xperienceDescription.getText().toString());
                finish();
            }
        });
    }

    private void postExperience(String name, String description) {
        Xperience xperience = new Xperience(name, description);
        mFirebaseDatabaseReference.child(XPERIENCES_CHILD)
                .push().setValue(xperience);
    }
}
