package br.senac.pi.imc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
      findViewById(R.id.bntCalcule).setOnClickListener(calcule());
    }


    private View.OnClickListener calcule() {
        return new View.OnClickListener() {
            public void onClick(View v) {
                //instancias dos elementos
                //(EditText) é uma chac do java
                EditText edtPeso = (EditText) findViewById(R.id.Peso);
                EditText edtAltura = (EditText) findViewById(R.id.Altura);
                //parse passar de um para outro no caso double peso e Altura para String .getText().toString()
                double Peso = Double.parseDouble(edtPeso.getText().toString());
                double Altura = Double.parseDouble(edtAltura.getText().toString());
                //caldulo
                double calcule = Peso / (Altura * Altura);

                TextView TextoResultado = (TextView) findViewById(R.id.TextResultado);

                if (calcule < 18.5) {
                    //(getString(R.string.Abaixo) para pegar la em string o calculo para apresentar na tela
                    TextoResultado.setText(getString(R.string.Abaixo)+ calcule);
                }else if ((calcule < 18.5) & (calcule <= 24.9)) {
                    TextoResultado.setText(getString(R.string.Ideal)+ calcule);
                }else if ((calcule >= 25.0) & (calcule <= 29.9)) {
                    TextoResultado.setText(getString(R.string.Levemente)+ calcule);
                }else if ((calcule >= 30.0) & (calcule <= 34.9)) {
                    TextoResultado.setText(getString(R.string.GrauI) + calcule);
                }else if ((calcule >= 35.0) & (calcule <= 39.9)) {
                     TextoResultado.setText(getString(R.string.GrauII)+ calcule);
                }else if (calcule >= 40) {
                     TextoResultado.setText(getString(R.string.GrauIII)+ calcule);
                }
//Toast.makeText(MainActivity.this, getString(R.string.Calcule)+ calcule ,Toast.LENGTH_LONG).show();
            }

            ;
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
