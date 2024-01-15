package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import dao.sinhvienDAO;
import dao.tksvDAO;
import model.sinhvien;
import model.tksv;
import util.adgv;
import util.combo;
import util.qlmh;
import util.qltkb;
import util.readfile;
import util.writefile;

public class AD_FRAME {
	int index = 0;
	//
	//
	private JLabel headerLabel;
	private JLabel iconLabel;
	private JLabel userLabel;
	private JLabel timeLabel;
	private JLabel timeNowLabel;
	private JLabel menuLabel;
	//
	private JPanel mainPanel;
	private JPanel N_Panel;
	private JPanel W_N_Panel;
	private JPanel info_Panel;
	private JPanel menuPanel;
	private JPanel item_menuPanel;
	private JPanel W_Panel;
	private JPanel C_Panel;
	//
	private JButton it1_Button;
	private JButton it2_Button;
	private JButton it3_Button;
	private JButton it4_Button;
	private JButton it6_Button;

	//

	public AD_FRAME() {
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
		it2_Button = new JButton("Giảng Viên");
		it2_Button.setBackground(Color.GRAY);
		it2_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		it3_Button = new JButton("Thời khóa biểu");
		it3_Button.setBackground(Color.GRAY);
		it3_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		it4_Button = new JButton("Môn Học");
		it4_Button.setBackground(Color.GRAY);
		it4_Button.setFont(new Font("Tahoma", Font.BOLD, 14));
		it6_Button = new JButton("Đăng xuất");
		it6_Button.setBackground(Color.GRAY);
		it6_Button.setFont(new Font("Tahoma", Font.BOLD, 14));

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
		JLabel user_label = new JLabel("ADMIN", JLabel.CENTER);
		info_Panel = new JPanel(new GridLayout(2, 2));
		info_Panel.add(userLabel);
		info_Panel.add(user_label);
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
		JPanel it4_P = new JPanel(new BorderLayout());
		it4_P.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		it4_P.add(it4_Button, BorderLayout.CENTER);
		it4_P.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
		JPanel it6_P = new JPanel(new BorderLayout());
		it6_P.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		it6_P.add(it6_Button, BorderLayout.CENTER);
		it6_P.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
		JPanel con_Button = new JPanel(new GridLayout(1, 5));
		
		item_menuPanel = new JPanel(new GridLayout(16, 3));
		item_menuPanel.add(Box.createVerticalStrut(5));
		item_menuPanel.add(it1_P);
		item_menuPanel.add(Box.createVerticalStrut(5));
		item_menuPanel.add(it2_P);
		item_menuPanel.add(Box.createVerticalStrut(5));
		item_menuPanel.add(it3_P);
		item_menuPanel.add(Box.createVerticalStrut(5));
		item_menuPanel.add(it4_P);
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
		JPanel SV_Panel = new JPanel();
		BorderLayout layoutget = new BorderLayout();
		layoutget.setHgap(20);
		layoutget.setVgap(20);
		SV_Panel.setLayout(layoutget);
		JPanel SV_Con_Panel = new JPanel(new GridLayout(1, 7));
		JButton setSV = new JButton("CHỈNH SỬA DANH SÁCH");
		setSV.setBackground(Color.GRAY);
		JButton getSV = new JButton("HIỆN DANH SÁCH");
		getSV.setBackground(Color.GRAY);
		SV_Con_Panel.add(Box.createHorizontalStrut(1));
		SV_Con_Panel.add(Box.createHorizontalStrut(1));
		SV_Con_Panel.add(getSV);
		SV_Con_Panel.add(Box.createHorizontalStrut(1));
		SV_Con_Panel.add(setSV);
		SV_Con_Panel.add(Box.createHorizontalStrut(1));
		SV_Con_Panel.add(Box.createHorizontalStrut(1));
		SV_Con_Panel.setPreferredSize(new Dimension(1000, 30));
		//
		JPanel SV_View_Panel = new JPanel();
		CardLayout SV_layout = new CardLayout();
		SV_View_Panel.setLayout(SV_layout);
		GridLayout gl = new GridLayout(15, 1);
		JPanel viewSetSV = new JPanel();
		viewSetSV.setLayout(gl);

		JLabel taofile = new JLabel("Nhập tên file");
		JLabel taotb = new JLabel("ĐÃ TẠO FILE !", JLabel.CENTER);
		taotb.setForeground(Color.BLUE);
		taotb.setVisible(false);
		JLabel taotb3 = new JLabel("ĐÃ NHẬP DANH SÁCH SINH VIÊN !", JLabel.CENTER);
		taotb3.setForeground(Color.BLUE);
		taotb3.setVisible(false);
		JLabel taotb2 = new JLabel("ĐÃ TẠO SINH VIÊN !", JLabel.CENTER);
		taotb2.setForeground(Color.BLUE);
		taotb2.setVisible(false);
		JTextField filetext = new JTextField(20);
		JTextField filetext2 = new JTextField(20);
		JButton tao = new JButton("Tạo file");
		JButton tao2 = new JButton("Tạo danh sách");
		JButton tao3 = new JButton("TẠO SINH VIÊN");
		tao3.setBackground(Color.CYAN);
		JTextField idtext = new JTextField(20);
		JTextField hdtext = new JTextField(20);
		JTextField ttext = new JTextField(20);
		JTextField agtext = new JTextField(20);
		JTextField dttext = new JTextField(20);
		JTextField qqtext = new JTextField(20);
		JTextField gttext = new JTextField(20);
		JTextField tthtext = new JTextField(20);
		JPanel h1 = new JPanel(new GridLayout(1, 5));
		JPanel h2 = new JPanel(new GridLayout(1, 5));
		JPanel h3 = new JPanel(new GridLayout(1, 5));
		JPanel h4 = new JPanel(new GridLayout(1, 5));
		JPanel h5 = new JPanel(new GridLayout(1, 5));
		JPanel h6 = new JPanel(new GridLayout(1, 5));
		JPanel h7 = new JPanel(new GridLayout(1, 12));

		h1.add(taofile);
		h1.add(filetext);
		h1.add(tao);
		h1.add(taotb);
		h1.add(Box.createHorizontalStrut(100));

		h6.add(new JLabel("Tạo danh sách sinh viên"));
		h6.add(filetext2);
		h6.add(tao2);
		h6.add(taotb3);
		h6.add(Box.createHorizontalStrut(100));

		h7.add(tao3);
		h7.add(taotb2);
		h7.add(Box.createHorizontalStrut(100));
		h7.add(Box.createHorizontalStrut(100));
		h7.add(Box.createHorizontalStrut(100));
		h7.add(Box.createHorizontalStrut(100));
		h7.add(Box.createHorizontalStrut(100));
		h7.add(Box.createHorizontalStrut(100));
		h7.add(Box.createHorizontalStrut(100));
		h7.add(Box.createHorizontalStrut(100));

		h2.add(new JLabel("Nhập mã sinh viên"));
		h2.add(idtext);
		h2.add(Box.createHorizontalStrut(100));
		h2.add(new JLabel("Nhập Họ và tên đệm"));
		h2.add(hdtext);

		h3.add(new JLabel("Nhập tên"));
		h3.add(ttext);
		h3.add(Box.createVerticalStrut(10));
		h3.add(new JLabel("Nhập ngày tháng năm sinh(dd/mm/yy)"));
		h3.add(agtext);

		h4.add(new JLabel("Nhập giới tính"));
		h4.add(gttext);
		h4.add(Box.createVerticalStrut(10));
		h4.add(new JLabel("Nhập dân tộc"));
		h4.add(dttext);

		h5.add(new JLabel("Nhập địa chỉ"));
		h5.add(qqtext);
		h5.add(Box.createVerticalStrut(10));
		h5.add(new JLabel("Nhập tình trạng học(đang học/đã thôi học)"));
		h5.add(tthtext);

		viewSetSV.add(new JLabel("TẠO FILE EXCEL MỚI (C:\\Users\\COMPUTER\\Downloads\\...)"));
		viewSetSV.add(h1);
		viewSetSV.add(new JLabel("Tạo FILE EXCEL DANH SÁCH SINH VIÊN (C:\\Users\\COMPUTER\\Downloads\\...)"));
		viewSetSV.add(h6);
		viewSetSV.add(new JLabel("TẠO SINH VIÊN MỚI"));
		viewSetSV.add(h2);
		viewSetSV.add(new JPanel());
		viewSetSV.add(h3);
		viewSetSV.add(new JPanel());
		viewSetSV.add(h4);
		viewSetSV.add(new JPanel());
		viewSetSV.add(h5);
		viewSetSV.add(new JPanel());
		viewSetSV.add(h7);
		combo cr = new combo();
		JPanel viewGetSV = cr.taora();
		SV_View_Panel.add("GET", viewGetSV);
		SV_View_Panel.add("SET", viewSetSV);
		SV_Panel.add(SV_Con_Panel, BorderLayout.NORTH);
		SV_Panel.add(SV_View_Panel, BorderLayout.CENTER);

		// L_Panel
		JPanel L_Panel = new JPanel(new BorderLayout());
		adgv adgv = new adgv();
		JPanel GV_panel = adgv.taora();
		L_Panel.add(GV_panel, BorderLayout.CENTER);

		// MH_Panel

		JPanel MH_Panel = new JPanel(new BorderLayout());
		qlmh qlmh = new qlmh();
		JPanel MHD_panel = qlmh.taora();
		MH_Panel.add(MHD_panel, BorderLayout.CENTER);

		// D_Panel

		JPanel D_Panel = new JPanel(new BorderLayout());
		qltkb qltkb = new qltkb();
		JPanel tkb_panel = qltkb.taora();
		D_Panel.add(tkb_panel, BorderLayout.CENTER);
		// TK_Panel
		JPanel TK_Panel = new JPanel(new BorderLayout());
		JLabel title_tk = new JLabel("THAY ĐỔI MẬT KHẨU", JLabel.CENTER);
		title_tk.setPreferredSize(new Dimension(100, 100));
		JPanel con_mk_Panel1 = new JPanel(new GridLayout(14, 1));
		JPanel mk_Panel1 = new JPanel(new GridLayout(1, 4));
		JPanel mk_Panel2 = new JPanel(new GridLayout(1, 4));
		JPanel mk_Panel3 = new JPanel(new GridLayout(1, 4));
		JPanel mk_Panel4 = new JPanel(new BorderLayout());
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
		mk_Panel4.add(xacNhan, BorderLayout.WEST);
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
		CardLayout C_layout = new CardLayout();
		C_Panel.setLayout(C_layout);
		
		C_Panel.add("SV", SV_Panel);
		C_Panel.add("GV", L_Panel);
		C_Panel.add("TKB", D_Panel);
		C_Panel.add("MH", MH_Panel);
		C_Panel.add("TK", TK_Panel);

		// chức năng

		ArrayList<String> State = new ArrayList<String>();
		State.add("SV");

		it1_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
				cardLayout1.show(C_Panel, "SV");
				State.add(it1_Button.getText());
			}
		});
		it2_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
				cardLayout1.show(C_Panel, "GV");
				State.add(it2_Button.getText());

			}
		});
		it3_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
				cardLayout1.show(C_Panel, "TKB");
				State.add(it3_Button.getText());

			}
		});
		it4_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				CardLayout cardLayout1 = (CardLayout) (C_Panel.getLayout());
				cardLayout1.show(C_Panel, "MH");
				State.add(it4_Button.getText());
			}
		});
		setSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout1 = (CardLayout) (SV_View_Panel.getLayout());
				cardLayout1.show(SV_View_Panel, "SET");
			}
		});
		getSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combo cr = new combo();
				JPanel viewGetSV = cr.taora();
				SV_View_Panel.add("HAHA", viewGetSV);
				CardLayout cardLayout1 = (CardLayout) (SV_View_Panel.getLayout());
				cardLayout1.show(SV_View_Panel, "HAHA");

			}
		});
		tao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = filetext.getText();
				List<sinhvien> a = new ArrayList<sinhvien>();
				writefile wf = new writefile(file, a);
				taotb.setVisible(true);

				TimerTask timerTask = new TimerTask() {
					@Override
					public void run() {
						taotb.setVisible(false);
					}
				};
				long delay = 2000L;
				Timer timer = new Timer("Timer");
				timer.schedule(timerTask, delay);
			}
		});
		tao2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = filetext2.getText();
				readfile rd = new readfile(file);
				taotb3.setVisible(true);

				TimerTask timerTask = new TimerTask() {
					@Override
					public void run() {
						taotb3.setVisible(false);
					}
				};
				long delay = 2000L;
				Timer timer = new Timer("Timer");
				timer.schedule(timerTask, delay);
				viewGetSV.repaint();
			}
		});
		tao3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sinhvien svmodel = new sinhvien();
				svmodel.setMasinhvien(Long.parseLong(idtext.getText()));
				//
				svmodel.setTen(ttext.getText());
				svmodel.setHovadem(hdtext.getText());
				//
				String tg = agtext.getText();
				String[] cat = tg.split("/");
				LocalDate date = LocalDate.of(Integer.parseInt(cat[2]), Integer.parseInt(cat[1]),
						Integer.parseInt(cat[0]));
				svmodel.setDate(date);
				//
				if (gttext.getText().equals("Nam")) {
					svmodel.setGioitinh(new Integer(1));
				} else if (gttext.getText().equals("Nữ")) {
					svmodel.setGioitinh(new Integer(0));
				}
				//
				if (tthtext.getText().equals("đang học")) {
					svmodel.setTrangthaihoc(new Integer(1));
				} else if (tthtext.getText().equals("đã thôi học")) {
					svmodel.setTrangthaihoc(new Integer(0));
				}
				//
				svmodel.setDantoc(dttext.getText());
				//
				svmodel.setQuequan(qqtext.getText());
				sinhvienDAO svd = new sinhvienDAO();
				svd.insert(svmodel);
				tksv tk = new tksv();
				tk.setMasinhvien(svmodel.getMasinhvien());
				tk.setSinhvien(svmodel);
				tksvDAO tkd = new tksvDAO();
				tkd.insert(tk);
				taotb2.setVisible(true);
				TimerTask timerTask = new TimerTask() {
					@Override
					public void run() {
						taotb2.setVisible(false);
					}
				};
				long delay = 2000L;
				Timer timer = new Timer("Timer");
				timer.schedule(timerTask, delay);

			}
		});
		it6_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LOGIN_FRAME lg = new LOGIN_FRAME();
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