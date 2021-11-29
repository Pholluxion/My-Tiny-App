package com.misiontic.mytinyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.misiontic.mytinyapp.models.Usuario;

public class HomeActivity extends AppCompatActivity {

    private String id;
    private ImageView goToPerfil;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        if(getIntent().hasExtra("id")){
            id = getIntent().getStringExtra("id");
            this.usuario = new Usuario();
            this.usuario.setId(id);
            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
        }

        this.goToPerfil =  findViewById(R.id.btnPerfil);
        this.goToPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToPerfil = new Intent(HomeActivity.this,PerfilActivity.class);
                goToPerfil.putExtra("id",id);
                startActivity(goToPerfil);
            }
        });

        ImageView goToToDo = findViewById(R.id.goToToDo);
        goToToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToToDo = new Intent(HomeActivity.this,ToDoActivity.class);
                startActivity(goToToDo);
            }
        });

    }


    private void cerrarSesion(){
        FirebaseAuth.getInstance().signOut();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.btnLogOut){
            cerrarSesion();
            Intent goToHome = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(goToHome);
            finish();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            closeApplication();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void closeApplication() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_baseline_exit_to_app_24)
                .setTitle("¿Desea cerrar sesión?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cerrarSesion();
                        Intent goToLogin = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(goToLogin);
                        finish();
                    }
                }).show();
    }



}