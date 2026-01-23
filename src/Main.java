import java.util.*;
import java.util.Scanner;



class Main{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        String[] roles = new String[] {"principal", "teacher", "student"};
        HashMap<String, Person> teachers = new HashMap<>();
        HashMap<String, Person> students = new HashMap<>();
        HashMap<String, Person> principal = new HashMap<>();

        //example users
        Principal head = new Principal("Mr. Adam Smith", "password123");
        principal.put(head.getName(), head);

        Teacher teacher1 = new Teacher ("Ivan Petrov", "pass1", "Maths");
        teachers.put(teacher1.getName(), teacher1);
        Teacher teacher2 = new Teacher ("Martin Bogdanov", "pass2", "History");
        teachers.put(teacher2.getName(), teacher2);
        Teacher teacher3 = new Teacher ("Petar Ivanov", "pass3", "English");
        teachers.put(teacher3.getName(), teacher3);

        Student student1 = new Student("Ivan","p123", new String [] {"Maths", "Physics"});
        students.put(student1.getName(), student1);
        Student student2 = new Student("Martin","m123", new String [] {"Literature", "German"});
        students.put(student2.getName(), student2);
        Student student3 = new Student("Petar","p123", new String [] {"Sports", "German"});
        students.put(student3.getName(), student3);

        String [] subjects = new String [] {"Maths", "English", "History", "Geography", "Literature", "Physics", "German", "Sports"};

