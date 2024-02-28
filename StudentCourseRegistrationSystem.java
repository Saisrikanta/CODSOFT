package codesoft;
import java.util.*;

public class StudentCourseRegistrationSystem {

    private static Map<String, Course> courseDatabase = new HashMap<>();
    private static Map<Integer, Student> studentDatabase = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourseDatabase();
        initializeStudentDatabase();

        while (true) {
            displayMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    registerStudentForCourse();
                    break;
                case 3:
                    removeStudentFromCourse();
                    break;
                case 4:
                    displayRegisteredCourses();
                    break;
                case 5:
                    exitSystem();
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Welcome to the Student Course Registration System");
        System.out.println("------------------------------------------------");
        System.out.println("1. Display Available Courses");
        System.out.println("2. Register Student for Course");
        System.out.println("3. Remove Student from Course");
        System.out.println("4. Display Registered Courses");
        System.out.println("5. Exit System");
        System.out.print("Enter your choice: \n");
    }

    private static void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
            System.out.println("Available Slots: " + course.getCapacity());
        }
    }

    private static void registerStudentForCourse() {
        System.out.println("Enter student ID:\n");
        int studentId = sc.nextInt();

        Student student = studentDatabase.get(studentId);
        if (student == null) {
            System.out.println("Student not found!\n");
            return;
        }

        System.out.println("Enter course code to register:");
        String courseCode = sc.next();

        Course course = courseDatabase.get(courseCode);
        if (course == null) {
            System.out.println("Course not found!\n");
            return;
        }

        if (course.getCapacity() > 0) {
            student.addRegisteredCourse(course);
            course.setCapacity(course.getCapacity() - 1);
            System.out.println("Course registration successful!\n");
        } else {
            System.out.println("Course is full!\n");
        }
    }

    private static void removeStudentFromCourse() {
        System.out.println("Enter student ID:\n");
        int studentId = sc.nextInt();

        Student student = studentDatabase.get(studentId);
        if (student == null) {
            System.out.println("Student not found!\n");
            return;
        }

        System.out.println("Enter course code to remove:\n");
        String courseCode = sc.next();

        Course course = courseDatabase.get(courseCode);
        if (course == null) {
            System.out.println("Course not found!\n");
            return;
        }

        if (student.getRegisteredCourses().contains(course)) {
            student.removeRegisteredCourse(course);
            course.setCapacity(course.getCapacity() + 1);
            System.out.println("Course removal successful!\n");
        } else {
            System.out.println("Student is not registered for this course!\n");
        }
    }

    private static void displayRegisteredCourses() {
        System.out.println("Enter student ID:\n");
        int studentId = sc.nextInt();

        Student student = studentDatabase.get(studentId);
        if (student == null) {
            System.out.println("Student not found!\n");
            return;
        }

        System.out.println("Registered Courses:");
        if (student.getRegisteredCourses().isEmpty()) {
            System.out.println("No registered courses found.\n");
        } else {
            for (Course course : student.getRegisteredCourses()) {
                System.out.println(course);
            }
        }
    }

    private static void initializeCourseDatabase() {
        Course course1 = new Course("Cid_101", "Introduction to Programming", "This course introduces the fundamental concepts of programming, including data types, variables, operators, control structures, and functions.", 50, "MWF 10:00-11:00\n");
        Course course2 = new Course("Cid_102", "Data Structures and Algorithms", "This course introduces the fundamental concepts of data structures and algorithms, including arrays, linked lists, stacks, queues, trees, and graphs.", 40, "TTh 11:00-12:30\n");
        Course course3 = new Course("Cid_103", "Database Systems", "This course introduces the fundamental concepts of database systems, including data models, database design, SQL, and transaction processing.", 30, "MWF 13:00-14:30\n");

        courseDatabase.put(course1.getCourseCode(), course1);
        courseDatabase.put(course2.getCourseCode(), course2);
        courseDatabase.put(course3.getCourseCode(), course3);
    }

    private static void initializeStudentDatabase() {
        Student student1 = new Student(12345, "Sai Srikanta");
        Student student2 = new Student(23456, "Laxmi Prasanna");
        Student student3 = new Student(34567, "Shiva");

        studentDatabase.put(student1.getStudentId(), student1);
        studentDatabase.put(student2.getStudentId(), student2);
        studentDatabase.put(student3.getStudentId(), student3);
    }

    private static void exitSystem() {
        System.out.println("Exiting the system. Thank you for using the Student Course Registration System!\n");
        System.exit(0);
    }
}

class Course {

    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    
    public String toString() {
        return "Course Code: " + courseCode + ", Title: " + title + ", Description: " + description + ", Capacity: " + capacity + ", Schedule: " + schedule;
    }
}

class Student {

    private int studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public void addRegisteredCourse(Course course) {
        registeredCourses.add(course);
    }

    public void removeRegisteredCourse(Course course) {
        registeredCourses.remove(course);
    }

    
    public String toString() {
       
        return "Student ID: " + studentId + ", Name: " + name + ", Registered Courses: " + registeredCourses;
    }
}