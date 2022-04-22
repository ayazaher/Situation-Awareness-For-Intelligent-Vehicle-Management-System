package org.tensorflow.lite.examples.detection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarDBhelper extends SQLiteOpenHelper {
    private static String databasename = "Car_DB";
    SQLiteDatabase Car_DB;
    private String Driver = " create table driver( driv_id integer primary key autoincrement , " +
            "driv_name text not null , user_name text not null , password text not null , address text not null " +
            " , car_type text not null ) ";


    private String Vehical = "create table vehical( veh_num integer primary key autoincrement ," +
            " color text not null , model text not null , structure_type text not null, " +
            " Driv_id integer , FOREIGN KEY(Driv_id) REFERENCES driver (driv_id)   )";

    private String Object = "create table object( obj_name text primary key  ," +
            " speed text not null , volume text not null  , mass text not null ,width text not null , height text not null  )";

    private String Admin = "create table admin( admin_id integer primary key autoincrement  ," + " mobile integer not null  )";

    private String Police =  "create table police( pol_id integer primary key autoincrement  ," + " pol_num integer not null ,Admin_id integer , FOREIGN KEY(Admin_id) REFERENCES admin (admin_id)  )";

    private String Ambulance =  "create table Ambulance( amb_id integer primary key autoincrement  ," + " amb_num integer not null ,Admin_id integer , FOREIGN KEY(Admin_id) REFERENCES admin (admin_id)  )";

    private String object_type = "create table object_type( obj_id integer primary key autoincrement  ," +
            " obj_name text not null , type text not null    )";

    private String Crash =  "create table crash( vec_num text not null ," + " obj_name text not null,PRIMARY KEY (vec_num,obj_name) )";

    public CarDBhelper (Context context)
    {
        super(context,databasename,null,10);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Driver);
        db.execSQL(Vehical);
        db.execSQL(Object);
        db.execSQL(Admin);
        db.execSQL(Police);
        db.execSQL(Ambulance);
        db.execSQL(object_type);
        db.execSQL(Crash);
        db.execSQL("insert into object (obj_name, speed, volume, mass ,width ,height) "+
                " values('person','30','10','40','40','10') ");
        db.execSQL("insert into object (obj_name, speed, volume, mass ,width ,height) "+
                " values('bicycle','40','20','50','50','20') ");
        db.execSQL("insert into object (obj_name, speed, volume, mass ,width ,height) "+
                " values('car','50','30','60','90','70') ");
        db.execSQL("insert into object (obj_name, speed, volume, mass ,width ,height) "+
                " values('motorcycle','10','5','20','50','100') ");
        db.execSQL("insert into object (obj_name, speed, volume, mass ,width ,height) "+
                " values('bus','50','60','80','40','10') ");
        db.execSQL("insert into object (obj_name, speed, volume, mass ,width ,height) "+
                " values('train','30','10','90','40','10') ");
        db.execSQL("insert into object (obj_name, speed, volume, mass ,width ,height) "+
                " values('cat','30','10','70','40','10') ");
        db.execSQL("insert into object (obj_name, speed, volume, mass ,width ,height) "+
                " values('truck','30','10','110','40','10') ");
        db.execSQL("insert into object (obj_name, speed, volume, mass ,width ,height) "+
                " values('dog','30','10','15','40','10') ");
        db.execSQL("insert into object (obj_name, speed, volume, mass ,width ,height) "+
                " values('keyboard','30','10','15','40','10') ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS driver ");
        db.execSQL("DROP TABLE IF EXISTS vehical ");
        db.execSQL("DROP TABLE IF EXISTS object ");
        db.execSQL("DROP TABLE IF EXISTS  admin ");
        db.execSQL("DROP TABLE IF EXISTS police ");
        db.execSQL("DROP TABLE IF EXISTS Ambulance ");
        db.execSQL("DROP TABLE IF EXISTS object_type ");
        db.execSQL("DROP TABLE IF EXISTS crash ");
        onCreate(db);
    }
    public boolean insert_driver(String D_name , String D_user , String D_pass , String D_add , String D_car ) {

        Car_DB=getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("driv_name",D_name );
        row.put("user_name", D_user);
        row.put("password", D_pass );
        row.put("address", D_add);
        row.put("car_type", D_car);
        long ins = Car_DB.insert("driver",null,row);
        if(ins==-1)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean Login_correct(String user , String pass )
    {
        Car_DB=getReadableDatabase();
        String [] arg = {user,pass};
        Cursor cursor = Car_DB.rawQuery("select * from driver where user_name=? and password=? ",arg);
        //0 -> not found , 1 --> found
        if(cursor.getCount()>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public void Update_password(String Username,String new_Password )
    {
        SQLiteDatabase dp=this.getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put("password",new_Password);
        dp.update("driver",row,"user_name like ?",new String[]{Username});
        dp.close();
    }
    public String get_type(String user)
    {

        String y;
        Car_DB=getReadableDatabase();
        String []arg = {user};
        Cursor cursor=Car_DB.rawQuery("Select car_type from driver where user_name like ?",arg);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            y=cursor.getString(0);
            return y;
        }
        else
        {
            return "a";
        }

    }
    public String get_mass(String name)
    {

        String x;
        Car_DB=getReadableDatabase();
        String []arg = {name};
        Cursor cursor=Car_DB.rawQuery("Select mass from object where obj_name like ?",arg);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            x=cursor.getString(0);
            return x;
        }
        else
        {
            return "a";
        }

    }
    public String get_speed(String name)
    {

        String x;
        Car_DB=getReadableDatabase();
        String []arg = {name};
        Cursor cursor=Car_DB.rawQuery("Select speed from object where obj_name like ?",arg);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            x=cursor.getString(0);
            return x;
        }
        else
        {
            return "a";
        }

    }
}
