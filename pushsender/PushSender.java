package pushsender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PushSender {
	JFrame frame;
	JPanel panelRegion, panelGeneral;
	FileAlterer fa = new FileAlterer();
	Settings settings = new Settings();
	int alto = 180;
	private static final String[] regions = { "ES", "FR", "DE", "DK", "IT", "NL", "RU", "SE", "TR", "US" };
	private static String[] texts = new String[4];

	public PushSender() {
		checker();
	}

	private void buildGUI(int ancho) {
		if (frame == null) {
			frame = new JFrame();
			frame.setBounds(0, 0, ancho, alto);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
		} else {
			frame.setBounds(0, 0, ancho, alto);
			frame.setLocationRelativeTo(null);
		}

	}

	private void checker() {
		boolean exists = fa.checkFile();
		if (!exists) {
			buildGUI(300);
			setPanelRegion(300);
			frame.setBackground(Color.BLACK);
			frame.setTitle("Set region");
			frame.getContentPane().add(panelRegion);
		} else {
			settings.setRegion(fa.getRegionFile());
			texts = settings.loadText(0, 4);
			buildGUI(600);
			frame.setBackground(Color.BLACK);
			frame.setTitle("Main_menu");
			setPanelGeneral(600);
			frame.getContentPane().add(panelGeneral);
			frame.revalidate();
			frame.repaint();
		}
	}

	private void setPanelGeneral(int ancho) {
		// Panel general settings
		panelGeneral = new JPanel();
		panelGeneral.setBackground(Color.WHITE);
		panelGeneral.setLayout(null);
		JLabel text = new JLabel(texts[3], JLabel.CENTER);
		text.setBounds((5 * ancho) / 100, (10 * alto) / 100, (80 * ancho) / 100, (10 * alto) / 100);
		text.setFont(new Font("Arial", Font.BOLD, 14));
		JButton[] buttons = { new JButton(texts[0]), new JButton(texts[1]), new JButton(texts[2]) };
		buttons[0].setBounds((5 * ancho) / 100, (45 * alto) / 100, (25 * ancho) / 100, (25 * alto) / 100);
		buttons[0].setMargin(new Insets(0, 0, 0, 0));
		buttons[0].setFocusPainted(false);
		buttons[1].setBounds((35 * ancho) / 100, (45 * alto) / 100, (25 * ancho) / 100, (25 * alto) / 100);
		buttons[1].setMargin(new Insets(0, 0, 0, 0));
		buttons[1].setFocusPainted(false);
		buttons[2].setBounds((65 * ancho) / 100, (45 * alto) / 100, (25 * ancho) / 100, (25 * alto) / 100);
		buttons[2].setMargin(new Insets(0, 0, 0, 0));
		buttons[2].setFocusPainted(false);
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent av) {
				new FormNotif(settings);
			}
		});
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent av) {
				try {
					// new looper().sendPost(new SetNotif(settings).getContent());
					new looper(settings);
				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					frame.remove(panelGeneral);
					frame.revalidate();
				}
			}
		});
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent av) {
				System.exit(0);
			}
		});
		panelGeneral.add(buttons[0]);
		panelGeneral.add(buttons[1]);
		panelGeneral.add(buttons[2]);
		panelGeneral.add(text);
	}

	private void setPanelRegion(int ancho) {
		panelRegion = new JPanel();
		panelRegion.setBackground(Color.WHITE);
		panelRegion.setBounds(100, 100, ancho, alto);
		panelRegion.setLayout(null);
		JLabel text = new JLabel("Choose your region", JLabel.CENTER);
		text.setBounds((10 * ancho) / 100, (10 * alto) / 100, (80 * ancho) / 100, (10 * alto) / 100);
		text.setFont(new Font("Arial", Font.BOLD, 14));
		JComboBox<String> combobox = new JComboBox<String>(regions);
		combobox.setBounds((5 * ancho) / 100, (55 * alto) / 100, (60 * ancho) / 100, (10 * alto) / 100);
		JButton button = new JButton("Confirm");
		button.setBounds((70 * ancho) / 100, (55 * alto) / 100, (20 * ancho) / 100, (10 * alto) / 100);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setFocusPainted(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch((String) combobox.getSelectedItem()) {
					case "ES":
					fa.saveFiles("ES");
					break;
					case "FR":
					fa.saveFiles("FR");
					break;
					case "DE":
					fa.saveFiles("DE");
					break;
					case "DK":
					fa.saveFiles("DK");
					break;
					case "IT":
					fa.saveFiles("IT");
					break;
					case "NL":
					fa.saveFiles("NL");
					break;
					case "RU":
					fa.saveFiles("RU");
					break;
					case "SE":
					fa.saveFiles("SE");
					break;
					case "TR":
					fa.saveFiles("TR");
					break;
					case "US":
					fa.saveFiles("US");
					break;
				}
				settings.setRegion(fa.getRegionFile());
				texts = settings.loadText(0, 4);
				frame.remove(panelRegion);
				checker();
			}
		});
		panelRegion.add(text);
		panelRegion.add(combobox);
		panelRegion.add(button);

	}
}