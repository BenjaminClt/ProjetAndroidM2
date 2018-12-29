package bcleton.com.projetcleton.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bcleton.com.projetcleton.Data.QuestionnaireManager;
import bcleton.com.projetcleton.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String ADMIN_PASSWORD = "MDP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_questionnaires_list).setOnClickListener(this);
        findViewById(R.id.show_scores).setOnClickListener(this);
        findViewById(R.id.reset_scores).setOnClickListener(this);
        findViewById(R.id.create_questionnaire).setOnClickListener(this);
        findViewById(R.id.close).setOnClickListener(this);
    }

    private void onCreateQuestionnaire() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mot de passe administrateur");

        final EditText input = new EditText(this);

        // On définie l'EditText comme étant un mot de passe
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(input.getText().toString().equals(ADMIN_PASSWORD)) {
                    Toast.makeText(MainActivity.this, "Succès",
                            Toast.LENGTH_LONG).show();

                    startCreateQuestionnaireActivity();
                }else {
                    Toast.makeText(MainActivity.this, "Mot de passe incorrect.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void startCreateQuestionnaireActivity() {
        startActivity(new Intent(this, CreateQuestionnaireActivity.class));
    }

    private void onClose() {
        QuestionnaireManager.getInstance().saveQuestionnaires();
        System.exit(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_questionnaires_list:
                startActivity(new Intent(this, SelectQuestionnaireActivity.class));
                break;
            case R.id.show_scores:
                startActivity(new Intent(this, ScoresActivity.class));
                break;
            case R.id.reset_scores:
                QuestionnaireManager.getInstance().resetAllScores();
                break;
            case R.id.create_questionnaire:
                onCreateQuestionnaire();
                break;
            case R.id.close:
                onClose();
                break;
        }
    }
}
