package pushsender;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class looper {
	private final String urlPost = //urlPost;
	public String[][] sendData = new String[4][10];
	private String[] text = new String[5];
	int counter = 0;
	private JFrame frame;
	private JLabel message;

	public looper(Settings settings) {
		text = settings.loadText(13, 17);
		String[][] data = new FileAlterer().getData();
		frame = new JFrame();
		frame.setBounds(0, 0, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Notif_push");
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JList<String> guardados = new JList<String>(data[0]);
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);
		panel.add(guardados, gbc);
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;

		DefaultListModel<String> dlm = new DefaultListModel<String>();
		JList<String> aenviar = new JList<String>(dlm);
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panel.add(aenviar, gbc);
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.NONE;

		JButton copiar = new JButton(">>");
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.anchor = GridBagConstraints.SOUTH;
		copiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = guardados.getSelectedValue();
				sendData[0][counter] = data[0][guardados.getSelectedIndex()];
				sendData[1][counter] = data[1][guardados.getSelectedIndex()];
				sendData[2][counter] = data[2][guardados.getSelectedIndex()];
				sendData[3][counter] = data[3][guardados.getSelectedIndex()];
				dlm.addElement(aux);
				counter++;
				frame.revalidate();
				frame.repaint();
			}
		});
		copiar.setFocusPainted(false);
		panel.add(copiar, gbc);
		gbc.insets = new Insets(0, 0, 0, 0);

		JButton borrar = new JButton("<<");
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.NORTH;
		borrar.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = aenviar.getSelectedIndex();
				String[][] aux = new String[4][9];
				int puntAux = 0;
				for (int i = 0; i < sendData[0].length - 1; i++) {
					if (i == pos) {
						puntAux++;
					}
					aux[0][i] = sendData[0][puntAux];
					aux[1][i] = sendData[1][puntAux];
					aux[2][i] = sendData[2][puntAux];
					aux[3][i] = sendData[3][puntAux];
					puntAux++;
				}
				sendData = aux;
				dlm.removeElementAt(pos);
				counter--;
				frame.revalidate();
				frame.repaint();
			}
		}));
		borrar.setFocusPainted(false);
		panel.add(borrar, gbc);
		gbc.anchor = GridBagConstraints.CENTER;

		JButton atras = new JButton(text[0]);
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		atras.setFocusPainted(false);
		panel.add(atras, gbc);

		JButton enviar = new JButton(text[1]);
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dlm.isEmpty()) {
					sendPost();
				} else
					try {
						buildGUI(false);
						addText(100, 0);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
		});
		enviar.setFocusPainted(false);
		panel.add(enviar, gbc);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	public void sendPost() {
		try {
			String[] dates = calculateDates(); 
			buildGUI(true);
			for (int i = 0; i < dates.length; i++) {
				if (sendData[0][i] != null) {
					URL url = new URL(urlPost);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("POST");
					con.setRequestProperty("Content-Type", "application/json");
					con.setRequestProperty("charset", "UTF-8");
					con.setRequestProperty("Authorization", "authorization");
					String param = "{ " + "\"app_id\": \"appId","
							+ "\"included_segments\":[\"Users " + new FileAlterer().getRegionFile() + "\"],"
							+ "\"contents\": { \"en\": \"" + sendData[1][i] + "\"}, "
							+ "\"headings\": { \"en\": \"" + sendData[0][i] + "\"}, "
							+ "\"send_after\": \"" + dates[i] + "\", " 
							+ "\"chrome_web_icon\": \"" + sendData[3][i] + "\", "
							+ "\"url\": \"" + sendData[2][i] + "\","
							+ "\"delayed_option\": \"last-active\"" 
							+ "}";
					con.setDoOutput(true);
					byte[] sendBytes = param.getBytes("UTF-8");
					con.setFixedLengthStreamingMode(sendBytes.length);
					OutputStream outputStream = con.getOutputStream();
					outputStream.write(sendBytes);
					int httpResponse = con.getResponseCode();
					addText(httpResponse, i);
				}
			}
			message.setText(message.getText() + "</html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String[] calculateDates() {
		String[] dates = new String[10];
		Calendar date = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss zZ");
		date.add(Calendar.HOUR, 6);
		for (int i = 0; i < sendData[0].length; i++) {
			if (i != 0)
				date.add(Calendar.DAY_OF_WEEK, 1);
			dates[i] = format.format(date.getTime());
		}
		return dates;
	}

	private void buildGUI(boolean isCorrect) {
		JFrame frameResp = new JFrame();
		if (counter != 0)
			frameResp.setBounds(0, 0, 350, 75 * counter);
		else 
			frameResp.setBounds(0, 0, 350, 250);
		frameResp.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		message = new JLabel("<html>");
		JButton confirm = new JButton("Ok");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isCorrect) {
					frameResp.dispose();
					frame.dispose();
				} else
					frameResp.dispose();
			}
		});
		confirm.setFocusPainted(false);
		layout.putConstraint(SpringLayout.NORTH, message, 10, SpringLayout.NORTH, frameResp.getContentPane());
		layout.putConstraint(SpringLayout.WEST, message, 20, SpringLayout.WEST, frameResp.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, confirm, frameResp.getHeight() - 50, SpringLayout.SOUTH, frameResp.getContentPane());
		layout.putConstraint(SpringLayout.WEST, confirm, (40 * frameResp.getWidth()) / 100, SpringLayout.WEST, frameResp.getContentPane());
		panel.add(message);
		panel.add(confirm);
		frameResp.getContentPane().add(panel);
		frameResp.setVisible(true);
	}

	private void addText(int response, int i) {
		i++;
		String aux = "";
		if (response == 200) {
			aux = message.getText() + "<br>";
			message.setText(aux + i + ": " + text[2]);
		} else if (response == 100)
			message.setText(text[4]);
		else {
			aux = message.getText() + "<br>";
			message.setText(i + ": " + text[3]);
		} 
	}
}
