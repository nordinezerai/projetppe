package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.CatchRules;
import modele.Rule;

public class AjouterRegleServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{ 
		
		 String expediteur = request.getParameter("expediteur");
		 String objet = request.getParameter("objet");
		 String body = request.getParameter("body");
		 String boite_destination = request.getParameter("select2");
		 String boite = request.getParameter("select");
		 String error = "Veuillez remplir les champs obligatoire ";
		 
		 if((boite == null || boite.equals("Selectionnez une boite")) || (boite_destination == null || boite_destination.equals("Selectionnez une boite")) || (expediteur.equals("") && objet.equals("") && body.equals(""))){			 
			 request.setAttribute("error", error);
			 this.getServletContext().getRequestDispatcher( "/WEB-INF/ajouter.jsp" ).forward( request, response );
		 }
			 
		 else{
		 Rule rule = new Rule(expediteur,objet,body,boite_destination,boite);
		 
		 CatchRules.rules.add(rule);
		 ConnectServlet.cr.addRule(rule);	 
		 
		 this.getServletContext().getRequestDispatcher( "/WEB-INF/rules.jsp" ).forward( request, response );
		 }
	}
}
