package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView forget ;
    EditText user , pass ;
    Button login , Reg ;
    String txt1 , txt2;
    CheckBox check ;
    CarDBhelper carDBhelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        carDBhelper = new CarDBhelper(this);
        user = (EditText)findViewById(R.id.Login_username);
        pass = (EditText)findViewById(R.id.Login_password);

        login = (Button)findViewById(R.id.main_login);
        Reg = (Button)findViewById(R.id.main_Register);

        forget = (TextView)findViewById(R.id.forgetpassword);

        check=(CheckBox)findViewById(R.id.remember);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1=user.getText().toString();
                Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
                intent.putExtra("User",txt1);
                startActivity(intent);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1=user.getText().toString();
                txt2=pass.getText().toString();
                if(txt1.isEmpty()||txt2.isEmpty())
                {
                    Toast.makeText(getApplicationContext()," Not Complete Data ",Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean res = carDBhelper.Login_correct(txt1,txt2);
                    if (res==true)
                    {
                        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("user",txt1);
                        editor.commit();
                        Toast.makeText(getApplicationContext()," Welcome :) " ,Toast.LENGTH_LONG).show();
                        Intent i = new Intent (LoginActivity.this, MainActivity.class );
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Error , Retry again :( ",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (LoginActivity.this,RegisterActivity.class );
                startActivity(in);
            }
        });
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true"))
        {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_LONG).show();
                }
                else if(!buttonView.isChecked())
                {
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Un-checked",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
