package bcleton.com.projetcleton.Controller.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bcleton.com.projetcleton.Controller.CreateQuestionnaireActivity;
import bcleton.com.projetcleton.Controller.MainActivity;
import bcleton.com.projetcleton.Model.Question;
import bcleton.com.projetcleton.R;
import bcleton.com.projetcleton.Utils;

public class CreateQuestionDialog extends Dialog implements View.OnClickListener{

    private CreateQuestionnaireActivity activity;
    private EditText wording;
    private RadioButton firstRadioButton;
    private RadioButton secondRadioButton;
    private RadioButton thirdRadioButton;
    private EditText firstAnswser;
    private EditText secondAnswser;
    private EditText thirdAnswser;

    public CreateQuestionDialog(CreateQuestionnaireActivity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_question_dialog);

        wording = (EditText)findViewById(R.id.wording);
        firstAnswser = (EditText)findViewById(R.id.firstAnswerText);
        secondAnswser = (EditText)findViewById(R.id.secondAnswerText);
        thirdAnswser = (EditText)findViewById(R.id.thirdAnswerText);

        firstRadioButton = (RadioButton)findViewById(R.id.firstAnswerButton);
        secondRadioButton = (RadioButton)findViewById(R.id.secondAnswerButton);
        thirdRadioButton = (RadioButton)findViewById(R.id.thirdAnswerButton);

        findViewById(R.id.onAddQuestion).setOnClickListener(this);
        findViewById(R.id.onCancelAddQuestion).setOnClickListener(this);
    }

    private void onAddQuestion() {

        String wordingText = wording.getText().toString();
        String firstAnswerText = firstAnswser.getText().toString();
        String secondAnswerText = secondAnswser.getText().toString();
        String thirdAnswerText = thirdAnswser.getText().toString();
        int index = getSelectedIndex();

        //On vérifie si tous les champs ont bien été complété
        if (!(Utils.isNullOrEmpty(wordingText)
                || Utils.isNullOrEmpty(firstAnswerText)
                || Utils.isNullOrEmpty(secondAnswerText)
                || Utils.isNullOrEmpty(thirdAnswerText)
                || index == -1)) {

            List<String> answers = new ArrayList<>();
            answers.add(firstAnswerText);
            answers.add(secondAnswerText);
            answers.add(thirdAnswerText);

            Question question = new Question(wordingText, answers, index);
            activity.addQuestion(question);
            dismiss();
        }
    }

    private int getSelectedIndex() {
        if (firstRadioButton.isChecked())
            return 0;

        if (secondRadioButton.isChecked())
            return 1;

        if (thirdRadioButton.isChecked())
            return 2;

        return -1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onAddQuestion:
                onAddQuestion();
                break;
            case R.id.onCancelAddQuestion:
                dismiss();
                break;
        }
    }

}
