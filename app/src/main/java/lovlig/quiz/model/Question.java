package lovlig.quiz.model;

public class Question {

    private String question;
    private String first;
    private String second;
    private String third;
    private String fourth;
    private int correctId;

    public Question(String question, String first, String second,
                    String third, String fourth, int correctId) {

        this.question = question;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.correctId = correctId;
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

    public int getCorrectId() {
        return correctId;
    }

    public void setCorrectId(int correctId) {
        this.correctId = correctId;
    }
}
