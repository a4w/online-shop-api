package fci.swe2.onlineshopapi;

import fci.swe2.onlineshopapi.dataWrappers.JsonTokenSerializer;
import fci.swe2.onlineshopapi.dataWrappers.Serializer;
import fci.swe2.onlineshopapi.exceptions.InvalidCredentialsException;
import fci.swe2.onlineshopapi.exceptions.ObjectNotFoundException;

public class DefaultLogin implements LoginBehaviour{
/// todo make function throws invalid credentials exception
    Serializer serializer = null;
    public <T extends Account> String login(T currentAccount) throws InvalidCredentialsException {
        AccountRepository<T> repo = RepoFactory.getMapperAccount(currentAccount);
        try{
            Account originalAccount = (Account) repo.findByUsername(currentAccount.getUsername());
            if(originalAccount.getPassword().equals(currentAccount.getPassword())){
                String token = JWT.createToken(originalAccount);
                serializer = new JsonTokenSerializer();
                return serializer.serialize(token);
            }else{
                // TODO: Throw exception as InvalidCredentials exception if username not valid
                throw new InvalidCredentialsException();
            }
        }catch(ObjectNotFoundException | InvalidCredentialsException e){
            /// todo catch from mysql mapper exceptions when qeuery not found
            /// todo throws if found and wrong credentials
            /// we throw invalid in both cases
            // TODO: Rethrow exception as InvalidCredentials exception
            throw new InvalidCredentialsException();
        }
    }



}
