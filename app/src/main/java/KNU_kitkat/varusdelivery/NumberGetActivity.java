//Add server

package KNU_kitkat.varusdelivery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static KNU_kitkat.varusdelivery.StartScreenActivity.STORAGE_NAME;

public class NumberGetActivity extends AppCompatActivity {

    private EditText number, numberS;
    private TextView text;
    private FloatingActionButton fabLogin;

    private Intent intent;
    private SharedPreferences sp;

    private String numberStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_number_get);

        number = (EditText)findViewById(R.id.number);
        numberS = (EditText)findViewById(R.id.numberS);
        text = (TextView)findViewById(R.id.getNumberText);
        fabLogin = (FloatingActionButton)findViewById(R.id.fabGetNumb);

        number.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf"));
        numberS.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf"));
        text.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf"));

        if(number.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        number.addTextChangedListener(new NumberTextWatcher());

        fabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberStr = number.getText().toString();

                if(checkNubmer(numberStr)) {
                    numberStr = numberStr.replaceAll("-", "");
                    numberStr = "+380" + numberStr;
                    login(isAlreadyExist(numberStr), numberStr);
                } else {
                    print("Пожалуйста, введите свой номер телефона");
                }
            }
        });

        sp = this.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        if(sp.getBoolean("isLogined", false))
            login(StartScreenActivity.class, null);
    }

    private boolean checkNubmer(String numberStr) {
        if(numberStr.length() == 12)
            return true;
        return false;
    }

    private Class isAlreadyExist(String number) {
        boolean exist = false;

        //TODO проверять на сервере

        if(exist)
            return LoginScreenActivity.class;
        else
            return RegisterScreenActivity.class;
    }

    private void login(Class o, String numberStr) {
        intent = new Intent(NumberGetActivity.this, o);
        intent.putExtra("phoneNumber", numberStr);
        startActivity(intent);
        finish();
    }

    private void print(String mMessage) {
        Toast.makeText(this, mMessage, Toast.LENGTH_LONG).show();
        System.out.println(mMessage);
    }



    public class NumberTextWatcher implements TextWatcher {

        private boolean isFormatting;
        private boolean deletingHyphen;
        private int hyphenStart;
        private boolean deletingBackward;

        @Override
        public void afterTextChanged(Editable text) {
            if (isFormatting)
                return;

            isFormatting = true;

            // If deleting hyphen, also delete character before or after it
            if (deletingHyphen && hyphenStart > 0) {
                if (deletingBackward) {
                    if (hyphenStart - 1 < text.length()) {
                        text.delete(hyphenStart - 1, hyphenStart);
                    }
                } else if (hyphenStart < text.length()) {
                    text.delete(hyphenStart, hyphenStart + 1);
                }
            }
            if (text.length() == 2 || text.length() == 6 || text.length() == 9) {
                text.append('-');
            }

            isFormatting = false;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (isFormatting)
                return;

            // Make sure user is deleting one char, without a selection
            final int selStart = Selection.getSelectionStart(s);
            final int selEnd = Selection.getSelectionEnd(s);
            if (s.length() > 1 // Can delete another character
                    && count == 1 // Deleting only one character
                    && after == 0 // Deleting
                    && s.charAt(start) == '-' // a hyphen
                    && selStart == selEnd) { // no selection
                deletingHyphen = true;
                hyphenStart = start;
                // Check if the user is deleting forward or backward
                if (selStart == start + 1) {
                    deletingBackward = true;
                } else {
                    deletingBackward = false;
                }
            } else {
                deletingHyphen = false;
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }
}
