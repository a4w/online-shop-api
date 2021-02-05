package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.exceptions.ObjectNotFoundException;

public class DefaultLogin implements LoginBehaviour{

    public <T extends Account> String login(T currentAccount) {
        AccountRepository<T> repo = RepoFactory.getMapperAccount(currentAccount);
        try{
            Account originalAccount = (Account) repo.findByUsername(currentAccount.getUsername());
            if(originalAccount.getPassword().equals(currentAccount.getPassword())){
                String token = JWT.createToken(originalAccount);
                return token;
            }else{
                // TODO: Throw exception as InvalidCredentials exception
            }
        }catch(ObjectNotFoundException e){
            // TODO: Rethrow exception as InvalidCredentials exception
        }
        return null;
    }



}
