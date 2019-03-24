package com.vacaJair.app.useful;

public class Useful {
    final static int CHARACTERS_PER_LINE = 80;


    public static void pause(){
        System.out.print("Presione cualquier tecla para continuar....");
        ScanF.readString();
    }

    public static void printTitle(String text, String  filler){
        int spaces = (CHARACTERS_PER_LINE - text.length()) /2;
        int i;
        for(i = 0; i < spaces; i++)
            System.out.print(filler);
        System.out.print(text);
        i += text.length();
        for(; i < CHARACTERS_PER_LINE; i++)
            System.out.print(filler);
        System.out.println();
    }

    public static void showError(String error){
        System.out.println("There was an aerror: " + error);
    }
}
