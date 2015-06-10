package controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.CatchRules;
import modele.Rule;

public class AfficherReglesServlet extends HttpServlet {
    public static String boxSelected ;
    public static CatchRules cr;
    String message ;
    boolean boxIsSelected = false;

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{	  	
			this.getServletContext().getRequestDispatcher( "/WEB-INF/rules.jsp" ).forward( request, response );
	}
	
}