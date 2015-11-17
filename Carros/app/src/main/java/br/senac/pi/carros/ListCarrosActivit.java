package br.senac.pi.carros;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import br.senac.pi.carros.domains.Carro;
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

        //Chamar Listener Delet
        listView.setOnItemClickListener(deletarItem());
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

    //Recupera o item do banco pelo id e faz o delet
    private AdapterView.OnItemClickListener deletarItem() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final long itemSelecionado = id;
                AlertDialog.Builder builder = new AlertDialog.Builder(ListCarrosActivit.this);
                builder.setTitle("Pergunta");
                builder.setMessage("Deseja excluir o item?");
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ListCarrosActivit.this, "Clicou em editar", Toast.LENGTH_LONG).show();
                    }
                });
builder.setNegativeButton("Deletar", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int id) {
        Log.i("carro", "ID do item selecionado" + itemSelecionado);
        Carro carro = new Carro();
        carro.setId(itemSelecionado);
        carrosDB.delete(carro);
    }
});
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };

    }
}
