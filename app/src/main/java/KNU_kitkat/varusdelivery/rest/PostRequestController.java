package KNU_kitkat.varusdelivery.rest;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.function.Consumer;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostRequestController extends AsyncTask<MyRequest, Integer, JSONObject> {

    @Override
    protected JSONObject doInBackground(MyRequest... myRequests) {
        final MyRequest myRequest = myRequests[0];

        try {
            OkHttpClient client = new OkHttpClient();
            final HttpUrl.Builder urlBuilder = HttpUrl.parse(myRequest.getUrl()).newBuilder();
            final FormBody.Builder bodyBuilder = new FormBody.Builder();


            myRequest.getParms().keys().forEachRemaining(new Consumer<String>() {
                @Override
                public void accept(String key) {
                    try {
                        urlBuilder.addQueryParameter(key, myRequest.getParms().getString(key));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


            myRequest.getData().keys().forEachRemaining(new Consumer<String>() {
                @Override
                public void accept(String key) {
                    try {
                        bodyBuilder.add(key, myRequest.getData().getString(key));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


            String url = urlBuilder.build().toString();
            RequestBody body = bodyBuilder.build();

            Request request = new Request.Builder()
                    .addHeader("Content-Type", "application/json")
                    .url(url)
                    .post(body)
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();

            return new JSONObject(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }
}
