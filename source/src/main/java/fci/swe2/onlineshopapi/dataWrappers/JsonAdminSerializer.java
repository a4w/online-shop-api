package fci.swe2.onlineshopapi.dataWrappers;

import fci.swe2.onlineshopapi.Admin;
import org.json.JSONObject;

public class JsonAdminSerializer implements Serializer<Admin> {
    @Override
    public String serialize(Admin admin) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email" , admin.getEmail());
        jsonObject.put("password" , admin.getPassword());
        jsonObject.put("username" , admin.getUsername());
        jsonObject.put("userID" , admin.getUserID());
        return jsonObject.toString();
    }

    @Override
    public Admin unserialize(String str) {
        JSONObject jsonObject = new JSONObject(str);
        String email= jsonObject.get("email").toString();
        String username= jsonObject.get("username").toString();
        String password= jsonObject.get("password").toString();
        return new Admin(0, username , email,password) ;
    }
}
