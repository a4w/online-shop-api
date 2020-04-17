package fci.swe2.onlineshopapi;

public class AccountManager {
    private static Proxy check;

    public static <T extends Account> T[] getAllAccounts(Class<T> clazz){
        Repository<T> repository = RepoFactory.getMapper(clazz);
        if(check.IsAdmin(clazz)){
            return  repository.retrieveAll();
        }

    }

}
