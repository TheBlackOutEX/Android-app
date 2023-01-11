package lovlig.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import lovlig.quiz.utils.Toasts;

public class EndActivity extends AppCompatActivity {

    TextView textStreak;
    Button buttonAgain;
    int streak = 0;
    String time;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        buttonAgain = findViewById(R.id.button_again_end);
        textStreak = findViewById(R.id.textView_passedCount_end);

        Intent in = getIntent();
        streak = in.getIntExtra("streak", 0);
        time = in.getStringExtra("time");

        buttonAgain.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("streak", streak);
            startActivity(intent);
            finish();
        });

        if (streak == 3) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(7000);
            Toasts.makeToastLong(getApplicationContext(),
                    "Вот это серия! Вряд ли это сообщение кто-то увидит :(");
        }

        textStreak.setText("Подряд: " + (streak));
    }
}
