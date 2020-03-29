package fci.swe2.onlineshopapi;

public class MySQLAdminMapper implements Repository<Admin> {
    public long exists(long id){
        return 0;
    }

    public long retrieve(long id){
        return 0;
    }

    public Admin[] retrieveAll(){
        return new Admin[0];
    }

    public void store(Admin obj){}
    public void update(Admin obj){}
    public void delete(Admin obj){}
}
