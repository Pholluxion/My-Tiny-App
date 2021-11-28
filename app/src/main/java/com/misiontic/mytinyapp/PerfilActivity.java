package com.misiontic.mytinyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.misiontic.mytinyapp.models.Usuario;

public class PerfilActivity extends AppCompatActivity {

    private String id;
    private Usuario usuario;
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

        usuario.setId(id);
        getDatabase();

    }

    public void setDatabase() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Usuario.class.getSimpleName()).child(this.usuario.getId().toString());
        reference.setValue(this.usuario);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean nom,ape,ed,corr,inst;

                nom  = nombre.getText().toString().isEmpty();
                ape  = apellido.getText().toString().isEmpty();
                ed   = edad.getText().toString().isEmpty();
                corr = correo.getText().toString().isEmpty();
                inst = insta.getText().toString().isEmpty();

                if( nom || ape || corr || inst || ed ){

                    Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();

                }else{

                    usuario.setNombre(nombre.getText().toString());
                    usuario.setApellido(apellido.getText().toString());
                    usuario.setEdad(Integer.parseInt(edad.getText().toString()));
                    usuario.setCorreo(correo.getText().toString());
                    usuario.setInsta(insta.getText().toString());
                    usuario.setId(id);

                    setDatabase();

                    nombre.setText("");
                    apellido.setText("");
                    edad.setText("");
                    correo.setText("");
                    insta.setText("");

                    getDatabase();

                }
            }
        });
    }

    public void getDatabase(){


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Usuario.class.getSimpleName()).child(usuario.getId().toString());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usuario = dataSnapshot.getValue(Usuario.class);

                nombre.setHint(usuario.getNombre());
                apellido.setHint(usuario.getApellido());
                edad.setHint(String.valueOf(usuario.getEdad()));
                correo.setHint(usuario.getCorreo());
                insta.setHint(usuario.getInsta());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
}