package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Random;

public class ResultActivity extends AppCompatActivity {
     TextView object_name , Res , circle ;
     String ob_name , S , D  , M_of_object ,V_of_object  ;
     float Speed , Distance , Mass , Result , Mass_obj ;
     float V_final , V_obj  , Delta_v;
    Double Fatality , Injury ;
     int out_dynmical , out_data ;
     SharedPreferences sharedPreferences;
     CarDBhelper carDBhelper;

    String [] case_1 = {"Front","Rear"};
    String [] case_2 = {"Right Side","Left Side"};
    String [] a_mancol = {"Not Collision","Angle" ,"Head-On", "Sideswipe","Rear-End"};
     String [] r_condition = {"Rural","Urban" ,"Unknown"};
    String [] roadway = {"Off Roadway","On Roadway" , "Other/Unknown"};
    String [] intersection = {"Intersection","Non-Intersection" , "Unknown"};
    String [] a_Rest = {"Restraint Used","Restraint Not Used","Restraint Use Unknown"};
     String road_condition , interstate , Roadway , Intersection ,Time_data,Restraint ,impact, belted , T  ,mancol ;

     int veh_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //database
        carDBhelper = new CarDBhelper(this);
        // user and mass
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");
        // get belted
        belted = sharedPreferences.getString("belted", "");
        T = carDBhelper.get_type(user);
        //TextViews
        object_name = (TextView) findViewById(R.id.obj_name);
        Res = (TextView) findViewById(R.id.res);
        circle = (TextView) findViewById(R.id.circle);
        //Intent Values
        ob_name = getIntent().getExtras().getString("Obj_name");
        S = getIntent().getExtras().getString("speed");
        D = getIntent().getExtras().getString("distance");
        // convert to float
        Speed = Float.parseFloat(S);
        Distance = Float.parseFloat(D);
        // List of object
        String[] Static = {"traffic light", "fire hydrant", "stop sign", "parking meter", "bench",
                "backpack", "umbrella", "handbag", "chair", "keyboard"};

        String[] Dynmaic = {"person", "bicycle", "car", "motorcycle", "airplane"
                , "bus", "train", "truck", "bird", "cat", "dog", "horse", "sheep", "cow"
                , "elephant", "bear", "zebra", "giraffe"};
        //------------------------------------------------------------------------------------------------------------------------//
        // Mass
        if (T.equals("bmw") || T.equals("Bus")) {
            Mass = Float.parseFloat("41.23344");
        } else if (T.equals("Chevrolet pickup") || T.equals("Nissan Altima")) {
            Mass = Float.parseFloat("53.5597");
        } else if (T.equals("Chevrolet lampla") || T.equals("Dodge Caravan")) {
            Mass = Float.parseFloat("69.33657");
        } else if (T.equals("Jeep") || T.equals("Toyota Camry")) {
            Mass = Float.parseFloat("75.2167");
        } else if (T.equals("Ford pickup") || T.equals("Honda")) {
            Mass = Float.parseFloat("88.636");
        } else {
            Mass = Float.parseFloat("150.5874");
        }
        object_name.setText("Object detected : "+" "+ob_name+"");
        //-------------------------------------------------------------------------------------------------------------------------//
        // ----- Dynamical Model Results -----

        // Calculate force
        if (found_obj(ob_name, Static)) {

            // Case 1  ..  Rear-fornt and lateral Collison
            int i = new Random().nextInt(case_1.length);
            impact = (case_1[i]);
            Result = (Mass * Speed * Speed) / 2 * Distance;
            if (Result < 50.0) {
                out_dynmical = 1;
            } else if (Result < 100.0) {
                out_dynmical = 2;

            } else {
                out_dynmical = 3;
            }
        } else if (found_obj(ob_name, Dynmaic)) {
            // Case 2   ..  Side-Swip Collison
            int i = new Random().nextInt(case_2.length);
            impact = (case_2[i]);
            // Get mass and speed of object
            M_of_object = carDBhelper.get_mass(ob_name);
            V_of_object = carDBhelper.get_speed(ob_name);

            //floating
            Mass_obj = Float.parseFloat(M_of_object);
            V_obj = Float.parseFloat(V_of_object);

            // (1) ..  Calculate final velocity
            V_final = (Mass_obj * V_obj + Mass * Speed) / (Mass_obj + Mass);

            //  (2)  .. Delta v
            Delta_v = V_final - Speed;
            // (3)  .. Case belted or not

            Double F, I;
            if (belted.equals("Yes")) {
                F = (Delta_v / 69.2);
                Fatality = Math.pow(F, 4.57);
                I = (Delta_v / 66.7);
                Injury = Math.pow(I, 2.62);

            } else if (belted.equals("No")) {
                F = (Delta_v / 70.6);
                Fatality = Math.pow(F, 2.54);
                I = (Delta_v / 66.7);
                Injury = Math.pow(I, 2.22);
            }
            if (Injury <= 0.4 || Fatality <= 0.25) {
                out_dynmical = 1;
            } else if (Injury <= 0.6 || Fatality <= 0.5) {
                out_dynmical = 2;
            } else {
                out_dynmical = 3;
            }
        }
        else
        {
            impact = "Not Collision";
        }

