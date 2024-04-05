package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        while (true) {
            ConsoleHelper.writeMessage("Please enter amount of many to withdraw");
            ConsoleHelper.writeMessage(res.getString("before"));

            try {
                int amount = Integer.parseInt(ConsoleHelper.readString());
                if (amount > 0) {
                    if (manipulator.isAmountAvailable(amount)) {
                        try {

                            Map<Integer, Integer> withdraw = manipulator.withdrawAmount(amount);
                            for (Integer nominal : withdraw.keySet()) {
                                ConsoleHelper.writeMessage("\t" + nominal + " - " + withdraw.get(nominal));
                            }
                            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, currency));

                            break;
                        } catch (NotEnoughMoneyException e) {
                            ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));

                        }

                    } else {
                        ConsoleHelper.writeMessage(res.getString("not.enough.money"));

                    }
                } else {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));

                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));

            }


        }

    }
}
