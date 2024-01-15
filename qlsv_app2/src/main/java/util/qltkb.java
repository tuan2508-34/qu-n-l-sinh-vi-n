package util;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import dao.lopDAO;
import model.lop;


public class qltkb {
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
		lop lop=new lop();
		lopDAO lopDao=new lopDAO();
		int soDong = lopDao.selectAll().size();
		int soCot = 9;
		String[][] thu = new String[soDong][soCot];

		for (int i = 0; i < soDong; i++) {
			lop t = lopDao.selectAll().get(i);
			thu[i][0] = "" + (i + 1);
			thu[i][1] = t.getMalop().toString();
			thu[i][2] = t.getBuoi().toString();
			thu[i][3] = t.getCa().toString();
			thu[i][4] = t.getMonhoc().getMamonhoc().toString();
			thu[i][5] = t.getMonhoc().getTenmonhoc();
			thu[i][6] = t.getGiangvien().getMagiangvien().toString();
			thu[i][7] = t.getGiangvien().getHovaten();
		}

		String column3[] = { "STT", "Mã lớp", "Buổi học","Ca học","Mã môn học","Môn học","Mã giảng viên","Giảng viên" };

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
	    JPanel con_ds=new JPanel();
		CardLayout C_layout = new CardLayout();
		con_ds.setLayout(C_layout);
		con_ds.add("t1",ds);
		//
		JPanel list_buts=new JPanel(new GridLayout(12,1));
		JButton b1=new JButton("Cập nhật");
		list_buts.add(b1);		//
		//
		viewGetSV.add(tim, BorderLayout.NORTH);
		viewGetSV.add(con_ds, BorderLayout.CENTER);
		viewGetSV.add(list_buts, BorderLayout.EAST);
		list_buts.setPreferredSize(new Dimension(100,1));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = jt3.getSelectedRow();
		        
				lop lop = new lop();
				lop.setMalop(Long.parseLong(String.valueOf(jt3.getModel().getValueAt(i, 1))));
				lop.setBuoi(Integer.valueOf(String.valueOf(jt3.getModel().getValueAt(i, 2))));
				lop.setCa(Integer.valueOf(String.valueOf(jt3.getModel().getValueAt(i, 3))));
				;
				lopDAO lopdao = new lopDAO();
				lopdao.update(lop);
			  
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
		
	
		return viewGetSV;
	}

}