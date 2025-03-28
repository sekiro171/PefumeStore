
package model;

public class Wallet {
     private String userName;
    private double balance;

    public Wallet(String userName, double balance) {
        this.userName = userName;
        this.balance = balance;
    }

    

    public Wallet() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" + "userName=" + userName + ", balance=" + balance + '}';
    }
}
