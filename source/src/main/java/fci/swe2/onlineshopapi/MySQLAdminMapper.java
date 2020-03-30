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

    public boolean exists(long id){
        return false;
    }

    public Admin retrieve(long id){
        return null;
    }

    public Admin[] retrieveAll(){
        return null;
    }

    public void store(Admin obj){}
    public void update(Admin obj){}
    public void delete(Admin obj){}
}
