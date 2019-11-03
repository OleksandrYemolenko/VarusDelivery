package KNU_kitkat.varusdelivery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static KNU_kitkat.varusdelivery.StartScreenActivity.STORAGE_NAME;

class LoginScreenActivity extends AppCompatActivity {
    private TextView numberText, passText;
    private Button loginButton;

    private SharedPreferences sp;
    private SharedPreferences.Editor ed;

    private Intent intent;

    private String number, pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        loginButton = (Button)findViewById(R.id.loginLButton);
        numberText = (TextView)findViewById(R.id.numberL);
        passText = (TextView)findViewById(R.id.passL);

        sp = this.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInfoValid()) {
                    ed = sp.edit();
                    ed.putBoolean("isLogined", true);
                    ed.apply();
                    login();
                }
            }
        });
    }


    private void login() {
        intent = new Intent(LoginScreenActivity.this, StartScreenActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkInfoValid() {
        //TODO проверять пользователя на сервере
        number = (String)numberText.getText();
        pass = (String)passText.getText();


        if (number.length() != 13) {
            Toast.makeText(this, "Номер телефона некоректен", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
