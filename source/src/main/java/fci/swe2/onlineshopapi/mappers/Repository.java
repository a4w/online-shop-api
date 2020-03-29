import java.util.Vector;

public interface Repository<T> {
    public long exists();  //ID
    public long retrieve(); //ID
    public Vector retrieveall();
    public void Store(T obj);
    public void update(T obj);
    public void delete(T obj);
}
