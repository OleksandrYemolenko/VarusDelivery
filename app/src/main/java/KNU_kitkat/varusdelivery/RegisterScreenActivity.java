//Add server

package KNU_kitkat.varusdelivery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

import static KNU_kitkat.varusdelivery.StartScreenActivity.JSON;
import static KNU_kitkat.varusdelivery.StartScreenActivity.STORAGE_NAME;
import static KNU_kitkat.varusdelivery.StartScreenActivity.base;

public class RegisterScreenActivity extends AppCompatActivity {
    private EditText passText, repPassText;
    private FloatingActionButton regBtn;

    private SharedPreferences sp;
    private SharedPreferences.Editor ed;

    private Intent intent;

    private String numberStr, pass, repPass;
    protected String mMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_screen);

        passText = (EditText) findViewById(R.id.pass);
        repPassText = (EditText) findViewById(R.id.repPass);
        regBtn = (FloatingActionButton) findViewById(R.id.fabRegister);

        if(passText.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }


        sp = this.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        intent = getIntent();

        numberStr = intent.getStringExtra("phoneNumber");

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(checkInfoValid()) {
//                   ed = sp.edit();
//                   ed.putBoolean("isLogined", true);
//                   ed.putString("phoneNumber", numberStr);
//                   ed.apply();
                   sendRegInfo();

               }
            }
        });
    }

    private void login() {
        intent = new Intent(RegisterScreenActivity.this, StartScreenActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkInfoValid() {
        pass = passText.getText().toString();
        repPass = repPassText.getText().toString();

        if(!pass.equals(repPass)) {
            Toast.makeText(this, "Введённые пароли не совпадают", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void sendRegInfo() {
        pass = passText.getText().toString();

        sendRequest request = new sendRequest();

        //TODO server

        //request.execute();

        login();
    }

    private void print(String mMessage) {
        Toast.makeText(this, mMessage, Toast.LENGTH_LONG).show();
        System.out.println(mMessage);
    }

    class sendRequest extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {

            try {
                OkHttpClient client = new OkHttpClient();

                JSONObject data = new JSONObject();
                data.put("phone", numberStr);
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
                        mMessage = e.getMessage();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            mMessage = response.body().toString();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            return mMessage;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            print(s);
            login();
        }
    }
}
