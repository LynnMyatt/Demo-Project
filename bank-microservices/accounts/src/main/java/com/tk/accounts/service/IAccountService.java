package com.tk.accounts.service;

import com.tk.accounts.dto.CustomerDTO;

public interface IAccountService {

    /**
     *
     * @param customerDTO CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     *
     * @param customerDTO CustomerDTO Object
     * @return boolean indicating if the update of account details is successful or not
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     *
     * @param phoneNumber Input phone number
     * @return boolean indicationg if the delete of account is successful of not
     */
    boolean deleteAccount(String phoneNumber);

    /**
     *
     * @param phoneNumber
     * @return
     */
    CustomerDTO fetchAccount(String phoneNumber);
}
