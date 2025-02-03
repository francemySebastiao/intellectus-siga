package ao.co.intellectus.servico.cafold;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.ListagemBancaria;
import ao.co.intellectus.model.ListagemEmolumento;
import ao.co.intellectus.model.MapaFinanceiro;
import ao.co.intellectus.model.RegistroBaseDadosAproveitamento;
import ao.co.intellectus.model.RegistroBaseDadosGraduadosPreliminarDefinitivo;
import ao.co.intellectus.model.RegistroDocumentos;

@Service
public class NovoModeloRelatorioFinanceiro {

	Workbook wb = new XSSFWorkbook();
	Map<String, CellStyle> styles = createStyles(wb);
	Sheet sheet = wb.createSheet("Timesheet");
	PrintSetup printSetup = sheet.getPrintSetup();

	private String Titulo = "Relatorio Financeiro";

	/*
	 * ####################################################### ##### INICIO DOS
	 * METODOS PRIVADO DA CLASSE ############
	 * #######################################################
	 * #######################################################
	 */

	/**
	 * Create a library of cell styles
	 */
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

	private Sheet doCriarPanilha(Workbook ambienteDeTrabalho, String titulo) {
		Sheet planilha = ambienteDeTrabalho.createSheet(titulo);
		return planilha;
	}

	private ByteArrayInputStream doReajustarTamanhoDasColunas(Sheet planilha, Workbook ambienteDeTrabalho,
			ByteArrayOutputStream out, int quantidadeColunas) throws IOException {
		for (int i = 0; i < (quantidadeColunas - 1); i++) {
			planilha.autoSizeColumn(i);
		}
		ambienteDeTrabalho.write(out);
		return new ByteArrayInputStream(out.toByteArray());
	}

