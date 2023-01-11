package lovlig.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import lovlig.quiz.R;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {
    public FirebaseAuth.AuthStateListener mAuthListener;
    public String mCurrentUserUid;
    public FirebaseAuth mAuth;
    public DatabaseReference mUserRefDatabase;
    Button button, online;
    private final List<Button> buttons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.action_logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), AuthActivity.class)));
            }
        });
        Button buttonStart = findViewById(R.id.button_start_main);


        buttonStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            startActivity(intent);
        });

    }
    public void setAuthInstance() {
        mAuth = FirebaseAuth.getInstance();
    }


    public void setAuthListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    setUserData(user);
                    getIntent();
                } else {
                    // User is signed out
                    goToLogin();
                }
            }
        };
    }
    public void setUserData(FirebaseUser user) {
        mCurrentUserUid = user.getUid();
    }
    public void setUsersDatabase() {
        mUserRefDatabase = FirebaseDatabase.getInstance().getReference().child("users");
    }
    private void goToLogin() {
        Intent intent = new Intent(this, AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // LoginActivity is a New Task
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // The old task when coming back to this activity should be cleared so we cannot come back to it.
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }

    @Override
    protected void onResume(){
        super.onResume();
        //startActivity(new Intent(getIntent()));
    }
    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.getItemId()==R.id.action_logout){
            logout();
            return true;
        }
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        mAuth.signOut();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Uncomment the below code to Set the message and title from the strings.xml file
        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
        builder.setMessage("Do you want to close this app?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });

        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        // alert.setTitle("CompQuiz");
        alert.show();
    }
}