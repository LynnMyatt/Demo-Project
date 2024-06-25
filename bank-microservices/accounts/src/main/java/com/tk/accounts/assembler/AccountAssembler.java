package com.tk.accounts.assembler;

import com.tk.accounts.dto.AccountDTO;
import com.tk.accounts.entity.Accounts;

public class AccountAssembler {

    public static AccountDTO mapsToAccountDTO(Accounts account, AccountDTO accountDTO) {

        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setBranchAddress(account.getBranchAddress());

        return accountDTO;
    }

    public static Accounts mapsToAccounts(AccountDTO accountDTO, Accounts accounts) {

        accounts.setAccountNumber(accountDTO.getAccountNumber());
        accounts.setAccountType(accountDTO.getAccountType());
        accounts.setBranchAddress(accountDTO.getBranchAddress());

        return accounts;
    }
}
