package modele;

public class Rule {
	
	private String expediteur;
	private String objet;
	private String body;
	private String boite_destination;
	private String boite;
	
	public Rule(String expediteur, String objet, String body, String boite_destination, String boite) {
		this.expediteur = expediteur;
		this.objet = objet;
		this.body = body;
		this.boite_destination = boite_destination ;
		this.boite = boite;	
	}

	public String getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBoite_destination() {
		return boite_destination;
	}

	public void setBoite_destination(String boite_destination) {
		this.boite_destination = boite_destination;
	}

	public String getBoite() {
		return boite;
	}

	public void setBoite(String boite) {
		this.boite = boite;
	}

}
