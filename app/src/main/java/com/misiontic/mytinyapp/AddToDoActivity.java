package com.misiontic.mytinyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                }else{
                    Toast.makeText(getApplicationContext(), "Debe llenar todo los campo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }





}