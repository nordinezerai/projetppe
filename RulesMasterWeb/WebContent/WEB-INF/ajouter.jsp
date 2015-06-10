<%@page import="servlet.ConnectServlet"%>
<%@ page pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1" />
    <title>Ajouter une règle</title>
    <link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body>
    <img src="logogsb.jpg" style="width:304px; height:228px;"/>
    
    <span style="font-size: 30px;margin-left:28%">RulesMasterWeb</span>
    
    <br /><br /><br /><br /><br /><br /><br /><br /><br />
                
    <h1 class="titleAjouter">Ajouter une règle</h1>
    
    <form method="get" action="ajouterregle">
          <fieldset>
                <legend>Ajouter</legend>
                
                <p class="note">Entrez les paramètres de la règle :</p>
                
                <br />
                
                <label for="boite">Boîte <span class="requis">*</span></label>
                <select name="select">
                	<% for(int i = 0 ; i < ConnectServlet.boxNames.length ; i++){ %>
  				    <option value="<%=ConnectServlet.boxNames[i]%>"><%=ConnectServlet.boxNames[i]%></option> 
  				    <%}%>
				</select>
                <br />
                <br />

                <label for="email">Expediteur <span class="requis">**</span></label>
                <input type="text" id="expediteur" name="expediteur" value="" size="20" maxlength="60" />
                <br />
				<br />
				
                <label for="motdepasse">Objet <span class="requis">**</span></label>
                <input type="text" id="objet" name="objet" value="" size="20" maxlength="60" />
                <br />
                <br />
                
                <label for="motdepasse">Body <span class="requis">**</span></label>
                <input type="text" id="body" name="body" value="" size="20" maxlength="60" />
                <br />
                <br />
                
				<label for="boite">Boîte de destination<span class="requis">*</span></label>
                <select name="select2">
                	<% for(int i = 0 ; i < ConnectServlet.boxNames.length ; i++){ %>
  				    <option value="<%=ConnectServlet.boxNames[i]%>"><%=ConnectServlet.boxNames[i]%></option> 
  				    <%}%>
				</select>
                <br />
                <br />

                <input type="submit" value="Ajouter" class="button" />
                <br />
         </fieldset>
    </form> 
    
    <span style="margin-left:5px;">${ error }</span> 
    
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
    
    <a href="http://localhost:8080/RulesMasterWeb/rules" style="font-size: 20px;">Retour</a>
    
    <p style="margin-left:10px;margin-top:6.1%;font-size: 13px">© 2015 RulesMasterWeb Tous droits reservés</p>
              
</body>
</html>