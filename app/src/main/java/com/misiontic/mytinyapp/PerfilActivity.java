package com.misiontic.mytinyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.misiontic.mytinyapp.models.Usuario;

public class PerfilActivity extends AppCompatActivity {

    private String id;
    private Usuario usuario;
    private RemoteDataBase remoteDataBase;
    private EditText nombre,apellido,edad,correo,insta;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //TODO: Falta implementar actualizar los datos en los campos de texto

        usuario = new Usuario();

        this.nombre = findViewById(R.id.txtNombres);
        this.apellido = findViewById(R.id.txtApellidos);
        this.edad = findViewById(R.id.txtEdad);
        this.correo = findViewById(R.id.txtCorreo);
        this.insta = findViewById(R.id.txtInsta);
        this.btnGuardar = findViewById(R.id.btnGuardar);

        if(getIntent().hasExtra("id")){
            id =  getIntent().getStringExtra("id").toString();
            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();

        }



        this.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                usuario.setNombre(nombre.getText().toString());
                usuario.setApellido(apellido.getText().toString());
                usuario.setEdad(Integer.parseInt(edad.getText().toString()));
                usuario.setCorreo(correo.getText().toString());
                usuario.setInsta(insta.getText().toString());

                usuario.setId(id);
                remoteDataBase = new RemoteDataBase(getApplicationContext(),usuario);
                remoteDataBase.setDatabase();


            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);



    }


}