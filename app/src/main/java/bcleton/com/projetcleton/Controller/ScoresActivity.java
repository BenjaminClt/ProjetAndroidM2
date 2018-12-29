package bcleton.com.projetcleton.Controller;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import bcleton.com.projetcleton.Controller.Adapter.ScoreAdapter;
import bcleton.com.projetcleton.Data.QuestionnaireManager;
import bcleton.com.projetcleton.Model.Questionnaire;
import bcleton.com.projetcleton.R;

public class ScoresActivity extends AppCompatActivity {

    private List<Questionnaire> questionnaires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        this.questionnaires = QuestionnaireManager.getInstance().getQuestionnaires();

        ScoreAdapter adapter = new ScoreAdapter(this, questionnaires);
        ((ListView)findViewById(R.id.scoreList)).setAdapter(adapter);

        ((TextView)findViewById(R.id.average_score)).setText(getAverage());
    }

    public void onGoBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    private String getAverage() {
        float average = 0;

        if (questionnaires.size() > 0) {

            for(Questionnaire questionnaire : questionnaires) {
                average += 20 * questionnaire.getScore() / questionnaire.getQuestions().size();
            }

            average /= questionnaires.size();
        }

        // On signal que le score doit s'afficher avec 2 décimales
        return String.format("%.2f", average);
    }
}
