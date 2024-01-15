package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
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
import dao.monhocDAO;
import model.monhoc;

public class mhsv {
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
		monhocDAO mhd = new monhocDAO();
		int soDong = mhd.selectAll().size();
		int soCot = 7;
		String[][] thu = new String[soDong][soCot];
		for (int i = 0; i < soDong; i++) {
			monhoc t = mhd.selectAll().get(i);
			thu[i][0] = "" + (i + 1);
			thu[i][1] = t.getMamonhoc().toString();
			thu[i][2] = t.getTenmonhoc();
			thu[i][3] = t.getSotinchi().toString();
		}

		String column3[] = { "STT", "Mã môn học", "Tên môn học", "Số tín chỉ" };

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
																				// // choose Tools | Templates.
			}
		});
		JScrollPane ds = new JScrollPane(jt3);
		viewGetSV.add(tim, BorderLayout.NORTH);
		viewGetSV.add(ds, BorderLayout.CENTER);
		return viewGetSV;
	}

}
