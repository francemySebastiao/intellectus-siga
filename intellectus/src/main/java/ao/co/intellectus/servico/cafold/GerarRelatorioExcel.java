package ao.co.intellectus.servico.cafold;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import ao.co.intellectus.model.RegistroBaseDadosMatriculaincial;
import ao.co.intellectus.model.RegistroBaseDeDadosPosGraduacao;
import ao.co.intellectus.model.ResgistroBaseDadosAcesso;

@Service
public class GerarRelatorioExcel {

	public ByteArrayInputStream mapaFinanceiro(List<MapaFinanceiro> mapa) throws IOException {
		String[] COLUNAS = { "ANO", "BOLSEIRO", "NUMERO", "DESCONTO", "NOME", "CURSO", "GRAU", "INSCRICAO", "JANEIRO",
				"FEVEREIRO", "MARCO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO",
				"DEZEMBRO" };

		Workbook ambienteDeTrabalho = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = criarPanilha(ambienteDeTrabalho, "MAPA-FINANCEIRO");

		Font fonteDoCabecalho = definirFonteDoCabecalho(ambienteDeTrabalho, true, IndexedColors.RED.getIndex());

		CellStyle estiloDaCelulaDoCabecalho = criarCelulas(ambienteDeTrabalho, fonteDoCabecalho);

		//
		/*
		 * Font titleFont = wb.createFont();
		 * 
		 * titleFont.setFontHeightInPoints((short)18); titleFont.setBold(true);
		 * estiloDaCelulaDoCabecalho = wb.createCellStyle();
		 * estiloDaCelulaDoCabecalho.setAlignment(HorizontalAlignment.CENTER);
		 * estiloDaCelulaDoCabecalho.setVerticalAlignment(VerticalAlignment.CENTER);
		 * estiloDaCelulaDoCabecalho.setFont(titleFont);
		 * estiloDaCelulaDoCabecalho.put("title", style);
		 */

		// DEFINIR VALOR DAS CELULAS
		definirValoresDasCelulas(planilha, estiloDaCelulaDoCabecalho, COLUNAS);

		// PREENCHER AS CELULAS
		int rowIdx = 1;
		for (MapaFinanceiro m : mapa) {
			Row row = planilha.createRow(rowIdx++);
			row.createCell(0).setCellValue(m.getLectivo());
			row.createCell(1).setCellValue(m.getBolseiro());
			row.createCell(2).setCellValue(m.getNumero());
			row.createCell(3).setCellValue(m.getDesconto());
			row.createCell(4).setCellValue(m.getNome());
			row.createCell(5).setCellValue(m.getCurso());
			row.createCell(6).setCellValue(m.getGrau());
			row.createCell(7).setCellValue(m.getInscricao());
			row.createCell(8).setCellValue(m.getJaneiro());
			row.createCell(9).setCellValue(m.getFevereiro());
			row.createCell(10).setCellValue(m.getMarco());
			row.createCell(11).setCellValue(m.getAbril());
			row.createCell(12).setCellValue(m.getMaio());
			row.createCell(13).setCellValue(m.getJulho());
			row.createCell(14).setCellValue(m.getJunho());
			row.createCell(15).setCellValue(m.getAgosto());
			row.createCell(16).setCellValue(m.getSetembro());
			row.createCell(17).setCellValue(m.getOutubro());
			row.createCell(18).setCellValue(m.getNovembro());
			row.createCell(19).setCellValue(m.getDezembro());

			/*
			 * row.setHeightInPoints(35); Cell cell; cell = row.createCell(0);
			 * cell.setCellStyle(styles.get("formula")); cell = sumRow.createCell(1);
			 * cell.setCellValue("Total Hrs:"); cell.setCellStyle(styles.get("formula"));
			 */

		}
		// RETORNAR O TOTAL
		return reajustarTamanhoDasColunas(planilha, ambienteDeTrabalho, out, COLUNAS.length);
	}

