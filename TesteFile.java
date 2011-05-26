package markup;

public class TesteFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String fileOutPath = System.getProperty("user.home") + System.getProperty("file.separator") + 
        "Desktop" + System.getProperty("file.separator") + "CALLS" + System.getProperty("file.separator") + "INCOMING.CFG" ;
		
		fileOutPath = fileOutPath.replace("\\","/");
			
		
		System.out.println(fileOutPath);
	}

}
