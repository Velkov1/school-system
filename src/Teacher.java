public class Teacher extends Person {
    static String subject;

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

    public void addGradeToStudent(Student student, double grade){
        student.addGrade(grade);
        System.out.println("Grade added successfully!");
    }
}
