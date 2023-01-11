package lovlig.quiz.model;

import androidx.annotation.NonNull;

public class QuestionClear {

    private String question;
    private String first;
    private String second;
    private String third;
    private String fourth;

    public QuestionClear(String question, String first, String second,
                         String third, String fourth) {

        this.question = question;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    @NonNull
    @Override
    public String toString() {
        return question + " | " + first + " | " + second + " | " + third + " | " + fourth;
    }
}
