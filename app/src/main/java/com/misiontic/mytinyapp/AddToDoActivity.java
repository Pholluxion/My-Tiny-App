package com.misiontic.mytinyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.misiontic.mytinyapp.db.DataBase;

public class AddToDoActivity extends AppCompatActivity {

    private EditText txtTitulo,txtDesc;
    private Button addToDo;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        this.db = new DataBase(AddToDoActivity.this);

        this.addToDo = findViewById(R.id.btnAddTODO);
        this.txtTitulo = findViewById(R.id.txtTituloTODO);
        this.txtDesc = findViewById(R.id.txtDescripcionTODO);

        this.addToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(txtTitulo.getText().toString().isEmpty() || txtDesc.getText().toString().isEmpty())){
                    //TODO:Agregar tarea
                    db.addToDo(txtTitulo.getText().toString(),txtDesc.getText().toString(),0);
                    Intent intent = new Intent(getApplicationContext(),ToDoActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Debe llenar todo los campo", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(getApplicationContext(),ToDoActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




}