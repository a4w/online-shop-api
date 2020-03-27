import java.util.Map;

public class RepoFactory {
    private Map repos;

    public void initFactory(){}
    public <T> void register(Class<T> clss, DataMapper<T> mapper){
        this.repos.put(clss, mapper);
    }
    public <T> DataMapper<T> getMapper(Class<T> cls){
        return (DataMapper<T>) this.repos.get(cls);
    }
    public <T> DataMapper<T> getMapper(Object cls){
        return (DataMapper<T>) this.repos.get(cls.getClass());
    }

}
