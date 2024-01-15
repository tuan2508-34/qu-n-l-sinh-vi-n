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
import dao.buoihocDAO;
import dao.lopDAO;
import dao.sinhvienDAO;
import model.buoihoc;
import model.lop;
import model.sinhvien;


public class tkbsv {
	static private Long msv = new Long(20182367);

	public tkbsv(Long msv) {
		super();
		this.msv = msv;
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
		
		
		
		sinhvien sv = new sinhvien();
		sv.setMasinhvien(msv);
		sinhvienDAO svd = new sinhvienDAO();
		sinhvien svm = svd.selectById(sv);
		
		buoihocDAO bhd=new buoihocDAO();
		int soDong =bhd.selectBySv(svm).size();
		int soCot = 9;
		String[][] thu = new String[soDong][soCot];

		for (int i = 0; i < soDong; i++) {
		    buoihoc t = bhd.selectBySv(svm).get(i);
			thu[i][0] = "" + (i + 1);
			thu[i][1] = t.getMabuoihoc().toString();
			thu[i][2] = t.getLop().getBuoi().toString();
			thu[i][3] = t.getLop().getCa().toString();
			thu[i][4] = t.getLop().getMalop().toString();
			thu[i][5] = t.getLop().getMonhoc().getMamonhoc().toString();
			thu[i][6] = t.getLop().getMonhoc().getTenmonhoc();
			thu[i][7] = t.getLop().getGiangvien().getHovaten();
			thu[i][8] = t.getSobuoivang().toString();
		}

		String column3[] = { "STT","Note","Thứ", "Ca học","Mã lớp", "Mã môn học", "Môn học","Giảng viên","Số buổi vắng"};

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
		//
	
		//
	
		//
		viewGetSV.add(tim, BorderLayout.NORTH);
		viewGetSV.add(ds, BorderLayout.CENTER);
	
	
		

		return viewGetSV;
	}

}
