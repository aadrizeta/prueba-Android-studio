package com.aadrizeta.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {


    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        //Creacion de instancias
        Button registerButton = findViewById(R.id.registerButton);
        TextInputLayout password1 = findViewById(R.id.passwordInput);
        TextInputLayout password2 = findViewById(R.id.passwordInputConfirmation);
        TextInputLayout registerUserInput = findViewById(R.id.registerUserInput);

        SharedPreferences preferences = getSharedPreferences("Registro", Context.MODE_PRIVATE);

        //Validar que los campos no esten vacios
        InputValidator.matchPassword(password1, password2, "Por favor, completa este campo", "Las contraseñas no coinciden");
        InputValidator.matchPassword2(password1, password2, "Por favor, completa este campo", "Las contraseñas no coinciden");
        InputValidator.validarCampoVacio(registerUserInput, "Por favor, completa este campo");

        //Metodo para mostrar y ocultar las contraseñas
        InputValidator.cambiarVisibilidadPassword(password1);
        InputValidator.cambiarVisibilidadPassword(password2);

        //Metodo para hacer saber al usuario que las contraseñas coinciden
        //InputValidator.matchPassword(password1, password2, "Las contraseñas no coinciden");

        // Evento Botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el nombre de usuario ingresado

                List<TextInputLayout> registerInputLayouts = new ArrayList<>();
                registerInputLayouts.add(registerUserInput);
                registerInputLayouts.add(password1);
                registerInputLayouts.add(password2);

                if (InputValidator.validateInputs(registerInputLayouts, this)){
                    String userName = String.valueOf(registerUserInput.getEditText().getText());
                    String stringPassword1 = String.valueOf(password1.getEditText().getText());
                    String stringPassword2 = String.valueOf(password2.getEditText().getText());
                    //Comprobar que las contraseñas coinciden
                       if (!stringPassword1.equals(stringPassword2)) {
                           toast = Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                           toast.show();
                       } else {
                           SharedPreferences.Editor editor = preferences.edit();
                           editor.putString("userName", userName);
                           editor.putString("password", stringPassword1);
                           editor.apply();
                           toast = Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT);
                           toast.show();
                           launchLogin();
                       }
                } else {
                    toast = Toast.makeText(getApplicationContext(), "Completa todos los campos", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    // Método para volver al Login
    public void launchLogin() {
        Intent intent = new Intent(Register.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}