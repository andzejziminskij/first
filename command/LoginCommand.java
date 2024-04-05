package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.login_en");


    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String userCard = ConsoleHelper.readString();
            String userPin = ConsoleHelper.readString();
            if (userCard == null || userCard.length() != 12 || userPin == null || userPin.length() != 4) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));

            } else if (validCreditCards.containsKey(userCard) && userPin.equals(validCreditCards.getString(userCard))) {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), userCard));
                break;
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), userCard));

            }

        }
    }
}
