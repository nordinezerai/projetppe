package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExecuterReglesServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{ 
		MatchToken mt = new MatchToken();
		int numMatch = mt.match();
		String numMatched = ""+numMatch;
		request.setAttribute("numMatch", numMatched);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/rules.jsp" ).forward( request, response );
	}

}
