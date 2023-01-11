package lovlig.quiz.utils;

import android.content.Context;
import android.widget.Toast;

public class Toasts {

    public static void makeToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void makeToastLong(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

}
