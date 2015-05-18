package RulesManager;

public class Rule {
	
	private String expediteur;
	private String subject;
	private String body;
	private String box;
	private String match;
	private String Modifier;
	
	public Rule()
	{
		
	}
	
	public Rule(String expediteur,String subject,String body,String box,String match,String mod)
	{
		this.expediteur = expediteur;
		this.subject = subject;
		this.body = body;
		this.box = box;
		this.match = match;
		this.Modifier = mod;
	}

	public String getModifier() {
		return Modifier;
	}

	public void setModifier(String modifier) {
		Modifier = modifier;
	}

	public String getExpediteur() {
		return expediteur;
	}


	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getBox() {
		return box;
	}


	public void setBox(String box) {
		this.box = box;
	}


	public String getMatch() {
		return match;
	}


	public void setMatch(String match) {
		this.match = match;
	}
	
	public boolean compareRule(Rule rule)
	{
		if(this.getExpediteur().equals(rule.getExpediteur())&& this.getSubject().equals(rule.getSubject()) && this.getBody().equals(rule.getBody()) && this.getBox().equals(rule.getBox()))
		{
			return true;
		}
		
		else
		{
			return false;
		}
			
	}


	
}
