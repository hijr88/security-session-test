package me.yh.springsecurity;

import lombok.RequiredArgsConstructor;
import me.yh.springsecurity.account.Account;
import me.yh.springsecurity.account.AccountService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account1 = new Account();
        account1.setUsername("user1");
        account1.setPassword("1234");
        account1.setRole("ADMIN");

        Account account2 = new Account();
        account2.setUsername("user2");
        account2.setPassword("1234");
        account2.setRole("USER");

        accountService.createNew(account1);
        accountService.createNew(account2);
    }
}
