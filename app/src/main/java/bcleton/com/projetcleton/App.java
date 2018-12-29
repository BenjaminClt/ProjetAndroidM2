package bcleton.com.projetcleton;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    // On crée une variable static du Context de l'application pour y avoir accès
    // dans QuestionnaireManager et récupérer les données des SharedPreferences
    public static Context getAppContext() {
        return context;
    }
}
