package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.sinhvienDAO;
import model.sinhvien;

public class combo {
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("Quản lí sinh viên");
		mainFrame.setSize(400, 600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel jj=taora();
		
		mainFrame.add(jj);
		mainFrame.setVisible(true);
	}
	static public JTable taotable() {
		sinhvienDAO svd1 = new sinhvienDAO();
		int soDong = svd1.selectAll().size();
		int soCot = 7;
		String[][] thu = new String[soDong][soCot];

		for (int i = 0; i < soDong; i++) {

			sinhvien t = svd1.selectAll().get(i);
			thu[i][0] = t.getMasinhvien().toString();
			thu[i][1] = t.getHovadem() + " " + t.getTen();
			thu[i][2] = t.getDate().toString();
			if (t.getGioitinh() == 1) {
				thu[i][3] = "Nam";
			} else if (t.getGioitinh() == 0) {
				thu[i][3] = "Nữ";
			}
			thu[i][4] = t.getDantoc();
			thu[i][5] = t.getQuequan();
			if (t.getTrangthaihoc() == 1) {
				thu[i][6] = "Đang học";
			} else if (t.getTrangthaihoc() == 0) {
				thu[i][6] = "Đã thôi học";
			}
		}

		String column3[] = { "Mã sinh viên", "Họ Và Tên", "Ngày tháng năm sinh", "Giới tính", "Dân tộc", "Quê quán",
				"Trạng thái học" };

		DefaultTableModel model = new DefaultTableModel(thu, column3);
		JTable jt3 = new JTable(model);
		return jt3;
	}

	static public JPanel taora() {
		JLabel taotb2 = new JLabel("Đã cập nhật sinh viên !", JLabel.CENTER);
		taotb2.setForeground(Color.BLUE);
		taotb2.setVisible(false);
		JPanel viewGetSV = new JPanel(new BorderLayout());

		//
		JPanel tim = new JPanel(new GridLayout(1, 3));
		JPanel maintim = new JPanel(new BorderLayout());
		JLabel tt = new JLabel("Tìm Kiếm ");
		tt.setFont(new Font("Tahoma", Font.BOLD, 18));
		JTextField textsearch = new JTextField(20);
		maintim.add(tt, BorderLayout.WEST);
		maintim.add(textsearch, BorderLayout.CENTER);
		tim.add(Box.createHorizontalStrut(1));
		tim.add(maintim);
		tim.add(Box.createHorizontalStrut(1));
		tim.setPreferredSize(new Dimension(1000, 30));
		//
		// JPanel ds=new JPanel();
		JTable jt3 = taotable();
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jt3.getModel());
		jt3.setRowSorter(rowSorter);
		textsearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = textsearch.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = textsearch.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
																				// choose Tools | Templates.
			}

		});

		jt3.setBounds(300, 400, 20, 30);
		JScrollPane ds = new JScrollPane(jt3);
		//
		JPanel buttons = new JPanel(new GridLayout(1, 10));
		JButton chinhsua = new JButton("Chỉnh sửa");
		chinhsua.setBackground(Color.green);
		JButton Xf = new JButton("Xuất file");
		chinhsua.setBackground(Color.green);
		JTextField txf=new JTextField();
		buttons.add(chinhsua);
		buttons.add(taotb2);
		buttons.add(Xf);
		buttons.add(txf);
		buttons.add(Box.createVerticalStrut(10));
		buttons.add(Box.createVerticalStrut(10));
		buttons.add(Box.createVerticalStrut(10));
		buttons.add(Box.createVerticalStrut(10));
		buttons.add(Box.createVerticalStrut(10));
		buttons.add(Box.createVerticalStrut(10));
		//
		viewGetSV.add(tim, BorderLayout.NORTH);
		viewGetSV.add(ds, BorderLayout.CENTER);
		viewGetSV.add(buttons, BorderLayout.SOUTH);
		
		
		
		chinhsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = jt3.getSelectedRow();
				System.out.println(i);
				sinhvien svmodel = new sinhvien();
				svmodel.setMasinhvien(Long.parseLong(String.valueOf(jt3.getModel().getValueAt(i, 0))));
				//
				String hovaten = String.valueOf(jt3.getModel().getValueAt(i, 1));
				//
				String tg = String.valueOf(jt3.getModel().getValueAt(i, 2));
				String[] cat3 = tg.split("-");
				LocalDate date = LocalDate.of(Integer.parseInt(cat3[0]), Integer.parseInt(cat3[1]),
						Integer.parseInt(cat3[2]));
				svmodel.setDate(date);

				//
				String[] cat = hovaten.split(" ");
				String[] cat2 = hovaten.split(" " + cat[cat.length - 1]);
				svmodel.setTen(cat[cat.length - 1]);
				svmodel.setHovadem(cat2[0]);
				//
				if (String.valueOf(jt3.getModel().getValueAt(i, 3)).equals("Nam")) {
					svmodel.setGioitinh(new Integer(1));
				} else if (String.valueOf(jt3.getModel().getValueAt(i, 3)).equals("Nữ")) {
					svmodel.setGioitinh(new Integer(0));
				}
				//
				if (String.valueOf(jt3.getModel().getValueAt(i, 6)).equals("Đang học")) {
					svmodel.setTrangthaihoc(new Integer(1));
				} else if (String.valueOf(jt3.getModel().getValueAt(i, 6)).equals("Đã thôi học")) {
					svmodel.setTrangthaihoc(new Integer(0));
				}
				//
				svmodel.setDantoc(String.valueOf(jt3.getModel().getValueAt(i, 4)));
				//
				svmodel.setQuequan(String.valueOf(jt3.getModel().getValueAt(i, 5)));
				sinhvienDAO svd = new sinhvienDAO();
				svd.update(svmodel);
			    viewGetSV.repaint();
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
				viewGetSV.repaint();
			}
		});
		Xf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String file = txf.getText();
				sinhvienDAO svd=new sinhvienDAO();
				List<sinhvien> a = svd.selectAll();
				writefile wf = new writefile(file, a);
			}
		});
		return viewGetSV;
	}
	

}