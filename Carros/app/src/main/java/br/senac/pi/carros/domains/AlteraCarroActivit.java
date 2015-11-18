package br.senac.pi.carros.domains;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.senac.pi.carros.R;

public class AlteraCarroActivit extends AppCompatActivity {

    private carrosDB carrosDB;
    private SQLiteDatabase db;
    private EditText edtAlteraNomeCarro, edtAlteraMarcaCarro;
    private TextView txtIdCarro;
    private String id;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_carro);
        id = getIntent().getStringExtra("id");
     //instanciando o carrosBD
        carrosDB = new carrosDB(this);

        //recuperação do textView e EditText do xml
        txtIdCarro = (TextView) findViewById(R.id.textIdCarro);
        edtAlteraNomeCarro = (EditText) findViewById(R.id.edtAlteraNomeCarro);
        edtAlteraMarcaCarro = (EditText) findViewById(R.id.edtAlteraMarcaCarro);

        //cursos consulta bo banco de dados
        cursor = carregaCarro(Integer.parseInt(id));

        //preenchendo os campos do xml com os dados dos carros para exibir na tela
        txtIdCarro.setText(cursor.getString(cursor.getColumnIndex("_id")));
        edtAlteraNomeCarro.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        edtAlteraMarcaCarro.setText(cursor.getString(cursor.getColumnIndexOrThrow("marca")));

//reculperaçao do botão do xml pelo id do button R.id.bntAlterarCarro
        findViewById(R.id.bntAlterarCarro).setOnClickListener(alterarCarro());

    }

// cursor = carregaCarro(Integer.parseInt(id)) reculperar
    private Cursor carregaCarro (int id) {
    db = carrosDB.getWritableDatabase();
        //arrey de dados
        String[] campos = {"_id", "nome", "marca"};
        String WhereArgs = String.valueOf(id);
        //SELECT * FROM CARRO WHERE _id = id->(int id)
        //null sao campos que nao quero chamar
        cursor = db.query("carro", campos, WhereArgs, null, null, null, null);
        if (cursor != null){
            //0+1 para o cursos ir para 1 (subir)
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

   // para reculperar o botton no  findViewById(R.id.bntAlterarCarro).setOnClickListener(alterarCarro());
    private View.OnClickListener alterarCarro(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = carrosDB.getWritableDatabase();
                ContentValues values = new ContentValues();
                String whereArgs = id;
                Log.i("curso", "ID capturado" + id);

                //put pega as alteraçoes do usuario e joga na tela depois de editado
                //ele pega seta na tabela carroDB nova atualização na tabela
                values.put("nome", edtAlteraNomeCarro.getText().toString());
                values.put("marca", edtAlteraMarcaCarro.getText().toString());

                //whereArgs id vindo a tela
                db.update("carro", values, "_id" + whereArgs, null);
                db.close();
            }
        };
    }
}
