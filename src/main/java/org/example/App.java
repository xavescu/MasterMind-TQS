package org.example;

import org.example.controller.GameController;

public class App
{
    public static void main( String[] args )
    {
        GameController controller = new GameController();

        // Game loop
        while(controller.isPlaying()){

        }
    }
}
