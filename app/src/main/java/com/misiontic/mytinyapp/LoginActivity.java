package com.misiontic.mytinyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email,pass;
    private FirebaseAuth auth;
    private  Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.auth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.email = findViewById(R.id.txtEmailLogin);
        this.pass  = findViewById(R.id.txtContraLogin);

        btnLogin = findViewById(R.id.btnLogin);
        TextView btnGoToRegister = findViewById(R.id.btnGoToRegistro);
        btnGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = auth.getCurrentUser();

        if (firebaseUser!=null){

            Toast.makeText(LoginActivity.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
            Intent goToHome = new Intent(LoginActivity.this,HomeActivity.class);
            goToHome.putExtra("id",firebaseUser.getUid());
            System.out.println(firebaseUser.getUid());
            startActivity(goToHome);
            finish();


        }

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validInputs()){

                    //TODO:Implementar autentificación

                    signIn(email.getText().toString(),pass.getText().toString());
                }

            }
        });



    }


    private void signIn(String email, String pass){

        this.auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    Toast.makeText(LoginActivity.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                    Intent goToHome = new Intent(LoginActivity.this,HomeActivity.class);
                    goToHome.putExtra("id",firebaseUser.getUid());
                    System.out.println(firebaseUser.getUid());

                    startActivity(goToHome);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "No se ha podido iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void goToRegister() {
        Intent goToRegister = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(goToRegister);
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
                .setTitle("¿Desea cerrar la aplicación?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid()); //Su funcion es algo similar a lo que se llama cuando se presiona el botón "Forzar Detención" o "Administrar aplicaciones", lo cuál mata la aplicación
                        //finish(); Si solo quiere mandar la aplicación a segundo plano
                    }
                }).show();
    }

    private boolean validInputs(){

        if(this.email.getText().toString().isEmpty() || this.pass.getText().toString().isEmpty()){

            Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            return false;

        }else{
            return true;
        }

    }


}