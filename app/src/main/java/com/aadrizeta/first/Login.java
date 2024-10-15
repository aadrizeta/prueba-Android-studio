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

public class Login extends AppCompatActivity {

    private boolean isPasswordVisible = false; // Control de visibilidad de la contraseña

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Referencias a las vistas en activity_login.xml
        Button loginButton = findViewById(R.id.PasswordButton);
        TextView registerTextView = findViewById(R.id.loginRegister);

        TextInputLayout passwordInputLayout = findViewById(R.id.Password);
        EditText passwordEditText = (EditText) passwordInputLayout.getEditText();

        // Listener para el botón de iniciar sesión
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMain();
            }
        });

        // Listener para el texto de registro
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegister();
            }
        });

        // Listener para mostrar/ocultar la contraseña al tocar el ícono del TextInputLayout
        passwordInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
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
