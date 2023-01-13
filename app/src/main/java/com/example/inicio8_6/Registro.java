package com.example.inicio8_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Registro extends AppCompatActivity {

    EditText txtNombre;
    EditText txtApellido;
    EditText txtEdad;
    EditText txtTelefono;
    EditText txtCorreo;
    EditText txtContresenia;
    Spinner spnrEstadoCivil;
    String spinnerSelected = "";
    RadioButton rbtFemenino;
    RadioButton rbtMasculino;
    String sexo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        init();
        List<String> estadoCivilList = Arrays.asList("    ", "Casado(a)", "Conviviente", "Viudo(a)", "Soltero(a)", "Anulado(a)", "Casi algo", "MejorAmigo", "Solo es un amigo");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, estadoCivilList));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerSelected = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void init() {
        txtNombre = (EditText) findViewById(R.id.txt_nombre);
        txtApellido = (EditText) findViewById(R.id.txt_apellido);
        txtEdad = (EditText) findViewById(R.id.txt_edad);
        txtTelefono = (EditText) findViewById(R.id.txt_telefono);
        txtCorreo = (EditText) findViewById(R.id.txt_correo);
        txtContresenia = (EditText) findViewById(R.id.txt_contrasenia);
        rbtFemenino = (RadioButton) findViewById(R.id.rdb_femenino);
        rbtMasculino = (RadioButton) findViewById(R.id.rdb_masculino);
        spnrEstadoCivil = (Spinner) findViewById(R.id.spinner);

    }

    public void onRadioClickButtom(View view) {
        RadioButton buttomR = ((RadioButton) view);
        if (!buttomR.isChecked()) {
            return;
        }
        if (view.getId() == R.id.rdb_masculino) {
//            Toast.makeText(getApplicationContext(), "Selecciono masculino", Toast.LENGTH_SHORT).show();
            toastMessage("Selecciono masculino");
            sexo = "Masculino";
        } else if (view.getId() == R.id.rdb_femenino) {
//            Toast.makeText(getApplicationContext(), "Selecciono femenino", Toast.LENGTH_SHORT).show();
            toastMessage("Selecciono femenino");
            sexo = "Femenino";
        }
    }

    public void onBtnRegistrar(View v) {
        MyOpenHelper dbHelper = new MyOpenHelper(v.getContext());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", txtNombre.getText().toString());
            cv.put("apellido", txtApellido.getText().toString());
            cv.put("edad", txtEdad.getText().toString());
            cv.put("telefono", txtTelefono.getText().toString());
            cv.put("correo", txtCorreo.getText().toString());
            cv.put("clave", txtContresenia.getText().toString());
            cv.put("estadoCivil", spinnerSelected);
            cv.put("sexo", sexo);
            db.insert("usuarios", null, cv);
            //Mostrar mensaje de usuario registrado
            toastMessage("Estimado " + txtNombre.getText() + " " + txtApellido.getText() + " su cuenta fue creada con exito");
            //Toast.makeText(getApplicationContext(), "Estimado "+txtNombre.getText()+" "+txtApellido.getText()+" su cuenta fue creada con exito", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBtnBorrar(View v) {
        Boolean valor = false;
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtContresenia.setText("");
        rbtFemenino.setChecked(false);
        rbtMasculino.setChecked(false);
        spnrEstadoCivil.setSelection(0);

    }

    public void onBtnCancelar(View v) {
        //Mensaje
        toastMessage("Selecciono cancelar");
        //Llamar a Log in
        Intent call_login = new Intent(v.getContext(), Login.class);
        startActivity(call_login);
    }

    public void onBtnConsultar(View view) {
        /**
         * Only calls "ConsultarUsuario" activity
         */
        toastMessage("Enviando a consultar");
        Intent call_consultar = new Intent(view.getContext(), ConsultarUsuario.class);
        startActivity(call_consultar);
    }

    private void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}