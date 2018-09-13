package pushsender;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class FormNotif {
	private String[] content = new String[4], text = new String[9];
	private Settings settings = new Settings();
	private JFrame frame;

	public FormNotif(Settings settings) {
		this.settings = settings;
		text = this.settings.loadText(4, 12);
		
		frame = new JFrame();
		frame.setBounds(0, 0, 600, 300);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Form_notif");
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel jb = new JLabel(text[0]);
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(0, 25, 0, 0);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(jb, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		JTextArea tfx = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(tfx);
		gbc.gridx = 2;
		gbc.gridwidth = 2;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.insets = new Insets(5, 0, 8, 5);
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(scrollPane, gbc);
		JLabel jb1 = new JLabel(text[1]);
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 25, 0, 0);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(jb1, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		JTextArea tfx1 = new JTextArea();
		JScrollPane scrollPane1 = new JScrollPane(tfx1);
		gbc.gridx = 2;
		gbc.gridwidth = 2;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(5, 0, 8, 5);
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(scrollPane1, gbc);
		JLabel jb2 = new JLabel(text[2]);
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 25, 0, 0);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(jb2, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		JTextArea tfx2 = new JTextArea();
		JScrollPane scrollPane2 = new JScrollPane(tfx2);
		gbc.gridx = 2;
		gbc.gridwidth = 2;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.insets = new Insets(5, 0, 8, 5);
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(scrollPane2, gbc);
		JLabel jb3 = new JLabel(text[3]);
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 25, 0, 0);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(jb3, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		JTextArea tfx3 = new JTextArea();

		JScrollPane scrollPane3 = new JScrollPane(tfx3);
		gbc.gridx = 2;
		gbc.gridwidth = 2;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.insets = new Insets(5, 0, 8, 5);
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(scrollPane3, gbc);
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		JButton jbt1 = new JButton(text[4]);
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 25, 0, 0);
		jbt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		jbt1.setFocusPainted(false);
		panel.add(jbt1, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 0, 0, 0);
		JButton jbt2 = new JButton(text[5]);
		gbc.gridx = 3;
		gbc.gridwidth = 1;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		jbt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content[0] = tfx.getText();
				content[1] = tfx1.getText();
				content[2] = tfx2.getText();
				content[3] = tfx3.getText();
				if (content[0].equals("") || content[1].equals("") || content[2].equals("") || content[3].equals(""))
					buildErrorGUI();
				else {
					boolean isCorrect = new FileAlterer().saveData(content);
					buildGUI(isCorrect);
				}
			}
		});
		jbt2.setFocusPainted(false);
		panel.add(jbt2, gbc);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	public String[] getContent() {
		return this.content;
	}
	public void buildGUI(boolean isCorrect) {
		JFrame status = new JFrame();
		status.setBounds(0, 0, 250, 150);
		status.setLocationRelativeTo(null);
		JPanel statusPanel = new JPanel();
		SpringLayout layout = new SpringLayout();
		statusPanel.setLayout(layout);
		JLabel message = new JLabel();
		JButton confirm = new JButton("Ok");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isCorrect) {
					status.dispose();
					frame.dispose();
				}
			}
		});
		if (isCorrect) {
			message.setText(text[7]);
		} else {
			message.setText(text[9]);
		}
		layout.putConstraint(SpringLayout.NORTH, message, 10, SpringLayout.NORTH, status.getContentPane());
		layout.putConstraint(SpringLayout.WEST, message, 20, SpringLayout.WEST, status.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, confirm, status.getHeight()-50, SpringLayout.SOUTH, status.getContentPane());
		layout.putConstraint(SpringLayout.WEST, confirm, (40*status.getWidth())/100, SpringLayout.WEST, status.getContentPane());
		statusPanel.add(message);
		statusPanel.add(confirm);
		status.getContentPane().add(statusPanel);
		status.setVisible(true);
	}
	public void buildErrorGUI() {
		JFrame error = new JFrame();
		error.setBounds(0, 0, 300, 150);
		error.setLocationRelativeTo(null);
		error.setResizable(false);
		JPanel errorPanel = new JPanel();
		SpringLayout layout = new SpringLayout();
		errorPanel.setLayout(layout);
		JLabel message = new JLabel(text[6]);
		JButton confirm = new JButton("Ok");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					error.dispose();
			}
		});
		confirm.setFocusPainted(false);
		layout.putConstraint(SpringLayout.NORTH, message, 10, SpringLayout.NORTH, error.getContentPane());
		layout.putConstraint(SpringLayout.WEST, message, 20, SpringLayout.WEST, error.getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, confirm, error.getHeight()-50, SpringLayout.SOUTH, error.getContentPane());
		layout.putConstraint(SpringLayout.WEST, confirm, (40*error.getWidth())/100, SpringLayout.WEST, error.getContentPane());
		errorPanel.add(message);
		errorPanel.add(confirm);
		error.getContentPane().add(errorPanel);
		error.setVisible(true);
	}
}
