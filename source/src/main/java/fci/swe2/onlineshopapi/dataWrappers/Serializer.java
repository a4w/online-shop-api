package fci.swe2.onlineshopapi.dataWrappers;

public interface Serializer<T>{
  String serialize(T obj);
  T unserialize(String str);
}
