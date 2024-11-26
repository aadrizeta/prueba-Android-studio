package com.aadrizeta.first;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Registro", MODE_PRIVATE);
        String name = sharedPreferences.getString("userName", "anonimo");
        String password = sharedPreferences.getString("password", "contraseña");

        TextView welcomeText = findViewById(R.id.welcomeText);
        TextView addedText = findViewById(R.id.addedText);
        Button addDialogButton = findViewById(R.id.buttonDialog);
        addDialogButton.setOnClickListener(view -> {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this)
                    .setIcon(R.drawable.logo)
                    .setTitle("Confirma tu identidad")
                    .setMessage("¿Eres tú " + name + "?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            MaterialAlertDialogBuilder builder2 = new MaterialAlertDialogBuilder(MainActivity.this)
                                    .setIcon(R.drawable.logo)
                                    .setTitle("Confirmación")
                                    .setMessage("¿Quieres cambiar tu nombre?")
                                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //Ir al layout de cambio de nombre de usuario
                                            launchRename();
                                        }
                                    });
                            builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Cerrar la aplicación
                                    finishAffinity();
                                }
                            });
                            Dialog dialog2 = builder2.create();
                            dialog2.show();
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
        });


        welcomeText.setText("Bienvenido " + name);
        addedText.setText("Tu contraseña es :" + password);
    }
    public void launchRename(){
        Intent intent = new Intent(MainActivity.this, Rename.class);
        startActivity(intent);
    }
}