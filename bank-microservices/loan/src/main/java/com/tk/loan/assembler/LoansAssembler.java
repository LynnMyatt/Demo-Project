package com.tk.loan.assembler;

import com.tk.loan.dto.LoansDTO;
import com.tk.loan.entity.Loans;

public class LoansAssembler {

    public static Loans mapsToLoans(LoansDTO loansDTO, Loans loans) {
        loans.setLoanNumber(loansDTO.getLoanNumber());
        loans.setLoanType(loansDTO.getLoanType());
        loans.setTotalLoan(loansDTO.getTotalLoan());
        loans.setAmountPaid(loansDTO.getAmountPaid());
        loans.setMobileNumber(loansDTO.getMobileNumber());
        loans.setOutstandingAmount(loansDTO.getOutstandingAmount());
        return loans;
    }

    public static LoansDTO mapsToLoansDTO(Loans loans, LoansDTO loansDTO) {
        loansDTO.setLoanNumber(loans.getLoanNumber());
        loansDTO.setLoanType(loans.getLoanType());
        loansDTO.setTotalLoan(loans.getTotalLoan());
        loansDTO.setAmountPaid(loans.getAmountPaid());
        loansDTO.setMobileNumber(loans.getMobileNumber());
        loansDTO.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDTO;
    }
}
