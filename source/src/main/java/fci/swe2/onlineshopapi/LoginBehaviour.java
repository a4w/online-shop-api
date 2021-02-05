package fci.swe2.onlineshopapi;

public interface LoginBehaviour {
    public <T extends Account> String login(T obj);
}
