package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fci.swe2.onlineshopapi.dataWrappers.AllUsersWrapper;
import fci.swe2.onlineshopapi.dataWrappers.Serializer;
import fci.swe2.onlineshopapi.dataWrappers.SerializerFactory;

class AccountsAPI extends API{
    @Override
    public void handle(HttpExchange exchange){
        try{
            this.setParserExchange(exchange);
        }catch(Exception e){
            // Do nothing
            return;
        }

        String[] urlParameters = parser.getURLpath();

        if(urlParameters.length != 2){
            sendMalformedRequestError();
            return;
        }

        String action= urlParameters[1];
        switch (action){
            case "getAll":
                if(this.loggedInAccount != null && Proxy.isAdmin(this.loggedInAccount)){
                    this.getAllAccounts();
                }else{
                    // TODO: Personalize error
                    sendResponse("Unauthorized", 401);
                }
                break;
            default:
                sendMalformedRequestError();
                break;
        }
    }

    private void getAllAccounts(){
        Customer[] customers = AccountManager.getAllAccounts(Customer.class);
        StoreOwner[] storeOwners = AccountManager.getAllAccounts(StoreOwner.class);
        AllUsersWrapper allUsers = new AllUsersWrapper(customers, storeOwners, new Admin[0]);
        Serializer<AllUsersWrapper> serializer = SerializerFactory.getSerializer(AllUsersWrapper.class, this.responseType);
        String output = serializer.serialize(allUsers);
        sendResponse(output);
    }
}
