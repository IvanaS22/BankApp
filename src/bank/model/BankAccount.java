
package bank.model;

import java.io.Serializable;

public class BankAccount extends AbstractBankAccount implements Serializable{
    
    private String accountNumber;
    private double ammount;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, double ammount) {
        this.accountNumber = accountNumber;
        this.ammount = ammount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    @Override
    public String toString() {
return accountNumber + " :"+ ammount;
    }
     
    
}
