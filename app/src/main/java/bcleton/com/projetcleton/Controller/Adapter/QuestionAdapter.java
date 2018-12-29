package bcleton.com.projetcleton.Controller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import bcleton.com.projetcleton.Model.Question;
import bcleton.com.projetcleton.R;

public class QuestionAdapter extends ArrayAdapter<Question> {

    private final Context context;

    public QuestionAdapter(Context context, List<Question> questions) {
        super(context, R.layout.question_row, questions);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.question_row, parent, false);

        TextView question_number = (TextView) rowView.findViewById(R.id.question_number);

        question_number.setText("Question " + (position + 1));

        return rowView;
    }

}
