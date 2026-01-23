import java.util.*;

public class Student extends Person{
    private String[] subjects;

    private List<List<Double>> allGrades = new ArrayList<>();
    public Student(String name, String password, String[] subjects){
        super(name, password);
        this.subjects = subjects;
        for(int i = 0; i < subjects.length; i++){
            allGrades.add(new ArrayList<>());
        }
    }


    public void getGrades(){
        for(int i = 0; i < allGrades.size(); i++){
            System.out.printf("%s: ", subjects[i]);
            for(int j = 0; j < allGrades.get(i).size(); j++){
                System.out.print(allGrades.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public void getSubjects(){
        for (String subject : subjects) {
            System.out.print(subject + " ");
        }
    }
    public void addGrade(double grade, String subject){
        int n = Integer.MIN_VALUE;
        for (int i = 0; i < subjects.length; i++){
            if(subjects[i].equals(subject)){
                n = i;
            }
        }
        if (n == Integer.MIN_VALUE){
            System.out.println("Subject not found!");
        }
        else{
            allGrades.get(n).add(grade);
            System.out.printf("Grade added successfully to student %s!", this.name);
        }
    }

    public void removeGrade(double grade, String subject){
        int n = Integer.MIN_VALUE;
        for (int i = 0; i < subjects.length; i++){
            if(subjects[i].equals(subject)){
                n = i;
            }
        }
        if (n == Integer.MIN_VALUE){
            System.out.println("Subject not found!");
        }
        else{
            for (int i = 0; i < allGrades.get(n).size(); i++){
                if(allGrades.get(n).get(i) == grade){
                    allGrades.get(n).remove(i);
                    System.out.println("Grade removed successfully! ");
                    break;
                }
            }
        }

    }


    public boolean hasSubject(String subject){
        for (int i = 0; i < subjects.length; i++){
            if(subjects[i].equals(subject)){
                return true;
            }
        }
        return false;
    }

    public boolean hasGrade(String subject, double grade){
        int n = Integer.MIN_VALUE;
        for (int i = 0; i < subjects.length; i++){
            if(subjects[i].equals(subject)){
                n = i;
            }
        }
        if(n == Integer.MIN_VALUE){
            return false;
        }
        for (int i = 0; i < allGrades.get(n).size(); i++){
            if(allGrades.get(n).get(i) == grade){
             return true;
            }
        }
        return false;
    }

    public double calculateGpa(){
        double sum = 0.0;
        int count = 0;
        for (List<Double> allGrade : allGrades) {
            for (int j = 0; j < allGrade.size(); j++) {
                sum += allGrade.get(j);
                count++;
            }
        }
        sum = sum / count;
        return sum;
    }
}
