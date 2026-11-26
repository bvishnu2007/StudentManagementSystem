import java.util.*;

public class HashMap1 {
    public static void main(String[] args) {
        HashMap <Integer,Integer> d=new HashMap<>();
        d.put(101,123456791);
        d.put(103,123676791);
        d.put(102,123456781);
        d.put(104,123459891);
       System.out.println(d);

        d.put(105,124676791);
        d.put(106,123676791);
       System.out.println(d);
       d.put(106,1000000000);
       System.out.println(d);
      System.out.println(d.containsValue(123456791));
      d.keySet().remove(101);
      d.values().remove(123456791);
       System.out.println(d);
       long a=15437543;
       if(d.containsValue(a)){
        System.out.println("hello");

       }
       else{
        System.out.println("bye");
       
}
      
        
    }
}
