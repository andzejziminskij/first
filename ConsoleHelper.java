package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "common_en");
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String text;
        try {
            text = bis.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (text.toUpperCase().equals("EXIT")) {
            writeMessage(res.getString("the.end"));
            throw new InterruptOperationException();
        } else {
            return text;
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String currency = readString();
        while (currency.length() != 3) {
            writeMessage(res.getString("invalid.data"));
            currency = readString();
        }
        return currency.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String[] splitinput = readString().split(" ");
            if (splitinput.length == 2 && Integer.parseInt(splitinput[0]) >= 0 && Integer.parseInt(splitinput[1]) >= 0) {
                return splitinput;
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage("1-" + res.getString("operation.INFO"));
            writeMessage("2-" + res.getString("operation.DEPOSIT"));
            writeMessage("3-" + res.getString("operation.WITHDRAW"));
            writeMessage("4-" + res.getString("operation.EXIT"));
            return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));

        }
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));

    }
}
/*


invalid.data=Please specify valid data.
 */