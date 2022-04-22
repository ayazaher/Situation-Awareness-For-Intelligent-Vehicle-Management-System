package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetActivity extends AppCompatActivity {
    EditText pass1, pass2;
    String user, Pass_1, Pass_2;
    CarDBhelper carDBhelper;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        carDBhelper = new CarDBhelper(this);
        pass1 = (EditText) findViewById(R.id.Pass1);
        pass2 = (EditText) findViewById(R.id.Pass2);
        ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = getIntent().getExtras().getString("User");
                Pass_1 = pass1.getText().toString();
                carDBhelper.Update_password(user,Pass_1);
                Toast.makeText(getApplicationContext(),"Password Reset Successfuly :) ", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ForgetActivity.this,LoginActivity.class);
                startActivity(i);


            }
        });
    }
}