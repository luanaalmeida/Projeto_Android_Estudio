package br.senac.pi.carros.domains;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 06/11/2015.
 */
//extends SQLiteOpenHelper banco de dados
public class carrosDB extends SQLiteOpenHelper{

    private static final String TAG = "sql";

    //nome do banco ("cursoAndroid.sqlite")
    // private static final String NOME_BANCO (constante do tipo string)
    private static final String NOME_BANCO = "cursoAndroid.sqlite";
    private static final int VERSAO_BANCO = 1;

    //contrutor do SQLiteOpenHelper
    public carrosDB (Context context){

        //context nome do banco, factory, versao\
        super(context, NOME_BANCO, null, VERSAO_BANCO);

    }
    //gera tabela
    public void onCreate (SQLiteDatabase db){

        Log.d(TAG, "Criação da tabela Carro");
    db.execSQL("CREATE TABLE IF NOT EXISTS carro (_id integer primary key autoincrement,"+"nome text, marca text);");
        Log.d(TAG, "Tabela de carro criada com sucesso");
    }

    //caso mude a versao do banco de dados o SQL de atualização
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }
    //faz o carro existir no banco de dados
    public long save(Carro carro) {
        // long id = carro.getId(); (pega / reculpera o id do objeto)
        long id = carro.getId();
        SQLiteDatabase db = getReadableDatabase();//abre o Banco de Dados

        //metodo try tratamento de erro na entrada e saida de dados
        try {
            //ContentValues values pega e seta os dados em cada local no caso nome emarca no caso pega com o get
            ContentValues values = new ContentValues();
            values.put("nome", carro.getNome());
            values.put("marca", carro.getMarca());

            //
            if (id != 0) {
                String _id = String.valueOf(carro.getId()); //pegando o _id (pegando int e transformando em string no sqline)
                String[] whereArgs = new String[]{_id}; //consulta do ID
                //update carro set values = ... where id = ?
                //SELECT * FROM carro WHERE id = 1;

                //whereArgs esta sendo pego
                int count = db.update("carro", values, "_id =?", whereArgs);
                return count;
            }else{
                // insert into carro values {...}
                id = db.insert("carro","",values);
                return id;
        }
            //execulta um passo necessario sempre sera execultado o que esta dentro do finally
    }finally {
            db.close();
        }
    }
    //deletar um carro
    public int delete (Carro carro){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //delete from carro where _id = ?
            int count = db.delete("carro", "_id =?", new String[]{String.valueOf(carro.getId())});
            Log.i(TAG, "Deletou[" + count + "] registro");
            return count;
        }finally {
            db.close();
        }
    }

    //consulte as listas com todos os carros
    public List<Carro> FindAli(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //SELECT * FROM carro;
            Cursor cursor = db.query("carro", null, null, null, null, null, null, null);
            return toList(cursor);
        }finally {
            db.close();
        }
    }

    private List<Carro> toList(Cursor cursor) {
        List<Carro> carros = new ArrayList<Carro>();
        if (cursor.moveToFirst()){
            do {
                Carro carro = new Carro();
                carros.add(carro);
                carro.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                carro.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                carro.setMarca(cursor.getString(cursor.getColumnIndex("marca")));
            }while (cursor.moveToNext());
        }
        return carros;
    }
}
