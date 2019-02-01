/*
 * MobileDeviceDetector.java
 *
 * This code is based on Andy Moore's lightweight device-detection code.
 *
 */

package comode.tools;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Enumeration;
import java.io.*;

/**
 *
 * @author Eduardo
 */
public class MobileDeviceDetector {
    
    public boolean isMobile(HttpServletRequest request) throws Exception {
        
        String user_agent = request.getHeader("user-agent");
        String accept = request.getHeader("accept");
        String x_wap_profile = request.getHeader("x-wap-profile");
        String profile = request.getHeader("profile");
        String opera = request.getHeader("X-OperaMini-Phone");
        String ua_pixels = request.getHeader("ua-pixels");
        
        
        // Checks the user-agent
        if (user_agent != null) {
            
            // Checks if its a Windows browser but not a Windows Mobile browser
            if (user_agent.contains("windows") &&! user_agent.contains("windows ce")){
                return false;
            }
            
            // Checks if it is a mobile browser
            Pattern pattern = Pattern.compile("up.browser|up.link|windows ce|iphone|iemobile|mini|mmp|symbian|midp|wap|phone|pocket|mobile|pda|psp", Pattern.CASE_INSENSITIVE);
            
            Matcher matcher = pattern.matcher(user_agent);
            
            if (matcher.find()){
                return true;
            }
            
            // Checks if the 4 first chars of the user-agent match any of the most popular user-agents
            String[] ua = {"acs-","alav","alca","amoi","audi","aste","avan","benq","bird","blac","blaz","brew","cell","cldc","cmd-","dang","doco","eric","hipt","inno","ipaq","java","jigs","kddi","keji","leno","lg-c","lg-d","lg-g","lge-","maui","maxo","midp","mits","mmef","mobi","mot-","moto","mwbp","nec-","newt","noki","opwv","palm","pana","pant","pdxg","phil","play","pluc","port","prox","qtek","qwap","sage","sams","sany","sch-","sec-","send","seri","sgh-","shar","sie-","siem","smal","smar","sony","sph-","symb","t-mo","teli","tim-","tosh","tsm-","upg1","upsi","vk-v","voda","w3c ","wap-","wapa","wapi","wapp","wapr","webc","winw","winw","xda","xda-"};
            
            for(int i = 0; i < ua.length; i++ ) {
                if (ua[i] == user_agent.substring(0,3)) {
                return true;
                }
            }

        }
        
        // Checks the accept header for wap.wml or wap.xhtml support
        if (accept != null) {
            if (accept.contains("text/vnd.wap.wml") || accept.contains("application/vnd.wap.xhtml+xml")) {
                return true;
            }
        }
        
        // Checks if it has any mobile HTTP headers
        if (x_wap_profile != null || profile != null || opera != null || ua_pixels != null) {
            return true;
        }
        
        return false;
    }
    
    public void printHeaders(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Enumeration headerNames = request.getHeaderNames();
        PrintWriter out = response.getWriter();
        
        while(headerNames.hasMoreElements()) {
            String headerName = (String)headerNames.nextElement();
            
            out.println("<b>" + headerName + ":</b>");
            out.println(" " + request.getHeader(headerName) + "<br>");
        }
    }
    
}
