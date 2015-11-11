package br.senac.pi.demoview;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;

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
        findViewById(R.id.btnTextViewDemo).setOnClickListener(abrirTextViewDemo());
        findViewById(R.id.btnDemoView).setOnClickListener(abrirDemoView());
        findViewById(R.id.bntimageButtonActivit).setOnClickListener(abririmageButtonActivit());
        findViewById(R.id.bntCheckBoxTogglerButoon).setOnClickListener(abrirCheckBoxTogglerButoon());
        findViewById(R.id.bntProgressDialogDemoActivit).setOnClickListener(abrirProgressDialogDemoActivit());
        findViewById(R.id.BntComentAlertDialog).setOnClickListener(abrirComentAlertDialog());
        findViewById(R.id.BntScrollViewDemoActivit).setOnClickListener(abrirScrollViewDemoActivi());


    }
    private View.OnClickListener abrirTextViewDemo() {

        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, TextViewDemo.class);
                startActivity(intent);

            };
        };
    }

    private View.OnClickListener abrirDemoView() {

        return new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AltoCompleteDemo.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener abririmageButtonActivit() {

        return new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageButtonActivit.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener abrirCheckBoxTogglerButoon() {

        return new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckBoxToggleButoonActivit.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener abrirProgressDialogDemoActivit() {

        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ProgressDialogDemoActivit.class);
                startActivity(intent);

            };
        };
    }


    private View.OnClickListener abrirComentAlertDialog() {

        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ComentAlertDialog.class);
                startActivity(intent);

            };
        };
    }

    private View.OnClickListener abrirScrollViewDemoActivi() {

        return new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ScrollViewDemoActivit.class);
                startActivity(intent);

            };
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
