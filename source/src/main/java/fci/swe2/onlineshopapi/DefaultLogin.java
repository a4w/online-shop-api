package fci.swe2.onlineshopapi;

import io.jsonwebtoken.JwtBuilder;

public class DefaultLogin implements LoginBehaviour{

    public <T> String login(T obj) {
        AccountRepository<T> repo = RepoFactory.getMapperAccount(obj);
        Account account = (Account) repo.findByUsername(obj);

        if(account!=null){   // assumed findByUsername returns null if not found
            Account temp = (Account) obj;
            if(account.getPassword().equals(temp.getPassword())){
                JWT jwt = new JWT();
                String token = jwt.createToken(account.getUserID(),"admin",8000);
                return token;
            }
        }
        // handle more details
        else{

        }
        return null;
    }



}
