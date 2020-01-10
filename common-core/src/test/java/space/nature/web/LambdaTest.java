/*
 * Copyright (c) 2019, LZx
 */

package space.nature.web;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTest {

    private static CountDownLatch cdl = new CountDownLatch(3);

    public static void main(String[] args) {
        Account account = new Account("1234");
        new Thread(() -> System.out.println(account.getType())).start();
        cdl.countDown();
        new Thread(() -> account.setType("5555")).start();
        cdl.countDown();
        new Thread(() -> System.out.println(account.getType())).start();
        cdl.countDown();

//        List<Account> accounts = Arrays.asList(new Account("1"), new Account("2"));
//        filter(accounts, e -> "2".equals(e.getType()), e -> {
//                    String type = e.getType();
//                    e.setType(type + "--->");
//                    return e;
//                }).forEach(System.out::println);

    }

    public static List<Account> filter(List<Account> accounts, Predicate<Account> predicate, Function<Account, Account> mapper) {
        return accounts.parallelStream()
                .filter(predicate)
                .map(mapper)
                .collect(Collectors.toList());
    }

    @Data
    private static class User {

        private List<Account> accounts = new ArrayList<>();

    }

    @Data
    private static class Account {

        private String type;

        public Account(String type) {
            this.type = type;
        }
    }

}
