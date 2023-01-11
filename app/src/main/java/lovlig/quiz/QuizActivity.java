package lovlig.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lovlig.quiz.model.Question;
import lovlig.quiz.model.QuestionClear;
import lovlig.quiz.utils.Csv;
import lovlig.quiz.utils.Toasts;

public class QuizActivity extends AppCompatActivity {

    int passed = 1;
    int mistakes = 0;
    String leftString = "1 из 20";
    private TextView textLeftQuestions;
    private TextView textQuestion;
    private TextView timer;
    private Button buttonFirst;
    private Button buttonSecond;
    private Button buttonThird;
    private Button buttonFourth;

    ArrayList<QuestionClear> questionsList;

    MediaPlayer click, error;
    CountDownTimer countDownTimer;

    int streak;
    long minutes, seconds;

    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        click = MediaPlayer.create(this, R.raw.click);
        error = MediaPlayer.create(this, R.raw.error);

        textLeftQuestions = findViewById(R.id.textView_leftQuestions_quiz);
        textQuestion = findViewById(R.id.textView_question_quiz);
        buttonFirst = findViewById(R.id.button_firstAnswer_quiz);
        buttonSecond = findViewById(R.id.button_secondAnswer_quiz);
        buttonThird = findViewById(R.id.button_thirdAnswer_quiz);
        buttonFourth = findViewById(R.id.button_fourthAnswer_quiz);
        timer = findViewById(R.id.textView_timer_quiz);

        Intent in = getIntent();
        streak = in.getIntExtra("streak", 0);

        questionsList = Csv.readData(QuizActivity.this);
        if (questionsList == null) return;

        init();

        setupNewQuestion();
        onClicks();
    }

    @SuppressLint("SetTextI18n")
    private void downTimer() {
        if (countDownTimer != null) countDownTimer.cancel();
        countDownTimer = new CountDownTimer(1200 * 1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                seconds = (millisUntilFinished / 1000) % 60;
                minutes = (millisUntilFinished/(1000 * 60)) % 60;
                String str = String.format("%d:%02d", minutes, seconds);
                timer.setText(str);
            }

            @Override
            public void onFinish() {
                Toasts.makeToastLong(getApplicationContext(),
                        "К сожалению, время вышло, тест не засчитался");
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(180);
                finish();
            }
        }.start();
    }

    private void onClicks() {
        buttonFirst.setOnClickListener(v -> answer(1));
        buttonSecond.setOnClickListener(v -> answer(2));
        buttonThird.setOnClickListener(v -> answer(3));
        buttonFourth.setOnClickListener(v -> answer(4));
    }

    private void init() {
        passed = 1;
        mistakes = 0;
        leftString = "1 из 20";
        downTimer();
        Collections.shuffle(questionsList);
    }

    private void answer(int answerId) {
        if (question.getCorrectId() == answerId) {
            click.start();
        } else {
            error.start();
            mistakes++;
            if (mistakes != 2) {
                Toasts.makeToast(getApplicationContext(), "Неверный ответ");
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(80);
            }
        }

        if (mistakes == 0 && passed == 20) {
            Toasts.makeToastLong(getApplicationContext(), "Тест успешно пройден!");
            successFinishTest();
            return;
        }

        if (mistakes == 1 && passed == 25) {
            Toasts.makeToastLong(getApplicationContext(), "Тест успешно пройден!");
            successFinishTest();
            return;
        }

        if (mistakes == 2) {
            Toasts.makeToast(getApplicationContext(), "Тест провален");
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(120);
            finish();
            return;
        }

        passed++;
        setupNewQuestion();
    }

    private void successFinishTest() {
        Intent intent = new Intent(this, EndActivity.class);
        long elapsedMin = 19 - minutes;
        long elapsedSec = 60 - seconds;
        String elapsedTime = String.format("%02d:%02d", elapsedMin, elapsedSec);
        intent.putExtra("time", elapsedTime);
        intent.putExtra("streak", streak + 1);
        startActivity(intent);
        finishAffinity();
    }

    private void setupNewQuestion() {
        // Перемешиваем варианты ответов
        String one = "", two = "", three = "", four = "";
        int correct = 0;
        List<Integer> answers = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(answers);
        for (int i = 0; i < answers.size(); i++) {
            switch (i) {
                case 0:
                    if (answers.get(i) == 0) {
                        one = questionsList.get(passed).getFirst(); correct = 1;
                    }
                    if (answers.get(i) == 1) one = questionsList.get(passed).getSecond();
                    if (answers.get(i) == 2) one = questionsList.get(passed).getThird();
                    if (answers.get(i) == 3) one = questionsList.get(passed).getFourth();
                    break;
                case 1:
                    if (answers.get(i) == 0) {
                        two = questionsList.get(passed).getFirst(); correct = 2;
                    }
                    if (answers.get(i) == 1) two = questionsList.get(passed).getSecond();
                    if (answers.get(i) == 2) two = questionsList.get(passed).getThird();
                    if (answers.get(i) == 3) two = questionsList.get(passed).getFourth();
                    break;
                case 2:
                    if (answers.get(i) == 0) {
                        three = questionsList.get(passed).getFirst(); correct = 3;
                    }
                    if (answers.get(i) == 1) three = questionsList.get(passed).getSecond();
                    if (answers.get(i) == 2) three = questionsList.get(passed).getThird();
                    if (answers.get(i) == 3) three = questionsList.get(passed).getFourth();
                    break;
                case 3:
                    if (answers.get(i) == 0) {
                        four = questionsList.get(passed).getFirst(); correct = 4;
                    }
                    if (answers.get(i) == 1) four = questionsList.get(passed).getSecond();
                    if (answers.get(i) == 2) four = questionsList.get(passed).getThird();
                    if (answers.get(i) == 3) four = questionsList.get(passed).getFourth();
                    break;
            }
        }

        question = new Question(
                questionsList.get(passed).getQuestion(),
                one, two, three, four, correct
        );

        if (mistakes == 0) leftString = passed + " из 20";
        else if (mistakes == 1) leftString = passed + " из 25";
        else return;

        textLeftQuestions.setText(leftString);
        textQuestion.setText(question.getQuestion());
        buttonFirst.setText(question.getFirst());
        buttonSecond.setText(question.getSecond());
        buttonThird.setText(question.getThird());
        buttonFourth.setText(question.getFourth());
    }

    @Override
    public void finish() {
        super.finish();
        if (countDownTimer != null) countDownTimer.cancel();
    }
}