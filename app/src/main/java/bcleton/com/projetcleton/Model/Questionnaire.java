package bcleton.com.projetcleton.Model;

import java.util.List;

public class Questionnaire {
    private String category;
    private List<Question> questions;
    private int score = 0;
    private boolean done = false;

    public Questionnaire(String category, List<Question> questions) {
        this.category = category;
        this.questions = questions;
    }

    public String getCategory() {
        return category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean value) {
        done = value;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
