/*
 * This contains all the constants used in the project
 * Daniel Aréchiga 2011
 */
package comode.common;

public class COMoDEconstants {
    // Max number of items to evaluate (DB limit:12)
    public static final int MAX_ITEMS = 12; //number of items to be shown to the user
    public static final int MAX_DB_ITEMS = 12; //number of items in DB
    public static final String ITM_TYPE[] = {"0","1","2","0","0","0","1","1","1","2","2","2"};
    
    // Installation path constant
    public static final String INSTALL_PATH = "/experiment";
    
    // some path constants
    public static final String JS_PATH = INSTALL_PATH+"/js/";
    public static final String JSP_PATH = INSTALL_PATH+"/jsp/";
    public static final String SERVLET_PATH = INSTALL_PATH+"/servlet/";
    public static final String CSS_PATH = INSTALL_PATH+"/style/";
    public static final String IMAGES_PATH = INSTALL_PATH+"/images/";
    public static final String ROOT_PATH = INSTALL_PATH+"/";
    
    // DB constants
    //public static final String DB_URL = "jdbc:mysql://localhost/test";
    //public static final String DB_RESULTS_NAME = "comode2";
    //public static final String DB_ITEMS_NAME = "comode_items";
    //public static final String DB_USR = "root";
    //public static final String DB_PWD = "burger";
    //public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    
    // Database constants
    public static final String DB_URL = "jdbc:mysql://148.202.119.6/dbExperiment";
    public static final String DB_RESULTS_NAME = "comode_results";
    public static final String DB_ITEMS_NAME = "comode_items";
    public static final String DB_USR = "comode";
    public static final String DB_PWD = "xperiment";
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    
    // user
    public static final String COMoDE_USER = "vdaniel";
    
    //Strings
    public static final String COMoDE_StrItemIns = "Por favor lea detalladamente el contexto que se describe, luego introduzca el texto que se le solicita en la caja de texto.";
    //public static final String COMoDE_StrItemIns = "Please read the following context described and introduce in the box the requested search query.";
    public static final String COMoDE_StrItemQuery = "texto a introducir";
    //public static final String COMoDE_StrItemQuery = "Search query";
    
    // Files
    public static final String COMoDE_THESAURUS = SERVLET_PATH+"comode/thesauri/comodethes.skos";
    
    // Styles
    public static final String COMoDE_STYLE = "styles_1.css";
    public static final String COMoDE_MOBILESTYLE = "stylesmobi.css";

}
