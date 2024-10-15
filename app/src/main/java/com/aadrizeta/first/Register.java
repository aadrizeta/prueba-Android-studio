package com.aadrizeta.first;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        EditText passwordEditText = (EditText) passwordInputLayout.getEditText();

        TextInputLayout passwordInputLayoutConfirmation = findViewById(R.id.passwordInputConfirmation);
        EditText passwordEditTextConfirmation = (EditText) passwordInputLayoutConfirmation.getEditText();

        //Mostrar contraseña caja1
        passwordInputLayout.setEndIconOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (isPasswordVisible){
                    assert passwordEditText != null;
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordInputLayout.setEndIconDrawable(R.drawable.baseline_visibility_24);
                } else {
                    assert passwordEditText != null;
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
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
                    assert passwordEditTextConfirmation != null;
                    passwordEditTextConfirmation.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordInputLayoutConfirmation.setEndIconDrawable(R.drawable.baseline_visibility_24);
                } else {
                    assert passwordEditTextConfirmation != null;
                    passwordEditTextConfirmation.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordInputLayoutConfirmation.setEndIconDrawable(R.drawable.baseline_visibility_off_24);
                }
                isPasswordVisible =!isPasswordVisible;
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              launchMain();
          }
        });


    }
    // Método para lanzar la actividad principal
    public void launchMain() {
        Intent intent = new Intent(Register.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}