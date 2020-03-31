package fci.swe2.onlineshopapi.dataWrappers;

import fci.swe2.onlineshopapi.Customer;
import org.json.JSONObject;

public class JsonCustomerSerializer implements Serializer<Customer> {

    @Override
    public String serialize(Customer customer) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email" , customer.getEmail());
        jsonObject.put("password" , customer.getPassword());
        jsonObject.put("username" , customer.getUsername());
        jsonObject.put("userID" , customer.getUserID());
        return jsonObject.toString();
    }

    @Override
    public Customer unserizlize(String str) {
        JSONObject jsonObject = new JSONObject(str);
        String email= jsonObject.get("email").toString();
        String username= jsonObject.get("username").toString();
        String password= jsonObject.get("password").toString();
        return new Customer(0, username , email,password) ;
    }
}
