<%@page import="modele.CatchRules"%>
<%@page import="modele.Rule"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.ConnectServlet"%>
<%@page import="servlet.AfficherReglesServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%! String[] boxTab = ConnectServlet.boxNames ; ArrayList<Rule> rules = CatchRules.rules ; %>
<!DOCTYPE html>

<html lang="fr" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="ISO-8859-1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>RulesMasterWeb</title>

    <!-- Bootstrap: latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />

</head>

<body>

	<img src="logogsb.jpg" style="width:304px;height:228px;display:inline;margin-left: 10px;margin-top: 7px;"/>
	
	<span style="font-size: 30px;margin-left:28%">RulesMasterWeb</span>
	
	<form method="get" action="deco" style="display:inline;"><input type="submit" name="deconnection" value="Deconnexion" style="position:absolute;margin-top:20px;margin-left:36%;" ></form>

	<div class="page">
	
		<h1 style="text-align:center;font-family:Lucida;">Règles de messagerie de : <span style="color:#1E90FF;"><%=ConnectServlet.id%></span></h1>
    
    	<div style="margin-left: 85%;">	
                
	        <span style="text-align: center;">
	            <form method="get" action="ajouter" style="display:inline;"><input type="submit" class="btn btn-success" name="ajouter" value="Ajouter"/></form> 
	        </span>
	        <span style="text-align: center;">
	            <input type="submit" form="suppr" class="btn btn-primary" name="supprimer" value="Supprimer" /> 
	        </span>
	        <span style="text-align: center;"> 
	        	<form method="get" action="executer" style="display:inline;"><input type="submit" class="btn btn-warning" name="executer" value="Executer"/></form>
	        </span>
        
   	    </div>

    	<br />
   		<span style="margin:20px;font-size: 20px;font-family:Lucida;">Nombre de mail deplacé : ${ numMatch }</span>
    	<br />
    
    	<div style="height: 400px;overflow: auto;"> 
		    <table style="margin-left:20px;width:97%;" class="table table-striped table-bordered">
		        <thead>
		            <tr>
		            	<th>Boite</th>
		                <th>Expéditeur</th>
		                <th>Sujet</th>
		                <th>Corps du Message</th>
		                <th>Boîte Destination</th>
		                <th style="width: 50px;">Supprimer</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<% for(int i = 0 ; i < rules.size() ; i++ ) { %>
		            <form method="get" action="supprimer" id="suppr">
		            	<tr>
		            		<td><%=rules.get(i).getBoite() %></td>
			                <td><%=rules.get(i).getExpediteur()%></td>
			                <td><%=rules.get(i).getObjet() %></td>
			                <td><%=rules.get(i).getBody() %></td>
			                <td><%=rules.get(i).getBoite_destination() %></td>
			                <td>
			                	<input type="checkbox" name="line" value="<%=i%>" />	                     
			                </td>
		            	</tr>
		            <form>
		            <% } %>
		        </tbody>
		    </table>
		</div>   
	</div>

	<p style="margin-left:20px;margin-top:200px;">© 2015 RulesMasterWeb Tous droits reservés</p>

</body>
</html>
