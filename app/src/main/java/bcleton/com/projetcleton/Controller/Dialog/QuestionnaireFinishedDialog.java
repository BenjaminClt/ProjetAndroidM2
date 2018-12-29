package bcleton.com.projetcleton.Controller.Dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import bcleton.com.projetcleton.Controller.QuestionnaireActivity;
import bcleton.com.projetcleton.Model.Questionnaire;
import bcleton.com.projetcleton.R;

public class QuestionnaireFinishedDialog extends Dialog implements View.OnClickListener {
    private Questionnaire questionnaire;
    private QuestionnaireActivity activity;

    public QuestionnaireFinishedDialog(QuestionnaireActivity activity, Questionnaire questionnaire) {
        super(activity);
        this.activity = activity;
        this.questionnaire = questionnaire;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.questionnaire_finished_dialog);

        findViewById(R.id.end_dialog).setOnClickListener(this);
        ((TextView)findViewById(R.id.score_end)).setText(Integer.toString(questionnaire.getScore()));
        ((TextView)findViewById(R.id.total_question_count_end)).setText(Integer.toString(questionnaire.getQuestions().size()));
    }

    @Override
    public void onClick(View v) {
        // Le questionnaire est terminé on signal qu'il a été fait et on retourne au menu principale
        questionnaire.setDone(true);
        dismiss();
        activity.backToMaintActivity();
    }
}
