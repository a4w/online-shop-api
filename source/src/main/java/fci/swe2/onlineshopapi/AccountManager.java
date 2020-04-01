package fci.swe2.onlineshopapi;

public class AccountManager {
    public static <T extends Account> T[] getAllAccounts(Class<T> clazz){
        Repository<T> repository = RepoFactory.getMapper(clazz);
        return  repository.retrieveAll();

    }
}
