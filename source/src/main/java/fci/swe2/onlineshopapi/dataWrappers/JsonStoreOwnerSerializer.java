package fci.swe2.onlineshopapi.dataWrappers;
import fci.swe2.onlineshopapi.StoreOwner;
import org.json.JSONObject;

public class JsonStoreOwnerSerializer implements Serializer<StoreOwner> {
    @Override
    public String serialize(StoreOwner storeOwner) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email" , storeOwner.getEmail());
        jsonObject.put("password" , storeOwner.getPassword());
        jsonObject.put("username" , storeOwner.getUsername());
        jsonObject.put("userID" , storeOwner.getUserID());
        return jsonObject.toString();
    }

    @Override
    public StoreOwner unserialize(String str) {
        JSONObject jsonObject = new JSONObject(str);
        String email= jsonObject.get("email").toString();
        String username= jsonObject.get("username").toString();
        String password= jsonObject.get("password").toString();
        return new StoreOwner(0, username , email,password) ;
    }
}
