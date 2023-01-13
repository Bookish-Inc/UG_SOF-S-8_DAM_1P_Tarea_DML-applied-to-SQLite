package com.example.inicio8_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtContrasenia;

    public User[] users =
            {
                    new User("Helen", "1234"),
                    new User("Nefi", "1234"),
                    new User("Renan", "1234"),
                    new User("Vane", "1234"),
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /*Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        Button btnExit = (Button) findViewById(R.id.btn_exit);
        txtUsuario = (EditText) findViewById(R.id.lbl_nombre);
        txtContrasenia = (EditText) findViewById(R.id.txt_password);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity(); // Close all activities in queue
                finish();
                System.exit(0);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bandera = false;
                Intent call_principal = new Intent(view.getContext(), Registro.class);
                for (User user : users) {
                    System.out.println("users.length = " + users.length + "\n Nombre: " + user.nombre + "\n Contraseña: " + user.contrasenia);
                    if ((txtUsuario.getText().toString().equals(user.nombre)) && (txtContrasenia.getText().toString().equals(user.contrasenia))) {
                        call_principal.putExtra("name_usuario", txtUsuario.getText().toString());
                        call_principal.putExtra("clave", txtContrasenia.getText().toString());
                        startActivity(call_principal);
                        bandera = true;
                        break; // para que salga cuando ya encuentre la respuesta
                    }
                }
                if (!bandera) {
                    Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_acerca:
                Intent call = new Intent(this, AcercaDe.class);
                startActivity(call);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
/* PARA MENU DENTRO DE MENU
    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater menuInflater = popup.getMenuInflater();
        menuInflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();
    }

    */
}