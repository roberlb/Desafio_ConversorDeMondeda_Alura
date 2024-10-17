package org.example.utils;

import org.example.api.ExchangeRate;
import org.example.models.PairMoney;

import java.util.*;

public class Menu {

    ExchangeRate exchangeRate = new ExchangeRate();
    int i = 1;
    Scanner scanner = new Scanner(System.in);
    int option = 0;
    public static boolean continueMenu = true;
    Map<String, String> map;
    List<String> listRate = new ArrayList<>();
    String base;
    String target;
    PairMoney pairMoney;
    float amount;
    float conversionAmount;
    String enter;

    public void printTitle() {
        System.out.println("\n\n########## EXCHANGE RATE ##########");
        System.out.println("_Welcome to the Currency Converter_");
    }

    public void printMenu() {
        map = exchangeRate.getAllRate();
        System.out.printf("%n%3S   %S%n", "id", "name");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            listRate.add(entry.getKey());
            System.out.printf("%3d.- %s %n", i++, entry.getValue());
        }
    }

    public void printSelectOption() {
        try {
            System.out.print("Enter the ID of base currency: ");
            option = scanner.nextInt();
            base = listRate.get(option - 1);
            System.out.print("Enter the ID of the destination currency: ");
            option = scanner.nextInt();
            target = listRate.get(option - 1);
            System.out.print("Enter the amount: ");
            amount = scanner.nextFloat();
        } catch (InputMismatchException | UnknownFormatConversionException e) {
            System.out.println("The user entered a correct value\n" + e);
            continueMenu = false;
        }
    }

    public void printSelectRate() {
        try {
            pairMoney = exchangeRate.getPairMoney(base, target);
            System.out.printf("Conversion result of %s to %s : %S Rate: %f%n",
                    pairMoney.base_code(), pairMoney.target_code(), pairMoney.result(), pairMoney.conversion_rate());
            conversionAmount = amount * pairMoney.conversion_rate();
            System.out.printf("%s %f %n%s %f%n", pairMoney.base_code(), amount, pairMoney.target_code(), conversionAmount);
        } catch (UnknownFormatConversionException e) {
            System.out.println(e);
        }
    }

    public void printSeparation() {
        System.out.println("###################################");
    }

    public void printReturnMenuOrExit() {
        i = 1;
        System.out.println("\nYou want to perform a currency conversion or exit");
        System.out.printf("%3d.- I want to perform a currency conversion%n", i++);
        System.out.printf("%3d.- Exit%n", i);
        System.out.print("Option: ");
        try {
            option = scanner.nextInt();
            if (option >= i) {
                continueMenu = false;
            } else {
                i = 1;
                continueMenu = true;
            }
        } catch (InputMismatchException | UnknownFormatConversionException e) {
            System.out.println("The user entered a correct value\n" + e);
            continueMenu = false;
        }


    }
}
