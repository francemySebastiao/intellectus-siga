package ao.co.intellectus.servico.cafold;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.RegistroDocumentos;

@Service
public class ListagemCertificadoExport {

	Workbook wb = new XSSFWorkbook();
	Map<String, CellStyle> styles = createStyles(wb);
	Sheet sheet = wb.createSheet("Timesheet");
	PrintSetup printSetup = sheet.getPrintSetup();

	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<>();
		CellStyle style;
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 18);
		titleFont.setBold(true);
		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(titleFont);
		styles.put("title", style);

		Font monthFont = wb.createFont();
		monthFont.setFontHeightInPoints((short) 11);
		monthFont.setColor(IndexedColors.WHITE.getIndex());
		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFont(monthFont);
		style.setWrapText(true);
		styles.put("header", style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styles.put("cell", style);

		style = wb.createCellStyle();
		style.setWrapText(true);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		styles.put("cell_2", style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
		styles.put("formula", style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
		styles.put("formula_2", style);

		return styles;
	}

	/*private void doDefinirEstiloDasCelulasDasColunasDosTitulos(String[] coluna) {
		Row headerRow = sheet.createRow(1);
		headerRow.setHeightInPoints(15);
		Cell headerCell;
		for (int i = 0; i < coluna.length; i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(coluna[i]);
			//headerCell.setCellStyle(styles.get("header"));
		}

	}*/

	/*private void doDefinirEstiloAndTituloNoCabecarioDasColunas(int tamanhoColunas) {
		int rownum = 2;
		Row row = sheet.createRow(rownum++);
		for (int i = 0; i < tamanhoColunas; i++) {
			Cell cell = row.createCell(i);
			//cell.setCellStyle(styles.get("cell"));
		}

	}*/

	public Workbook ListagemDeCertificados_1(List<RegistroDocumentos> mapa) throws ParseException {
		final String[] Coluna = { " ", "Estudante", "Data de emissão", "Modelo" };

		//doDefinirEstiloDasCelulasDasColunasDosTitulos(Coluna);
		//doDefinirEstiloAndTituloNoCabecarioDasColunas(Coluna.length);

		Row headerRow = sheet.createRow(1);
		headerRow.setHeightInPoints(15);

		Cell headerCell;
		for (int i = 0; i < Coluna.length; i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(Coluna[i]);
		}

		int rownum = 2;
		int numLinha = 1;

		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		for (RegistroDocumentos m : mapa) {

			Row row1 = sheet.createRow(rownum++);
			row1.createCell(0).setCellValue(numLinha++);
			row1.createCell(1).setCellValue(m.getAluno().getNome());
			row1.createCell(2).setCellValue(formatar.format(m.getDataEmissao()));
			row1.createCell(3).setCellValue(m.getTipoDeclaracao().getDescricao());
		}
		return wb;
	}

	public Workbook ListagemDeCertificados_2(List<RegistroDocumentos> mapa) throws ParseException {
		final String[] Coluna = { " ", "Estudante", "Data de emissão", "Modelo" };

		Row headerRow = sheet.createRow(1);
		headerRow.setHeightInPoints(15);

		Cell headerCell;
		for (int i = 0; i < Coluna.length; i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(Coluna[i]);
		}

		int rownum = 2;
		int numLinha = 1;

		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		for (RegistroDocumentos m : mapa) {

			Row row1 = sheet.createRow(rownum++);
			row1.createCell(0).setCellValue(numLinha++);
			row1.createCell(1).setCellValue(m.getAluno().getNome());
			row1.createCell(2).setCellValue(formatar.format(m.getDataEmissao()));
			row1.createCell(3).setCellValue(m.getTipoDeclaracao().getDescricao());
		}

		return wb;
	}
}
