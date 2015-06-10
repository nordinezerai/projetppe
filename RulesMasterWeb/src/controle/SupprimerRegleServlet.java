package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.CatchRules;
import modele.Rule;

public class SupprimerRegleServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		Rule rule;
		String[] str = request.getParameterValues("line");
		
		if(str != null)
		{
			int i = 0 ;
			
			for(String num : str){
				System.out.println(num);
				
				
				rule = CatchRules.rules.get(Integer.parseInt(num)-i);
				
				ConnectServlet.cr.removeRule(rule);
				
				CatchRules.rules.remove(Integer.parseInt(num)-i);
				
				i++;
			}
		}
		
		 
		this.getServletContext().getRequestDispatcher( "/WEB-INF/rules.jsp" ).forward( request, response );
		
	}
}
