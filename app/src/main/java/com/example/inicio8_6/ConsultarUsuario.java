package com.example.inicio8_6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class ConsultarUsuario extends AppCompatActivity {
    EditText txtBuscar;
    EditText txtNombre;
    EditText txtApellido;
    EditText txtEdad;
    EditText txtTelefono;
    EditText txtCorreo;
    EditText txtContresenia;
    //RadioButton rbtFemenino;
   // RadioButton rbtMasculino;
   // Spinner spnrEstadoCivil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);
        init();
    }

    private void init(){
        txtBuscar = (EditText) findViewById(R.id.txt_Buscar);
        txtNombre = (EditText) findViewById(R.id.txt_nombre);
        txtApellido = (EditText) findViewById(R.id.txt_apellido);
        txtEdad = (EditText) findViewById(R.id.txt_edad);
        txtTelefono = (EditText) findViewById(R.id.txt_telefono);
        txtCorreo = (EditText) findViewById(R.id.txt_correo);
        txtContresenia = (EditText) findViewById(R.id.txt_contrasenia);
        //rbtFemenino = (RadioButton) findViewById(R.id.rdb_femenino);
       // rbtMasculino = (RadioButton) findViewById(R.id.rdb_masculino);
       // spnrEstadoCivil = (Spinner) findViewById(R.id.spinner);

    }
    @SuppressLint("Range")
    public void onBtnBuscar(View v){
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(db !=null){
            int id = Integer.parseInt(txtBuscar.getText().toString());
            Cursor c = db.rawQuery("SELECT * FROM usuarios WHERE _id=" +id, null);
            if(c!= null){
                c.moveToFirst();
                txtNombre.setText(c.getString(c.getColumnIndex("nombre")).toString());
                txtApellido.setText(c.getString(c.getColumnIndex("apellido")).toString());
                txtEdad.setText(c.getString(c.getColumnIndex("edad")).toString());
                txtTelefono.setText(c.getString(c.getColumnIndex("telefono")).toString());

            }
            c.close();
            db.close();
        }
    }
}