	public ByteArrayInputStream mapaReconcialicaoBancaria(List<ListagemBancaria> mapa) throws IOException {
		String[] COLUNAS = { "NUMÉRO BORDEUX", "DATA DEPÓSITO", "NÚMERO GUIA", "BANCO", "VALOR" };
		Workbook ambienteDeTrabalho = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = criarPanilha(ambienteDeTrabalho, "MAPA-RECONCIALIAÇÃO-BANCÁRIA");
		Font fonteDoCabecalho = definirFonteDoCabecalho(ambienteDeTrabalho, true, IndexedColors.RED.getIndex());
		CellStyle estiloDaCelulaDoCabecalho = criarCelulas(ambienteDeTrabalho, fonteDoCabecalho);
		definirValoresDasCelulas(planilha, estiloDaCelulaDoCabecalho, COLUNAS);
		planilha = this.definirTitulo(ambienteDeTrabalho, planilha, "LISTAGEM BANCARIA", "$A$1:$E$1");
		// PREENCHER AS CELULAS
		int rowIdx = 2;
		for (ListagemBancaria lista : mapa) {
			Row row = planilha.createRow(rowIdx++);
			row.createCell(0).setCellValue(lista.getNumero_bordereux());
			row.createCell(1).setCellValue(lista.getDataDeposito().toString());
			row.createCell(2).setCellValue(lista.getNumero_guia());
			row.createCell(3).setCellValue(lista.getBanco());
			row.createCell(4).setCellValue(lista.getValor());
		}
		return reajustarTamanhoDasColunas(planilha, ambienteDeTrabalho, out, COLUNAS.length);
	}

	public ByteArrayInputStream mapaEmolumentos(List<ListagemEmolumento> mapa) throws IOException {
		String[] COLUNAS = { "NUMÉRO BORDEUX", "DATA DEPÓSITO", "NÚMERO GUIA", "EMOLUMENTO", "VALOR" };
		Workbook ambienteDeTrabalho = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = criarPanilha(ambienteDeTrabalho, "MAPA-LISTAGEM-EMOLUMENTO");
		Font fonteDoCabecalho = definirFonteDoCabecalho(ambienteDeTrabalho, true, IndexedColors.RED.getIndex());
		CellStyle estiloDaCelulaDoCabecalho = criarCelulas(ambienteDeTrabalho, fonteDoCabecalho);
		definirValoresDasCelulas(planilha, estiloDaCelulaDoCabecalho, COLUNAS);
		planilha = this.definirTitulo(ambienteDeTrabalho, planilha, "LISTAGEM EMOLUMENTO", "$A$1:$E$1");
		// PREENCHER AS CELULAS
		int rowIdx = 2;
		for (ListagemEmolumento lista : mapa) {
			Row row = planilha.createRow(rowIdx++);
			row.createCell(0).setCellValue(lista.getNumero_bordereux());
			row.createCell(1).setCellValue(lista.getDataDeposito().toString());
			row.createCell(2).setCellValue(lista.getNumero_guia());
			row.createCell(3).setCellValue(lista.getEmolumento());
			row.createCell(4).setCellValue(lista.getValor());
		}
		return reajustarTamanhoDasColunas(planilha, ambienteDeTrabalho, out, COLUNAS.length);
	}

