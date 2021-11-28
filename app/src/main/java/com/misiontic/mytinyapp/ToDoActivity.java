package com.misiontic.mytinyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.misiontic.mytinyapp.adapters.ToDoAdapter;
import com.misiontic.mytinyapp.db.DataBase;
import com.misiontic.mytinyapp.models.ToDo;

import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity {

    private FloatingActionButton button;
    private RecyclerView recyclerView;
    private ToDoAdapter toDoAdapter;
    private ArrayList<ToDo> toDos;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        this.recyclerView = findViewById(R.id.myList);
        this.button = findViewById(R.id.addTodoBtn);

        this.db = new DataBase(getApplicationContext());

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddToDo = new Intent(getApplicationContext(),AddToDoActivity.class);
                startActivity(goToAddToDo);
                finish();
            }
        });

        this.toDos = new ArrayList<>();

        mostrarToDos();

        this.toDoAdapter = new ToDoAdapter(this.toDos,getApplicationContext());
        this.recyclerView.setAdapter(this.toDoAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void mostrarToDos(){

        Cursor cursor = db.readToDo();

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No hay TODOS en la base de datos", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){

               ToDo todo = new ToDo();

               todo.setId(cursor.getInt(0));
               todo.setTitulo(cursor.getString(1));
               todo.setDescripcion(cursor.getString(2));
               todo.setEstado(cursor.getInt(3));

               this.toDos.add(todo);

            }
        }
    }


}