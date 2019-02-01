/*
 * Author   : Daniel Aréchiga
 * Function : Generates the word recommendation items list, also filters the 
 * matched terms
 */

package comode.wordrecom;

import comode.common.COMoDEconstants;
import comode.main.COMoDEMain;
import java.util.ArrayList;
import java.util.List;
//import java.util.StringTokenizer;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.*;

public class termsList {
    private int totalItems;
    private List<String> items;

    //Function which resturns the entire list of terms
    //@to-do: insert the thesaurus code
    public termsList(String user, String ActualItem) throws IOException {
        // initializes the items list
        // items = readFile(COMoDEconstants.COMoDE_THESAURUS_FILE);
        items = new ArrayList<String>();
        int ItemType = Integer.parseInt(COMoDEconstants.ITM_TYPE[Integer.parseInt(ActualItem)-1]);
        /*
        items.add("guadalajara");
        items.add("scuba");
        items.add("madrid");
        items.add("mexico");
        items.add("restaurant");
        items.add("movie");
        items.add("london");
        items.add("hotel");
        items.add("hostal");
        items.add("museum");
        items.add("football");
        items.add("storage");
        items.add("raquetball");
        */
        // COMoDETermsList code from here -->>
        System.out.println("Creando instancia de COMoDEMain");
        COMoDEMain docomode = new COMoDEMain();
        System.out.println("Ejecutando COMoDEMain.COMoDEMainProcess");
        switch (ItemType){
                case 0: System.out.println("switch 0: no word recommendation") ;
                        // Do nothing, no list should be created
                        break;
                case 1: System.out.println("switch 1: simple word recommendation");
                        items = docomode.COMoDEMainProcess(user, false, 1);
                        break;
                case 2: System.out.println("switch 2: context supported word recommendation");
                        items = docomode.COMoDEMainProcess(user, true, 1);
                        break;
            }
        
        System.out.println("The final terms list is "+items.size()+" long");
        
        // <<-- to here
        totalItems = items.size();
    }

    //generates the keystroked-matched terms list
    public List<String> getData(String query) {
        String item = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i<totalItems; i++) {
            item = items.get(i).toLowerCase();
            //System.out.println(items.get(i));
            if(item.startsWith(query)) {
                matched.add(items.get(i));
            }
        }
        return matched;
    }
    
    // Generates the terms list form the given file
    private List<String> readFile(String filename)
    {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }
    
    
}
