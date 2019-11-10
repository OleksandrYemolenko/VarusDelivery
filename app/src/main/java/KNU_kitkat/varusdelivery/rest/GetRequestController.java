package KNU_kitkat.varusdelivery.rest;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.function.Consumer;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRequestController extends AsyncTask<MyRequest, Integer, JSONObject>  {

    @Override
    protected JSONObject doInBackground(MyRequest... requests) {
        final MyRequest myRequest = requests[0];

        try {
            OkHttpClient client = new OkHttpClient();
            final HttpUrl.Builder urlBuilder = HttpUrl.parse(myRequest.getUrl()).newBuilder();

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

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder().url(url).build();
            Call call = client.newCall(request);
            Response response = call.execute();

            return new JSONObject(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }
}