        while(true){
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
            System.out.println();
            switch(role){
                case "teacher":
                    Teacher teacher = (Teacher) person;
                    teacherActions(input, teacher, students);
                    break;
                case "student":
                    Student student = (Student) person;
                    studentActions(input, student, students);
                    break;
                case "principal":
                    Principal prin = (Principal) person;
                    principalActions(input, prin, teachers, students, subjects);
                    break;
                default:
                    System.out.println("There is an error. Try again later.");
            }
            System.out.println("Do you want to use another user? (Y / N)");
            String choice = input.next();
            input.nextLine();
            if(choice.equals("n") ||choice.equals("N")){
                System.out.println("Thank you for using the School System today! Have a nice day\nLogging out...");
                break;
            }
        }

    }
    public static String getRole(Scanner input, String[] roles){

        String role = "";
        while(true){
            System.out.println("Please enter your role in the school:");
            role = input.next();
            input.nextLine();
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
        while(true){
            System.out.println("Please enter your username: ");
            username = input.nextLine();

            if(map.containsKey(username)){
                break;
            }
            else{
                System.out.println("Please enter a valid username.");
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
        return person.getPassword(pass);
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

    public static Person getUser(String username, Map<String, Person> map){
        if(map.containsKey(username)){
            return map.get(username);
        }
        return null;
    }


    public static void teacherActions(Scanner input, Teacher teacher, Map<String, Person> students){
        while(true){
            int action = 0;
            while(true){
                System.out.println("What do you want to do today?\nEnter a number from: ");
                System.out.println("1. Add grade.");
                System.out.println("2. Remove grade.");
                System.out.println("3. Open the gpa of the school.");
                System.out.println("4. Change password.");
                System.out.println("5. Back.");
                if(input.hasNextInt()){
                    action = input.nextInt();
                    input.nextLine();
                    if(action < 1 || action > 5){
                        System.out.println("Please enter a valid action.");

                    }
                    else{
                        break;
                    }
                }
                else{
                    System.out.println("Please enter the number of the wanted action.");
                    input.nextLine();
                }
            }

            if(action == 5){
                System.out.println("Going back. One moment...");
                break;
            }
            switch(action){
                case 1:
                    addGradeToStudent(input, teacher, students);
                    break;
                case 2:
                    removeGradeToStudent(input, teacher, students);
                    break;
                case 3:
                    openGpa(input, students);
                    break;
                case 4:
                    changePassword(input, teacher);
                    break;
            }
            System.out.println();
        }


    }

    public static void studentActions(Scanner input, Student student, Map <String, Person> students){
        while(true){
            int action = 0;
            while(true){
                System.out.println("What do you want to do today?\nEnter a number from: ");
                System.out.println("1. See my grades.");
                System.out.println("2. See my subjects.");
                System.out.println("3. See your place in the leaderboard.");
                System.out.println("4. Change password.");
                System.out.println("5. Back.");
                if(input.hasNextInt()){
                    action = input.nextInt();
                    if(action < 1 || action > 5){
                        System.out.println("Please enter a valid action.");
                    }
                    else{
                        break;
                    }
                }
                else{
                    System.out.println("Please enter the number of wanted action.");
                    input.nextLine();
                }
            }
            if(action == 5){
                System.out.println("Going back. One moment...");
                break;
            }
            switch(action){
                case 1:
                    System.out.println("Your grades are:");
                    student.getGrades();
                    break;
                case 2:
                    System.out.println("Your subjects are: ");
                    student.getSubjects();
                    break;
                case 3:
                    System.out.print("Your place in the school based on the gpa is: ");
                    findPlace(student, students);
                    break;
                case 4:
                    changePassword(input, student);
                    break;
                default:
                    break;

            }
            System.out.println();
        }


    }

    public static void principalActions(Scanner input, Principal principal, Map <String, Person> teachers, Map<String, Person> students, String[] subjects){
        while(true){
            int action = 0;
            while(true){
                System.out.println("What do you want to do today?\nEnter a number from: ");
                System.out.println("1. Add new teacher");
                System.out.println("2. Remove a teacher");
                System.out.println("3. Add new student");
                System.out.println("4. Remove a student");
                System.out.println("5. See the details of a teacher");
                System.out.println("6. See the details of a student");
                System.out.println("7. See gpa of the school/one student");
                System.out.println("8. Change password");
                System.out.println("9. Back");
                if(input.hasNextInt()){
                    action = input.nextInt();
                    input.nextLine();
                    if(action < 1 || action > 9){
                        System.out.println("Please enter a number from the choices. ");
                    }
                    else{
                        break;
                    }
                }
                else{
                    System.out.println("Please enter a number to choose. ");
                    input.nextLine();
                }
            }
            if(action == 9){
                System.out.println("Going back. One moment...");
                break;
            }
            switch(action){
                case 1:
                    Teacher created = addTeacher(input, subjects);
                    teachers.put(created.getName(), created);
                    break;
                case 2:
                    removePerson(input, teachers);
                    break;
                case 3:
                    Student createdStudent = addStudent(input, subjects);
                    students.put(createdStudent.getName(), createdStudent);
                    break;
                case 4:
                    removePerson(input, students);
                    break;
                case 5:
                    getTeacherInfo(input, teachers);
                    break;
                case 6:
                    getStudentInfo(input, students);
                    break;
                case 7:
                    openGpa(input, students);
                    break;
                case 8:
                    changePassword(input, principal);
                    break;
            }
            System.out.println();
        }

    }



    public static void addGradeToStudent(Scanner input, Teacher teacher, Map<String, Person> students){
        String name = "";
        while(true){

            System.out.println("Enter a student's name:");
            name  = input.nextLine();
            if(students.containsKey(name)){
                break;
            }
            else{
                System.out.println("Please enter a valid student's name.");
            }
        }
        String subject = "";
        while(true){
            System.out.println("Enter the subject you want to add the note(only in the subject that you teach!): ");
            if(input.hasNextLine()){
                subject = input.nextLine();
                if(subject.equals(teacher.subject)){
                    break;
                }
                else{
                    System.out.println("Please enter your subject: ");
                }
            }
        }
        double grade = 0.0;
        while(true){
            System.out.printf("Enter the %s's grade: ", name);
            if(input.hasNextDouble()){
                grade = input.nextDouble();
                if(grade < 1.0 || grade > 6.0){
                    System.out.println("Please enter a valid grade(1 - 6).");
                }
                else{
                    break;
                }
            }
            else{
                System.out.println("Please enter a number for the grade(1-6): ");
            }

        }

        Student currentStudent = (Student) getUser(name, students);
        if(currentStudent == null){
            System.out.println("Student not found");
            return;
        }
        currentStudent.addGrade(grade, subject);

    }

    public static void removeGradeToStudent(Scanner input, Teacher teacher, Map<String, Person> students){
        String name = "";
        while(true){
            System.out.println("Enter a student's name:");
            name  = input.nextLine();
            if(students.containsKey(name)){
                break;
            }
            else{
                System.out.println("Please enter a valid student's name.");
            }
        }
        Student currentStudent = (Student) getUser(name, students);
        if(currentStudent == null){
            System.out.println("Student not found");
            return;
        }
        String subject = "";
        while(true){
            System.out.println("Enter the subject you want to remove the note(only in the subject that you teach and the students has this subject!): ");
            if(input.hasNextLine()){
                subject = input.nextLine();
                if(subject.equals(teacher.subject)){
                    if(currentStudent.hasSubject(subject)){
                        break;
                    }
                    else{
                        System.out.println("This student does not have this subject. Try again.");
                    }

                }
                else{
                    System.out.println("Please enter your subject: ");
                }
            }
        }

        double grade = 0.0;
        while(true){
            System.out.printf("Enter the %s's grade: ", name);
            if(input.hasNextDouble()){
                grade = input.nextDouble();
                if(grade < 1.0 || grade > 6.0){
                    System.out.println("Please enter a valid grade(1 - 6).");
                }
                else{
                    if(currentStudent.hasGrade(subject, grade)){
                        currentStudent.removeGrade(grade, subject);
                        break;
                    }
                    else{
                        System.out.println("The student does not have such grade in this subject. Enter a correct grade");
                    }

                }
            }
            else{
                System.out.println("Please enter a number for the grade(1-6): ");
            }

        }
    }

    public static void openGpa(Scanner input, Map <String, Person> students){
        int choice = 0;
        double gpa = 0.0;
        while(true){
            System.out.println("Please chose whose gpa you want to see: ");
            System.out.println("1. Only of one student.");
            System.out.println("2. Of the whole school. ");
            System.out.println("Please enter only the number of your choice");
            if(input.hasNextInt()){
                choice = input.nextInt();
                input.nextLine();
                if(choice < 1 || choice > 2){
                    System.out.println("Please enter a valid choice!");
                }
                else{
                    break;
                }
            }
            else{
                System.out.println("Please enter the number of the your choice!");
            }
        }
        switch(choice){
            case 1:
                String name = "";
                while(true){
                    System.out.println("Enter a student's name you want to check:");
                    name  = input.nextLine();
                    if(students.containsKey(name)){
                        break;
                    }
                    else{
                        System.out.println("Please enter a valid student's name.");
                    }
                }
                Student currentStudent = (Student) getUser(name, students);
                if(currentStudent != null){
                    gpa = currentStudent.calculateGpa();
                    System.out.printf("The GPA of %s is %f", name, gpa);
                    System.out.println();
                }
                else{
                    System.out.println("Error. Person not found. Please try again later.");
                }
                break;
            case 2:
                for (Map.Entry<String, Person> i : students.entrySet()){
                    Student stud = (Student) getUser(i.getKey(), students);
                    if(stud != null){
                        System.out.printf("%s - %.2f", i.getKey(), stud.calculateGpa());
                        System.out.println();
                    }
                }
                break;

        }
    }

    public static void changePassword(Scanner input, Person person){
        System.out.println("Enter a new password: ");
        String newPass = input.next();
        input.nextLine();
        person.changePassword(newPass);
        System.out.println("Password changed successfully!");
    }

    public static void findPlace(Student student, Map <String, Person> students){
        double currStudentGpa = student.calculateGpa();
        List<Double> allGPAs = new ArrayList<>();
        int place = 1;
        for(Map.Entry<String, Person> i : students.entrySet()){
            Student current = (Student) getUser(i.getKey(),students);
            if(current != null){
                allGPAs.add(current.calculateGpa());
            }
        }
        allGPAs.sort(Collections.reverseOrder());
        for (Double allGPA : allGPAs) {
            if (currStudentGpa < allGPA) {
                place++;
            }
        }
        if(place > 0){
            System.out.println(place);
        }
        else{
            System.out.println("Error occurred. Couldn't calculate the place. ");
        }
    }
    public static Teacher addTeacher (Scanner input, String[] subjects){
        String name = "";
        String password = "";
        String subject = "";

        System.out.println("Please enter the name of the teacher you want to add");
        name = input.nextLine();

        System.out.println("Enter the password of this teacher: ");
        password = input.next();
        input.nextLine();

        boolean valid = false;
        while(true){
            System.out.println("Enter the subject the teacher will teach");
            subject = input.next();
            input.nextLine();
            for (String s : subjects){
                if(subject.equals(s)){
                    valid = true;
                    break;
                }
            }
            if(valid){
                break;
            }
            else{
                System.out.println("Please enter a valid subject");
            }
        }
        Teacher teacher = new Teacher(name, password, subject);
        System.out.println("New teacher added successfully!");
        return teacher;
    }
    public static void removePerson(Scanner input, Map <String, Person> people){
        String name = "";
        while(true){
            if(input.hasNextLine()){
                System.out.println("Please enter the name of the person you want to remove: ");
                name = input.nextLine();

                Person current = getUser(name, people);
                if(current == null){
                    System.out.println("There is no such person yet. ");
                    input.nextLine();
                }
                else{
                    people.remove(name);
                    break;
                }
            }
        }
        System.out.println("Removed successfully!");
    }

    public static Student addStudent(Scanner input, String[] subjects){
        String name = "";
        String password = "";
        List <String> subject = new ArrayList<>();
        String s = "";

        System.out.println("Please enter the name of the student you want to add");
        name = input.nextLine();

        System.out.println("Enter the password of this student: ");
        password = input.nextLine();



        while(true){
            boolean valid = false;
            System.out.println("Enter the subjects the student will learn (one per click)");
            s = input.next();
            input.nextLine();
            for (String sub : subjects){
                if(s.equals(sub)){
                    valid = true;
                    break;
                }
            }
            if(valid){
                subject.add(s);
                System.out.println("Do you want to add more ( Y/N ): ");
                String choose = input.next();
                input.nextLine();
                if(choose.equals("N") || choose.equals("n")){

                    break;
                }

            }
            else{
                System.out.println("Please enter a valid subject");
            }
        }

        String [] allSubjects = listArray(subject);
        Student student = new Student(name, password, allSubjects);
        System.out.println("New student added successfully!");
        return student;
    }

    public static String[] listArray(List <String> list){
        String[] array = new String[list.size()];

        for(int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }
    public static void getTeacherInfo(Scanner input, Map <String, Person> teachers){
        String name = "";
        while(true){
            System.out.println("Enter teacher name: ");
            name = input.nextLine();
            //input.nextLine();
            Teacher current = (Teacher) getUser(name, teachers);
            if(current != null){
                teacherPrintInfo(current);
                break;
            }
            else{
                System.out.println("Teacher not found. Try again.");
            }
        }
    }

    public static void teacherPrintInfo(Teacher teacher){
        System.out.printf("The %s's information is: ", teacher.getName());
        System.out.println("Name: " + teacher.getName());
        System.out.println("Subject " + teacher.getSubject());
    }
    public static void getStudentInfo(Scanner input, Map <String, Person> students){
        String name = "";
        while(true){

            System.out.println("Enter the name of the student: ");
            name = input.nextLine();
            Student current = (Student) getUser(name, students);
            if(current != null){
                studentPrintInfo(current);
                break;
            }
            else{
                System.out.println("Student not found. Try again.");
            }
        }
    }

    public static void studentPrintInfo(Student student){
        System.out.printf("The %s's information is: ", student.getName());
        System.out.println("Name: " + student.getName());
        System.out.print("Subjects ");
        student.getSubjects();
        System.out.println();
        System.out.print("Grades: ");
        student.getGrades();
        System.out.println();
    }

}

