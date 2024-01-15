package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import model.sinhvien;

public class writefile {

	public static final int COLUMN_INDEX_STT = 0;
	public static final int COLUMN_INDEX_ID = 1;
	public static final int COLUMN_INDEX_HVD = 2;
	public static final int COLUMN_INDEX_NAME = 3;
	public static final int COLUMN_INDEX_AGE = 4;
	public static final int COLUMN_INDEX_SEX = 5;
	public static final int COLUMN_INDEX_DT = 6;
	public static final int COLUMN_INDEX_QUE = 7;
	public static final int COLUMN_INDEX_TTH = 8;

	private static CellStyle cellStyleFormatNumber = null;
	private String tenfile;
	private List<sinhvien> list;

	public writefile(String tenfile, List<sinhvien> list) {
		super();
		this.tenfile = tenfile;
		this.list = list;
		String path = "C:\\Users\\COMPUTER\\Downloads\\" + tenfile + ".xlsx";
		try {
			writeExcel(list, path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeExcel(List<sinhvien> sinhviens, String excelFilePath) throws IOException {
		// Create Workbook
		SXSSFWorkbook workbook = new SXSSFWorkbook();

		// Create sheet
		SXSSFSheet sheet = workbook.createSheet("Danh sách sinh viên"); // Create sheet with sheet name

		// register the columns you wish to track and compute the column width
		sheet.trackAllColumnsForAutoSizing();

		int rowIndex = 0;

		// Write header
		writeHeader(sheet, rowIndex);

		// Write data
		rowIndex++;
		for (sinhvien sv : sinhviens) {
			// Create row
			SXSSFRow row = sheet.createRow(rowIndex);
			// Write data on row
			writeBook(sv, row, rowIndex);
			rowIndex++;
		}

		// Auto resize column witdth
		int numberOfColumn = 9; // sheet.getRow(0).getPhysicalNumberOfCells();
		autosizeColumn(sheet, numberOfColumn);

		// Create file excel
		createOutputFile(workbook, excelFilePath);
		System.out.println("Done!!!");
	}

	// Write header with format
	private static void writeHeader(SXSSFSheet sheet, int rowIndex) {
		// create CellStyle
		CellStyle cellStyle = createStyleForHeader(sheet);

		// Create row
		SXSSFRow row = sheet.createRow(rowIndex);

		// Create cells
		SXSSFCell cell = row.createCell(COLUMN_INDEX_STT);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("STT");

		cell = row.createCell(COLUMN_INDEX_ID);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Mã sinh viên");

		cell = row.createCell(COLUMN_INDEX_HVD);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Họ và tên đệm");

		cell = row.createCell(COLUMN_INDEX_NAME);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Tên");

		cell = row.createCell(COLUMN_INDEX_SEX);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Giới tính");

		cell = row.createCell(COLUMN_INDEX_AGE);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Tuổi");

		cell = row.createCell(COLUMN_INDEX_DT);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Dân tộc");

		cell = row.createCell(COLUMN_INDEX_QUE);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Quê quán");

		cell = row.createCell(COLUMN_INDEX_TTH);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Trạng thái học");
	}

	// Write data
	private static void writeBook(sinhvien sv, SXSSFRow row, int i) {
		if (cellStyleFormatNumber == null) {
			// Format number
			short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
			// DataFormat df = workbook.createDataFormat();
			// short format = df.getFormat("#,##0");

			// Create CellStyle
			SXSSFWorkbook workbook = row.getSheet().getWorkbook();
			cellStyleFormatNumber = workbook.createCellStyle();
			cellStyleFormatNumber.setDataFormat(format);
		}
		SXSSFCell cell = row.createCell(COLUMN_INDEX_STT);
		cell.setCellValue(String.valueOf(i));

		cell = row.createCell(COLUMN_INDEX_ID);
		cell.setCellValue(sv.getMasinhvien().toString());

		cell = row.createCell(COLUMN_INDEX_HVD);
		cell.setCellValue(sv.getHovadem());

		cell = row.createCell(COLUMN_INDEX_NAME);
		cell.setCellValue(sv.getTen());
		cell.setCellStyle(cellStyleFormatNumber);

		if (sv.getGioitinh() == 1) {
			cell = row.createCell(COLUMN_INDEX_SEX);
			cell.setCellValue("Nam");
			cell.setCellStyle(cellStyleFormatNumber);
		} else if (sv.getGioitinh() == 0) {
			cell = row.createCell(COLUMN_INDEX_SEX);
			cell.setCellValue("Nữ");
			cell.setCellStyle(cellStyleFormatNumber);

		}
		cell = row.createCell(COLUMN_INDEX_AGE);
		cell.setCellValue(sv.getDate().toString());
		cell.setCellStyle(cellStyleFormatNumber);

		cell = row.createCell(COLUMN_INDEX_DT);
		cell.setCellValue(sv.getDantoc());
		cell.setCellStyle(cellStyleFormatNumber);

		cell = row.createCell(COLUMN_INDEX_QUE);
		cell.setCellValue(sv.getQuequan());
		cell.setCellStyle(cellStyleFormatNumber);

		if (sv.getTrangthaihoc() == 1) {
			cell = row.createCell(COLUMN_INDEX_TTH);
			cell.setCellValue("Đang học");
			cell.setCellStyle(cellStyleFormatNumber);
		} else if (sv.getTrangthaihoc() == 0) {
			cell = row.createCell(COLUMN_INDEX_TTH);
			cell.setCellValue("Đã thôi học");
			cell.setCellStyle(cellStyleFormatNumber);
		}

		// Create cell formula
		// totalMoney = price * quantity

	}

	// Create CellStyle for header
	private static CellStyle createStyleForHeader(Sheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(true);
		font.setFontHeightInPoints((short) 14); // font size
		font.setColor(IndexedColors.WHITE.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		return cellStyle;
	}

	// Auto resize column width
	private static void autosizeColumn(SXSSFSheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

	// Create output file
	private static void createOutputFile(SXSSFWorkbook workbook, String excelFilePath) throws IOException {
		try (OutputStream os = new FileOutputStream(excelFilePath)) {
			workbook.write(os);
		}
	}

}