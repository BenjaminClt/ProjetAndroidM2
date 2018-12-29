package bcleton.com.projetcleton.Controller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bcleton.com.projetcleton.Model.Questionnaire;
import bcleton.com.projetcleton.R;

public class QuestionnaireAdapter extends ArrayAdapter<Questionnaire> {

    private final Context context;
    private final List<Questionnaire> questionnaires;

    public QuestionnaireAdapter(Context context, List<Questionnaire> questionnaires) {
        super(context, R.layout.questionnaire_row, questionnaires);
        this.context = context;
        this.questionnaires = questionnaires;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.questionnaire_row, parent, false);

        TextView categoryName = (TextView) rowView.findViewById(R.id.select_q_name);
        TextView nbQuestions = (TextView) rowView.findViewById(R.id.select_q_nb_question);

        Questionnaire questionnaire = questionnaires.get(position);

        categoryName.setText(questionnaire.getCategory());
        nbQuestions.setText(Integer.toString(questionnaire.getQuestions().size()));

        return rowView;
    }

}