package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;

public class LoginAPI extends API {
    @Override
    public void handle(HttpExchange exchange) {
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

        String userType= urlParameters[1]; /// login/user , login/admin , login/storeowner
        switch (userType){
            case "customer":
                this.loginAccount();
                break;
            case "admin":
                this.loginAccount();
                break;
            case "storeowner":
                this.loginAccount();
                break;
            default:
                sendMalformedRequestError();
                break;
        }
    }

    private void loginAccount(){

        Admin admin = new Admin(1,"admin","email@gmail.com","123456789");
        loginAccount((Account) admin);
    }
    private void loginAccount(Account account){
        sendResponse(account.login());
    }
}
