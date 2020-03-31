package fci.swe2.onlineshopapi.dataWrappers;

interface Serializer<T>{
  String serialize(T obj);
  T unserizlize(String str);
}
