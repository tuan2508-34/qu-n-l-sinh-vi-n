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
import dao.sinhvienDAO;
import dao.tkgvDAO;
import dao.tksvDAO;
import model.giangvien;
import model.sinhvien;
import model.tkgv;
import model.tksv;

public class adgv {
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
		giangvienDAO gvd1 = new giangvienDAO();
		int soDong = gvd1.selectAll().size();
		int soCot = 7;
		String[][] thu = new String[soDong][soCot];

		for (int i = 0; i < soDong; i++) {

			giangvien t = gvd1.selectAll().get(i);
			thu[i][0] = "" + (i + 1);
			thu[i][1] = t.getMagiangvien().toString();
			thu[i][2] = t.getHovaten();
		}

		String column3[] = { "STT", "Mã giảng viên", "Họ Và Tên" };

		DefaultTableModel model = new DefaultTableModel(thu, column3);
		JTable jt3 = new JTable(model);
		return jt3;
	}

	static public JPanel taora() {
		JLabel taotb2 = new JLabel("Đã cập nhật môn học !", JLabel.CENTER);
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
		JButton addgv = new JButton("Thêm giảng viên");
		JTextField idtext = new JTextField();
		JTextField nametext = new JTextField();
		JPanel h1 = new JPanel(new GridLayout(1, 4));
		h1.add(chinhsua);
		h1.add(new JPanel());
		h1.add(new JPanel());
		h1.add(new JPanel());
		JPanel h2 = new JPanel(new GridLayout(1, 3));
		h2.add(new JLabel("NHẬP MÃ GIẢNG VIÊN"));
		h2.add(idtext);
		h2.add(new JPanel());
		JPanel h3 = new JPanel(new GridLayout(1, 3));
		h3.add(new JLabel("NHẬP TÊN GIẢNG VIÊN"));
		h3.add(nametext);
		h3.add(new JPanel());
		JPanel h4 = new JPanel(new GridLayout(1, 4));
		h4.add(addgv);
		h4.add(new JPanel());
		h4.add(new JPanel());
		h4.add(new JPanel());
		control.add(h1);
		control.add(new JPanel());
		control.add(h2);
		control.add(new JPanel());
		control.add(h3);
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
				giangvien gvmodel = new giangvien();
				gvmodel.setMagiangvien(Long.parseLong(String.valueOf(jt3.getModel().getValueAt(i, 1))));
				gvmodel.setHovaten(String.valueOf(jt3.getModel().getValueAt(i, 2)));
				giangvienDAO gvd = new giangvienDAO();
				System.out.println(gvmodel.getHovaten());
				gvd.update(gvmodel);
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
				giangvien gvmodel = new giangvien();
				gvmodel.setMagiangvien(Long.parseLong(idtext.getText()));
				gvmodel.setHovaten(nametext.getText());
				giangvienDAO gvd = new giangvienDAO();
				sinhvienDAO svd = new sinhvienDAO();
				gvd.insert(gvmodel);
				tkgv tk = new tkgv();
				tk.setMagiangvien(gvmodel.getMagiangvien());
				tk.setGiangvien(gvmodel);
				;
				tkgvDAO tkd = new tkgvDAO();
				tkd.insert(tk);
				taotb2.setVisible(true);

				adgv cr = new adgv();
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