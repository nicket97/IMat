package main.backend;

import se.chalmers.ait.dat215.project.IMatDataHandler;

/**
 * Created by Pontus on 2017-02-18.
 */
public class CustomDataHandler extends IMatDataHandler {
    private static CustomDataHandler instance = null;

    public static CustomDataHandler getInstance(){
        if(instance == null)
            instance = new CustomDataHandler();

        return instance;
    }

    private CustomDataHandler(){
        super();
    }
}
