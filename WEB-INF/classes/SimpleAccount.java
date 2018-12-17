// 武田佑樹 286120 課題5 (1)
public class SimpleAccount {
    private String name;
    private int balance;

    public SimpleAccount(String myName){
        name = myName;
        balance = 0;
    }
    
    public int deposit(int amount){
        if(amount <= 0){
            return -3;
        } else {
            balance += amount;
            return 0;
        }
    }
    
    public int withdraw(int amount){
        if(amount <= 0){
            return -3;
        } else if(amount > balance){
            return -1;
        } else {
            balance -= amount;
            return 0;
        }
    }
    
    public int showBalance(){
        return balance;
    }
    
}
