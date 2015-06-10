<%@ page pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
     <meta charset="ISO-8859-1" />
     <title>Connexion</title>
     <link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body>
    <img src="logogsb.jpg" style="width:304px;height:228px;display:inline;margin-left: 10px;margin-top: 7px;"/>
    
    <span style="font-size: 30px;margin-left:28%">RulesMasterWeb</span>
    
    <br /><br /><br /><br /><br /><br /><br /><br /><br />
    
    <h1 class="titleAjouter">Connexion à votre boîte mail Outlook </h1>
        <form method="get" action="connect">
            <fieldset>
                <legend>Connexion</legend>
                <p class="note">Entrez vos identifiants de connexion :</p>
                <br />

                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="text" id="email" name="email" value=""/>
                <br /><br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" />
                <br /><br />

                <input type="submit" value="Connexion" class="button" />
                <br />
            </fieldset>
        </form>
        
     <p class="info" style="margin-left:5px;" >${ message }</p>
        
     <p style="margin-left:16px;margin-top:19.5%;font-size: 13px">© 2015 RulesMasterWeb Tous droits reservés</p>       
        
</body>
</html>