package com.github.hw1;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task3 {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        HashMap<Integer, HashMap<Integer, BigDecimal>> months = new HashMap<>();
        HashMap<Integer, BigDecimal> month;
        HashMap<Integer, Currency> currencies = new HashMap<>();

        int m;
        int c = 0;

        currencies.put(0, new Currency('₽', 1.0));
        currencies.put(1, new Currency('$', 0.011));
        currencies.put(2, new Currency('€', 0.010));

        while (true) {
            System.out.print(
                    "Choose an option:\n" +
                    "\t0: Exit\n" +
                    "\t1: Add expenses\n" +
                    "\t2: Month expenses\n" +
                    "\t3: Largest expense in a month\n" +
                    "\t4: Converter\n" +
                    "$> "
            );

            switch (scanner.nextInt()) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.print("select month $> ");
                    month = new HashMap<>();

                    m = scanner.nextInt();

                    for (int i = 0; i < 30; i++) {
                        System.out.printf("\t%d $> ", i);
                        month.put(i, scanner.nextBigDecimal());
                    }

                    months.put(m, month);

                    break;
                case 2:

                    System.out.print("select month $> ");

                    m = scanner.nextInt();

                    if (months.get(m) == null) {
                        System.out.println("There is no such month");
                        break;
                    }

                    int finalC = c;

                    months.get(m).forEach((d, e) -> {
                        double expense = e.doubleValue() * currencies.get(finalC).exchange;
                        System.out.printf("Day %d: %.02f%c\n", d + 1, expense, currencies.get(finalC).sign);
                    });

                    break;
                case 3:
                    System.out.print("month $> ");

                    m = scanner.nextInt();

                    if (months.get(m) == null) {
                        System.out.println("There is no such month");
                        break;
                    }

                    month = months.get(m);

                    BigDecimal largest = month.values().iterator().next();

                    for (BigDecimal value : month.values())
                        if (value.compareTo(largest) > 0)
                            largest = value;

                    System.out.printf("Largest expense on the %d month is %.02f%c\n", m, largest, currencies.get(c).sign);

                    break;
                case 4:
                    System.out.println("Available currencies:");

                    for (int i = 0; i < currencies.size(); i++) {
                        System.out.printf("%d: %c\n", i, currencies.get(i).sign);
                    }

                    System.out.print("select currency $> ");

                    int currency = scanner.nextInt();

                    if (currency > currencies.size()) {
                        System.out.println("There is only 3 currencies");
                        break;
                    }

                    c = currency;

                    break;
            }
        }
    }
}
