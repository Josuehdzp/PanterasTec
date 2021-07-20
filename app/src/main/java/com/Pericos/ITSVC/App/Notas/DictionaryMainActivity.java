package com.Pericos.ITSVC.App.Notas;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.Pericos.ITSVC.App.Horarios.DatabaseHelper;
import com.Pericos.ITSVC.App.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class DictionaryMainActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    public static ArrayList<StringsNotas> data;
    DatabaseHelper db ;
    ArrayList<String> titulocombimelist;
    ArrayList<String> cuerpocombimelist;
    ArrayList<String> fechacombimelist;
    ArrayList<String> horacombimelist;
    ArrayList<String> colorcombimelist;


    LinkedHashMap<String,String> namelist;
    LinkedHashMap<String,String> namelista;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notas_inicio);


        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        db= new DatabaseHelper(this, "HorarioDos.db", 1);
        searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Busca");
        searchView.setQueryRefinementEnabled(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<StringsNotas>();
        fetchData();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {return  false; }

            @Override
            public boolean onQueryTextChange(String newText) {


                newText = newText.toLowerCase();

                final ArrayList<StringsNotas> filteredList = new ArrayList<StringsNotas>();

                for (int i = 0; i < titulocombimelist.size(); i++) {

                    final String text = titulocombimelist.get(i).toLowerCase();
                    if (text.contains(newText)) {

                        filteredList.add(new StringsNotas(titulocombimelist.get(i),cuerpocombimelist.get(i), fechacombimelist.get(i), fechacombimelist.get(i), fechacombimelist.get(i)));
                    }
                }
                adapter = new CustomAdapter(filteredList);
                recyclerView.setAdapter(adapter);


                return true;
            }
        });


    }
    public void fetchData() {
        db =new DatabaseHelper(this,"HorarioDos.db", 1);
        try {

            db.CheckDb();
            //db.openDataBase();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        namelist=new LinkedHashMap<>();
        int ii;
        int i3;

        SQLiteDatabase sd = db.getReadableDatabase();

        Cursor cursor = sd.query("Notas" ,null, null, null, null, null, null);
        ii=cursor.getColumnIndex("TituloNota");
        i3=cursor.getColumnIndex("TituloNota");

        titulocombimelist=new ArrayList<String>();
        cuerpocombimelist= new ArrayList<String>();
        fechacombimelist= new ArrayList<String>();
        horacombimelist= new ArrayList<String>();
        colorcombimelist= new ArrayList<String>();

        while (cursor.moveToNext()){
            namelist.put(cursor.getString(ii), cursor.getString(cursor.getColumnIndex("CuerpoNota")));

        }
        while (cursor.moveToNext()){
            namelista.put(cursor.getString(i3), cursor.getString(cursor.getColumnIndex("FechaNota")));

        }


        Iterator entries = namelist.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();
            Map.Entry otrothisEntry = (Map.Entry) entries.next();
            titulocombimelist.add(String.valueOf(thisEntry.getKey()));
            cuerpocombimelist.add("- "+String.valueOf(thisEntry.getValue()));
            fechacombimelist.add("- "+String.valueOf(otrothisEntry.getValue()));
        }



        for (int i = 0; i < titulocombimelist.size(); i++) {
          data.add(new StringsNotas(titulocombimelist.get(i), cuerpocombimelist.get(i), fechacombimelist.get(i), fechacombimelist.get(i), fechacombimelist.get(i)));
    }
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
