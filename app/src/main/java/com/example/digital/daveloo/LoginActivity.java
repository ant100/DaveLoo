package com.example.digital.daveloo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{

    ActionBar actionBar;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c281e2")));
    }

    /*@Override
    public void onClick(View view) {
        //TODO: login validation
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        setContentView(R.layout.activity_main);
    }*/

    public void ingresar(View view) throws DAOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        EditText nombre = (EditText) findViewById(R.id.input_nombre);
        EditText password = (EditText) findViewById(R.id.input_password);
        Log.i("==========>", "hola");
        // autenticar
        try {
            int usuario_id = usuarioDAO.autenticar(nombre.getText().toString(), password.getText().toString());
            Log.i("====>usuarioid", Integer.toString(usuario_id));
            if (usuario_id != 0){
                // llamar al main
                Intent i = new Intent(this, MainActivity.class);

                //Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(i);
                //setContentView(R.layout.activity_main);

                // send the id to the activity
                Bundle b = new Bundle();
                b.putInt("usuario_id", usuario_id);
                i.putExtras(b);

                // start the activity
                this.startActivity(i);

            } else {
                // mostrar mensaje de error
                Toast toast= Toast.makeText(getApplicationContext(), "Error al ingresar sesión", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        }
        catch (Exception e){
            throw new DAOException("ingresar: Error al obtener: " + e.getMessage());
        }
    }
}
