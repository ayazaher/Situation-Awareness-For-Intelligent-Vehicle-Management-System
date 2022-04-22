package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    CarDBhelper carDBhelper;
    EditText N_txt , U_txt , P_txt  , A_txt , C_txt;
    String str_1 , str_2 ,str_3 , str_4 ,str_5  ;
    Button Done ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        carDBhelper = new CarDBhelper(this);
        N_txt=(EditText)findViewById(R.id.Name_text);
        U_txt = (EditText)findViewById(R.id.username_text);
        P_txt = (EditText)findViewById(R.id.password_text);
        A_txt = (EditText)findViewById(R.id.add_text);
        C_txt = (EditText)findViewById(R.id.car_text);
        Done=(Button)findViewById(R.id.register);

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_1 = N_txt.getText().toString();
                str_2 = U_txt.getText().toString();
                str_3 = P_txt.getText().toString();
                str_4 = A_txt.getText().toString();
                str_5 = C_txt.getText().toString();
                if(str_1.isEmpty()||str_2.isEmpty()||str_3.isEmpty()||str_4.isEmpty()||str_5.isEmpty())
                {
                    Toast.makeText(getApplicationContext()," Not Complete Data ",Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean res = carDBhelper.insert_driver(str_1,str_2,str_3,str_4,str_5);
                    if(res == true) {
                        Toast.makeText(getApplicationContext(), " Register is Done ! , Thank You :) ", Toast.LENGTH_LONG).show();
                        N_txt.setText("");
                        U_txt.setText("");
                        P_txt.setText("");
                        A_txt.setText("");
                        C_txt.setText("");

                        Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
                        startActivity(intent);
                    }
                    else if (res==false)
                    {
                        Toast.makeText(getApplicationContext(), " Error :( ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
