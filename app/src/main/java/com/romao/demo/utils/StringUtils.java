package com.romao.demo.utils;

import android.widget.EditText;

public class StringUtils {

    public static boolean isEmpty(CharSequence text) {
        return text == null || text.toString().trim().isEmpty();
    }

    public static String getNotEmptyOrNull(String str) {
        return isEmpty(str) ? null : str.trim();
    }

    public static boolean matchEmailPattern(String email) {
        return !isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean validateEditTexts(EditText[] fields) {
        for (EditText currentField : fields) {
            if (currentField.getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;
    }
}
