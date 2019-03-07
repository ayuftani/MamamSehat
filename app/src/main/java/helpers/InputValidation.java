package helpers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class InputValidation {
    private Context context;

    public InputValidation(Context context){
        this.context = context;
    }

        public boolean isInputEditTextFilled(EditText textInputEditText, TextView textInputLayout, String message) {
            String value = textInputEditText.getText().toString().trim();
            if (value.isEmpty()) {
                textInputLayout.setError(message);
                hideKeyboardFrom(textInputEditText);
                return false;
            } else {
                textInputLayout.setError(message);
            }

            return true;
        }

        public boolean isInputEditTextEmail(EditText textInputEditText, TextView textInputLayout, String message) {
            String value = textInputEditText.getText().toString().trim();
            if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
                textInputLayout.setError(message);
                hideKeyboardFrom(textInputEditText);
                return false;
            } else {
                textInputLayout.setError(message);
            }
            return true;
        }

        public boolean isInputEditTextMatches(EditText Email, EditText Password, TextView textInputLayout, String message) {
            String value1 = Email.getText().toString().trim();
            String value2 = Password.getText().toString().trim();
            if (!value1.contentEquals(value2)) {
                textInputLayout.setError(message);
                hideKeyboardFrom(Password);
                return false;
            } else {
                textInputLayout.setError(message);
            }
            return true;
        }

        private void hideKeyboardFrom(View view) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }
