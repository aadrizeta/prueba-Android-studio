package com.aadrizeta.first;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;


public class Rename extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rename);

        //Mostrar un snackbar al iniciar la actividad
        Snackbar.make(findViewById(android.R.id.content), "Cambiado a nueva actividad", Snackbar.LENGTH_SHORT)
                .setAction("Deshacer", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        launchMain();
                    }
                })
                .show();

        SharedPreferences sharedPreferences = getSharedPreferences("Registro", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String actualName = sharedPreferences.getString("userName", "anonimo");
        TextView currentNameText = findViewById(R.id.currentNameText);
        currentNameText.setText("Nombre actual: " + actualName);
        // TODO: Implementar el resto de la actividad para permitir al usuario cambiar su nombre y guardar los cambios en el sharedPreferences.

        TextInputLayout nameInput = findViewById(R.id.newNameInputLayout);

        Button boton = findViewById(R.id.confirmButton);
        //evento para obtener el nuevonombre y guardarlo en el sharedpreferences
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = nameInput.getEditText().getText().toString().trim();

                if (!newName.isEmpty()){
                    nameInput.setError(null);

                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Rename.this)
                            .setIcon(R.drawable.logo)
                            .setTitle("Confirmación")
                            .setMessage("¿Quieres cambiar tu nombre a " + newName + "?")
                            .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        editor.putString("UserName", newName);
                                        editor.apply();
                                        launchMain();
                                    }
                                })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                    builder.create();
                    builder.show();

                }
            }
        });

    }
    public void launchMain(){
        Intent intent = new Intent(Rename.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}