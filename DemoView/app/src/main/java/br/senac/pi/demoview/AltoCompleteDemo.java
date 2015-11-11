package br.senac.pi.demoview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AltoCompleteDemo extends AppCompatActivity {

    private static final String[] ESTADOS = new String[]{"Piauí" , "Maranhão" , "Ceará"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alto_complete_demo);
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
        AutoCompleteTextView estados =(AutoCompleteTextView) findViewById(R.id.estados);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_dropdown_item_1line, ESTADOS);
        estados.setAdapter(adapter);
    }

}
