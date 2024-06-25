package com.tk.loan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Loans extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "loan_number")
    private String loanNumber;
    @Column(name = "loan_type")
    private String loanType;
    @Column(name = "total_loan")
    private int totalLoan;
    @Column(name = "amount_paid")
    private int amountPaid;
    @Column(name = "outstanding_amount")
    private int outstandingAmount;

}
