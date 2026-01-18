import java.util.*;

public class Student extends Person{
    private String[] subjects;
    private List<Double> grades = new ArrayList<Double>();
    public Student(String name, String password, String[] subjects){
        super(name, password);
        this.subjects = new String[] {Arrays.toString(subjects)};
    }

    public String getName(){
        return this.name;
    }
    public void addGrade(double grade){
        this.grades.add(grade);
    }
    public List<Double> getGrades(){
        return this.grades;
    }
}
