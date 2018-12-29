package bcleton.com.projetcleton.Controller;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import bcleton.com.projetcleton.Controller.Dialog.QuestionnaireFinishedDialog;
import bcleton.com.projetcleton.Data.QuestionnaireManager;
import bcleton.com.projetcleton.Model.Question;
import bcleton.com.projetcleton.Model.Questionnaire;
import bcleton.com.projetcleton.R;
import bcleton.com.projetcleton.Utils;

public class QuestionnaireActivity extends AppCompatActivity implements View.OnClickListener{

    private Questionnaire questionnaire;
    private Question question;
    private ArrayList<Integer> remainingQuestions;

    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;

    private int totalQuestionCount;
    private int questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        Intent intent = getIntent();
        remainingQuestions = intent.getIntegerArrayListExtra("remainingQuestions");
        questionnaire = QuestionnaireManager.getInstance().getQuestionnaire(intent.getStringExtra("categoryName"));

        // On choisit un index au hasard parmis les ceux des questions restantes.
        int randomIndex = Utils.getRandomNumber(0, remainingQuestions.size());
        question = questionnaire.getQuestions().get(remainingQuestions.get(randomIndex));

        // On enlève ensuite cet index de la liste pour ne pas retomber dessus.
        remainingQuestions.remove(randomIndex);

        initialize();
    }

    private void initialize() {
        answer1 = (RadioButton)findViewById(R.id.answer1);
        answer2 = (RadioButton)findViewById(R.id.answer2);
        answer3 = (RadioButton)findViewById(R.id.answer3);

        totalQuestionCount = questionnaire.getQuestions().size();

        // Le numéro de la question est le nombre de question total moins le nombre de questions restantes
        questionId = totalQuestionCount - remainingQuestions.size();

        ((TextView)findViewById(R.id.current_category_name)).setText(questionnaire.getCategory());
        ((TextView)findViewById(R.id.current_question_id)).setText(Integer.toString(questionId));
        ((TextView)findViewById(R.id.total_question_count)).setText(Integer.toString(totalQuestionCount));
        ((TextView)findViewById(R.id.current_question_wording)).setText(question.getWording());
        answer1.setText(question.getAnswers().get(0));
        answer2.setText(question.getAnswers().get(1));
        answer3.setText(question.getAnswers().get(2));

        findViewById(R.id.validate_question).setOnClickListener(this);
        findViewById(R.id.stop_questionnaire).setOnClickListener(this);
    }

    private void startNextQuestion(){
        int answer = getSelectedAnswer();

        if (answer == -1) {
            Utils.showToast(this, "Vous devez sélectionner une réponse.");
        }
        else {
            if (question.getGoodAnswerIndex() == answer) {
                questionnaire.setScore(questionnaire.getScore() + 1);
            }

            // Fin du questionnaire, on montre la fenêtre de dialog annonçant la fin
            if (totalQuestionCount == questionId) {
                QuestionnaireFinishedDialog dialog = new QuestionnaireFinishedDialog(this, questionnaire);
                dialog.show();
            }
            else {
                Intent intent = new Intent(this, QuestionnaireActivity.class);
                intent.putExtra("categoryName", questionnaire.getCategory());
                intent.putIntegerArrayListExtra("remainingQuestions", remainingQuestions);

                startActivity(intent);
            }
        }
    }

    private int getSelectedAnswer() {
        if (answer1.isChecked())
            return 0;

        if (answer2.isChecked())
            return 1;

        if (answer3.isChecked())
            return 2;

        return -1;
    }

    public void backToMaintActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.validate_question:
                startNextQuestion();
                break;

            case R.id.stop_questionnaire:
                // Le questionnaire a été stoppé, on signal qu'il n'a pas été fait et on réinitialise son score à 0
                questionnaire.setDone(false);
                questionnaire.setScore(0);
                backToMaintActivity();
                break;
        }
    }
}