	private Cell doDefinirEstiloAndCabecarioPrincipal(String tituloprincipal, int ultimoindicedacoluna) {

		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(25);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(styles.get("title"));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, ultimoindicedacoluna));
		return titleCell;
	}

	private void doDefinirEstiloDasCelulasDasColunasDosTitulos(String[] coluna) {
		Row headerRow = sheet.createRow(1);
		headerRow.setHeightInPoints(15);
		Cell headerCell;
		for (int i = 0; i < coluna.length; i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(coluna[i]);
			headerCell.setCellStyle(styles.get("header"));
		}

	}

	private void doDefinirEstiloAndTituloNoCabecarioDasColunas(int tamanhoColunas) {
		int rownum = 2;
		// seta o style e titulo no cabecario
		Row row = sheet.createRow(rownum++);
		for (int i = 0; i < tamanhoColunas; i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(styles.get("cell"));
		}

	}

	private void doDefinirLargura(int largura, int tamanhoColuna) {
		// definir a largura das colunas
		for (int i = 0; i < tamanhoColuna; i++) {
			sheet.setColumnWidth(i, largura * 256);
		}

	}

	/*
	 * ####################################################### ##### INICIO DOS
	 * METODOS PUBLIC DA CLASSE##############
	 * #######################################################
	 * #######################################################
	 */

	public ByteArrayInputStream doMapaFinanceiroTesteDaErro(List<MapaFinanceiro> mapa) throws IOException {
		final String[] Coluna = { "ANO", "BOLSEIRO", "NUMERO", "DESCONTO", "NOME", "CURSO", "GRAU", "INSCRICAO",
				"JANEIRO", "FEVEREIRO", "MARCO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO",
				"NOVEMBRO", "DEZEMBRO" };
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = doCriarPanilha(wb, "MAPAFINANCEIRO");

		doDefinirEstiloDasCelulasDasColunasDosTitulos(Coluna);
		doDefinirEstiloAndTituloNoCabecarioDasColunas(Coluna.length);

		// seta os dados da bdados
		int rowIdx = 2;
		for (MapaFinanceiro m : mapa) {
			Row row1 = sheet.createRow(rowIdx++);
			// titleCell.setCellValue(Titulo.toUpperCase()+" - "+m.getLectivo());
			row1.createCell(0).setCellValue(m.getLectivo());
			row1.createCell(1).setCellValue(m.getBolseiro());
			row1.createCell(2).setCellValue(m.getNumero());
			row1.createCell(3).setCellValue(m.getDesconto());
			row1.createCell(4).setCellValue(m.getNome());
			row1.createCell(5).setCellValue(m.getCurso());
			row1.createCell(6).setCellValue(m.getGrau());
			row1.createCell(7).setCellValue(m.getInscricao());
			row1.createCell(8).setCellValue(m.getJaneiro());
			row1.createCell(9).setCellValue(m.getFevereiro());
			row1.createCell(10).setCellValue(m.getMarco());
			row1.createCell(11).setCellValue(m.getAbril());
			row1.createCell(12).setCellValue(m.getMaio());
			row1.createCell(13).setCellValue(m.getJulho());
			row1.createCell(14).setCellValue(m.getJunho());
			row1.createCell(15).setCellValue(m.getAgosto());
			row1.createCell(16).setCellValue(m.getSetembro());
			row1.createCell(17).setCellValue(m.getOutubro());
			row1.createCell(18).setCellValue(m.getNovembro());
			row1.createCell(19).setCellValue(m.getDezembro());

			// seta o style nas linhas
			for (int i = 0; i < 4; i++) {
				row1.getCell(i).setCellStyle(styles.get("cell"));
			}
			row1.getCell(4).setCellStyle(styles.get("cell_2"));
			row1.getCell(5).setCellStyle(styles.get("cell_2"));
			for (int i = 6; i < Coluna.length; i++) {
				row1.getCell(i).setCellStyle(styles.get("cell"));
			}

		}

		return doReajustarTamanhoDasColunas(planilha, wb, out, Coluna.length);

	}

	public Workbook TesteMapa(List<MapaFinanceiro> mapa) {
		final String[] Colunas = { "ANO", "BOLSEIRO", "NUMERO", "DESCONTO", "NOME", "CURSO", "GRAU", "INSCRICAO",
				"JANEIRO", "FEVEREIRO", "MARCO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO",
				"NOVEMBRO", "DEZEMBRO" };
		printSetup.setLandscape(true);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);

		// title row
		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(40);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(Titulo.toUpperCase());
		titleCell.setCellStyle(styles.get("title"));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19));

		// header row
		Row headerRow = sheet.createRow(1);
		headerRow.setHeightInPoints(40);

		Cell headerCell;
		for (int i = 0; i < Colunas.length; i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(Colunas[i]);
			headerCell.setCellStyle(styles.get("header"));
		}

		int rownum = 2;
		// finalmente o bundão seta o style e titulo no cabecario
		Row row = sheet.createRow(rownum++);
		for (int j = 0; j < Colunas.length; j++) {
			Cell cell = row.createCell(j);
			cell.setCellStyle(styles.get("cell"));

		}
		// int rowIdx = 2;

		// row with totals below
		Row sumRow = sheet.createRow(rownum++);
		sumRow.setHeightInPoints(35);
		Cell cell;
		cell = sumRow.createCell(0);
		cell.setCellStyle(styles.get("formula"));
		cell = sumRow.createCell(1);
		cell.setCellValue("Total Hrs:");
		cell.setCellStyle(styles.get("formula"));

		for (int j = 2; j < 12; j++) {
			cell = sumRow.createCell(j);
			String ref = (char) ('A' + j) + "3:" + (char) ('A' + j) + "12";
			cell.setCellFormula("SUM(" + ref + ")");
			if (j >= 9)
				cell.setCellStyle(styles.get("formula_2"));
			else
				cell.setCellStyle(styles.get("formula"));
		}

		// seta os dados da bdados
		for (MapaFinanceiro m : mapa) {
			// Row row1 = sheet.getRow(2 + i);
			Row row1 = sheet.createRow(rownum++);
			row1.createCell(0).setCellValue(m.getLectivo());
			// row1.getCell(i).setCellValue(Titulo.toUpperCase());

			// seta o style nas linhas
			for (int i1 = 0; i1 < 4; i1++) {
				// row1.getCell(i1).setCellStyle(styles.get("cell"));
			}
			// row1.getCell(4).setCellStyle(styles.get("cell_2"));
			// row1.getCell(5).setCellStyle(styles.get("cell_2"));
			for (int i1 = 6; i1 < Colunas.length; i1++) {
				// row1.getCell(i1).setCellStyle(styles.get("cell"));
			}

		}

		// finalmente o bundão vai definir a largura das colunas, a largura é medida em
		// unidades de 1/256 da largura de um caractere
		sheet.setColumnWidth(0, 6 * 256); // 6 characters wide
		for (int i = 1; i < Colunas.length; i++) {
			sheet.setColumnWidth(i, 12 * 256); // 12 characters wide
		}
		sheet.setColumnWidth(4, 40 * 256);
		sheet.setColumnWidth(5, 35 * 256);
		sheet.setColumnWidth(6, 18 * 256);
		return wb;

	}

	public Workbook doGerarMapaFinanceiroTeste(List<MapaFinanceiro> mapa) {
		final String[] Coluna = { "ANO", "BOLSEIRO", "NUMERO", "DESCONTO", "NOME", "CURSO", "GRAU", "INSCRICAO",
				"JANEIRO", "FEVEREIRO", "MARCO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO",
				"NOVEMBRO", "DEZEMBRO" };
		final String TituloPrincipal = "Relatorio Financeiro";

		Cell titleCell = doDefinirEstiloAndCabecarioPrincipal(TituloPrincipal, 19);
		doDefinirEstiloDasCelulasDasColunasDosTitulos(Coluna);
		doDefinirEstiloAndTituloNoCabecarioDasColunas(Coluna.length);

		int rowIdx = 2;
		for (MapaFinanceiro m : mapa) {
			Row row1 = sheet.createRow(rowIdx++);
			titleCell.setCellValue(TituloPrincipal.toUpperCase() + " - " + m.getLectivo());
			row1.createCell(0).setCellValue(m.getLectivo());
			row1.createCell(1).setCellValue(m.getBolseiro());
			row1.createCell(2).setCellValue(m.getNumero());
			row1.createCell(3).setCellValue(m.getDesconto());
			row1.createCell(4).setCellValue(m.getNome());
			row1.createCell(5).setCellValue(m.getCurso());
			row1.createCell(6).setCellValue(m.getGrau());
			row1.createCell(7).setCellValue(m.getInscricao());
			row1.createCell(8).setCellValue(m.getJaneiro());
			row1.createCell(9).setCellValue(m.getFevereiro());
			row1.createCell(10).setCellValue(m.getMarco());
			row1.createCell(11).setCellValue(m.getAbril());
			row1.createCell(12).setCellValue(m.getMaio());
			row1.createCell(13).setCellValue(m.getJulho());
			row1.createCell(14).setCellValue(m.getJunho());
			row1.createCell(15).setCellValue(m.getAgosto());
			row1.createCell(16).setCellValue(m.getSetembro());
			row1.createCell(17).setCellValue(m.getOutubro());
			row1.createCell(18).setCellValue(m.getNovembro());
			row1.createCell(19).setCellValue(m.getDezembro());

			// seta o style nas linhas
			for (int i = 0; i < 4; i++) {
				row1.getCell(i).setCellStyle(styles.get("cell"));
			}
			row1.getCell(4).setCellStyle(styles.get("cell_2"));
			row1.getCell(5).setCellStyle(styles.get("cell_2"));
			for (int i = 6; i < Coluna.length; i++) {
				row1.getCell(i).setCellStyle(styles.get("cell"));
			}

		}
		// definir a largura das colunas
		sheet.setColumnWidth(0, 6 * 256);
		for (int i = 1; i < Coluna.length; i++) {
			sheet.setColumnWidth(i, 12 * 256);
		}
		sheet.setColumnWidth(4, 43 * 256);
		sheet.setColumnWidth(5, 35 * 256);
		sheet.setColumnWidth(6, 18 * 256);

		return wb;

	}

	public Workbook doMapaReconcialicaoBancaria(List<ListagemBancaria> mapa) throws IOException {
		final String[] COLUNAS = { "NUMÉRO BORDEUX", "DATA DEPÓSITO", "NÚMERO GUIA", "BANCO", "VALOR" };
		final String TituloPrincipal = "LISTAGEM BANCARIA";

		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(40);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(styles.get("title"));
		titleCell.setCellValue(TituloPrincipal.toUpperCase());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		doDefinirEstiloDasCelulasDasColunasDosTitulos(COLUNAS);
		doDefinirEstiloAndTituloNoCabecarioDasColunas(COLUNAS.length);

		int rowIdx = 2;
		for (ListagemBancaria lista : mapa) {
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue(lista.getNumero_bordereux());
			row.createCell(1).setCellValue(lista.getDataDeposito().toString());
			row.createCell(2).setCellValue(lista.getNumero_guia());
			row.createCell(3).setCellValue(lista.getBanco());
			row.createCell(4).setCellValue(lista.getValor());
			for (int i = 0; i < COLUNAS.length; i++) {
				row.getCell(i).setCellStyle(styles.get("cell"));
			}
		}

		// definir a largura das colunas
		for (int i = 0; i < COLUNAS.length; i++) {
			sheet.setColumnWidth(i, 20 * 256);
		}

		return wb;
	}

	public Workbook doMapaEmolumentos(List<ListagemEmolumento> mapa) throws IOException {
		final String[] COLUNAS = { "NUMÉRO BORDEUX", "DATA DEPÓSITO", "NÚMERO GUIA", "EMOLUMENTO", "VALOR" };
		final String TituloPrincipal = "MAPA-LISTAGEM-EMOLUMENTO";

		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(40);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(styles.get("title"));
		titleCell.setCellValue(TituloPrincipal.toUpperCase());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		doDefinirEstiloDasCelulasDasColunasDosTitulos(COLUNAS);
		doDefinirEstiloAndTituloNoCabecarioDasColunas(COLUNAS.length);

		// PREENCHER AS CELULAS
		int rowIdx = 2;
		for (ListagemEmolumento lista : mapa) {
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue(lista.getNumero_bordereux());
			row.createCell(1).setCellValue(lista.getDataDeposito().toString());
			row.createCell(2).setCellValue(lista.getNumero_guia());
			row.createCell(3).setCellValue(lista.getEmolumento());
			row.createCell(4).setCellValue(lista.getValor());
			for (int i = 0; i < COLUNAS.length; i++) {
				row.getCell(i).setCellStyle(styles.get("cell"));
			}
		}
		// definir a largura das colunas
		for (int i = 0; i < COLUNAS.length; i++) {
			sheet.setColumnWidth(i, 20 * 256);
		}
		return wb;
	}

	public Workbook doMapaBancos(List<ListagemBancaria> mapas) throws IOException {
		final String[] COLUNAS = { "N.º de borderaux", "Data de Depósito", "N.º de Recibo", "Banco",
				"Valor Depositado" };
		final String TituloPrincipal = "LISTAGEM - RECONCILIAÇÃO BANCÁRIA";

		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(40);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(styles.get("title"));
		titleCell.setCellValue(TituloPrincipal.toUpperCase());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		doDefinirEstiloDasCelulasDasColunasDosTitulos(COLUNAS);
		doDefinirEstiloAndTituloNoCabecarioDasColunas(COLUNAS.length);

		int rowIdx = 2;
		for (ListagemBancaria mapa : mapas) {
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue(mapa.getNumero_bordereux());
			row.createCell(1).setCellValue(mapa.getDataDeposito().toString());
			row.createCell(2).setCellValue(mapa.getNumero_guia());
			row.createCell(3).setCellValue(mapa.getBanco());
			row.createCell(4).setCellValue(mapa.getValor());
			for (int i = 0; i < COLUNAS.length; i++) {
				row.getCell(i).setCellStyle(styles.get("cell"));
			}

		}

		doDefinirLargura(20, COLUNAS.length);
		return wb;
	}

	// work but not used yet
	public Workbook doRegistroBaseDadosGraduadosPreliminarDefinitivo(
			List<RegistroBaseDadosGraduadosPreliminarDefinitivo> mapas) throws IOException {
		final String[] COLUNAS = { "Ano Lectivo", "Nome Completo", "Bilhete de identidade", "Sexo", "Idade",
				"Data de nascimento", "Província de residência", "Município de residencia", "Nacionalidade",
				"Periodo de estudo", "Unidade orgânica", "Nome do Curso", "Ano Frequência", "Ano Primeira Matrícula",
				"Trabalhador", "Nível de graduação", "Quadro de Honra", "Media final" };

		final String TituloPrincipal = "PRELIMINAR E DEFINITIVO";

		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(40);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(styles.get("title"));
		titleCell.setCellValue(TituloPrincipal.toUpperCase());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 17));
		doDefinirEstiloDasCelulasDasColunasDosTitulos(COLUNAS);
		doDefinirEstiloAndTituloNoCabecarioDasColunas(COLUNAS.length);

		int rowIdx = 2;
		for (RegistroBaseDadosGraduadosPreliminarDefinitivo mapa : mapas) {
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue(mapa.getAnoLectivo());
			row.createCell(1).setCellValue(mapa.getNomeCompleto());
			row.createCell(2).setCellValue(mapa.getBilheteDeEEntidade());
			row.createCell(3).setCellValue(mapa.getSexo());
			row.createCell(4).setCellValue(mapa.getIdade());
			row.createCell(5).setCellValue(mapa.getDataNascimento());
			row.createCell(6).setCellValue(mapa.getProvinciaResidencia());
			row.createCell(7).setCellValue(mapa.getMunicipioResidencia());
			row.createCell(8).setCellValue(mapa.getNacionalidade());
			row.createCell(9).setCellValue(mapa.getPeriodoEstudo());
			row.createCell(10).setCellValue(mapa.getUnidadeOrganica());
			row.createCell(11).setCellValue(mapa.getNomeCurso());
			row.createCell(12).setCellValue(mapa.getAnoFrequencia());
			row.createCell(13).setCellValue(mapa.getAnoPrimeiroMatricula());
			row.createCell(14).setCellValue(mapa.getTrabalhador());
			row.createCell(15).setCellValue(mapa.getNivelGraducao());
			row.createCell(16).setCellValue(mapa.getQuadroHonra());
			row.createCell(17).setCellValue(mapa.getMediaFinal());
			for (int i = 0; i < COLUNAS.length; i++) {
				row.getCell(i).setCellStyle(styles.get("cell"));
			}
		}

		doDefinirLargura(20, COLUNAS.length);
		return wb;
	}

	// work not used yet
	public Workbook doRegistroBaseDadosAproveitamento(List<RegistroBaseDadosAproveitamento> mapas) throws IOException {
		final String[] COLUNAS = { "Ano Lectivo", "Nome Completo", "Bilhete de identidade", "Sexo", "Idade",
				"Data de nascimento", "Provincia de Residência", "Município de Residência", "Nacionalidade",
				"Periodo de estudo", "Unidade orgânica", "Nome do curso", "Ano de frequência", "Situação academica",
				"Aproveitamento" };

		final String TituloPrincipal = "Lista de aproveitamento";

		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(40);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(styles.get("title"));
		titleCell.setCellValue(TituloPrincipal.toUpperCase());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
		doDefinirEstiloDasCelulasDasColunasDosTitulos(COLUNAS);
		doDefinirEstiloAndTituloNoCabecarioDasColunas(COLUNAS.length);

		int rowIdx = 2;
		for (RegistroBaseDadosAproveitamento mapa : mapas) {
			Row row = sheet.createRow(rowIdx++);
			row.createCell(0).setCellValue(mapa.getAnolectivo());
			row.createCell(1).setCellValue(mapa.getNomeCompleto());
			row.createCell(2).setCellValue(mapa.getBilheteDeItendidade());
			row.createCell(3).setCellValue(mapa.getSexo());
			if (mapa.getIdade() != null)
				row.createCell(4).setCellValue(mapa.getIdade());
			row.createCell(5).setCellValue(mapa.getDataDeNascimento());
			row.createCell(6).setCellValue(mapa.getProvinciaoResidencia());
			row.createCell(7).setCellValue(mapa.getMunicipioResidencia());
			row.createCell(8).setCellValue(mapa.getNacionalidade());
			row.createCell(9).setCellValue(mapa.getPeriodoEstudo());
			row.createCell(10).setCellValue(mapa.getUnidadeOrganica());
			row.createCell(11).setCellValue(mapa.getNomeCurso());
			row.createCell(12).setCellValue(mapa.getAnoFrequencia());
			row.createCell(13).setCellValue(mapa.getSituacaoAcademica());
			row.createCell(14).setCellValue(mapa.getAproveitamento());
			for (int i = 0; i < COLUNAS.length; i++) {
				row.getCell(i).setCellStyle(styles.get("cell"));
			}
		}
		// definir a largura das colunas
		for (int i = 0; i < COLUNAS.length; i++) {
			sheet.setColumnWidth(i, 20 * 256);
		}
		return wb;
	}
	
	
	/*public Workbook ListagemDeCertificados_1(List<RegistroDocumentos> mapa) throws ParseException {
		final String[] Coluna = { " ", "Estudante", "Data de emissão", "Modelo" };
		final String TituloPrincipal = "Listagem Certificados imprenssos";
		Cell titleCell = doDefinirEstiloAndCabecarioPrincipal(TituloPrincipal, 14);
		doDefinirEstiloDasCelulasDasColunasDosTitulos(Coluna);
		doDefinirEstiloAndTituloNoCabecarioDasColunas(Coluna.length);

		// header row
		Row headerRow = sheet.createRow(1);
		headerRow.setHeightInPoints(30);

		Cell headerCell;
		for (int i = 0; i < Coluna.length; i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(Coluna[i]);
			// headerCell.setCellStyle(styles.get("header"));
		}

		int rownum = 2;
		int numLinha = 1;

		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		// seta os dados da bdados
		for (RegistroDocumentos m : mapa) {

			Row row1 = sheet.createRow(rownum++);
			titleCell.setCellValue(TituloPrincipal.toUpperCase());
			row1.createCell(0).setCellValue(numLinha++);
			row1.createCell(1).setCellValue(m.getAluno().getNome());
			row1.createCell(2).setCellValue(formatar.format(m.getDataEmissao()));
			row1.createCell(3).setCellValue(m.getTipoDeclaracao().getDescricao());

		}

		// finalmente o bundão vai definir a largura das colunas, a largura é medida em
		// unidades de 1/256 da largura de um caractere
		sheet.setColumnWidth(0, 6 * 256); // 6 characters wide
		for (int i = 1; i < Coluna.length; i++) {
			sheet.setColumnWidth(i, 12 * 256); // 12 characters wide
		}

		return wb;

	}*/
	

	/*public Workbook ListagemDeCertificados_2(List<RegistroDocumentos> mapa) throws ParseException {
		final String[] Coluna = { " ", "Estudante", "Data de emissão", "Modelo" };
		final String TituloPrincipal = "Listagem Certificados imprenssos";
		Cell titleCell = doDefinirEstiloAndCabecarioPrincipal(TituloPrincipal, 14);
		doDefinirEstiloDasCelulasDasColunasDosTitulos(Coluna);
		doDefinirEstiloAndTituloNoCabecarioDasColunas(Coluna.length);

		// header row
		Row headerRow = sheet.createRow(1);
		headerRow.setHeightInPoints(30);

		Cell headerCell;
		for (int i = 0; i < Coluna.length; i++) {
			headerCell = headerRow.createCell(i);
			headerCell.setCellValue(Coluna[i]);
			// headerCell.setCellStyle(styles.get("header"));
		}

		int rownum = 2;
		int numLinha = 1;

		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		// seta os dados da bdados
		for (RegistroDocumentos m : mapa) {

			Row row1 = sheet.createRow(rownum++);
			titleCell.setCellValue(TituloPrincipal.toUpperCase());
			row1.createCell(0).setCellValue(numLinha++);
			row1.createCell(1).setCellValue(m.getAluno().getNome());
			row1.createCell(2).setCellValue(formatar.format(m.getDataEmissao()));
			row1.createCell(3).setCellValue(m.getTipoDeclaracao().getDescricao());

		}

		// finalmente o bundão vai definir a largura das colunas, a largura é medida em
		// unidades de 1/256 da largura de um caractere
		/*sheet.setColumnWidth(0, 6 * 256); // 6 characters wide
		for (int i = 1; i < Coluna.length; i++) {
			sheet.setColumnWidth(i, 12 * 256); // 12 characters wide
		}

		return wb;

	}*/

	public void GerarMapaFinanceiro() {
	}

	public void GerarMapaBancos() {
	}

	public void GerarMapaEmolumentos() {
	}

	public void GerarMapaReconcialicaoBancaria() {
	}
}
