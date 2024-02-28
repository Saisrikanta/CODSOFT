package codesoft;

import java.util.Scanner;

interface BankAccount
{
    double getBalance();
    boolean withdraw(double amount);
    void deposit(double amount);
}

class SavingsAccount implements BankAccount 
{
    private double balance;

    public SavingsAccount(double balance) 
    {
        this.balance = balance;
    }

    public double getBalance()
    {
        return balance;
    }

    public boolean withdraw(double amount) 
    {
        if (balance >= amount)
        {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void deposit(double amount)
    {
        balance += amount;
    }
}

class ATM 
{
    private BankAccount account;

    public ATM(BankAccount account)
    {
        this.account = account;
    }

    public void withdraw(double amount) 
    {
        if (account.withdraw(amount)) 
        {
            System.out.println("Withdraw successfu, The remaining balance in the account is: $" + account.getBalance());
        } else
        {
            System.out.println("There is no sufficient amount to withdrawal.");
        }
    }

    public void deposit(double amount)
    {
        account.deposit(amount);
        System.out.println("Deposit successful. Balance in account after deposit : $" + account.getBalance());
    }

    public void checkBalance() 
    {
        System.out.println("Your current account  balance is: $" + account.getBalance());
    }
}

class ATMFactory
{
    public static ATM createATM(BankAccount account)
    {
        return new ATM(account);
    }
}

public class ATMInterface 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        BankAccount userAccount = new SavingsAccount(10000.0);

        ATM atm = ATMFactory.createATM(userAccount);

        while (true) 
        {
            System.out.println("Welcome to the ATM interface.");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            int choice = input.nextInt();

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter the amount to withdraw: $");
                    double withdrawAmount = input.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter the amount to deposit: $");
                    double depositAmount = input.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    atm.checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you!  Have a nice day");
                    System.exit(0);
                default:
                    System.out.println("Please choose a correct option.");
            }
        }
    }
}