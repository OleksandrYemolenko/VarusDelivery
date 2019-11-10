package KNU_kitkat.varusdelivery.rest;

import org.json.JSONObject;

public class MyRequest {
    private JSONObject data;
    private String url;
    private JSONObject parms;

    public static final String COMMON = "https://varus-delivery.herokuapp.com/";
    public static final String GET_CATEGORIES = "getAllCategories";
    public static final String GET_PRODUCTS_BY_CATEGORY = "getProductsByCategory";
    public static final String ADD_ORDER = "addOrder";
    public static final String GET_USER_ORDERS = "getOrdersByUser";
    public static final String GET_USER_DELIVRIES = "getDeliveriesByUser";
    public static final String SET_ORDER_STATUS = "setOrderStatus";
    public static final String SET_DELIVERY_STATUS = "setDeliveryStatus";
    public static final String SET_ORDER_PAYMENT_METHOD = "setOrderPayment";
    public static final String SET_DELIVERY_PAYMENT_METHOD = "setDeliveryPayment";
    public static final String SET_DELIVERY_PAYMENT_STATUS = "setDeliveryPaymentStatus";

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
