package fci.swe2.onlineshopapi;

public class MySQLAdminMapper implements Repository<Admin> {

    private static MySQLAdminMapper instance = null;

    private MySQLAdminMapper(){
        // Initialize stuff
    }

    public static MySQLAdminMapper getInstance(){
        if(instance == null)
            instance = new MySQLAdminMapper();
        return instance;
    }

    public long exists(long id){
        return 0;
    }

    public long retrieve(long id){
        return 0;
    }

    public Admin[] retrieveAll(){
        return null;
    }

    public void store(Admin obj){}
    public void update(Admin obj){}
    public void delete(Admin obj){}
}
