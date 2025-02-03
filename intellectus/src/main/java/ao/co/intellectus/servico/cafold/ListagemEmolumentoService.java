package ao.co.intellectus.servico.cafold;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.DataFiltro;
import ao.co.intellectus.model.ListagemEmolumento;
import ao.co.intellectus.model.RelatorioFinanceiro;
import ao.co.intellectus.repository.ListatagemEmolumentoRespository;

@Service
public class ListagemEmolumentoService {
	
	@Autowired
	private ListatagemEmolumentoRespository listagemEmolumentoRepository;
	@Autowired
	private DataService dataServico;
	@Autowired
	private FicheiroService ficheiroService;
	@Autowired 
	private GerarRelatorioExcel excel;
	
	public List<ListagemEmolumento> listarPorDataDePara(String de, String para) throws ParseException {
		DataFiltro data =  this.dataServico.stringParaData(de, para);
		return this.listagemEmolumentoRepository.findByDataLiquidacaoBetween(data.getDe(), data.getPara());
	}
	
	public byte[] ficheiro(RelatorioFinanceiro relatorioFinanceiro) throws IOException {
		Integer anoLectivo = relatorioFinanceiro.getAnoLectivo();
		Integer mesLiquidacao = relatorioFinanceiro.getMesInteiro();
		List<ListagemEmolumento> mapaEmolumentos = this.listagemEmolumentoRepository.findByAnoLectivoAndMesLiquidacaoInteiro(anoLectivo, mesLiquidacao);
		ByteArrayInputStream byteArrayInputStream = excel.mapaEmolumentos(mapaEmolumentos);
		return this.ficheiroService.ByteArrayParaByte(byteArrayInputStream);
	}

}
