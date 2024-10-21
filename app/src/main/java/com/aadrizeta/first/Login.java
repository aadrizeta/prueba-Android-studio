package com.aadrizeta.first;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Register r = new Register();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Creacion de instancias
        Button loginButton = findViewById(R.id.PasswordButton);
        TextView registerTextView = findViewById(R.id.loginRegister);
        TextInputLayout userInputLayout = findViewById(R.id.loginUsernameTIL);
        TextInputLayout passwordInputLayout = findViewById(R.id.Password);

        SharedPreferences sharedPreferences = getSharedPreferences("Registro", MODE_PRIVATE);

        //Metodo para mostrar y ocultar la contraseña
        InputValidator.cambiarVisibilidadPassword(passwordInputLayout);

        //Validar que los campos no esten vacios
        InputValidator.validarCampoVacio(userInputLayout, "Por favor, completa este campo");
        InputValidator.validarCampoVacio(passwordInputLayout, "Por favor, completa este campo");

        // Listener para el botón de iniciar sesión
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringUserNick = String.valueOf(userInputLayout.getEditText().getText());
                String stringUserPassword = String.valueOf(passwordInputLayout.getEditText().getText());

                List <TextInputLayout> loginInputLayouts = new ArrayList<>();
                loginInputLayouts.add(userInputLayout);
                loginInputLayouts.add(passwordInputLayout);

                if (InputValidator.validateInputs(loginInputLayouts, this)){

                    String savedUser = sharedPreferences.getString("userName", "anonimo");
                    String savedPassword = sharedPreferences.getString("password", "contraseña");

                    if (!stringUserNick.equals(savedUser)) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (stringUserPassword.equals(savedPassword)){
                            launchMain();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            }
        });

        // Listener para el texto de registro
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegister();
            }
        });
    }

    // Método para lanzar la actividad principal
    public void launchMain() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    // Método para lanzar la actividad de registro
    public void launchRegister() {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}
