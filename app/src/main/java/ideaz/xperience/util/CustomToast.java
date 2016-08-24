package ideaz.xperience.util;

import android.content.Context;
import android.widget.Toast;

public class CustomToast {
    public static void displayMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
