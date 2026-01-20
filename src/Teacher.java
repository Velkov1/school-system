import java.util.Scanner;

public class Teacher extends Person {
    String subject;

    public Teacher(String name, String password, String subject){
        super(name, password);
        this.subject = subject;
    }
    public boolean checkPassword(String password){
        if (this.password.equals(password)){
            return true;
        }
        else{
            return false;
        }
    }

    public void showRole(){
        System.out.printf("You are %s and you are a teacher!", name);
    }


}
