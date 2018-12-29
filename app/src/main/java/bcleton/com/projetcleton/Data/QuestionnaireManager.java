package bcleton.com.projetcleton.Data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bcleton.com.projetcleton.App;
import bcleton.com.projetcleton.Model.Question;
import bcleton.com.projetcleton.Model.Questionnaire;

public class QuestionnaireManager {
    private final String QUESTIONNAIRES_PATH = "listQuestionnaires";
    private static QuestionnaireManager instance = null;
    private List<Questionnaire> questionnaires;

    // Constructeur privé pour que seul cette classe puisse s'instancier elle-même
    private QuestionnaireManager() {
        loadQuestionnaires();
    }

    public static QuestionnaireManager getInstance() {
        if (instance == null) {
            instance = new QuestionnaireManager();
        }

        return instance;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public List<Questionnaire> getNotDoneQuestionnaires() {
        List<Questionnaire> result = new ArrayList<>();

        for(Questionnaire questionnaire : questionnaires) {
            if (!questionnaire.isDone()) {
                result.add(questionnaire);
            }
        }

        return result;
    }

    public void addQuestionnaire(Questionnaire questionnaire) {
        if (!exists(questionnaire.getCategory())) {
            questionnaires.add(questionnaire);
        }
    }

    public Questionnaire getQuestionnaire(String category) {
        for(Questionnaire questionnaire : questionnaires) {
            if (questionnaire.getCategory().equals(category)) {
                return questionnaire;
            }
        }

        return null;
    }

    public void resetAllScores() {
        for(Questionnaire questionnaire : questionnaires) {
            questionnaire.setScore(0);
            questionnaire.setDone(false);
        }
    }

    public boolean exists(String categoryName) {
        for(Questionnaire questionnaire : questionnaires) {
            if (questionnaire.getCategory().equals(categoryName))
                return true;
        }

        return false;
    }

    public void saveQuestionnaires() {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(App.getAppContext());
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        // On crée une instance de la classe Gson qui va nous permettre de sérializer notre liste
        // de questionnaires en un string qu'on enregistre ensuite dans les SharedPreferences
        // mis à disposition par Android

        Gson gson = new Gson();
        String json = gson.toJson(questionnaires);
        prefsEditor.putString(QUESTIONNAIRES_PATH, json);
        prefsEditor.commit();
    }

    private void loadQuestionnaires() {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(App.getAppContext());

        // De la même façon on crée une instance de la classe Gson pour désérializer notre liste
        // récupérée dans les SharedPreferences

        Gson gson = new Gson();
        String json = mPrefs.getString(QUESTIONNAIRES_PATH, "");
        if (json.isEmpty()) {
            questionnaires = createDemoQuestionnaires();

        } else {
            Questionnaire[] tmp = gson.fromJson(json, Questionnaire[].class);
            questionnaires = Arrays.asList(tmp);
            questionnaires = new ArrayList<>(questionnaires);
        }
    }

    private List<Questionnaire> createDemoQuestionnaires() {
        List<Questionnaire> demo = new ArrayList<>();

        String q1_category = "Préhistoire";
        List<Question> q1_questions = new ArrayList<Question>(){{
            add(new Question("Les dinosaures ont vécus sur la terre il y a...", Arrays.asList("800 millions d'années", "230 millions d'années", "100 millions d'années"), 1));
            add(new Question("Les dinosaures étaient des êtres", Arrays.asList("Mammifères", "Vivipares", "Ovipares"), 2));
            add(new Question("Les dinosaures ont disparuus il y a...", Arrays.asList("20 millions d'années", "65 millions d'années", "105 millions d'années"), 1));
            add(new Question("On prétend que les dinosaures ont disparu à cause...", Arrays.asList("D'éruptions volcaniques", "De la chute d'une météorite", "D'une inondation à la surface de la planète"), 1));
            add(new Question("Qui étaient les australopithèques ?", Arrays.asList("Les habitants de l'Australie", "Les premiers singes bipèdes et premiers ancètres de l'Homme", "Les ancètres directs de l'Homme"), 1));
            add(new Question("Les autralopithèques sont apparus en Afrique il y a...", Arrays.asList("100 000 ans", "5 millions d'années", "10 millions d'années"), 1));
            add(new Question("Le feu à été découvert par...", Arrays.asList("Les australopithèques", "L'Homme de Cro-Magnon", "L'Homo erectus"), 2));
            add(new Question("La préhistoire se termine à...", Arrays.asList("l'invention de l'écriture", "l'invention de la roue", "la naissance de Jésus-Christ"), 0));
            add(new Question("La paléontologie est la science qui étudie", Arrays.asList("les premiers êtres vivants à travers les fossiles", "les pollens des plantes fossiles", "les restes des momies égyptiennes"), 0));
            add(new Question("Comment peut-on dater les vestiges de la Préhistoire ?", Arrays.asList("C'est impossible", "Grâce au carbone 14", "Grâce à la chronologie des fossiles"), 1));
        }};

        String q2_category = "Informatique";
        List<Question> q2_questions = new ArrayList<Question>(){{
            add(new Question("La partie de l'ordinateur qui réalise la multiplication est", Arrays.asList("L'unité centrale", "L'unité arithmétique et logique", "le multiplexeur"), 1));
            add(new Question("Un co-processeur est", Arrays.asList("Un périphérique", "Une unité d'échange", "Un 'supplément' à l'unité arthmétique et logique"), 2));
            add(new Question("Dans un ordinateur, les instructions sont stockées", Arrays.asList("sur le disque dur", "en mémoire centrale à coté des variables", "dans une unité d'échange"), 1));
            add(new Question("Assembler le code de l'utilisateur et les bibliothèques fournies par le constructeur est précisement le rôle", Arrays.asList("de l'assembleur", "du compilateur", "de l'éditeur de liens"), 2));
            add(new Question("Chasser l'intrus parmi les langages de programmation suivants", Arrays.asList("PL1", "Linux", "Fortran"), 1));
        }};

        String q3_category = "Littérature";
        List<Question> q3_questions = new ArrayList<Question>(){{
            add(new Question("L'auteur des fleurs du mal est ...", Arrays.asList("Victor Hugo", "Charles Baudelaire", "Lawrence d'Arabie"), 1));
            add(new Question("Le Da Vinci Code a été écrit par ...", Arrays.asList("Stephen King", "Umberto Ecco", "Dan Brown"), 2));
            add(new Question("Georges Sand est une écrivain du ...", Arrays.asList("18ème siècle", "19ème siècle", "20ème siècle"), 1));
        }};


        demo.add(new Questionnaire(q1_category, q1_questions));
        demo.add(new Questionnaire(q2_category, q2_questions));
        demo.add(new Questionnaire(q3_category, q3_questions));

        return demo;
    }
}
