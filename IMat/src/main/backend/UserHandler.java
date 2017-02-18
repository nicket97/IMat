package main.backend;

/**
 * Created by Pontus on 2017-02-18.
 */
public class UserHandler {
    private static UserHandler instance = null;

    public static UserHandler getInstance(){
        if(instance == null)
            instance = new UserHandler();

        return instance;
    }

}
