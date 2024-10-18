package com.aadrizeta.first;

import android.text.TextUtils;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class InputValidator {
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
}
