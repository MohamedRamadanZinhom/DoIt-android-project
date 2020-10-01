package net.penguincoders.doit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class activity_details extends AppCompatActivity {

    private TextView textViewEmail,textViewTask;
    private Button button;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detatails);
        textViewEmail=findViewById(R.id.textViewEmail);
        textViewTask=findViewById(R.id.textViewTaskDetails);
        bundle = getIntent().getExtras();
        button = findViewById(R.id.button);

        if(bundle!=null){

            textViewTask.setText(bundle.getString("task"));
            textViewEmail.setText(bundle.getString("Email"));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ic = new Intent(activity_details.this,MainActivity.class);
               ic.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ic);
            }
        });

    }
}