package com.misiontic.mytinyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText email,pass_1,pass_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.email  = findViewById(R.id.txtEmailRegistro);
        this.pass_1 = findViewById(R.id.txtContraRegistro_1);
        this.pass_2 = findViewById(R.id.txtContraRegistro_2);
        Button btnRegister = findViewById(R.id.btnRegistro);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validInputs()){
                    Intent goToLogin = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(goToLogin);
                    finish();
                    //TODO:Implementar el registro con FireBase
                }
            }
        });


    }

    private boolean validInputs(){

        if(this.email.getText().toString().isEmpty() || this.pass_2.getText().toString().isEmpty() || this.pass_1.getText().toString().isEmpty()){

            Toast.makeText(RegisterActivity.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            return false;

        }else{

            if(this.pass_1.getText().toString().equals(this.pass_2.getText().toString())){

                if(this.pass_1.getText().toString().length() < 8){

                    Toast.makeText(getApplicationContext(), "La contraseña debe tener una longitud minima de 8 carácteres", Toast.LENGTH_SHORT).show();
                    return false;

                }else {

                    return true;

                }

            }else{

                Toast.makeText(RegisterActivity.this, "Las constraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return false;

            }
        }

    }


    //TODO:Implementar metodos para registro



}