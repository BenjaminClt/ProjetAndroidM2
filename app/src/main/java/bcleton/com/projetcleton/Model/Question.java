package bcleton.com.projetcleton.Model;

import java.util.List;

public class Question {
    private String wording;
    private List<String> answers;
    private int goodAnswerIndex;

    public Question(String wording, List<String> answers, int goodAnswerIndex) {
        this.wording = wording;
        this.answers = answers;
        this.goodAnswerIndex = goodAnswerIndex;
    }

    public String getWording() {
        return wording;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getGoodAnswerIndex() {
        return goodAnswerIndex;
    }
}
