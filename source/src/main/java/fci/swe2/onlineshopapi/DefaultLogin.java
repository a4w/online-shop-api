package fci.swe2.onlineshopapi;

public class DefaultLogin implements LoginBehaviour{

    public <T> void login(T obj) {
        AccountRepository<T> repo = RepoFactory.getMapperAccount(obj);
        Account account = (Account) repo.findByUsername(obj);

        if(account!=null){   // assumed findByUsername returns null if not found
            Account temp = (Account) obj;
            if(account.getPassword().equals(temp.getPassword())){
                String token = generateToken(10);
                repo.updateToken(obj,token);
            }
        }
        // handle more details
        else{

        }
    }

    String generateToken(int n){
        final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder string = new StringBuilder();
        while (n> 0 )
        {
            int pos = (int)(Math.random()*alpha.length());
            string.append(alpha.charAt(pos));
            n--;
        }
        return string.toString();
    }
}
