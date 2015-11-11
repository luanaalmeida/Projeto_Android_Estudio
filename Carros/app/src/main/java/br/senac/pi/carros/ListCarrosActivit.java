package br.senac.pi.carros;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import br.senac.pi.carros.domains.carrosDB;

public class ListCarrosActivit extends AppCompatActivity {

    private CursorAdapter dataSource;
    private SQLiteDatabase database;
    private static final String campos[] = {"nome","marca","_id"};
    ListView listView;
    carrosDB carrosDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_carros);
        listView = (ListView) findViewById(R.id.listView);
        carrosDB = new carrosDB (this);
        database = carrosDB.getWritableDatabase();
        findViewById(R.id.bntListarCarros).setOnClickListener(listarCarros());

    }

    private View.OnClickListener listarCarros(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Cursor carros = database.query("carro", campos , null, null, null, null, null);
                if (carros.getCount()>0){
                    dataSource = new SimpleCursorAdapter(ListCarrosActivit.this, R.layout.row , carros, campos, new int[]{R.id.txtNomeCarro, R.id.txtMarCarro});
                    listView.setAdapter(dataSource);
                }else {
                    Toast.makeText(ListCarrosActivit.this, getString(R.string.erro_registrador), Toast.LENGTH_LONG).show();
                }
            }
        };
    }
}
