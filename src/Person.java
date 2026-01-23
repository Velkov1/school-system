public class Person {
    protected String name;
    protected String password;

    public Person(String name, String password){
        this.name = name;
        this.password = password;
    }

    public boolean getPassword(String password){
        return this.password.equals(password);
    }
    public String getName(){
        return name;
    }

    public void changePassword(String password){
        this.password = password;
    }

}
