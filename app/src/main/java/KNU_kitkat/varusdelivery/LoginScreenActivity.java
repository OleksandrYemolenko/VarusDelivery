//Add server

package KNU_kitkat.varusdelivery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static KNU_kitkat.varusdelivery.StartScreenActivity.STORAGE_NAME;

class LoginScreenActivity extends AppCompatActivity {
    private EditText passText;
    private Button loginButton;

    private SharedPreferences sp;
    private SharedPreferences.Editor ed;

    private Intent intent;

    private String pass, mMessage, numberStr;

    private boolean loginComplete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        loginButton = (Button)findViewById(R.id.loginLButton);
        passText = (EditText) findViewById(R.id.passL);

        sp = this.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        intent = getIntent();

        numberStr = intent.getStringExtra("phoneNumber");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPass()) {/*
                    ed = sp.edit();
                    ed.putBoolean("isLogined", true);
                    ed.putString("phoneNumber", numberStr);
                    ed.apply();*/
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

    private boolean checkPass() {
        //TODO проверять пользователя на сервере
        pass = passText.getText().toString();

        return true;
    }
}
