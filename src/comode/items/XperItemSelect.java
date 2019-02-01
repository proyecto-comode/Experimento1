/*
 * Defines the items selection issues
 * Daniel Aréchiga 2011
 */
package comode.items;

import java.io.IOException;
import comode.common.*;
import java.util.*;
import comode.db.*;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
public class XperItemSelect {
    public static final String queryString = "Daniel";
    
    /**
     * List of terms to feed the item screen
     * @param session HTTP session instance variable
     * @return a List with all the database information related to the random item selected
     * @throws SQLException
     * @throws IOException 
     */
    public List ItemSelect(HttpSession session) throws SQLException, IOException{
        List<String> tempo;
        List<String> ls = new ArrayList<String>();
        DBAdmin db = new DBAdmin();
        Randomize random = new Randomize();
        int i = random.DoRandom(COMoDEconstants.MAX_DB_ITEMS, ItemsUsed(session));
        //@to-do: eliminate the print
        System.out.println("D@N: The random item "+i+" was selected");
        //tempo = db.DBGetItem();
        session.setAttribute("ItemNumber"+session.getAttribute("SactualItem").toString(), i);
        tempo = db.DBItemGet(i);
        ls.add(tempo.get(0));
        ls.add(tempo.get(1));
        ls.add(tempo.get(2));
        ls.add(tempo.get(3));
        return ls;
    }
    
    /**
     * Identifyes the random items already used and creates the "banned" numbers list
     * @param session HTTP session instance variable
     * @return a List of already used numbers, to avoid using it again.
     */
    private List ItemsUsed(HttpSession session){
        List<String> UsedItems = new ArrayList<String>();
        if (Integer.parseInt(session.getAttribute("SactualItem").toString()) != 1){
            int actualItem = Integer.parseInt(session.getAttribute("SactualItem").toString());
            //@to-do: eliminate the print
            System.out.print("D@N: creating the used items list for the "+actualItem+" item: ");
            for (int i=1; i<actualItem; i++){
                UsedItems.add(session.getAttribute("ItemNumber"+i).toString());
                //@to-do: eliminate the print
                System.out.print(UsedItems.get(i-1)+" ");
            }
            //@to-do: eliminate the print
            System.out.println(" ");
        }
        else {
            //@to-do: eliminate the print
            System.out.println("D@N: doesn't need to create a used items list");
            UsedItems.add("0");
        }
        return UsedItems;
    }
    
    
}
