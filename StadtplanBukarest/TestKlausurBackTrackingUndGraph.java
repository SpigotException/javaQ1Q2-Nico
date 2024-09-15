package StadtplanBukarest;

import gui.GUI;
import linear.List;
import linear.ListWithViewer;
import linear.Stack;
import linear.StackWithViewer;
import org.apache.commons.collections15.functors.WhileClosure;

import java.sql.SQLOutput;

public class TestKlausurBackTrackingUndGraph {

    public List<TestObjekt> Test(){

         List<TestObjekt> t1 = new ListWithViewer<>();
         TestObjekt A = new TestObjekt(0,null, "A");
        TestObjekt D = new TestObjekt(5,"B","D");
        TestObjekt E = new TestObjekt(10,"C","E");
        TestObjekt B = new TestObjekt(11,"A","B");
        TestObjekt G = new TestObjekt(11,"A","G");
        TestObjekt H = new TestObjekt(15,"D","H");
         t1.append(A);
        t1.append(D);
        t1.append(E);
        t1.append(B);
        t1.append(G);
        t1.append(H);

        TestObjekt Start = A;
        TestObjekt Ziel = H;
        Stack<TestObjekt> stack = new StackWithViewer<>();
        stack.push(Ziel);
        while (!Start.id.equals(Ziel.getVorgägnger())){


        for(t1.toFirst(); t1.hasAccess(); t1.next()){
            if(Ziel.getVorgägnger().equals(t1.getContent().getId())){
                stack.push(t1.getContent());
                System.out.println(Ziel);
                Ziel = t1.getContent();
                System.out.println(Ziel);
            }
        }stack.push(Start);
        }
         return t1;
    }
    public static void main(String[] args) {
     TestKlausurBackTrackingUndGraph testKlausurBackTrackingUndGraph = new TestKlausurBackTrackingUndGraph();
        new GUI(testKlausurBackTrackingUndGraph);

    }
}