	public ByteArrayInputStream registroBaseDadosAproveitamento(List<RegistroBaseDadosAproveitamento> mapas)
			throws IOException {
		String[] COLUNAS = { "Ano Lectivo", "Nome Completo", "Bilhete de identidade", "Sexo", "Idade",
				"Data de nascimento", "Provincia de Residência", "Município de Residência", "Nacionalidade", "Periodo de estudo",
				"Unidade orgânica", "Nome do curso", "Adno de frequência", "Situação academica", "Aproveitamento" };

		Workbook ambienteDeTrabalho = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = criarPanilha(ambienteDeTrabalho, "APROVEITAMENTO");
		Font fonteDoCabecalho = definirFonteDoCabecalho(ambienteDeTrabalho, true, IndexedColors.BLACK.getIndex());
		CellStyle estiloDaCelulaDoCabecalho = criarCelulas(ambienteDeTrabalho, fonteDoCabecalho);
		definirValoresDasCelulas(planilha, estiloDaCelulaDoCabecalho, COLUNAS);

		planilha = this.definirTitulo(ambienteDeTrabalho, planilha, "LISTAGEM - APROVEITAMENTO", "$A$1:$P$1");
		int rowIdx = 2;
		for (RegistroBaseDadosAproveitamento mapa : mapas) {
			Row row = planilha.createRow(rowIdx++);
			row.createCell(0).setCellValue(mapa.getAnolectivo());
			row.createCell(1).setCellValue(mapa.getNomeCompleto());
			row.createCell(2).setCellValue(mapa.getBilheteDeItendidade());
			row.createCell(3).setCellValue(mapa.getSexo());
			if(mapa.getIdade() != null)
				row.createCell(4).setCellValue(mapa.getIdade());
			row.createCell(5).setCellValue(mapa.getDataDeNascimento() );
			row.createCell(6).setCellValue(mapa.getProvinciaoResidencia());
			row.createCell(7).setCellValue(mapa.getMunicipioResidencia());
			row.createCell(8).setCellValue(mapa.getNacionalidade());
			row.createCell(9).setCellValue(mapa.getPeriodoEstudo());
			row.createCell(10).setCellValue(mapa.getUnidadeOrganica());
			row.createCell(11).setCellValue(mapa.getNomeCurso());
			row.createCell(12).setCellValue(mapa.getAnoFrequencia());
			row.createCell(13).setCellValue(mapa.getSituacaoAcademica());
			row.createCell(14).setCellValue(mapa.getAproveitamento());
		}
		return reajustarTamanhoDasColunas(planilha, ambienteDeTrabalho, out, COLUNAS.length);
	}

	 
	public ByteArrayInputStream registroBaseDadosGraduadosPreliminarDefinitivo(List<RegistroBaseDadosGraduadosPreliminarDefinitivo> mapas) throws IOException {
		String[] COLUNAS = {

				"Ano Lectivo", "Nome Completo", "Bilhete de identidade", "Sexo", "Idade", "Data de nascimento",
				"Província de residência", "Município de residencia", "Nacionalidade", "Periodo de estudo", "Unidade orgânica",
				"Nome do Curso", "Ano Frequência", "Ano Primeira Matrícula", "Trabalhador", "Nível de graduação",
				"Quadro de Honra", "Media final"

		};

		Workbook ambienteDeTrabalho = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = criarPanilha(ambienteDeTrabalho, "PRELIMINAR_E_DEFINITIVO");
		Font fonteDoCabecalho = definirFonteDoCabecalho(ambienteDeTrabalho, true, IndexedColors.BLACK.getIndex());
		CellStyle estiloDaCelulaDoCabecalho = criarCelulas(ambienteDeTrabalho, fonteDoCabecalho);
		definirValoresDasCelulas(planilha, estiloDaCelulaDoCabecalho, COLUNAS);

		planilha = this.definirTitulo(ambienteDeTrabalho, planilha, "PRELIMINAR E DEFINITIVO", "$A$1:$R$1");
		int rowIdx = 2;
		for (RegistroBaseDadosGraduadosPreliminarDefinitivo mapa : mapas) {
			Row row = planilha.createRow(rowIdx++);
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
		}
		return reajustarTamanhoDasColunas(planilha, ambienteDeTrabalho, out, COLUNAS.length);
	}

	public ByteArrayInputStream RegistroBaseDadosMatriculaincial(List<RegistroBaseDadosMatriculaincial> mapas)
			throws IOException {
		String[] COLUNAS = { 
				"Ano Lectivo", "Nome completo", "Bilhete de identidade", "Sexo", "Idade",
				"Data de nascimento", "Provincia de residência", "Município de residência", "Nacionalidade", "Periodo de estudo",
				"Unidade orgânica", "Nome do curso", "Ano de frequência", "Situação academica", "Ano primeira matrícula",
				"Ness. educação especial", "Trabalhador", "Nível de graduação"
		};

		Workbook ambienteDeTrabalho = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = criarPanilha(ambienteDeTrabalho, "MATRICULA_INICIAL");
		Font fonteDoCabecalho = definirFonteDoCabecalho(ambienteDeTrabalho, true, IndexedColors.BLACK.getIndex());
		CellStyle estiloDaCelulaDoCabecalho = criarCelulas(ambienteDeTrabalho, fonteDoCabecalho);
		definirValoresDasCelulas(planilha, estiloDaCelulaDoCabecalho, COLUNAS);

		planilha = this.definirTitulo(ambienteDeTrabalho, planilha, "LISTAGEM - MATRICULA INICIAL", "$A$1:$S$1");
		int rowIdx = 2;
		for (RegistroBaseDadosMatriculaincial mapa : mapas) {
			Row row = planilha.createRow(rowIdx++);
			row.createCell(0).setCellValue(mapa.getAnoLectivo());
			row.createCell(1).setCellValue(mapa.getNomeCompleto());
			row.createCell(2).setCellValue(mapa.getBilheteIdentidade());
			row.createCell(3).setCellValue(mapa.getSexo());
			if(mapa.getIdade() != null)
				row.createCell(4).setCellValue(mapa.getIdade());
			row.createCell(5).setCellValue(mapa.getDataNascimento());
			row.createCell(6).setCellValue(mapa.getProvinciaResidencia());
			row.createCell(7).setCellValue(mapa.getMunicioResidencia());
			row.createCell(8).setCellValue(mapa.getNacionalidade());
			row.createCell(9).setCellValue(mapa.getPeriodoEstudo());
			row.createCell(10).setCellValue(mapa.getUnidadeOrganica());
			row.createCell(11).setCellValue(mapa.getNomeCurso());
			row.createCell(12).setCellValue(mapa.getAnoFrequencia());
			row.createCell(13).setCellValue(mapa.getSsituacaoAcademica());
			row.createCell(14).setCellValue(mapa.getAnoPrimeiraMatricula());
			row.createCell(15).setCellValue(mapa.getNessEducacaoEspecial());
			row.createCell(16).setCellValue(mapa.getTrabalhador());
			row.createCell(17).setCellValue(mapa.getNivelGraduacao());
		}
		return reajustarTamanhoDasColunas(planilha, ambienteDeTrabalho, out, COLUNAS.length);
	}

