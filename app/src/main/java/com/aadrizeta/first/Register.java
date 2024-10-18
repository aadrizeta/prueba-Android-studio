package com.aadrizeta.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class Register extends AppCompatActivity {

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.registerButton);

        TextInputLayout passwordInputLayout = findViewById(R.id.passwordInput);
        EditText password1 = (EditText) passwordInputLayout.getEditText();

        TextInputLayout passwordInputLayoutConfirmation = findViewById(R.id.passwordInputConfirmation);
        EditText password2 = (EditText) passwordInputLayoutConfirmation.getEditText();

        TextInputLayout registerUserInput = findViewById(R.id.registerUserInput);

        SharedPreferences preferences = getSharedPreferences("Registro", Context.MODE_PRIVATE);


        //Mostrar contraseña caja1
        passwordInputLayout.setEndIconOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (isPasswordVisible){
                    assert password1 != null;
                    password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordInputLayout.setEndIconDrawable(R.drawable.baseline_visibility_24);
                } else {
                    assert password1 != null;
                    password1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordInputLayout.setEndIconDrawable(R.drawable.baseline_visibility_off_24);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        //Mostrar contraseña caja2
        passwordInputLayoutConfirmation.setEndIconOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (isPasswordVisible){
                    assert password2 != null;
                    password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordInputLayoutConfirmation.setEndIconDrawable(R.drawable.baseline_visibility_24);
                } else {
                    assert password2 != null;
                    password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordInputLayoutConfirmation.setEndIconDrawable(R.drawable.baseline_visibility_off_24);
                }
                isPasswordVisible =!isPasswordVisible;
            }
        });

        // Evento Botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              // Obtener el nombre de usuario ingresado
              String userName = String.valueOf(registerUserInput.getEditText().getText());
              String stringPassword1 = String.valueOf(passwordInputLayout.getEditText().getText());
              String stringPassword2 = String.valueOf(passwordInputLayoutConfirmation.getEditText().getText());
              // Comprobar que las contraseñas coinciden
              if (!stringPassword1.equals(stringPassword2)){
                  Toast toast = Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                  toast.show();
              } else {
                  SharedPreferences.Editor editor = preferences.edit();
                  editor.putString("userName", userName);
                  editor.putString("password", stringPassword1);
                  editor.apply();
                  Toast toast = Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT);
                  toast.show();
                  launchLogin();
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