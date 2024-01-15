package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import dao.sinhvienDAO;
import model.sinhvien;
import util.mhsv;
import util.tdmk;
import util.tkbgv;
import util.tkbsv;

class Panel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon img = new ImageIcon("C:\\Users\\COMPUTER\\Downloads\\a.jpg");
		g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
}

class Panel2 extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon img = new ImageIcon("C:\\Users\\COMPUTER\\Downloads\\dh-bach-khoa-hn-15879138925811115307570.jpg");
		g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
}

class Panel3 extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon img = new ImageIcon("C:\\Users\\COMPUTER\\Downloads\\AX51-vector-corel-trong-dong.jpg");
		g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
}

class But1 extends JButton {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon img = new ImageIcon("C:\\Users\\COMPUTER\\Downloads\\back.png");
		g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
}

class But2 extends JButton {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon img = new ImageIcon("C:\\Users\\COMPUTER\\Downloads\\next.png");
		g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
}

public class SV_FRAME {
	int index = 0;
	private Long idsv;
	//
	private JFrame mainFrame;
	//
	private JLabel headerLabel;
	private JLabel iconLabel;
	private JLabel userLabel;
	private JLabel timeLabel;
	private JLabel timeNowLabel;
	private JLabel loginLabel;
	private JLabel menuLabel;
	//
	private JPanel mainPanel;
	private JPanel N_Panel;
	private JPanel W_N_Panel;
	private JPanel info_Panel;
	private JPanel user_Panel;
	private JPanel menuPanel;
	private JPanel item_menuPanel;
	private JPanel C_N_Panel;
	private JPanel W_Panel;
	private JPanel C_Panel;
	private JPanel SV_Panel;
	private JPanel L_Panel;
	private JPanel TK_Panel;
	private JPanel MH_Panel;
	private JPanel D_Panel;

	//
	private JButton it1_Button;
	private JButton it2_Button;
	private JButton it3_Button;
	private JButton it4_Button;
	private JButton it5_Button;
	private JButton it6_Button;
	private But1 preButton;
	private But2 nextButton;

	//
	public SV_FRAME(Long idsv) {
		this.idsv = idsv;
		prepareGUI();
		Times();
	}

	javax.swing.Timer t;
	SimpleDateFormat st;

