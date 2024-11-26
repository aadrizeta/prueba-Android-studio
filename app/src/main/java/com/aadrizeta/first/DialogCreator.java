package com.aadrizeta.first;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DialogCreator {

    public static AlertDialog crearDialog(
            Context context,
            Integer icono,
            String titulo,
            String mensaje,
            String botonPositivo,
            String botonNegativo,
            Runnable onPositiveClick,
            Runnable onNegativeClick
    ){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);

        if (icono != null) {
            builder.setIcon(icono);
        }

        // Configurar el título si no es nulo
        if (titulo != null) {
            builder.setTitle(titulo);
        }

        // Configurar el mensaje si no es nulo
        if (mensaje != null) {
            builder.setMessage(mensaje);
        }

        builder.setPositiveButton(botonPositivo, (dialog, which) -> {
           if (onPositiveClick != null){
                onPositiveClick.run();
            }
        });

        builder.setNegativeButton(botonNegativo, (dialog, which) -> {
           if (onNegativeClick != null){
                onNegativeClick.run();
           }
        });
        return builder.create();
    }
}
