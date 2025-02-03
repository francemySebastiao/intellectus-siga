package ao.co.intellectus.servico.cafold;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.DataFiltro;
import ao.co.intellectus.model.ListagemBancaria;
import ao.co.intellectus.model.RelatorioFinanceiro;
import ao.co.intellectus.repository.ListagemBancariaRepository;

@Service
public class ListagemBancoService {
	
	@Autowired
	private ListagemBancariaRepository listagemBancariaRepository;
	@Autowired
	private DataService dataServico;
	@Autowired 
	private GerarRelatorioExcel excel;
	@Autowired
	private FicheiroService ficheiroService;
	
	public List<ListagemBancaria> listarPorDataDePara(String de, String para) throws ParseException {
		DataFiltro data =  this.dataServico.stringParaData(de, para);
		return this.listagemBancariaRepository.findByDataLiquidacaoBetween(data.getDe(), data.getPara());
	}
	
	public byte[] ficheiro(RelatorioFinanceiro relatorioFinanceiro) throws IOException {
		Integer anoLectivo = relatorioFinanceiro.getAnoLectivo();
		Integer mesLiquidacao = relatorioFinanceiro.getMesInteiro();
		
		List<ListagemBancaria> mapaEmolumentos = this.listagemBancariaRepository.findByAnoLectivoAndMesLiquidacaoInteiro(anoLectivo, mesLiquidacao);
		
		ByteArrayInputStream byteArrayInputStream = excel.mapaBancos(mapaEmolumentos);
		
		return this.ficheiroService.ByteArrayParaByte(byteArrayInputStream);
	}

}
