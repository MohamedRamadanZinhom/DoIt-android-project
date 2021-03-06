package net.penguincoders.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.penguincoders.doit.Utils.DatabaseHandler;
import net.penguincoders.doit.Utils.DatabaseHelper;

public class LoginScreen extends AppCompatActivity {
    EditText e1 ,e2;
    Button b1;
    DatabaseHelper db;

    private static final String LOG_IN_KEY ="LogIn";
    private static final String PASSWORD_KEY ="Password";
    private static final String EMAIL_KEY ="Email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        db = new DatabaseHelper(this);
        e1 = (EditText) findViewById(R.id.email);
        e2 = (EditText) findViewById(R.id.password);
        b1 = (Button)findViewById(R.id.login);



        SharedPreferences sharedPreferences= this.getSharedPreferences(LOG_IN_KEY,0);

            e2.setText(sharedPreferences.getString(PASSWORD_KEY, "null"));
            e1.setText(sharedPreferences.getString(EMAIL_KEY , "null"));



        try {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = e1.getText().toString();
                    String password = e2.getText().toString();
                    Boolean chkemailpass = db.emailPassword(email , password);
                    if(chkemailpass==true){
                        DatabaseHandler.NAME = email;
                        Intent ic = new Intent(LoginScreen.this,MainActivity.class);
                        startActivity(ic);
                    }
                    else {
                        Toast.makeText(getApplicationContext() , "WRONG" , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void goback(View view) {
        Intent ic = new Intent(LoginScreen.this,SignUp.class);
        startActivity(ic);
    }

}