package controle;

import com.moyosoft.connector.com.ComponentObjectModelException;
import com.moyosoft.connector.exception.LibraryNotFoundException;
import com.moyosoft.connector.ms.outlook.Outlook;

public class ThreadOutlookSession {
	
	public Outlook outlookApplication ;
	
	public ThreadOutlookSession() {

	      try {
			this.outlookApplication = new Outlook();
		  } catch (ComponentObjectModelException | LibraryNotFoundException e) {
			e.printStackTrace();
		  }

	}
}
