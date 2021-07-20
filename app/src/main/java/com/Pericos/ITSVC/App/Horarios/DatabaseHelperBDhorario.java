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



public class DatabaseHelperBDhorario extends SQLiteOpenHelper {

    public static String DBNOMBRE= "HorarioDos.db";
    public static String DBLOCACION= "/data/data/com.Pericos.ITSVC.App/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public int dbversiony=1;


    public DatabaseHelperBDhorario(Context context){
    super(context, DBNOMBRE,null,1);
    this.mContext=context;
}

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DBNOMBRE).getPath();
        if (mDatabase !=null  && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if (mDatabase!=null){
            mDatabase.close();
        }
    }






    /////////////////////////

/*
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
*/



    private boolean checkDataBase(){
        //  this.getReadableDatabase();

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DBLOCACION ;
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
            InputStream myI = mContext.getAssets().open(DBNOMBRE);
            OutputStream myO = new FileOutputStream(DBLOCACION + DBNOMBRE);
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
/*
    public void CheckDatabase(){
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

    }
*/


    /////////////////////////
    public void StartWork(){

//      dbpathy=mcontext.getFilesDir().getParent()+"/databases/";

        if(!checkDataBase()){
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

    public void open(){


      //  Toast.makeText(mcontext, dbruta+dbnombre, Toast.LENGTH_LONG).show();
//        mainActivity.ETselecBDGuar.getText().toString();


    }

}
