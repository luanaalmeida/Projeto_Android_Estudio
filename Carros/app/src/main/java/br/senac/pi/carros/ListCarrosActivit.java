package br.senac.pi.carros;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import br.senac.pi.carros.domains.AlteraCarroActivit;
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
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final long itemSelecionado = id;
               //alerta para escolher deletar ou alterar
                AlertDialog.Builder builder = new AlertDialog.Builder(ListCarrosActivit.this);
                builder.setTitle("Pergunta");
                //mensagem para o usuario setMessage
                builder.setMessage("Deseja excluir o item?");
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    //chamada para ometodo que fara a exclusao
                    public void onClick(DialogInterface dialog, int id) {
                        String codigo;
                        Carro carro = new Carro();
                        //select * from carro
                        Cursor Carro = database.query("carro", campos, null, null, null, null, null);
                        Carro.moveToPosition(position);
                        codigo = Carro.getString(Carro.getColumnIndexOrThrow("_id"));

                        Intent intent = new Intent(getApplicationContext(), AlteraCarroActivit.class);
                        intent.putExtra("id", codigo);
                        startActivity(intent);
                        finish();

                    }
                });

        builder.setNegativeButton("deletar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
