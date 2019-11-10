package KNU_kitkat.varusdelivery.rest;

import org.json.JSONObject;

public class MyRequest {
    private JSONObject data;
    private String url;
    private JSONObject parms;

    public MyRequest() {}

    public MyRequest(JSONObject data, String url, JSONObject parms) {
        this.data = data;
        this.url = url;
        this.parms = parms;
    }

    public JSONObject getParms() {
        return parms;
    }

    public void setParms(JSONObject parms) {
        this.parms = parms;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
