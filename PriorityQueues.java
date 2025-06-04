import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueues {
       static class Student implements Comparable<Student> {//over riding
       String name;
       int rank ;

       public Student(String name , int rank) {
              this.name = name ;
              this.rank = rank ;
       }

 
              @Override
                      public int compareTo(Student s2){
                     return this.rank -s2.rank ;
              }
       }
       public static void main(String[] args) {
              PriorityQueue <Student> pq = new PriorityQueue<>(Comparator.reverseOrder()) ;

              pq.add(new Student("A",1) );//o(log n)
              pq.add(new Student("B",5));
              pq.add(new Student("C",2));
              pq.add(new Student("D",9));


              while(!pq.isEmpty()){
                     System.out.println(pq.peek().name + " "+pq.peek().rank);//o(1)
                     pq.remove() ; //o(log n)
              }
       }
}
  