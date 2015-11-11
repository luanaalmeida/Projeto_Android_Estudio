package br.senac.pi.carros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import br.senac.pi.carros.domains.Carro;
import br.senac.pi.carros.domains.carrosDB;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cadastras através do bottom no xml para a lista
        Button bntCadastrarCarros = (Button) findViewById(R.id.bntCadastrarCarros);
        bntCadastrarCarros.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText edtNomeCarro = (EditText) findViewById(R.id.edtNomeCarro);
                EditText edtMarcaCarro = (EditText) findViewById(R.id.edtMarcaCarro);

                String nomeCarro = edtNomeCarro.getText().toString();
                String marcaCarro = edtMarcaCarro.getText().toString();

                Carro carro = new Carro();
                carro.setNome(nomeCarro);
                carro.setMarca(marcaCarro);

                //para ser armazenado
                carrosDB carrosDB = new carrosDB(MainActivity.this);
                if (carrosDB.save(carro) != -1){
                    Toast.makeText(MainActivity.this, getString(R.string.sucesso),Toast.LENGTH_LONG).show();

                    //para retornar depois do cadastro os campos em branco
                    edtNomeCarro.setText("");
                    edtMarcaCarro.setText("");
                    edtNomeCarro.requestFocus();

                }else{
                    Toast.makeText(MainActivity.this, getString(R.string.error),Toast.LENGTH_LONG).show();

                }
            }
        });
        //chamar botton
        findViewById(R.id.bntListarCarros).setOnClickListener(abrirListarCarros());
    }

    private View.OnClickListener abrirListarCarros() {
        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ListCarrosActivit.class);
                startActivity(intent);

            }
        };

    }


}