	public void Times() {

		t = new javax.swing.Timer(0, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date dt = new Date();
				st = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
				String dd = st.format(dt);
				timeNowLabel.setText(dd);
				timeNowLabel.setForeground(Color.red);
			}
		});
		t.start();
	}

	private void prepareGUI() {
		sinhvienDAO svd1 = new sinhvienDAO();
		sinhvien svm = new sinhvien();
		svm.setMasinhvien(this.idsv);
		sinhvien sv = svd1.selectById(svm);
		String ntk = sv.getHovadem() + " " + sv.getTen();
		//
		timeNowLabel = new JLabel();

		Panel BG = new Panel();
		BG.setLayout(new BorderLayout());

		ImageIcon icon = new ImageIcon("C:\\Users\\COMPUTER\\Downloads\\1200px-Logo_Đại_học_Bách_Khoa_Hà_Nội.svg.png",
				"Lock");
		int width = icon.getIconWidth() / 15;
		int height = icon.getIconHeight() / 15;
		Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaled);
		//
		iconLabel = new JLabel(scaledIcon);
		iconLabel.setPreferredSize(new Dimension(height, 0));
		headerLabel = new JLabel("ĐẠI HỌC BÁCH KHOA HÀ NỘI", JLabel.CENTER);
		userLabel = new JLabel("Tài khoản:", JLabel.CENTER);
		timeLabel = new JLabel("Thời gian:", JLabel.CENTER);
		menuLabel = new JLabel("MENU", JLabel.CENTER);
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 45));
		userLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		timeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		menuLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		// headerLabel.setForeground(Color.white);;
		it1_Button = new JButton("Sinh Viên");
		it1_Button.setBackground(Color.GRAY);
		it1_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		it2_Button = new JButton("Thời Khóa Biểu");
		it2_Button.setBackground(Color.GRAY);
		it2_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		it3_Button = new JButton("Môn Học");
		it3_Button.setBackground(Color.GRAY);
		it3_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		it5_Button = new JButton("Tài Khoản");
		it5_Button.setBackground(Color.GRAY);
		it5_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		it6_Button = new JButton("Đăng xuất");
		it6_Button.setBackground(Color.GRAY);
		it6_Button.setFont(new Font("Tahoma", Font.BOLD, 14));

		preButton = new But1();
		nextButton = new But2();
		nextButton.setBackground(Color.LIGHT_GRAY);
		preButton.setBackground(Color.LIGHT_GRAY);
		//
		JFrame frame = new JFrame("Quản lí sinh viên");
		BorderLayout layout = new BorderLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		BorderLayout layout1 = new BorderLayout();
		layout1.setHgap(10);
		layout1.setVgap(10);
		mainPanel = new JPanel();
		mainPanel.setLayout(layout);

		// N
		N_Panel = new JPanel();
		N_Panel.setLayout(layout1);
		N_Panel.setPreferredSize(new Dimension(0, 160));

		// W_N

		JLabel nameTk = new JLabel(ntk);
		nameTk.setFont(new Font("Tahoma", Font.BOLD, 16));
		nameTk.setForeground(Color.blue);
		info_Panel = new JPanel(new GridLayout(2, 2));
		info_Panel.add(userLabel);
		info_Panel.add(nameTk);
		info_Panel.add(timeLabel);
		info_Panel.add(timeNowLabel);
		W_N_Panel = new JPanel(new BorderLayout());
		W_N_Panel.setPreferredSize(new Dimension(300, 0));
		W_N_Panel.setBackground(Color.orange);
		W_N_Panel.add(info_Panel);

		BG.add(iconLabel, BorderLayout.WEST);
		BG.add(headerLabel, BorderLayout.CENTER);
		N_Panel.add(W_N_Panel, BorderLayout.WEST);
		N_Panel.add(BG, BorderLayout.CENTER);
		// W
		menuPanel = new JPanel();
		menuPanel.add(menuLabel);
		menuPanel.setBackground(Color.pink);
		Border border = BorderFactory.createLineBorder(Color.pink);
		TitledBorder title = new TitledBorder(border, "✿✿✿✿✿✿✿✿");
		title.setTitleColor(Color.pink);
		//
		JPanel it1_P = new JPanel(new BorderLayout());
		it1_P.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		it1_P.add(it1_Button, BorderLayout.CENTER);
		it1_P.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
		JPanel it2_P = new JPanel(new BorderLayout());
		it2_P.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		it2_P.add(it2_Button, BorderLayout.CENTER);
		it2_P.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
		JPanel it3_P = new JPanel(new BorderLayout());
		it3_P.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		it3_P.add(it3_Button, BorderLayout.CENTER);
		it3_P.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
		JPanel it5_P = new JPanel(new BorderLayout());
		it5_P.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		it5_P.add(it5_Button, BorderLayout.CENTER);
		it5_P.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
		JPanel it6_P = new JPanel(new BorderLayout());
		it6_P.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		it6_P.add(it6_Button, BorderLayout.CENTER);
		it6_P.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
		JPanel con_Button = new JPanel(new GridLayout(1, 5));
		con_Button.add(Box.createHorizontalStrut(10));
		con_Button.add(preButton);
		con_Button.add(Box.createHorizontalStrut(10));
		con_Button.add(nextButton);
		con_Button.add(Box.createHorizontalStrut(10));
		item_menuPanel = new JPanel(new GridLayout(16, 3));
		item_menuPanel.add(Box.createVerticalStrut(5));
		item_menuPanel.add(it1_P);
		item_menuPanel.add(Box.createVerticalStrut(5));
		item_menuPanel.add(it2_P);
		item_menuPanel.add(Box.createVerticalStrut(5));
		item_menuPanel.add(it3_P);
		item_menuPanel.add(Box.createVerticalStrut(5));
		item_menuPanel.add(it5_P);
		item_menuPanel.add(Box.createVerticalStrut(5));
		item_menuPanel.add(it6_P);
		item_menuPanel.add(Box.createVerticalStrut(5));
		item_menuPanel.add(con_Button);

		W_Panel = new JPanel(new BorderLayout());
		W_Panel.setBorder(title);
		W_Panel.setPreferredSize(new Dimension(300, 0));
		menuLabel.setBackground(Color.pink);
		W_Panel.add(menuPanel, BorderLayout.NORTH);
		W_Panel.add(item_menuPanel, BorderLayout.CENTER);

		// PANEL CHỨC NĂNG
		// SV_Panel
		Panel3 SV_Panel = new Panel3();
		GridLayout gr1 = new GridLayout(10, 3);
		SV_Panel.setLayout(gr1);
		;
		JLabel ten = new JLabel("     Họ và tên:");
		ten.setFont(new Font("Tahoma", Font.BOLD, 17));
		JLabel vlten = new JLabel(sv.getHovadem() + " " + sv.getTen());
		vlten.setFont(new Font("Tahoma", Font.BOLD, 17));
		vlten.setForeground(Color.BLUE);
		JLabel msv = new JLabel("     Mã sinh viên:");
		msv.setFont(new Font("Tahoma", Font.BOLD, 17));
		JLabel vlmsv = new JLabel(sv.getMasinhvien().toString());
		vlmsv.setFont(new Font("Tahoma", Font.BOLD, 17));
		vlmsv.setForeground(Color.BLUE);
		JLabel date = new JLabel("     Ngày tháng năm sinh:");
		date.setFont(new Font("Tahoma", Font.BOLD, 17));
		JLabel vldate = new JLabel(sv.getDate().toString());
		vldate.setFont(new Font("Tahoma", Font.BOLD, 17));
		vldate.setForeground(Color.BLUE);
		JLabel sex = new JLabel("     Giới tính:");
		sex.setFont(new Font("Tahoma", Font.BOLD, 17));
		JLabel vlsex=null;
		if (sv.getGioitinh() == 1) {
			vlsex = new JLabel("Nam");
			vlsex.setFont(new Font("Tahoma", Font.BOLD, 17));
			vlsex.setForeground(Color.BLUE);
		} else if (sv.getGioitinh() == 0) {
			vlsex = new JLabel("Nữ");
			vlsex.setFont(new Font("Tahoma", Font.BOLD, 17));
			vlsex.setForeground(Color.BLUE);
		}
		JLabel dt = new JLabel("     Dân tộc:");
		dt.setFont(new Font("Tahoma", Font.BOLD, 17));
		JLabel vldt = new JLabel(sv.getDantoc());
		vldt.setFont(new Font("Tahoma", Font.BOLD, 17));
		vldt.setForeground(Color.BLUE);

		JLabel nganh = new JLabel("     Ngành học:");
		nganh.setFont(new Font("Tahoma", Font.BOLD, 17));
		JLabel vlnganh = new JLabel("Điện tử viễn thông");
		vlnganh.setFont(new Font("Tahoma", Font.BOLD, 17));
		vlnganh.setForeground(Color.BLUE);

		JLabel dc = new JLabel("     Địa chỉ:");
		dc.setFont(new Font("Tahoma", Font.BOLD, 17));
		JLabel vldc = new JLabel(sv.getQuequan());
		vldc.setFont(new Font("Tahoma", Font.BOLD, 17));
		vldc.setForeground(Color.BLUE);

		JLabel tth = new JLabel("     Trạng thái học:");
		tth.setFont(new Font("Tahoma", Font.BOLD, 17));
		JLabel vltth=null;
		if (sv.getTrangthaihoc() == 1) {
			vltth = new JLabel("Đang học");
			vltth.setFont(new Font("Tahoma", Font.BOLD, 17));
			vltth.setForeground(Color.BLUE);
		} else if (sv.getTrangthaihoc() == 0) {
			vltth = new JLabel("Đã thôi học");
			vltth.setFont(new Font("Tahoma", Font.BOLD, 17));
			vltth.setForeground(Color.BLUE);
		}

		SV_Panel.add(ten);
		SV_Panel.add(vlten);
		SV_Panel.add(Box.createHorizontalStrut(1));
		SV_Panel.add(msv);
		SV_Panel.add(vlmsv);
		SV_Panel.add(Box.createHorizontalStrut(1));
		SV_Panel.add(date);
		SV_Panel.add(vldate);
		SV_Panel.add(Box.createHorizontalStrut(1));
		SV_Panel.add(sex);
		SV_Panel.add(vlsex);
		SV_Panel.add(Box.createHorizontalStrut(1));
		SV_Panel.add(dt);
		SV_Panel.add(vldt);
		SV_Panel.add(Box.createHorizontalStrut(1));
		SV_Panel.add(nganh);
		SV_Panel.add(vlnganh);
		SV_Panel.add(Box.createHorizontalStrut(1));
		SV_Panel.add(dc);
		SV_Panel.add(vldc);
		SV_Panel.add(Box.createHorizontalStrut(1));
		SV_Panel.add(tth);
		SV_Panel.add(vltth);
		SV_Panel.add(Box.createHorizontalStrut(1));

		// L_Panel
		JPanel L_Panel = new JPanel(new BorderLayout());
		tkbsv adgv = new tkbsv(idsv);
		JPanel tkb_panel = adgv.taora();
		L_Panel.add(tkb_panel, BorderLayout.CENTER);
		
		// MH_Panel
		JPanel MH_Panel = new JPanel(new BorderLayout());
		mhsv mhsv = new mhsv();
		JPanel mh_panel = mhsv.taora();
		MH_Panel.add(mh_panel, BorderLayout.CENTER);
		// TK_Panel
		JPanel TK_Panel = new JPanel(new BorderLayout());
		JLabel title_tk = new JLabel("THAY ĐỔI MẬT KHẨU", JLabel.CENTER);
		title_tk.setPreferredSize(new Dimension(100, 100));
		JPanel con_mk_Panel1 = new JPanel(new GridLayout(14, 1));
		JPanel mk_Panel1 = new JPanel(new GridLayout(1, 4));
		JPanel mk_Panel2 = new JPanel(new GridLayout(1, 4));
		JPanel mk_Panel3 = new JPanel(new GridLayout(1, 4));
		JPanel mk_Panel4 = new JPanel(new GridLayout(1, 6));
		JLabel title_mkc = new JLabel("Nhập mật khẩu cũ:");
		title_mkc.setFont(new Font("Tahoma", Font.BOLD, 18));
		JLabel title_mkm = new JLabel("Nhập mật khẩu mới:");
		title_mkm.setFont(new Font("Tahoma", Font.BOLD, 18));
		JLabel title_xnmk = new JLabel("Xác nhận mật khẩu:");
		title_xnmk.setFont(new Font("Tahoma", Font.BOLD, 18));
		JTextField text1 = new JTextField(5);
		JTextField text2 = new JTextField(5);
		JTextField text3 = new JTextField(5);
		JButton xacNhan = new JButton("Xác nhận");
		xacNhan.setBackground(Color.lightGray);
		mk_Panel1.add(title_mkc);
		mk_Panel1.add(Box.createHorizontalStrut(100));
		mk_Panel1.add(text1);
		mk_Panel1.add(Box.createHorizontalStrut(100));
		//

		mk_Panel2.add(title_mkm);
		mk_Panel2.add(Box.createHorizontalStrut(100));
		mk_Panel2.add(text2);
		mk_Panel2.add(Box.createHorizontalStrut(100));
		//

		mk_Panel3.add(title_xnmk);
		mk_Panel3.add(Box.createHorizontalStrut(100));
		mk_Panel3.add(text3);
		mk_Panel3.add(Box.createHorizontalStrut(100));
		//
		JPanel thongbao=new JPanel();
		CardLayout C_layout = new CardLayout();
		thongbao.setLayout(C_layout);
		thongbao.add("t1",new JPanel());
		mk_Panel4.add(xacNhan);
		mk_Panel4.add(new JPanel());
		mk_Panel4.add(thongbao);
		mk_Panel4.add(new JPanel());
		mk_Panel4.add(new JPanel());
		mk_Panel4.add(new JPanel());
		//
		con_mk_Panel1.add(mk_Panel1);
		con_mk_Panel1.add(Box.createVerticalStrut(50));
		con_mk_Panel1.add(mk_Panel2);
		con_mk_Panel1.add(Box.createVerticalStrut(50));
		con_mk_Panel1.add(mk_Panel3);
		con_mk_Panel1.add(Box.createVerticalStrut(50));
		con_mk_Panel1.add(mk_Panel4);

		title_tk.setFont(new Font("Tahoma", Font.BOLD, 30));
		TK_Panel.add(title_tk, BorderLayout.NORTH);
		TK_Panel.add(con_mk_Panel1, BorderLayout.CENTER);
		TK_Panel.add(Box.createHorizontalStrut(100), BorderLayout.WEST);
		TK_Panel.add(Box.createHorizontalStrut(100), BorderLayout.EAST);
		TK_Panel.add(Box.createVerticalStrut(50), BorderLayout.SOUTH);
		// C

		TitledBorder title1 = new TitledBorder(border, "Thông Tin");
		title1.setTitleColor(Color.black);
		title1.setTitleFont(new Font("Tahoma", Font.BOLD, 16));

		C_Panel = new JPanel();
		C_Panel.setBorder(title1);
		C_Panel.setLayout(C_layout);
		C_Panel.add("Mở đầu", new Panel2());
		C_Panel.add("Sinh Viên", SV_Panel);
		C_Panel.add("Thời Khóa Biểu", L_Panel);
		C_Panel.add("Môn Học", MH_Panel);
		C_Panel.add("Tài Khoản", TK_Panel);
		// chức năng

		ArrayList<String> State = new ArrayList<String>();
		State.add("Mở đầu");

		it1_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
				cardLayout1.show(C_Panel, "Sinh Viên");
				State.add(it1_Button.getText());

			}
		});
		it2_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
				cardLayout1.show(C_Panel, "Thời Khóa Biểu");
				State.add(it2_Button.getText());

			}
		});
		it3_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
				cardLayout1.show(C_Panel, "Môn Học");
				State.add(it3_Button.getText());

			}
		});
		it5_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
				cardLayout1.show(C_Panel, "Tài Khoản");
				State.add(it5_Button.getText());
			}
		});
		it6_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LOGIN_FRAME lg = new LOGIN_FRAME();
			}
		});
		xacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tdmk tdmk=new tdmk("sv",text1.getText(),text2.getText(),text3.getText(), idsv);
				JLabel tb=new JLabel(tdmk.kt());
				thongbao.add("t2",tb);
				CardLayout cardLayout1 = (CardLayout) (thongbao.getLayout());
				cardLayout1.show(thongbao, "t2");
				TimerTask timerTask = new TimerTask() {
					@Override
					public void run() {
						cardLayout1.show(thongbao, "t1");
					}
				};
				long delay = 1500L;
				Timer timer = new Timer("Timer");
				timer.schedule(timerTask, delay);

			}
		});

		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index < State.size() - 1) {
					index++;

					CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
					cardLayout1.show(C_Panel, State.get(index));

				}
			}
		});
		preButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 0) {
					index--;
					CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
					cardLayout1.show(C_Panel, State.get(index));
				}
			}
		});
		//
		mainPanel.add(N_Panel, BorderLayout.NORTH);
		mainPanel.add(W_Panel, BorderLayout.WEST);
		mainPanel.add(C_Panel, BorderLayout.CENTER);

		//
		frame.add(mainPanel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}