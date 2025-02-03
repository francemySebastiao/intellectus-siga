package ao.co.intellectus.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.servico.GerarNumeroDocumento;
import ao.co.intellectus.util.Utils;

@Service
public class GeradorNumeroDocumentos implements GerarNumeroDocumento {

	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepo;
	@Autowired
	private AnoLectivoRepository anoLectivoRepo;

	@Override
	public String geracaoNumero() {

		 Integer totalGuia = this.guiaCandidaturaRepo.TOTAL_GUIA_CANDIDATUR_PLUS();
		 AnoLectivo anoLectivoActual = anoLectivoRepo.buscarPorEstado();
		 String anoLectivo = String.valueOf(anoLectivoActual.getAnoLectivo());
		 String ano = anoLectivo.substring(2,4);
		 return Utils.geracaoNumeroProforma(Integer.parseInt(ano), totalGuia);
	}

	@Transactional
	@Override
	public String gerarNumeroFacturaProforma(String definitivo, Integer lectivo, Long proximoNumero) {
		
		if (proximoNumero >= 1 && proximoNumero <= 999999)
			definitivo = "PP UGS" + lectivo + "/" + proximoNumero;
		return definitivo;
	}

	@Transactional
	@Override
	public String gerarNumeroFacturaRecibo(String definitivo, Integer lectivo, Long proximoNumero) {
		
		if (proximoNumero >= 1 && proximoNumero <= 999999)
			definitivo = "FR IGSL" + lectivo + "/" + proximoNumero;
		return definitivo;
	}

	@Transactional
	@Override
	public String gerarNumeroNotaCredito(String definitivo, Integer lectivo, Long proximoNumero) {
		
		if (proximoNumero >= 1 && proximoNumero <= 999999)
			definitivo = "NC IGSL" + lectivo + "/" + proximoNumero;
		return definitivo;
	}

	@Transactional
	@Override
	public String gerarNumeroFactura(String definitivo, Integer lectivo, Long proximoNumero) {
		
		if (proximoNumero >= 1 && proximoNumero <= 999999)
			definitivo = "FT IGSL" + lectivo + "/" + proximoNumero;
		return definitivo;
	}

	@Transactional
	@Override
	public String gerarNumeroRecibo(String definitivo, Integer lectivo, Long proximoNumero) {
		
		if (proximoNumero >= 1 && proximoNumero <= 999999)
			definitivo = "RG IGSL" + lectivo + "/" + proximoNumero;
		return definitivo;
	}
}
