package fci.swe2.onlineshopapi.dataWrappers;

import org.json.JSONObject;
import org.json.JSONArray;
import fci.swe2.onlineshopapi.Customer;
import fci.swe2.onlineshopapi.StoreOwner;
import fci.swe2.onlineshopapi.Admin;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory.Type;

public class JsonAllUsersSerializer implements Serializer<AllUsersWrapper> {
    @Override
    public String serialize(AllUsersWrapper obj) {
        JSONObject jsonObject = new JSONObject();
        JSONArray arr = new JSONArray();
        Serializer<Customer> customerSerializer = SerializerFactory.getSerializer(Customer.class, Type.JSON);
        for(Customer customer : obj.customers){
            JSONObject cobj = new JSONObject(customerSerializer.serialize(customer));
            cobj.put("type", "Customer");
            arr.put(cobj);
        }
        Serializer<StoreOwner> storeOwnerSerializer = SerializerFactory.getSerializer(StoreOwner.class, Type.JSON);
        for(StoreOwner storeOwner : obj.storeOwners){
            JSONObject sobj = new JSONObject(storeOwnerSerializer.serialize(storeOwner));
            sobj.put("type", "Store Owner");
            arr.put(sobj);
        }
        Serializer<Admin> adminSerializer = SerializerFactory.getSerializer(Admin.class, Type.JSON);
        for(Admin admin : obj.admins){
            JSONObject aobj = new JSONObject(adminSerializer.serialize(admin));
            aobj.put("type", "Admin");
            arr.put(aobj);
        }
        jsonObject.put("users", arr);
        return jsonObject.toString();
    }

    @Override
    public AllUsersWrapper unserialize(String str) {
        // We do not need that method
        return new AllUsersWrapper(null, null, null) ;
    }
}

