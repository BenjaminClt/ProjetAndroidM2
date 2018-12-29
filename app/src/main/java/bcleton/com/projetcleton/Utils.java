package bcleton.com.projetcleton;

import android.content.Context;
import android.widget.Toast;

import java.util.Random;

public class Utils {

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max) + min;
    }
}
