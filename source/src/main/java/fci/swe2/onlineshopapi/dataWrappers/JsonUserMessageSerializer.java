package fci.swe2.onlineshopapi.dataWrappers;

import fci.swe2.onlineshopapi.exceptions.UserFriendlyError;
import org.json.JSONObject;

public class JsonUserMessageSerializer implements Serializer<UserFriendlyError> {
    @Override
    public String serialize(UserFriendlyError obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error" , true);
        jsonObject.put("message" , obj.getUserFriendlyError());
        return  jsonObject.toString();
    }

    @Override
    public UserFriendlyError unserialize(String str) {
        /// todo we don't need this
        return null;
    }
}
