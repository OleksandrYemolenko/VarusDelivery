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

public class RegisterScreenActivity extends AppCompatActivity {
    private TextView numberText, passText, repPassText;
    private Button regButton, loginButton;

    private SharedPreferences sp;
    private SharedPreferences.Editor ed;

    private Intent intent;

    private String number, pass, repPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_screen);

        regButton = (Button)findViewById(R.id.regButton);
        loginButton = (Button)findViewById(R.id.loginButton);
        numberText = (TextView)findViewById(R.id.number);
        passText = (TextView)findViewById(R.id.pass);
        repPassText = (TextView)findViewById(R.id.repPass);

        sp = this.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        if(sp.getBoolean("isLogined", false))
            login();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(checkInfoValid()) {
                   ed = sp.edit();
                   ed.putBoolean("isLogined", true);
                   ed.apply();
                   sendRegInfo();
               }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RegisterScreenActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void login() {
        intent = new Intent(RegisterScreenActivity.this, StartScreenActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkInfoValid() {
        //TODO проверять нет ли такого-же пользователя уже
        number = (String)numberText.getText();
        pass = (String)passText.getText();
        repPass = (String)repPassText.getText();

        if(!pass.equals(repPass)) {
            Toast.makeText(this, "Введённые пароли не совпадают", Toast.LENGTH_LONG).show();
            return false;
        } else if (number.length() != 13) {
            Toast.makeText(this, "Номер телефона некоректен", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void sendRegInfo() {
        //TODO отправлять информацию о пользователе
        number = (String)numberText.getText();
        pass = (String)passText.getText();
        login();
    }
}
