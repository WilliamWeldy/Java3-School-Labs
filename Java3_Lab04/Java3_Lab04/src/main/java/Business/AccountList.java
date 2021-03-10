/*****************************************
 * Class: CIST 2373 Java Programming III
 * Semester: SUMMER 2020
 * Instructor: Ron Enz
 * Lab #04
 *
 * @author William  G.  Weldy
 * @version 1.0
 *****************************************/
package Business;

public class AccountList {
    public Account accArr[] = new Account[10];
    public int count;
    
    public void addAccount(Account a1) {
        accArr[count] = a1;
        count++;
    }
    
    public void displayList() {
        for(int x=0;x<count;x++) accArr[x].display();
    }
    
    public static void main(String args[]) {
        AccountList aList = new AccountList();
        
        Account a = new Account("7777", "3001", "SAV", 500.00);
        Account b = new Account("8888", "3002", "CHK", 700.00);
        
        aList.addAccount(a);
        aList.addAccount(b);
        
        aList.displayList();
    }
}
