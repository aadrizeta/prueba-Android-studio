package com.aadrizeta.first;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class InputValidator {
    private static boolean isPasswordVisible = false;
    public  static boolean validateInputs(List<TextInputLayout> inputLayouts, View.OnClickListener context){
        boolean isValid = true;
        for (TextInputLayout inputLayout : inputLayouts){
            String text = inputLayout.getEditText().getText().toString();
            if (TextUtils.isEmpty(text)){
                inputLayout.setError("Por favor, completa este campo");
                isValid = false;
            } else {
                inputLayout.setError(null);
            }
        }
        return isValid;
    }
    //Mostrar y ocultar las contraseñas
    public static void cambiarVisibilidadPassword(TextInputLayout layout) {
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

    public static void validarCampoVacio(TextInputLayout campo, String mensajeError) {
        EditText editText = campo.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                campo.setError(charSequence.toString().isEmpty() ? mensajeError : null);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public static void matchPassword2(TextInputLayout campo1, TextInputLayout campo2, String mensajeError1, String mensajeError2) {
        EditText password = campo2.getEditText();
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()){
                    campo2.setError(mensajeError1);
                } else {
                    if (!charSequence.toString().equals(campo1.getEditText().getText().toString())){
                        campo2.setError(mensajeError2);
                    } else {
                        campo2.setError(null);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
    public static void matchPassword(TextInputLayout campo1, TextInputLayout campo2, String mensajeError1, String mensajeError2) {
        EditText password = campo1.getEditText();
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()){
                    campo1.setError(mensajeError1);
                } else {
                    if (!charSequence.toString().equals(campo2.getEditText().getText().toString())){
                        campo2.setError(mensajeError2);
                    } else {
                        campo2.setError(null);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
}
