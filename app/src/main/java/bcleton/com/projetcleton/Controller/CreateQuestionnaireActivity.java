package bcleton.com.projetcleton.Controller;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import bcleton.com.projetcleton.Controller.Adapter.QuestionAdapter;
import bcleton.com.projetcleton.Controller.Dialog.CreateQuestionDialog;
import bcleton.com.projetcleton.Data.QuestionnaireManager;
import bcleton.com.projetcleton.Model.Question;
import bcleton.com.projetcleton.Model.Questionnaire;
import bcleton.com.projetcleton.R;
import bcleton.com.projetcleton.Utils;

public class CreateQuestionnaireActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText newCategoryName;
    private List<Question> questions = new ArrayList<>();
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_questionnaire);

        newCategoryName = (EditText)findViewById(R.id.new_category_name);

        findViewById(R.id.validate_create_questionnaire).setOnClickListener(this);
        findViewById(R.id.cancel_create_questionnaire).setOnClickListener(this);
        findViewById(R.id.add_question).setOnClickListener(this);

        ListView questionsList = (ListView)findViewById(R.id.questionsList);

        adapter = new QuestionAdapter(this, questions);
        questionsList.setAdapter(adapter);
    }

    private void onValidate() {
        String name = newCategoryName.getText().toString();

        if (Utils.isNullOrEmpty(name)) {
            Utils.showToast(CreateQuestionnaireActivity.this, "Le nom de la catégorie doit être remplie.");
        }
        else if (questions.size() == 0) {
            Utils.showToast(CreateQuestionnaireActivity.this, "Le questionnaire doit contenir au moins une question.");
        }
        else if (QuestionnaireManager.getInstance().exists(name)) {
            Utils.showToast(CreateQuestionnaireActivity.this, "Cette catégorie existe déjà.");
        }
        else {
            Questionnaire questionnaire = new Questionnaire(name, questions);
            QuestionnaireManager.getInstance().addQuestionnaire(questionnaire);

            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void onAddQuestion() {
        CreateQuestionDialog dialog = new CreateQuestionDialog(this);
        dialog.show();
    }

    public void addQuestion(Question question) {
        questions.add(question);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.validate_create_questionnaire:
                onValidate();
                break;

            case R.id.cancel_create_questionnaire:
                finish();
                break;

            case R.id.add_question:
                onAddQuestion();
                break;
        }
    }
}
