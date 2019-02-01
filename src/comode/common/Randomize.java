/*
 * Generates the random items list.
 * Daniel Aréchiga 2011
 */
package comode.common;

import java.util.*;

public class Randomize {
    
    // receive the maximum random number and a list of "banned" numbers
    // returns a random number between 1 <= n without "banned" numbers
    public int DoRandom(int n, List lst){
        Random generator = new Random();
        int i = 0;
        System.out.println("D@n: List size: "+lst.size());
        System.out.print("D@N: selected items list ");
        for (int j=0; j<lst.size(); j ++){
            System.out.print(lst.get(j)+",");
        }
        System.out.println();
        do {
            i = (generator.nextInt(n) + 1);
            System.out.println("D@n: random item obtained (to validate): "+i);;
        } while ( lst.contains(Integer.toString(i)));
        System.out.println("D@n: final random item: "+i);
        return i;
    }

    
}
