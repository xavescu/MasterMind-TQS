package org.example;

/**
 * Hello world!
 */
public class App 
{
    public static void main( String[] args )
    {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";
        String asciiBox = "█";

        System.out.println("1)"
                +(ANSI_PURPLE+asciiBox)
                +(ANSI_YELLOW+asciiBox)
                +(ANSI_RED+asciiBox)
                +(ANSI_CYAN+asciiBox)
                +(ANSI_RED+asciiBox)
                +(ANSI_CYAN+asciiBox)
                +(ANSI_RESET+"|")
                +(ANSI_RED+"x")
                +(ANSI_RESET+"x")
                +(ANSI_RESET+"x")
                +(ANSI_RESET+"_")
        );
        System.out.println("2)"
                +(ANSI_CYAN+asciiBox)
                +(ANSI_YELLOW+asciiBox)
                +(ANSI_GREEN+asciiBox)
                +(ANSI_BLACK+asciiBox)
                +(ANSI_RESET+"|")
                +(ANSI_RED+"x")
                +(ANSI_RED+"x")
                +(ANSI_RESET+"_")
                +(ANSI_RESET+"_")
        );
    }
}
