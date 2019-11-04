package KNU_kitkat.varusdelivery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static KNU_kitkat.varusdelivery.StartScreenActivity.JSON;
import static KNU_kitkat.varusdelivery.StartScreenActivity.STORAGE_NAME;
import static KNU_kitkat.varusdelivery.StartScreenActivity.base;

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
               if(true || checkInfoValid()) {
//                   ed = sp.edit();
//                   ed.putBoolean("isLogined", true);
//                   ed.apply();
                   try {
                       sendRegInfo();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
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

    private void sendRegInfo() throws IOException {
        number = (String)numberText.getText();
        pass = (String)passText.getText();

        number = "+380676988515";
        pass = "pass";

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    JSONObject data = new JSONObject();
                    data.put("phone", number);
                    data.put("password", HashGenerator.getHash(pass));

                    RequestBody body = RequestBody.create(JSON, data.toString());

                    Request request = new Request.Builder()
                            .url(base + "registration")
                            .post(body)
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json")
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            String mMessage = e.getMessage().toString();
                            System.out.println(mMessage);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String mMessage = response.body().string();
                            System.out.println(mMessage);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        login();
    }

}
