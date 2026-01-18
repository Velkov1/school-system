public class Person {
    protected String name;
    protected String password;

    public Person(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String showPassword(){
        return password;
    }
    public boolean getPassword(String password){
        if(this.password == password){
            return true;
        }
        else{
            return false;
        }
    }
    public String getName(){
        return name;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }
    public void setName(String newName){
        this.name = newName;
    }

}
