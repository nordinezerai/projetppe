package modele;

public class Mail {
	
	private String from;
	private String objet;
	private String body;
	private String date;
	
	public Mail( String from, String objet, String body, String date)
	{
		this.from = from ;
		this.objet = objet ;
		this.body = body ;
		this.date = date ;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
