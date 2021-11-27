package com.misiontic.mytinyapp;

import android.widget.Toast;

public class UnitTestExample {

    public UnitTestExample() {
    }

    public boolean validPassword(String pass){

        if( pass.length() <= 8){

            return false;

        }else {

            return true;

        }
    }

}
