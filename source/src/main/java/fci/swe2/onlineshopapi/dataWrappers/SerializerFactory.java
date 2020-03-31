package fci.swe2.onlineshopapi.dataWrappers;

import java.util.HashMap;
import java.util.Map;

public class SerializerFactory{
  public enum Type{
    JSON, XML
  }

  public static class MapKey {
    private Object object;
    private Type type;

    MapKey(Object o, Type t){
      this.object = o;
      this.type = t;
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && obj instanceof MapKey){
            MapKey other = (MapKey) obj;
            return other.object == object && other.type == type;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return object.hashCode() + type.hashCode();
    }

  }

  private static Map<MapKey, Object> map = new HashMap<>();

  public static void init(){ }

  static{
      init();
  }

  public static <T>  void register(Class<T> c, Type t, Serializer<T> s){
      map.put(new MapKey(c, t), s);
  }

  @SuppressWarnings("unchecked")
  public static <T> Serializer<T> getSerializer(Class<T> c, Type t){
    return (Serializer<T>) map.get(new MapKey(c, t));
  }

}


