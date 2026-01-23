public class Teacher extends Person {
    String subject;

    public Teacher(String name, String password, String subject){
        super(name, password);
        this.subject = subject;
    }

    public String getSubject (){
        return this.subject;
    }
}
