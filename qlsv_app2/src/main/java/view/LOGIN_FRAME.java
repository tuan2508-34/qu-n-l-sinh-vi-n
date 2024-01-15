package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import dao.tkgvDAO;
import dao.tksvDAO;
import model.tkgv;
import model.tksv;

import java.awt.GridBagConstraints;
import java.awt.Image;

public class LOGIN_FRAME {
	private JFrame mainFrame;
	//
	private JLabel headerLabel;
	private JLabel loginLabel;
	private JLabel loginLabel1;
	private JLabel userNameLabel;
	private JLabel passLabel;
	private JLabel selectLabel;
	private JLabel tb;
	private JLabel tb1;
	//
	private JPanel mainPanel;
	private JPanel headerPanel;
	private JPanel loginPanel;
	private JPanel controlPanel;
	private JPanel infoPanel;
	private JPanel userPanel;
	private JPanel passPanel;
	private JPanel selectPanel;
	private JPanel pan1;
	private JPanel Spanel;
	//
	private JButton cancelButton;
	private JButton loginButton;
	//
	String tk;
	String mk;

	public LOGIN_FRAME() {
		prepareGUI();
	}

	private void prepareGUI() {
		//

		ImageIcon icon = new ImageIcon("C:\\Users\\COMPUTER\\Downloads\\1200px-Logo_Đại_học_Bách_Khoa_Hà_Nội.svg.png",
				"Lock");
		int width = icon.getIconWidth() / 19;
		int height = icon.getIconHeight() / 19;
		Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaled);
		//
		mainFrame = new JFrame("Quản lí sinh viên");
		mainFrame.setSize(400, 600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//
		headerLabel = new JLabel("", JLabel.CENTER);
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		loginLabel = new JLabel(scaledIcon);
		loginLabel1 = new JLabel("Mời bạn đăng nhập tài khoản", JLabel.CENTER);
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		userNameLabel = new JLabel("     Tài khoản", JLabel.LEFT);
		userNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		passLabel = new JLabel("     Mật khẩu", JLabel.LEFT);
		passLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		selectLabel = new JLabel("     Vai trò", JLabel.LEFT);
		selectLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		//
		headerPanel = new JPanel();
		headerPanel.setLayout(new FlowLayout());

		headerPanel.add(headerLabel);
		//
		JPanel p1 = new JPanel();
		pan1 = new JPanel(new GridLayout(2, 1));
		pan1.add(loginLabel);
		pan1.add(loginLabel1);

		pan1.setPreferredSize(new Dimension(0, 200));

		infoPanel = new JPanel(new GridLayout(3, 3));
		selectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JTextField text1 = new JTextField(15);
		text1.setPreferredSize(new Dimension(0, 30));
		p2.add(text1);
		p2.add(p4);
		JTextField text2 = new JTextField(15);
		text2.setPreferredSize(new Dimension(0, 30));
		p3.add(text2);
		p3.add(p5);

		final DefaultComboBoxModel Role = new DefaultComboBoxModel();
		Role.addElement("Admin");
		Role.addElement("Giảng viên");
		Role.addElement("Sinh viên");
		final JComboBox fruitCombo = new JComboBox(Role);
		fruitCombo.setSelectedIndex(0);
		JScrollPane fruitListScrollPane = new JScrollPane(fruitCombo);
		selectPanel.add(fruitListScrollPane);

		Border border = BorderFactory.createLineBorder(Color.black);
		TitledBorder title = new TitledBorder(border, "✿✿✿✿✿✿✿✿");
		title.setTitleColor(Color.pink);

		infoPanel.setBorder(title);
		infoPanel.add(userNameLabel);
		infoPanel.add(p2);
		infoPanel.add(passLabel);
		infoPanel.add(p3);
		infoPanel.add(selectLabel);
		infoPanel.add(selectPanel);
		//
		JPanel G1 = new JPanel();
		G1.setPreferredSize(new Dimension(0, 30));
		loginPanel = new JPanel(new BorderLayout());
		loginPanel.add(pan1, BorderLayout.NORTH);
		loginPanel.add(infoPanel, BorderLayout.CENTER);
		loginPanel.add(G1, BorderLayout.SOUTH);
		//
		Spanel = new JPanel(new GridLayout(3, 1));
		Spanel.setPreferredSize(new Dimension(0, 160));
		controlPanel = new JPanel(new GridLayout(1, 5));

		JPanel butG1 = new JPanel();
		JPanel butG2 = new JPanel();
		JPanel butG3 = new JPanel();
		JPanel but1 = new JPanel(new BorderLayout());
		JPanel but2 = new JPanel(new BorderLayout());
		cancelButton = new JButton("Cancel");
		loginButton = new JButton("Login");
		loginButton.setBackground(Color.PINK);
		but1.add(cancelButton, BorderLayout.NORTH);
		but2.add(loginButton, BorderLayout.NORTH);
		tb = new JLabel("Sai mật khẩu !", JLabel.CENTER);
		tb.setForeground(Color.red);
		tb.setFont(new Font("Tahoma", Font.BOLD, 13));
		tb1 = new JLabel("Không tồn tại tài khoản này !", JLabel.CENTER);
		tb1.setForeground(Color.red);
		tb1.setFont(new Font("Tahoma", Font.BOLD, 13));
		JLabel tb2 = new JLabel("Đang đăng nhập", JLabel.CENTER);
		tb2.setForeground(Color.red);
		tb2.setFont(new Font("Tahoma", Font.BOLD, 13));
		controlPanel.add(butG1);
		controlPanel.add(but1);
		controlPanel.add(butG2);
		controlPanel.add(but2);
		controlPanel.add(butG3);
		Spanel.add(controlPanel);
		Spanel.add(Box.createHorizontalStrut(1));
		JPanel C_Panel = new JPanel();
		CardLayout C_layout = new CardLayout();
		C_Panel.setLayout(C_layout);
		C_Panel.add("true", Box.createVerticalStrut(1));
		C_Panel.add("false", tb);
		C_Panel.add("false1", tb1);
		C_Panel.add("dn", tb2);
		Spanel.add(C_Panel);
		//
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text1.setText("");
				text2.setText("");
			}
		});
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tk = text1.getText();
				mk = text2.getText();
				CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
				
				String role = fruitCombo.getSelectedItem().toString();
				if (role == "Sinh viên") {
					tksvDAO tk1 = new tksvDAO();
					tksv t = new tksv();
					t.setMasinhvien(new Long(tk));
					if (tk1.selectById(t) == null) {
						cardLayout1.show(C_Panel, "false1");
					} else {
						String mk1 = tk1.selectById(t).getMatkhau();
						if (mk.equals(mk1)) {
							SV_FRAME JJ = new SV_FRAME(t.getMasinhvien());
							mainFrame.setVisible(false);
						} else {
							cardLayout1.show(C_Panel, "false");
						}
					}

				}else if (role == "Giảng viên") {
					tkgvDAO tk1 = new tkgvDAO();
					tkgv t = new tkgv();
					t.setMagiangvien(new Long(tk));
					if (tk1.selectById(t) == null) {
						cardLayout1.show(C_Panel, "false1");
					} else {
						String mk1 = tk1.selectById(t).getMatkhau();
						if (mk.equals(mk1)) {
							GV_FRAME JJ = new GV_FRAME(t.getMagiangvien());
							mainFrame.setVisible(false);
						} else {
							cardLayout1.show(C_Panel, "false");
						}
					}

				}
				
				
				
				
				
				else if (role == "Admin") {
					String tkad = "admin";
					String mkad = "123";
					if (tk.equals(tkad) == true && mk.equals(mkad)) {
						AD_FRAME JJ = new AD_FRAME();
						mainFrame.setVisible(false);
					} else {
						cardLayout1.show(C_Panel, "false");
					}
				}

			}
		});
		//
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(loginPanel, BorderLayout.CENTER);
		mainPanel.add(Spanel, BorderLayout.SOUTH);
		//
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		headerLabel.setText("Chào mừng đăng nhập");
	}
}