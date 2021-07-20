package com.Pericos.ITSVC.App.Horarios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;



public class DatabaseHelperBDproto extends SQLiteOpenHelper {

    Context mcontext;


    public static String dbpathy= Environment.getExternalStorageDirectory().getPath()+"/";
    public static String dbnamey=  "HorarioDos.db";
    public static String dbpathydata= "/data/data/com.Pericos.ITSVC.App/databases/";


    public int dbversiony=1;

    /*String dir = Environment.getExternalStorageDirectory().getPath();
    File dbfile = new File(dir+"/EjemNumUno.db");*/


    public DatabaseHelperBDproto(Context context){
    super(context, dbnamey,null,1);
    mcontext=context;
}


/////////////////////////
//    public DatabaseHelper(Context context, String name, int version){
//        super(context, name, null, version);
//
//
//        this.mcontext = context;
//        this.DbName= name;
//
//        DbName ="EjemNumUno2.db";
//        DbPath="/storage/emulated/0/Android/data/" +mcontext.getPackageName() +"/";
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /////////////////////////

    private boolean ExistDataBase(){
        SQLiteDatabase checkDB = null;
        try {
            String myPath = dbpathydata+dbnamey;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
    catch(
    SQLiteException e){

        //database does't exist yet.

    }
    return true;

//        File myFiley = new File(dbpathy+dbnamey);
//        return myFiley.exists();
    }



    private boolean checkDataBase(){
        //  this.getReadableDatabase();

        SQLiteDatabase checkDB = null;

        try{
            String myPath = dbpathydata ;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }


    /////////////////////////



    /////////////////////////

    private void copydatabasey(){

        try {
            InputStream myI = mcontext.getAssets().open(dbnamey);
            OutputStream myO = new FileOutputStream(dbpathy + dbnamey);
            byte[] myBayte = new byte[1024];
            int lenght;
            while ((lenght = myI.read(myBayte)) > 0) {
                myO.write(myBayte, 0, lenght);

            }
            myO.flush();
            myO.close();
            myI.close();
        }
        catch (Exception e) {

        }

    }

    /////////////////////////



    // pa checar si la base de datos esta disponible para abrirla o no
    public boolean CheckDatabase(){
        SQLiteDatabase checkdb = null;
        String Filepath = dbpathy+dbnamey;
        try {
            checkdb = SQLiteDatabase.openDatabase(Filepath, null,SQLiteDatabase.OPEN_READONLY);
        }catch (Exception e){}

        if (checkdb == null){
            copydatabasey();
        }
        else {
            SQLiteDatabase.openDatabase(Filepath,null,SQLiteDatabase.OPEN_READWRITE);
        }

        return false;
    }


    /////////////////////////
    public void StartWork(){

//      dbpathy=mcontext.getFilesDir().getParent()+"/databases/";

        if(!CheckDatabase()){
            this.getWritableDatabase();
            copydatabasey();
        }

    }
    /////////////////////////


    // para copiar la base de datos en la carpeta /data/data/databases
/*
    public void CopyDatabase(){
        this.getReadableDatabase();

        try{
            InputStream ios = mcontext.getAssets().open(DbName);
            String filepath = DbPath+DbName;
            OutputStream os = new FileOutputStream(filepath);

            byte[] buffer = new byte[1024];
            int lenght;


            while ((lenght = ios.read(buffer)) > 0) {
                os.write(buffer, 0, lenght);
            }

            os.flush();
            os.close();
            ios.close();

        } catch (IOException e) {}
    }
*/

//    public void OpenDatabase(){
//
//        String path = DbPath+DbName;
//
//        SQLiteDatabase.openDatabase(path,null,0);
//    }

    public static boolean copyFile(String from, String to) {
        boolean result = false;
        try{
            File dir = new File(to.substring(0, to.lastIndexOf('/')));
            dir.mkdirs();
            File tof = new File(dir, to.substring(to.lastIndexOf('/') + 1));
            int byteread;
            File oldfile = new File(from);
            if(oldfile.exists()){
                InputStream inStream = new FileInputStream(from);
                FileOutputStream fs = new FileOutputStream(tof);
                byte[] buffer = new byte[1024];
                while((byteread = inStream.read(buffer)) != -1){
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.close();
            }
            result = true;
        }catch (Exception e){
            Log.e("copyFile", "Error copiando archivo: " + e.getMessage());
        }
        return result;
    }


}
