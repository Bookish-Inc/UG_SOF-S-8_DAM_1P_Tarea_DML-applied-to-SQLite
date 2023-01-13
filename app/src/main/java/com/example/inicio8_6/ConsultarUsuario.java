package com.example.inicio8_6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarUsuario extends AppCompatActivity {
    EditText txtId;
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

    private void init() {
        txtId = (EditText) findViewById(R.id.txt_Id);
        txtNombre = (EditText) findViewById(R.id.txt_Nombre);
        txtApellido = (EditText) findViewById(R.id.txt_Apellido);
        txtEdad = (EditText) findViewById(R.id.txt_Edad);
        txtTelefono = (EditText) findViewById(R.id.txt_Telefono);
//        txtCorreo = (EditText) findViewById(R.id.txt_Correo);
//        txtContresenia = (EditText) findViewById(R.id.txt_contrasenia);
        //rbtFemenino = (RadioButton) findViewById(R.id.rdb_femenino);
        // rbtMasculino = (RadioButton) findViewById(R.id.rdb_masculino);
        // spnrEstadoCivil = (Spinner) findViewById(R.id.spinner);

    }

    @SuppressLint("Range")
    public void onBtnConsultar(View v) {
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
//        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        System.out.println("IF CONSULTA ---> " + db);
        if (db != null && !txtId.getText().toString().equals("")) {
            int id = Integer.parseInt(txtId.getText().toString());
            Cursor c = db.rawQuery("SELECT * FROM usuarios WHERE _id=" + id, null);
//            System.out.println("IF CURSOR 1 ---> " + c);
//            System.out.println("IF CURSOR 2 ---> " + c.getString(c.getColumnIndex("_id")).toString() != null);
            if (c != null && c.moveToNext()) {
//                c.moveToFirst();
                do {
                    txtNombre.setText(c.getString(c.getColumnIndex("nombre")).toString());
                    txtApellido.setText(c.getString(c.getColumnIndex("apellido")).toString());
                    txtEdad.setText(c.getString(c.getColumnIndex("edad")).toString());
                    txtTelefono.setText(c.getString(c.getColumnIndex("telefono")).toString());
                } while (c.moveToNext());
                toastMessage("Datos consultados");
            }
//            c.close();
//            db.close();
        }
    }

    public void onBtnActualizar(View view) {
        /**
         * Update date based on ID
         */
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", txtNombre.getText().toString());
            cv.put("apellido", txtApellido.getText().toString());
            cv.put("edad", txtEdad.getText().toString());
            cv.put("telefono", txtTelefono.getText().toString());
            int id = Integer.parseInt(txtId.getText().toString());
            db.update("usuarios", cv, "_id = " + id, null);
            toastMessage("Datos actualizados");
        }
    }

    public void onBtnEliminar(View view) {
        /**
         * Dale data on DataBase based on ID
         */
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int id = Integer.parseInt(txtId.getText().toString());
        db.delete("usuarios", "_id=" + id, null);
        toastMessage("Datos eliminados");
    }

    private void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}