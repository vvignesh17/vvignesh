import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;

class Student { // This class is created for initialization  of Student details
private final int tid;
private final String name;
private final double cgpa;

public Student(int tid, String name, double cgpa) { //constructor is created to initialize cgpa,name,tid
this.tid=tid;       
this.name=name;
this.cgpa=cgpa;     
    }
    
public int getTID() {// method to return token ID of the student
return tid;
}
    
public String getName() {// method to return Name of the student
return name;
}
    
public double getCGPA() {// method to return CGPA of the student
return cgpa;
}
}

class StudentComparator implements Comparator<Student> //Comparator Interface is used
{
@Override
public int compare(Student s1, Student s2){ //compare method in Comparator Interface
if (s1.getCGPA() == s2.getCGPA()) {   /* equal methd is used;if both the students have same CGPA then following code should perform */
if(s1.getName().equals(s2.getName())) {//if both the student have equal name then check tokenID
return Integer.compare(s2.getTID(),s1.getTID());
}
else {
return s1.getName().compareTo(s2.getName());//compareTo is used to compare two strings
 } 
 }
 return Double.compare(s2.getCGPA(),s1.getCGPA());    
 }
}

class Priorities {
public List<Student> getStudents(List<String> events)//to process all the given events and return all the students yet to be served in the priority order 
{
PriorityQueue pq = new PriorityQueue<>(events.size(), new StudentComparator());
for (String event: events) {
String parts[] = event.split(" ");//splting the events so that we can use Name,CGPA and tokenID seperately
 if (parts[0].equals("ENTER")) {//we want to add the data in queue only when enter is written
 Student stu = new Student(Integer.parseInt(parts[3]), parts[1], Double.parseDouble(parts[2]));
 pq.add(stu);// for adding above details to pq
 } 
 else {
 if (!pq.isEmpty()) {// if SERVED is entered,then it means we have to pop or remove the student according to the priority
  pq.poll();
  }     
 }
}
 List<Student> students = new ArrayList<>();
while (!pq.isEmpty()) {// we want to add all the students which are not served in one queue
students.add((Student) pq.poll());
        }
  return students;// returning students who are in the queue
    }
}



public class Solution { //main class
    private final static Scanner in = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {//main function
        int totalEvents = Integer.parseInt(in.nextLine());// Total number of events to be performed    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {//Perform all the events one by one
            String event = in.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);//to perform the events given by the user
        
        if (students.isEmpty()) { //if the queue is empty,print EMPTY
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {// else print the name of all the students in the queue
                System.out.println(st.getName());
            }
        }
    }
}
