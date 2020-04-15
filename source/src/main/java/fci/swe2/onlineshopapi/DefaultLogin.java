package fci.swe2.onlineshopapi;

public class DefaultLogin implements LoginBehaviour{

    public <T> void login(T obj) {
        AccountRepository<T> repo = RepoFactory.getMapperAccount(obj);
        Account account = (Account) repo.findByUsername(obj);

        if(account!=null){   // assumed findByUsername returns null if not found
            Account temp = (Account) obj;
            if(account.getPassword().equals(temp.getPassword())){
                // generate token
            }
        }
        // handle more details
        else{

        }
    }
}
