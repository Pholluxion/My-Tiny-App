package com.misiontic.mytinyapp;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.misiontic.mytinyapp.models.Usuario;

public class RemoteDataBase {

    private Context context;
    private Usuario usuario;


    public RemoteDataBase(Context context,Usuario usuario) {
        this.context = context;
        this.usuario = usuario;
    }

    public void setDatabase() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Usuario.class.getSimpleName()).child(usuario.getId().toString());
        reference.setValue(usuario);

    }
    public Usuario getDatabase(){


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(Usuario.class.getSimpleName()).child(usuario.getId().toString());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usuario = dataSnapshot.getValue(Usuario.class);
                System.out.println(usuario.toString());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        return usuario;
    }



}
