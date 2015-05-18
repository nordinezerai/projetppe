package MessengerConect;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import RulesManager.Bouton;
import RulesManager.IHM;

public class ConnectPanel extends JPanel 
{
	private static final long serialVersionUID = 7205776525568788694L;

	JLabel label_Id = new JLabel("Adresse mail :");
	JLabel label_Psw = new JLabel("Mot de passe :");

	static JTextField tfId = new JTextField();
	static JTextField tfPsw = new JTextField();

	Bouton quitButton = new Bouton("Quitter", this);
	Bouton conectButton = new Bouton("Connection", this);

	JRadioButton rbGoogle = new JRadioButton("Google");
	JRadioButton rbOutLook = new JRadioButton("OutLook");
	JRadioButton rbYahoo = new JRadioButton("Yahoo");

	ButtonGroup group = new ButtonGroup();
	
	static Session ses = null;
	
    static Store store = null;
    
    static Properties properties = new Properties();
    
    static String fichier = "propri.properties";
    
    static Folder boxtest = null;
    
    public static Folder[] boxs ;
    
    public static String[] listBox ;
    public static String[] listBox2 ;
    
    private static String host;
    public static String id;
    public static String psw;
    
    private IHM frame;

	public ConnectPanel(IHM frame) 
	{		
		this.frame = frame;
		
		label_Id.setBounds(90, 100, 150, 30);
		label_Psw.setBounds(90, 180, 150, 30);

		quitButton.setBounds(130, 300, 100, 20);
		conectButton.setBounds(330, 300, 100, 20);

		rbGoogle.setBounds(420, 100, 230, 30);
		rbOutLook.setBounds(420, 150, 230, 30);
		rbYahoo.setBounds(420, 200, 230, 30);

		tfId.setBounds(180, 100, 150, 30);
		tfPsw.setBounds(180, 180, 150, 30);

		group.add(rbGoogle);
		group.add(rbOutLook);
		group.add(rbYahoo);

		this.setLayout(null);
		this.add(quitButton);
		this.add(conectButton);
		this.add(rbGoogle);
		this.add(rbOutLook);
		this.add(rbYahoo);
		this.add(tfId);
		this.add(tfPsw);
		this.add(label_Id);
		this.add(label_Psw);
	}
	
	public void connect() throws Exception  
	{
		
		InputStreamReader isr = new InputStreamReader(new FileInputStream(fichier), "UTF-8");
		
		properties.load(isr);
		
		isr.close();
		
		Properties props = new Properties();	
		
		id = tfId.getText();
		psw = tfPsw.getText();
		//id="nordine.zerai@gmail.com";
		//psw="ninoninonino";
		host = null;
		
		if(id.contains("@gmail.com"))
		{ 
			host = properties.getProperty("gmail");
		}
		
		if(id.contains("@outlook.com"))
		{
			host = properties.getProperty("outlook");
		}
		
		if(id.contains("@yahoo.fr"));
		{
			host = properties.getProperty("yahoo");
		}
		props.put("mail.imaps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.imaps.socketFactory.fallback", "false");
		props.put("mail.imaps.socketFactory.port", "993");
		props.put("mail.imaps.host","imaps.gmail.com");
		props.put("mail.store.protocol","imaps");
		
		ses = Session.getInstance(props, null);	
		
		ses.setDebug(true);
		
		store = ses.getStore(properties.getProperty("typesession"));
     	store.connect("imap.gmail.com",id,psw);

     	boxtest = store.getFolder("[Gmail]");

     	boxs = boxtest.list();
     	listBox = new String[boxs.length+1];
     	listBox[0] = "Sélectionnez une boîte";
     	
     	listBox2 = new String[boxs.length+1];
     	listBox2[0] = "Sélectionnez une boîte";
     	
     	for(int i = 1 ;  i <= boxs.length ; i++ )
		{				
			listBox[i] = boxs[i-1].getName() + " (" + boxs[i-1].getMessageCount() + ") " ;
			listBox2[i] = boxs[i-1].getName();
		}  
     	
        if(listBox[1].contains("INBOX")){listBox[1]="Boîte de réception"+ " (" + boxs[0].getMessageCount() + ") " ;}
        if(listBox2[1].contains("INBOX")){listBox2[1]="Boîte de réception";}	
     	
        boxs[0].open(Folder.READ_WRITE);
        
     	Message[] mails = boxs[0].getMessages();

     	frame.accessMailBox();
	}

	public static String[] getListBox() 
	{
		return listBox;
	}

	public static void setListBox(String[] listBox) 
	{
		ConnectPanel.listBox = listBox;
	}
}
