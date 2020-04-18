package fci.swe2.onlineshopapi.dataWrappers;

import org.json.JSONObject;

public class JsonTokenSerializer implements Serializer<TokenWrapper> {
    @Override
    public String serialize(TokenWrapper obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token" , obj.getToken());
        return jsonObject.toString();
    }

    @Override
    public TokenWrapper unserialize(String str) {
        JSONObject jsonObject = new JSONObject(str);
        TokenWrapper wrapper = new TokenWrapper(jsonObject.getString("token"));
        return wrapper;
    }
}