	public ByteArrayInputStream registroBaseDeDadosPosGraduacao(List<RegistroBaseDeDadosPosGraduacao> mapas)
			throws IOException {
		String[] COLUNAS = { "Ano lectivo", "Nome completo", "Bilhete de identidade", "Sexo", "Idade",
				"Data de nascimento", "Provincia de residência", "Minicípio de residência", "Nacionalidade", "Unidade orgânica",
				"Tipo de graduação", "Nome de curso", "Número da edição", "Situação academica", "Tipo de funcionário",
				"Trabalhador", "Ness. educação especial", "Ano primeira matrícula", "Duração de formação"
		};

		Workbook ambienteDeTrabalho = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = criarPanilha(ambienteDeTrabalho, "POS_GRADUACAO");
		Font fonteDoCabecalho = definirFonteDoCabecalho(ambienteDeTrabalho, true, IndexedColors.BLACK.getIndex());
		CellStyle estiloDaCelulaDoCabecalho = criarCelulas(ambienteDeTrabalho, fonteDoCabecalho);
		definirValoresDasCelulas(planilha, estiloDaCelulaDoCabecalho, COLUNAS);

		planilha = this.definirTitulo(ambienteDeTrabalho, planilha, "LISTAGEM - POS GRADUACAO", "$A$1:$S$1");
		int rowIdx = 2;
		for (RegistroBaseDeDadosPosGraduacao mapa : mapas) {
			Row row = planilha.createRow(rowIdx++);
			row.createCell(0).setCellValue(mapa.getAnoLectivo());
			row.createCell(1).setCellValue(mapa.getNomeCompleto());
			row.createCell(2).setCellValue(mapa.getBilheteDeItendidade());
			row.createCell(3).setCellValue(mapa.getSexo());
			row.createCell(4).setCellValue(mapa.getIdade());
			row.createCell(5).setCellValue(mapa.getDataDeNascimento());
			row.createCell(6).setCellValue(mapa.getProvinciaoResidencia());
			row.createCell(7).setCellValue(mapa.getMunicipioResidencia());
			row.createCell(8).setCellValue(mapa.getNacionalidade());
			row.createCell(9).setCellValue(mapa.getUnidadeOrganica());
			row.createCell(10).setCellValue(mapa.getTipoGraduacao());
			row.createCell(11).setCellValue(mapa.getNomeCurso());
			row.createCell(12).setCellValue(mapa.getNumeroEdicao());
			row.createCell(13).setCellValue(mapa.getSituacaoAcademica());
			row.createCell(14).setCellValue(mapa.getTipoFuncionario());
			row.createCell(15).setCellValue(mapa.getTrabalhador());
			row.createCell(16).setCellValue(mapa.getNessEducacaoEspecia());
			row.createCell(17).setCellValue(mapa.getAnoPrimeiroMatricula());
			row.createCell(18).setCellValue(mapa.getDuracaoFormacao());
		}
		return reajustarTamanhoDasColunas(planilha, ambienteDeTrabalho, out, COLUNAS.length);
	}

