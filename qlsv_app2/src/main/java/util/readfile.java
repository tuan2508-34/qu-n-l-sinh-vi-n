package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.sinhvienDAO;
import dao.tksvDAO;
import model.sinhvien;
import model.tksv;

public class readfile {
	public static final int COLUMN_INDEX_STT = 0;
	public static final int COLUMN_INDEX_ID = 1;
	public static final int COLUMN_INDEX_HVD = 2;
	public static final int COLUMN_INDEX_NAME = 3;
	public static final int COLUMN_INDEX_AGE = 4;
	public static final int COLUMN_INDEX_SEX = 5;
	public static final int COLUMN_INDEX_DT = 6;
	public static final int COLUMN_INDEX_QUE = 7;
	public static final int COLUMN_INDEX_TTH = 8;

	private String tenfile;
	
	public readfile(String tenfile) {
		super();
		this.tenfile = tenfile;
		String path="C:\\Users\\COMPUTER\\Downloads\\"+tenfile+".xlsx";
		try {
			readExcel(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<sinhvien> readExcel(String excelFilePath) throws IOException {
		List<sinhvien> listBooks = new ArrayList<>();

		// Get file
		InputStream inputStream = new FileInputStream(new File(excelFilePath));

		// Get workbook
		Workbook workbook = getWorkbook(inputStream, excelFilePath);

		// Get sheet
		Sheet sheet = workbook.getSheetAt(0);

		// Get all rows
		Iterator<Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if (nextRow.getRowNum() == 0) {
				// Ignore header
				continue;
			}

			// Get all cells
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			// Read cells and set value for book object
			sinhvien book = new sinhvien();
			while (cellIterator.hasNext()) {
				// Read cell
				Cell cell = cellIterator.next();
				Object cellValue = getCellValue(cell);
				if (cellValue == null || cellValue.toString().isEmpty()) {
					continue;
				}
				// Set value for book object
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case COLUMN_INDEX_STT:
					break;
				case COLUMN_INDEX_ID:
					double d = cell.getNumericCellValue();
					long l = (new Double(d)).longValue();
					book.setMasinhvien(l);
					break;
				case COLUMN_INDEX_HVD:
					book.setHovadem((String) getCellValue(cell));
					;
					break;
				case COLUMN_INDEX_NAME:
					book.setTen((String) getCellValue(cell));
					;
					break;
				case COLUMN_INDEX_AGE:
					String tg = (String) getCellValue(cell);
					String[] cat = tg.split("-");
					LocalDate date = LocalDate.of(Integer.parseInt(cat[0]), Integer.parseInt(cat[1]),
							Integer.parseInt(cat[2]));
					book.setDate(date);
					break;
				case COLUMN_INDEX_SEX:
					String sex = (String) getCellValue(cell);
					if (sex.equals("Nam")) {
						book.setGioitinh(new Integer(1));
					} else if (sex.equals("Nữ")) {
						book.setGioitinh(new Integer(0));
					}
					break;
				case COLUMN_INDEX_QUE:
					book.setQuequan((String) getCellValue(cell));
					;
					break;
				case COLUMN_INDEX_DT:
					book.setDantoc((String) getCellValue(cell));
					break;
				case COLUMN_INDEX_TTH:
					String tth = (String) getCellValue(cell);
					if (tth.equals("Đang học")||tth.equals("đang học")) {
						book.setTrangthaihoc(new Integer(1));
					} else if (tth.equals("Đã thôi học")||tth.equals("đã thôi học")) {
						book.setTrangthaihoc(new Integer(0));
					}
					break;
				default:
					break;
				}

			}
			listBooks.add(book);
			sinhvienDAO svd=new sinhvienDAO();
			svd.insert(book);
			tksv tk=new tksv(); tk.setMasinhvien(book.getMasinhvien());tk.setSinhvien(book);
			tksvDAO tkd=new tksvDAO();
			tkd.insert(tk);
		}

		workbook.close();
		inputStream.close();

		return listBooks;
	}

	// Get Workbook
	private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	// Get cell value
	private static Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellTypeEnum();
		Object cellValue = null;
		switch (cellType) {
		case BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case FORMULA:
			Workbook workbook = cell.getSheet().getWorkbook();
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			cellValue = evaluator.evaluate(cell).getNumberValue();
			break;
		case NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case _NONE:
		case BLANK:
		case ERROR:
			break;
		default:
			break;
		}

		return cellValue;
	}
}