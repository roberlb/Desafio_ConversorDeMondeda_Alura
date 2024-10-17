package org.example;

import org.example.utils.Menu;

import static org.example.utils.Menu.continueMenu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        while (continueMenu) {
            menu.printTitle();
            menu.printReturnMenuOrExit();
            if(continueMenu){
                menu.printMenu();
                menu.printSelectOption();
                menu.printSelectRate();
                menu.printSeparation();
            }

        }
    }
}