	public ByteArrayInputStream resgistroBaseDadosAcesso(List<ResgistroBaseDadosAcesso> mapas) throws IOException {
		String[] COLUNAS = {
				"Ano lectivo", "Nome completo", "Bilhete de identidade", "Sexo", "Idade", "Data de nascimento",
				"Província de residência", "Município de residência", "Nacionalidade", "Periodo de estudo", "Unidade orgânica",
				"Nome do curso", "Nota exame", "Adimissão", "Proc. ens. médio", "Curso ens. médio", "Trabalhador"
		};

		Workbook ambienteDeTrabalho = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = criarPanilha(ambienteDeTrabalho, "REGISTRO_BASE_DE_DADOS_ACESSO");
		Font fonteDoCabecalho = definirFonteDoCabecalho(ambienteDeTrabalho, true, IndexedColors.BLACK.getIndex());
		CellStyle estiloDaCelulaDoCabecalho = criarCelulas(ambienteDeTrabalho, fonteDoCabecalho);
		definirValoresDasCelulas(planilha, estiloDaCelulaDoCabecalho, COLUNAS);

		planilha = this.definirTitulo(ambienteDeTrabalho, planilha, "LISTAGEM - Acesso", "$A$1:$Q$1");
		int rowIdx = 2;
		for (ResgistroBaseDadosAcesso mapa : mapas) {
			Row row = planilha.createRow(rowIdx++);
			row.createCell(0).setCellValue(mapa.getAnoLectivo());
			row.createCell(1).setCellValue(mapa.getNomeCompleto());
			row.createCell(2).setCellValue(mapa.getBilheteDeIdentidade());
			row.createCell(3).setCellValue(mapa.getSexo());
			if(mapa.getIdade() != null)
				row.createCell(4).setCellValue(mapa.getIdade());
			row.createCell(5).setCellValue(mapa.getDataDeNascimento());
			row.createCell(6).setCellValue(mapa.getProvinciaResidencia());
			row.createCell(7).setCellValue(mapa.getMunicipioResidencia());
			row.createCell(8).setCellValue(mapa.getNacionalidade());
			row.createCell(9).setCellValue(mapa.getPeriodoEstudo());
			row.createCell(10).setCellValue(mapa.getUnidadeOrganica());
			row.createCell(11).setCellValue(mapa.getNomeCurso());
			if(mapa.getNotaExame() != null)
				row.createCell(12).setCellValue(mapa.getNotaExame());
			row.createCell(13).setCellValue(mapa.getAdimissao());
			row.createCell(14).setCellValue(mapa.getProcEnsMedio());
			row.createCell(15).setCellValue(mapa.getCursoEnsMedio());
			row.createCell(16).setCellValue(mapa.getTrabalhador());
		}
		return reajustarTamanhoDasColunas(planilha, ambienteDeTrabalho, out, COLUNAS.length);
	}
	 
	public ByteArrayInputStream mapaBancos(List<ListagemBancaria> mapas) throws IOException {
		String[] COLUNAS = { "N.º de borderaux", "Data de Depósito", "N.º de Recibo","Banco" ,"Valor Depositado"};

		Workbook ambienteDeTrabalho = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = criarPanilha(ambienteDeTrabalho, "MAPA-RECONCIALIAÇÃO-BANCÁRIA");
		Font fonteDoCabecalho = definirFonteDoCabecalho(ambienteDeTrabalho, true, IndexedColors.RED.getIndex());
		
		CellStyle estiloDaCelulaDoCabecalho = criarCelulas(ambienteDeTrabalho, fonteDoCabecalho);
		
		definirValoresDasCelulas(planilha, estiloDaCelulaDoCabecalho, COLUNAS);

		planilha = this.definirTitulo(ambienteDeTrabalho, planilha, "LISTAGEM - RECONCILIAÇÃO BANCÁRIA", "$A$1:$F$1");
		int rowIdx = 2;
		for (ListagemBancaria mapa : mapas) {
			Row row = planilha.createRow(rowIdx++);
			row.createCell(0).setCellValue(mapa.getNumero_bordereux());
			row.createCell(1).setCellValue(mapa.getDataDeposito().toString());
			row.createCell(2).setCellValue(mapa.getNumero_guia());
			row.createCell(3).setCellValue(mapa.getBanco());
			row.createCell(4).setCellValue(mapa.getValor());
			
		}
		return reajustarTamanhoDasColunas(planilha, ambienteDeTrabalho, out, COLUNAS.length);
	}

	
	
