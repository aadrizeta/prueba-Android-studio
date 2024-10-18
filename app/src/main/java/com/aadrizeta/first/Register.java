package com.aadrizeta.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    private boolean isPasswordVisible = false;
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
        validarCampoVacio(password1, "Por favor, completa este campo");
        validarCampoVacio(password2, "Por favor, completa este campo");
        validarCampoVacio(registerUserInput, "Por favor, completa este campo");

        //Metodo para mostrar y ocultar las contraseñas
        cambiarVisibilidadPassword(password1);
        cambiarVisibilidadPassword(password2);

        // Evento Botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el nombre de usuario ingresado
                String userName = String.valueOf(registerUserInput.getEditText().getText());
                String stringPassword1 = String.valueOf(password1.getEditText().getText());
                String stringPassword2 = String.valueOf(password2.getEditText().getText());

                List<TextInputLayout> registerInputLayouts = new ArrayList<>();
                registerInputLayouts.add(registerUserInput);
                registerInputLayouts.add(password1);
                registerInputLayouts.add(password2);

                if (InputValidator.validateInputs(registerInputLayouts, this)){
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

    //Mostrar y ocultar las contraseñas
    public void cambiarVisibilidadPassword(TextInputLayout layout) {
        EditText editText = layout.getEditText();
        layout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert editText != null;
                if (isPasswordVisible) {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    layout.setEndIconDrawable(R.drawable.baseline_visibility_24);
                } else {
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    layout.setEndIconDrawable(R.drawable.baseline_visibility_off_24);
                }
                isPasswordVisible = !isPasswordVisible;
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

    //Metodo para verificar que los campos no esten vacios
    public void validarCampoVacio(TextInputLayout campo, String mensajeError) {
        EditText editText = campo.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                campo.setError(charSequence.toString().isEmpty() ? mensajeError : null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}