        //--------------------------------------------------------------------------------------------------------------------//
        // ----- Data Driven -----
        int i = new Random().nextInt(r_condition.length);
        road_condition = (r_condition[i]);
        if (Speed > 60) {
            interstate = "Interstate";
        } else {
            interstate = "Non-Interstate";
        }
        int in = new Random().nextInt(roadway.length);
        Roadway = (roadway[in]);
        int inv = new Random().nextInt(intersection.length);
        Intersection = (intersection[inv]);
        Time_data = "Daytime";
        int resta = new Random().nextInt(a_Rest.length);
        Restraint = (a_Rest[resta]);
        int c = new Random().nextInt(a_mancol.length);
        mancol = (a_mancol[c]);
        veh_no = (int) (Math.random() * ((14 - 0) + 0)) + 0;
        //road_condition , interstate , Roadway , Intersection , mancol ,Restraint,Time_data, T , impact
        final  Naive_Classifier naive = new Naive_Classifier();
        out_data = naive.get_Severity(road_condition , interstate , Roadway , Intersection , mancol ,Time_data,Restraint, T , impact);
        //--------------------------------------------------------------------------------------------------------------------//
        // ----------------- Merge Results  -----------------
        if(out_dynmical > out_data || out_dynmical < out_data )
        {
            int z = out_data + out_dynmical / 2 ;
            if(z == 1)
            {
                Res.setText("Low");
                circle.setBackground(getResources().getDrawable(R.drawable.circle_low));
                AlertDialog.Builder altdial = new AlertDialog.Builder(ResultActivity.this);
                altdial.setMessage("Don't worry,Everything Oky :)").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent(ResultActivity.this,DetectorActivity.class);
                                startActivity(i);
                            }
                        });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("Notify");
                alertDialog.show();
            }
            else if (z == 2)
            {
                Res.setText("Medium");
                circle.setBackground(getResources().getDrawable(R.drawable.color_medium));
                AlertDialog.Builder altdial = new AlertDialog.Builder(ResultActivity.this);
                altdial.setMessage("There is May be a Problem,Take care !!").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent(ResultActivity.this,DetectorActivity.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("NOT OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String x = "123";
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:"+ x));
                                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                                    return;
                                }
                                startActivity(intent);
                            }
                        });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("Alert !");
                alertDialog.show();
            }
            else
            {
                Res.setText("High");
                circle.setBackground(getResources().getDrawable(R.drawable.circle));
                AlertDialog.Builder altdial = new AlertDialog.Builder(ResultActivity.this);
                altdial.setMessage("There is a Problem").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String x = "122";
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:"+ x));
                                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                                    return;
                                }
                                startActivity(intent);
                            }
                        });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("WARNING !!");
                alertDialog.show();
            }
        }
        else
        {
            if(out_dynmical == 1)
            {
                Res.setText("Low");
                circle.setBackground(getResources().getDrawable(R.drawable.circle_low));
                AlertDialog.Builder altdial = new AlertDialog.Builder(ResultActivity.this);
                altdial.setMessage("Don't worry,Everything Oky :)").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent(ResultActivity.this,DetectorActivity.class);
                                startActivity(i);
                            }
                        });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("Notify");
                alertDialog.show();
            }
            else if(out_dynmical == 2)
            {
                Res.setText("Medium");
                circle.setBackground(getResources().getDrawable(R.drawable.color_medium));
                AlertDialog.Builder altdial = new AlertDialog.Builder(ResultActivity.this);
                altdial.setMessage("There is May be a Problem,Take care !!").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent(ResultActivity.this,DetectorActivity.class);
                                startActivity(i);
                            }
                        });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("Alert !");
                alertDialog.show();
            }
            else
            {
                Res.setText("High");
                circle.setBackground(getResources().getDrawable(R.drawable.circle));
                AlertDialog.Builder altdial = new AlertDialog.Builder(ResultActivity.this);
                altdial.setMessage("There is a Problem").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String x = "122";
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:"+ x));
                                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                                    return;
                                }
                                startActivity(intent);
                            }
                        });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("WARNING !!");
                alertDialog.show();
            }
        }




    }


    public boolean found_obj(String s , String [] arr)
    {
        boolean found = false;
        for (int i = 0 ; i<arr.length;i++)
        {
         if(s.equals(arr[i]))
         {
             found = true ;
             break;
         }
        }
        if (found == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
