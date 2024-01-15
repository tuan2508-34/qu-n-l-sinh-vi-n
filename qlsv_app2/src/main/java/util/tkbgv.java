package util;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import dao.buoihocDAO;
import dao.giangvienDAO;
import dao.lopDAO;
import model.buoihoc;
import model.giangvien;
import model.lop;

public class tkbgv {
	static private Long mgv = new Long(101);

	public tkbgv(Long mgv) {
		super();
		this.mgv = mgv;
	}

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
		giangvien gv = new giangvien();
		gv.setMagiangvien(mgv);
		giangvienDAO gvd = new giangvienDAO();
		giangvien gvm = gvd.selectById(gv);
		lop lop = new lop();
		lopDAO lopDao = new lopDAO();
		int soDong = lopDao.selectByGv(gvm).size();
		int soCot = 9;
		String[][] thu = new String[soDong][soCot];

		for (int i = 0; i < soDong; i++) {
			lop t = lopDao.selectByGv(gvm).get(i);
			thu[i][0] = "" + (i + 1);
			thu[i][1] = t.getMalop().toString();
			thu[i][2] = t.getMonhoc().getMamonhoc().toString();
			thu[i][3] = t.getMonhoc().getTenmonhoc();
			thu[i][4] = t.getBuoi().toString();
			thu[i][5] = t.getCa().toString();
		}

		String column3[] = { "STT", "Mã lớp", "Mã môn học", "Môn học", "Thứ", "Ca học" };

		DefaultTableModel model = new DefaultTableModel(thu, column3);
		JTable jt3 = new JTable(model);
		return jt3;
	}

	static public JTable taotable2(Long ml) {
		lop lop = new lop();
		lop.setMalop(ml);
		lopDAO lopDao = new lopDAO();
		lop l = lopDao.selectById(lop);
		buoihocDAO bhd = new buoihocDAO();
		int soDong = bhd.selectByLop(l).size();
		int soCot = 9;
		String[][] thu = new String[soDong][soCot];
		for (int i = 0; i < soDong; i++) {
			buoihoc t = bhd.selectByLop(l).get(i);
			thu[i][0] = "" + (i + 1);
			thu[i][1] = t.getMabuoihoc().toString();
			thu[i][2] = t.getSinhvien().getMasinhvien().toString();
			thu[i][3] = t.getSinhvien().getHovadem() + " " + t.getSinhvien().getTen();
			thu[i][4] = t.getSobuoivang().toString();
		}

		String column3[] = { "STT", "Note", "Mã sinh viên", "Họ và Tên", "Số buổi vắng" };

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
		//

		//
		JScrollPane ds = new JScrollPane(jt3);
		CardLayout C_layout = new CardLayout();
		//
		JPanel list_buts = new JPanel(new GridLayout(12, 1));
		JButton b1 = new JButton("Chi tiết");
		list_buts.add(b1); 
		//
		JPanel list_buts2 = new JPanel(new GridLayout(12, 1));
		JButton b2 = new JButton("Cập nhật");
		JButton b3 = new JButton("Quay lại");
		list_buts2.add(b2); 
		list_buts2.add(new JPanel()); 
		list_buts2.add(b3); 
		//
		viewGetSV.add(tim, BorderLayout.NORTH);
		viewGetSV.add(ds, BorderLayout.CENTER);
		viewGetSV.add(list_buts, BorderLayout.EAST);
		list_buts.setPreferredSize(new Dimension(100, 1));
		//
		JPanel Detail = new JPanel(new BorderLayout());
		JPanel Tong = new JPanel();
		Tong.setLayout(C_layout);
		Tong.add("t1", viewGetSV);
		Tong.add("t2", Detail);
		///////////////////
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = jt3.getSelectedRow();
				lop lop = new lop();
				JTable jt = taotable2(Long.parseLong(String.valueOf(jt3.getModel().getValueAt(i, 1))));
				TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jt.getModel());
				jt.setRowSorter(rowSorter);
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
						throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
																						// methods,
																						// choose Tools | Templates.
					}

				});
				
				JScrollPane ds2 = new JScrollPane(jt);
				
				Detail.add(tim, BorderLayout.NORTH);
			    Detail.add(ds2, BorderLayout.CENTER);
			    Detail.add(list_buts2, BorderLayout.EAST);
				CardLayout cardLayout1 = (CardLayout) (Tong.getLayout());
				cardLayout1.show(Tong, "t2");
				
				
				
				b2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int i = jt.getSelectedRow();
						buoihoc bh=new buoihoc();
						bh.setMabuoihoc(Long.parseLong(String.valueOf(jt.getModel().getValueAt(i, 1))));
						bh.setSobuoivang(Integer.valueOf(String.valueOf(jt.getModel().getValueAt(i, 4))));;
						buoihocDAO bhd=new buoihocDAO();
						bhd.update(bh);
					}
				});
				
				
				
			}
		});
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout1 = (CardLayout) (Tong.getLayout());
				cardLayout1.show(Tong, "t1");
				
			}
		});

		return Tong;
	}

}