	private Sheet definirTitulo(Workbook ambienteDeTrabalho, Sheet planilha, String titulo, String cumprimento) {
		Map<String, CellStyle> styles = createStyles(ambienteDeTrabalho);
		// Titulo na linha
		Row titleRow = planilha.createRow(0);
		titleRow.setHeightInPoints(45);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(titulo);
		titleCell.setCellStyle(styles.get("title"));
		planilha.addMergedRegion(CellRangeAddress.valueOf(cumprimento));
		return planilha;
	}

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

	private Sheet criarPanilha(Workbook ambienteDeTrabalho, String titulo) {
		Sheet planilha = ambienteDeTrabalho.createSheet(titulo);
		return planilha;
	}

	private Font definirFonteDoCabecalho(Workbook ambienteDeTrabalho, boolean bold, short cor) {
		Font fonteDoCabecalho = ambienteDeTrabalho.createFont();
		fonteDoCabecalho.setBold(bold);
		fonteDoCabecalho.setColor(cor);
		return fonteDoCabecalho;
	}

	private CellStyle criarCelulas(Workbook ambienteDeTrabalho, Font fonteDoCabecalho) {
		CellStyle estiloDaCelulaDoCabecalho = ambienteDeTrabalho.createCellStyle();
		estiloDaCelulaDoCabecalho.setFont(fonteDoCabecalho);
		return estiloDaCelulaDoCabecalho;
	}

	private void definirValoresDasCelulas(Sheet planilha, CellStyle estiloDaCelulaDoCabecalho, String[] COLUNAS) {
		Row headerRow = planilha.createRow(1);
		for (int col = 0; col < COLUNAS.length; col++) {
			Cell cell = headerRow.createCell(col);
			cell.setCellValue(COLUNAS[col]);
			cell.setCellStyle(estiloDaCelulaDoCabecalho);
		}
	}

	private ByteArrayInputStream reajustarTamanhoDasColunas(Sheet planilha, Workbook ambienteDeTrabalho,
			ByteArrayOutputStream out, int quantidadeColunas) throws IOException {
			for (int i = 0; i < (quantidadeColunas - 1); i++) {
				planilha.autoSizeColumn(i);
			}
		ambienteDeTrabalho.write(out);
		return new ByteArrayInputStream(out.toByteArray());
	}

	

/*
	public ByteArrayInputStream mapaBancos(List<ListagemBancaria> mapas) throws IOException {
		String[] COLUNAS = { "N.º de borderaux", "Data de Depósito", "N.º de Recibo", "Valor Depositado",
				"Valor Kwanzas", "Banco" };

		Workbook ambienteDeTrabalho = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet planilha = criarPanilha(ambienteDeTrabalho, "MAPA-RECONCIALIAÇÃO-BANCÁRIA");
		Font fonteDoCabecalho = definirFonteDoCabecalho(ambienteDeTrabalho, true, IndexedColors.RED.getIndex());
		CellStyle estiloDaCelulaDoCabecalho = criarCelulas(ambienteDeTrabalho, fonteDoCabecalho);
		definirValoresDasCelulas(planilha, estiloDaCelulaDoCabecalho, COLUNAS);

		planilha = this.definirTitulo(ambienteDeTrabalho, planilha, "LISTAGEM - RECONCILIAÇÃO BANCÁRIA", "$A$1:$F$1");
		int rowIdx = 2;
		for (ListagemBancaria mapa : mapas) {
			Row row = planilha.createRow(rowIdx++);
			row.createCell(0).setCellValue(mapa.getNumero_bordereux());
			row.createCell(1).setCellValue(mapa.getDataDeposito().toString());
			row.createCell(2).setCellValue(mapa.getNumero_guia());
			row.createCell(3).setCellValue(mapa.getValor());
			row.createCell(4).setCellValue(mapa.getValor());
			row.createCell(5).setCellValue(mapa.getBanco());
		}
		return reajustarTamanhoDasColunas(planilha, ambienteDeTrabalho, out, COLUNAS.length);
	}
*/
	
	
}
