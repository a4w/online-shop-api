package fci.swe2.onlineshopapi.dataWrappers;

import org.json.JSONObject;

public class JsonTokenSerializer implements Serializer<String> {
    @Override
    public String serialize(String obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token" , obj);
        return jsonObject.toString();
    }

    @Override
    public String unserialize(String str) {
        JSONObject jsonObject = new JSONObject(str);
        return jsonObject.getString("token");
    }
}
