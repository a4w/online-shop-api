
package fci.swe2.onlineshopapi.exceptions;

public class UnauthorizedRequestException extends Exception implements UserFriendlyError{
    private static final long serialVersionUID = 7840844788649672210L;

    public String getUserFriendlyError(){
        return "Unauthorized request";
    }

};
