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

public class ScoreAdapter extends ArrayAdapter<Questionnaire> {

    private final Context context;
    private final List<Questionnaire> questionnaires;

    public ScoreAdapter(Context context, List<Questionnaire> questionnaires) {
        super(context, R.layout.score_row, questionnaires);
        this.context = context;
        this.questionnaires = questionnaires;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.score_row, parent, false);

        TextView category = (TextView) rowView.findViewById(R.id.questionnaire_category);
        TextView score = (TextView) rowView.findViewById(R.id.questionnaire_score);
        TextView countAnswers = (TextView) rowView.findViewById(R.id.questionnaire_count_answers);

        Questionnaire questionnaire = questionnaires.get(position);

        category.setText(questionnaire.getCategory());
        score.setText(Integer.toString(questionnaire.getScore()));
        countAnswers.setText(Integer.toString(questionnaire.getQuestions().size()));

        return rowView;
    }

}
