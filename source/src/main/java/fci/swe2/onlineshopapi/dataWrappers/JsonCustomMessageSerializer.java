package fci.swe2.onlineshopapi.dataWrappers;

import org.json.JSONObject;

public class JsonCustomMessageSerializer implements Serializer<String> {
    @Override
    public String serialize(String str) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error" , false);
        jsonObject.put("message" , str);
        return jsonObject.toString();
    }

    @Override
    public String unserialize(String str) {
        /// todo we don't need this
        return null;
    }
}
