package com.example.digital.daveloo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // guardar el id
        Bundle b = getIntent().getExtras();
        int value = 0;
        if (b != null){
            value = b.getInt("usuario_id");
        }
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("usuario_id", value);
        editor.apply();

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c281e2")));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Noticias"));
        tabLayout.addTab(tabLayout.newTab().setText("Teléfonos"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacto"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.inicio) {

            Intent intent = new Intent(this,MainActivity.class);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.redes) {
            Intent intent = new Intent(this,Redes.class);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.lugares) {
            Intent intent = new Intent(this,Lugares.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void grabar(View view) {

        Spinner tipo = (Spinner) findViewById(R.id.spinner);
        EditText texto = (EditText) findViewById(R.id.texto);
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        int usuario_id = sharedPreferences.getInt("usuario_id", -1);

        FormularioDAO dao = new FormularioDAO(getBaseContext());
        try {
            //dao.eliminarTodos();
            dao.insertar(tipo.getSelectedItem().toString(), texto.getText().toString(), "imagen", usuario_id);

            Toast toast= Toast.makeText(getApplicationContext(), "Se envió correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

            tipo.setSelection(0);
            texto.setText("");
        } catch (DAOException e) {
            Log.i("FormularioNuevoActi", "====> " + e.getMessage());
        }
    }
}
