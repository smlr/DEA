package vea.uebung05.b;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BackupManager bm = new BackupManager();
		//bm.backupOrder("/home/sven/");
		bm.restore("/home/sven/order.sql");
		System.out.println("BackupManager Ende");

	}

}
