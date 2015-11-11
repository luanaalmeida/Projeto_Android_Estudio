package br.senac.pi.ciclodevida;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "cursoandroid";

    @Override
    //recursos da aplicação
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "metodo onCreate chamado");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"metodo onStart chamado");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"metodo onResume chamado");

    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG,"metodo onStop chamado");

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"metodo onDestroy chamado");

    }
}
