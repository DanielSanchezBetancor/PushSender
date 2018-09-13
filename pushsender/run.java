package pushsender;

import java.awt.EventQueue;

public class run {
	public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PushSender window = new PushSender();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
/*
 *Notas de version 
 * 1.0.0 - Alfa
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
