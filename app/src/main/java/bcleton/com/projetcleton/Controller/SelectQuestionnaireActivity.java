package bcleton.com.projetcleton.Controller;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import bcleton.com.projetcleton.Controller.Adapter.QuestionnaireAdapter;
import bcleton.com.projetcleton.Data.QuestionnaireManager;
import bcleton.com.projetcleton.Model.Questionnaire;
import bcleton.com.projetcleton.R;

public class SelectQuestionnaireActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Questionnaire> notDoneQuestionnaires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_questionnaire);
        findViewById(R.id.back_button).setOnClickListener(this);

        notDoneQuestionnaires = QuestionnaireManager.getInstance().getNotDoneQuestionnaires();
        QuestionnaireAdapter adapter = new QuestionnaireAdapter(this, notDoneQuestionnaires);

        ListView questionnairesList = (ListView)findViewById(R.id.questionnairesList);
        questionnairesList.setAdapter(adapter);

        questionnairesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startQuestionnaire((Questionnaire)parent.getItemAtPosition(position));
            }
        });

    }

    private void startQuestionnaire(Questionnaire questionnaire) {
        ArrayList<Integer> remainingQuestions = new ArrayList<>();

        // On initialize la liste des index des questions restantes
        for(int i = 0; i < questionnaire.getQuestions().size(); i++) {
            remainingQuestions.add(i);
        }

        Intent intent = new Intent(this, QuestionnaireActivity.class);
        intent.putExtra("categoryName", questionnaire.getCategory());
        intent.putIntegerArrayListExtra("remainingQuestions", remainingQuestions);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
