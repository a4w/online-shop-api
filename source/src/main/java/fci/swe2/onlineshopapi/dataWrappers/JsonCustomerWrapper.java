package fci.swe2.onlineshopapi.dataWrappers;

import fci.swe2.onlineshopapi.Customer;

import org.json.JSONObject;

public class JsonCustomerWrapper implements CustomerWrapper{
    @Override
    public Customer getCustomer(String data) {
        JSONObject jsonObject = new JSONObject(data);
        String email= jsonObject.get("email").toString();
        String username= jsonObject.get("username").toString();
        String password= jsonObject.get("password").toString();
        return new Customer(0, username , email,password) ;
    }

    @Override
    public String getData(Customer customer) {
        ///todo
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email" , customer.getEmail());
        jsonObject.put("password" , customer.getPassword());
        jsonObject.put("username" , customer.getUsername());
        jsonObject.put("userID" , customer.getUserID());
        return jsonObject.toString();
    }
}
