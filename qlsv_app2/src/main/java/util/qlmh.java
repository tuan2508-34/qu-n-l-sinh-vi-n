package util;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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

import dao.giangvienDAO;
import dao.monhocDAO;
import dao.sinhvienDAO;
import dao.tkgvDAO;
import dao.tksvDAO;
import model.giangvien;
import model.monhoc;
import model.sinhvien;
import model.tkgv;
import model.tksv;

public class qlmh {
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("Quản lí sinh viên");
		mainFrame.setSize(1000, 1000);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel jj = taora();

		mainFrame.add(jj);
		mainFrame.setVisible(true);
	}

	static public JTable taotable() {
		monhocDAO mhd1 = new monhocDAO();
		int soDong = mhd1.selectAll().size();
		int soCot = 4;
		String[][] thu = new String[soDong][soCot];

		for (int i = 0; i < soDong; i++) {

			monhoc t = mhd1.selectAll().get(i);
			thu[i][0] = "" + (i + 1);
			thu[i][1] = t.getMamonhoc().toString();
			thu[i][2] = t.getTenmonhoc();
			thu[i][3] = t.getSotinchi().toString();
		}

		String column3[] = { "STT", "Mã môn học", "Tên môn học","Số tín chỉ" };

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
		JScrollPane ds = new JScrollPane(jt3);
		JPanel conds = new JPanel();
		JPanel con = new JPanel(new CardLayout());
		conds.add(ds);
		con.add("l1", conds);
		//
		JPanel control = new JPanel(new GridLayout(20, 1));
		JButton chinhsua = new JButton("Chỉnh sửa");
		JButton addgv = new JButton("Thêm môn học");
		JTextField idtext = new JTextField();
		JTextField nametext = new JTextField();
		JTextField stctext = new JTextField();
		JPanel h1 = new JPanel(new GridLayout(1, 4));
		h1.add(chinhsua);
		h1.add(new JPanel());
		h1.add(new JPanel());
		h1.add(new JPanel());
		JPanel h2 = new JPanel(new GridLayout(1, 3));
		h2.add(new JLabel("NHẬP MÃ MÔN HỌC"));
		h2.add(idtext);
		h2.add(new JPanel());
		JPanel h3 = new JPanel(new GridLayout(1, 3));
		h3.add(new JLabel("NHẬP TÊN MÔN HỌC"));
		h3.add(nametext);
		h3.add(new JPanel());
		JPanel h4 = new JPanel(new GridLayout(1, 4));
		h4.add(addgv);
		h4.add(new JPanel());
		h4.add(new JPanel());
		h4.add(new JPanel());
		JPanel h5 = new JPanel(new GridLayout(1, 3));
		h5.add(new JLabel("NHẬP SỐ TÍN CHỈ"));
		h5.add(stctext);
		h5.add(new JPanel());
		control.add(h1);
		control.add(new JPanel());
		control.add(h2);
		control.add(new JPanel());
		control.add(h3);
		control.add(new JPanel());
		control.add(h5);
		control.add(new JPanel());
		control.add(h4);
		//
		JPanel viewPanel = new JPanel(new GridLayout(1, 2));
		viewPanel.add(con);
		viewPanel.add(control);
		//

		viewGetSV.add(tim, BorderLayout.NORTH);
		viewGetSV.add(viewPanel, BorderLayout.CENTER);
		// viewGetSV.add(buttons, BorderLayout.SOUTH);

		chinhsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = jt3.getSelectedRow();
				// if(i>=0){}
				System.out.println(i);
				monhoc mhmodel = new monhoc();
				mhmodel.setMamonhoc(Long.parseLong(String.valueOf(jt3.getModel().getValueAt(i, 1))));
				mhmodel.setTenmonhoc(String.valueOf(jt3.getModel().getValueAt(i, 2)));
				mhmodel.setSotinchi(Integer.valueOf(String.valueOf(jt3.getModel().getValueAt(i, 3))));
				monhocDAO mhd = new monhocDAO();
				System.out.println(mhmodel.getTenmonhoc());
				mhd.update(mhmodel);
				taotb2.setVisible(true);

				/*
				 * adgv cr=new adgv(); JTable reload = cr.taotable(); JScrollPane ds = new
				 * JScrollPane(reload); JPanel conds=new JPanel(); conds.add(ds);
				 * con.add("l2",conds); CardLayout cardLayout1 = (CardLayout) (con.getLayout());
				 * cardLayout1.show(con,"l2");
				 */

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
		addgv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monhoc mhmodel = new monhoc();
				mhmodel.setMamonhoc(Long.parseLong(idtext.getText()));
				mhmodel.setTenmonhoc(nametext.getText());
				mhmodel.setSotinchi(Integer.valueOf(stctext.getText()));
				monhocDAO mhd = new monhocDAO();
	            System.out.println(mhmodel.getMamonhoc());
				mhd.insert(mhmodel);
				taotb2.setVisible(true);

				qlmh cr = new qlmh();
				JTable reload = cr.taotable();
				JScrollPane ds = new JScrollPane(reload);
				JPanel conds = new JPanel();
				conds.add(ds);
				con.add("l2", conds);
				CardLayout cardLayout1 = (CardLayout) (con.getLayout());
				cardLayout1.show(con, "l2");

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
		return viewGetSV;
	}

}
