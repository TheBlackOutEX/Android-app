package lovlig.quiz.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import lovlig.quiz.R;
import lovlig.quiz.model.QuestionClear;

public class Csv {

    public static ArrayList<QuestionClear> readData(Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.questions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String line = "";

        ArrayList<QuestionClear> arrayList = new ArrayList<>();

        try {
            while ((line = reader.readLine()) != null) {
                //String[] tokens = line.split(",");
                try {
                    String[] tokens = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                    QuestionClear question = new QuestionClear(
                            tokens[0].trim().replaceAll("^\"|\"$", ""),
                            tokens[1].trim().replaceAll("^\"|\"$", ""),
                            tokens[2].trim().replaceAll("^\"|\"$", ""),
                            tokens[3].trim().replaceAll("^\"|\"$", ""),
                            tokens[4].trim().replaceAll("^\"|\"$", ""));

                    arrayList.add(question);

                    //Log.d("MainActivity", "Just Created " + question);

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }

            return arrayList;
        } catch (IOException e1) {
            Log.e("MainActivity", "Error" + line, e1);
            e1.printStackTrace();
            return null;
        }
    }

}
