package fci.swe2.onlineshopapi.dataWrappers;
import org.json.JSONObject;
public class LoginRequestSerializer implements Serializer<LoginRequestWrapper> {
    @Override
    public String serialize(LoginRequestWrapper obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username" , obj.getUsername());
        jsonObject.put("password" ,obj.getPassword());
        return jsonObject.toString();
    }

    @Override
    public LoginRequestWrapper unserialize(String str) {
        JSONObject jsonObject = new JSONObject(str);
        String username= jsonObject.get("username").toString();
        String password= jsonObject.get("password").toString();
        return new LoginRequestWrapper(username,password);
    }
}
