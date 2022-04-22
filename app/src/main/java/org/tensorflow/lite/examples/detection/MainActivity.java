package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
     Button logout ;
     String belt;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button)findViewById(R.id.button);
        logout=(Button)findViewById(R.id.Logout);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(MainActivity.this);
                altdial.setMessage("Are you wearing a belt or not?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                belt = "Yes";
                                Intent i=new Intent(MainActivity.this,DetectorActivity.class);
                                sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
                                editor = sharedPreferences.edit();
                                editor.putString("belted",belt);
                                editor.commit();
                                startActivity(i);
                            }
                        })
                      .setNegativeButton("No", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              belt = "No";
                              Intent i=new Intent(MainActivity.this,DetectorActivity.class);
                              sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
                              editor = sharedPreferences.edit();
                              editor.putString("belted",belt);
                              editor.commit();
                              startActivity(i);
                          }
                      });

                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("Alert !");
                alertDialog.show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                finish();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
