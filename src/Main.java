import java.util.*;
import java.util.Scanner;


class Main{

    public static String getRole(Scanner input, String[] roles){
        String role = "";
        while(input.hasNextLine()){
            System.out.println("Please enter your role in the school:");
            role = input.nextLine();
            if(isRole(role, roles)){
                break;
            }
            else{
                System.out.println("Please enter a valid role:");

            }
        }
        return role;
    }
    public static boolean isRole(String role, String[] roles){
        for(String i : roles){
            if (i.equals(role)){
                return true;
            }
        }
        return false;
    }


    public static String getUsername(Scanner input, Map<String, Person> map){
        String username = "";
        while(input.hasNextLine()){
            System.out.println("Please enter your username: ");
            username = input.nextLine();
            if(map.containsKey(username)){
                break;
            }
            else{
                System.out.println("Please enter a valid username: ");
            }
        }
        return username;
    }



    public static String checkPassword(Scanner input, String username, Map<String, Person> map){
        String pass = "";
        while(true){
            System.out.println("Enter your password");
            pass = input.nextLine();
            if(isValidPass(pass, username, map)){
                return pass;
            }
            else{
                System.out.println("Invalid password. Try again.");
            }
        }
    }

    public static boolean isValidPass(String pass, String username, Map<String, Person> map){
        Person person = map.get(username);
        if(person.getPassword(pass)){
            return true;
        }
        else{
            return false;
        }
    }

    public static Person getUser(String username, String password, Map<String, Person> map){
        if(map.containsKey(username)){
            Person person = map.get(username);
            if(person.getPassword(password)){
                return person;
            }

        }
        return null;
    }
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        String[] roles = new String[] {"principal", "teacher", "student"};
        HashMap<String, Person> teachers = new HashMap<String, Person>();
        HashMap<String, Person> students = new HashMap<String, Person>();
        HashMap<String, Person> principal = new HashMap<String, Person>();
        Principal head = new Principal("Mr. Adam Smith", "password123");
        principal.put(head.getName(), head);

        String [] subjects = new String [] {"Maths", "English", "History", "Geography", "Literature", "Physics", "German", "Sports"};
        System.out.println("Hello, please log in the school system to continue.");

        String role =getRole(input, roles);
        String username = "";
        String password = "";
        Person person = null;
        while(true){
            if(role.equals("teacher")){
                if(teachers.isEmpty()){
                    System.out.println("There are not teachers added yet. PLease log in as the principal to continue!");
                    role =getRole(input, roles);
                }
                else{
                    username = getUsername(input, teachers);
                    password = checkPassword(input,username, teachers );
                    person = getUser(username, password, teachers);
                    break;
                }
            }
            else if(role.equals("student")){
                if(students.isEmpty()){
                    System.out.println("There are not students added yet. PLease log in as the principal to continue!");
                    role =getRole(input, roles);
                }
                else{
                    username = getUsername(input, students);
                    password = checkPassword(input,username, students );
                    person = getUser(username, password, students);
                    break;
                }
            }
            else if(role.equals("principal")){
                username = getUsername(input, principal);
                password = checkPassword(input,username, principal);
                person = getUser(username, password, principal);
                break;
            }
            else{
                break;
            }
        }

        System.out.printf("Welcome again %s. How are you today?", username);
        switch(role){
            case "teacher":
                Teacher teacher = (Teacher) person;
                teacherActions(teacher);
                break;
            case "student":
                Student student = (Student) person;
                studentActions(student);
                break;
            case "principal":
                Principal prin = (Principal) person;
                principalActions(prin);
                break;
            default:
                System.out.println("There is an error. Try again later.");
        }
    }


    public static void teacherActions(Teacher teacher){

    }

    public static void studentActions(Student student){

    }

    public static void principalActions(Principal principal){

    }
}

