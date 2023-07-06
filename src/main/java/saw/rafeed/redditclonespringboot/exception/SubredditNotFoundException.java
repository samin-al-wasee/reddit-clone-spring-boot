package saw.rafeed.redditclonespringboot.exception;

public class SubredditNotFoundException extends RuntimeException{
    public SubredditNotFoundException(String message){
        super(message);
    }
}
