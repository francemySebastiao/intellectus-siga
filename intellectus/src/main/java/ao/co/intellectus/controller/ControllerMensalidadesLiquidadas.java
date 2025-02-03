package ao.co.intellectus.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.MensalidadesLiquidadasResumo;
import ao.co.intellectus.DTO.SimulacaoFinanceira;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.MensalidadesLiquidadas;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.TipoDisciplina;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.MensalidadesLiquidadasRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.servico.cafold.GuiaService;
import ao.co.intellectus.servico.guias.GerarNumeroDeGuia;

@RestController
@RequestMapping("/mensalidadas-liquidadas")
public class ControllerMensalidadesLiquidadas {
	@Autowired
	private MensalidadesLiquidadasRepository mensalidadesLiquidadasRepository; 
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private HistoricoAlunoRepository historicoAlunoRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaPagamentoRepository;
	@Autowired
	private GuiaPagamentoRepository guiaPagamentoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private GuiaService guiaService;
	
	
	@RequestMapping(value = "/aluno/{ano}/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> matriculas(@PathVariable Integer ano,@PathVariable Integer numero) {
		ResponseCliente c = new ResponseCliente();
		//Iterable<Matricula> todas = repositoryMatricula.findAll();
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		Emolumento emolumento;
		MensalidadesLiquidadas mLiquidadas;
		EmolumentoHistorico historicoEmolumento;
		List<HistoricoAluno> historicoAluno;
	    float valorPrimeiroSemestre=0;
	    float valorSegundoSemestre=0;
	    Date dataVemcimentoGuia;
	    Date dataHoje=new Date();
	    Emolumento emolumentoPorDisciplinas;
		List<GuiaPagamentoHistorico> hisoricosGuia;
		float valorMensalidade=0;
		String mensagem="";
		
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero.toString());
		if(aluno==null) {
			c.setMensagem("Verifique o número de aluno");
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		AnoLectivo anoLectivo = this.anoLectivoRepository.findByAnoLectivo(ano);
		
		
		List<MensalidadesLiquidadas> mensalidade = this.mensalidadesLiquidadasRepository.findByNumeroDeAlunoAndAnoLectivo(numero, anoLectivo);
		
		if(mensalidade.isEmpty()) {
			Matricula matricula = this.matriculaRepository.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivo, false);
			if(matricula==null) {
				c.setMensagem("aluno não matriculado neste ano.");
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}else {
				//aqui vamos fazer o favor de relatar o mapa financeiro do aluno.
				Integer emolumentos   []= {55,59,60,61,62,63,64,65,66,67};
				Integer elDisciplinas []= {113,114,115,116,117,118,119,120,121,122};
				
				if(matricula.getTipoInscricao().getId()==1 || matricula.getTipoInscricao().getId()==2) {
					mensagem="ANO COOMPLETO BEM TESTADO....";
					for (Integer pego : emolumentos) {
						mLiquidadas         =new MensalidadesLiquidadas();
						emolumento          = this.emolumentoRepository.findOne(pego);
					    historicoEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento, aluno.getCurso(), anoLectivo);
						
	                    hisoricosGuia = this.historicoGuiaPagamentoRepository.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(emolumento, aluno, anoLectivo,false);
						
						
						if(!hisoricosGuia.isEmpty()) {
							dataVemcimentoGuia=hisoricosGuia.get(0).getGuia().getDataVencimento();
							mLiquidadas.setDataVencientoPropina(dataVemcimentoGuia);
							mLiquidadas.setMesPago(hisoricosGuia.get(0).getGuia().isLiquidada()? true:false);
							valorMensalidade=(float)hisoricosGuia.get(0).getGuia().getValor();
						}else {
							Moeda moeda = this.moedaRepository.findOne(3);
							Instituicao instituicao = this.instituicaoRepository.findOne(2);
							//GERA A GUIA CASO NÃO FOI EMITIDA DURANTE O ANO.
							gerarGuiaEmFalta(emolumento, historicoEmolumento, aluno, anoLectivo, moeda, instituicao);
							
							hisoricosGuia = this.historicoGuiaPagamentoRepository.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(emolumento, aluno, anoLectivo,false);
							 
							dataVemcimentoGuia=hisoricosGuia.get(0).getGuia().getDataVencimento();
							mLiquidadas.setDataVencientoPropina(dataVemcimentoGuia);
							mLiquidadas.setMesPago(hisoricosGuia.get(0).getGuia().isLiquidada()? true:false);
							valorMensalidade=(float)hisoricosGuia.get(0).getGuia().getValor();
						}
					    
						gerarMultaAnoCompleto(formatar, mLiquidadas,valorMensalidade,dataHoje, hisoricosGuia, pego);
						
						
						
					    mLiquidadas.setAluno(aluno);
						mLiquidadas.setAnoLectivo(anoLectivo);
						mLiquidadas.setMes(emolumento.getEmolumento());
						mLiquidadas.setNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
						mLiquidadas.setEmolumento(emolumento);
						mLiquidadas.setPropina(historicoEmolumento.getValor());
						

						mLiquidadas.setPropina(valorMensalidade);
						
						this.mensalidadesLiquidadasRepository.save(mLiquidadas);						
					}	
					mensalidade = this.mensalidadesLiquidadasRepository.findByNumeroDeAlunoAndAnoLectivo(numero, anoLectivo);
					float totalMes=0;
					for (MensalidadesLiquidadas ms : mensalidade) {
						MensalidadesLiquidadas mesalidadeTotal = this.mensalidadesLiquidadasRepository.findOne(ms.getId());
						totalMes=ms.getAbril()+ms.getMaio()+ms.getJunho()+ms.getJulho()+ms.getAgosto()+ms.getSetembro()+ms.getOutubro()+ms.getNovembro()+ms.getDezembro();
						mesalidadeTotal.setTotalMes(totalMes);
						this.mensalidadesLiquidadasRepository.save(mesalidadeTotal);
					}
					
				}
				
				
				if(matricula.getTipoInscricao().getId()==4) {
					emolumentoPorDisciplinas = this.emolumentoRepository.findOne(113);
					
					historicoEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumentoPorDisciplinas, aluno.getCurso(), anoLectivo);
					historicoAluno = this.historicoAlunoRepository.findByMatricula(matricula);
					for (HistoricoAluno hist : historicoAluno) {
						if(hist.getDisciplina().getTipo()!=TipoDisciplina.SEGUNDO_SEMESTRE) {
							valorPrimeiroSemestre+=historicoEmolumento.getValor();
						}
						
						if(hist.getDisciplina().getTipo()!=TipoDisciplina.PRIMEIRO_SEMESTRE) {
							valorSegundoSemestre+=historicoEmolumento.getValor();
						}
					}
					//startCal.getTimeInMillis() > endCal.getTimeInMillis()
					for (Integer pego : elDisciplinas) {
						mLiquidadas         =new MensalidadesLiquidadas();
						emolumento          = this.emolumentoRepository.findOne(pego);
						hisoricosGuia = this.historicoGuiaPagamentoRepository.findByEmolumentoAndAlunoAndAnoLectivoAndGuiaAnulada(emolumento, aluno, anoLectivo,false);
						
						
						gerarMultaPorDisciplina(formatar, mLiquidadas, valorPrimeiroSemestre, valorSegundoSemestre,dataHoje, hisoricosGuia, pego);
						
						
					    historicoEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento, aluno.getCurso(), anoLectivo);
						mLiquidadas.setAluno(aluno);
						mLiquidadas.setAnoLectivo(anoLectivo);
						mLiquidadas.setMes(emolumento.getEmolumento());
						mLiquidadas.setNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
						mLiquidadas.setEmolumento(emolumento);
						
						
						mLiquidadas.setPropina(pego<=117 ? valorPrimeiroSemestre:valorSegundoSemestre);
						this.mensalidadesLiquidadasRepository.save(mLiquidadas);						
					}	
					float totalMes=0;
					mensalidade = this.mensalidadesLiquidadasRepository.findByNumeroDeAlunoAndAnoLectivo(numero, anoLectivo);
					for (MensalidadesLiquidadas ms : mensalidade) {
						MensalidadesLiquidadas mesalidadeTotal = this.mensalidadesLiquidadasRepository.findOne(ms.getId());
						totalMes=ms.getAbril()+ms.getMaio()+ms.getJunho()+ms.getJulho()+ms.getAgosto()+ms.getSetembro()+ms.getOutubro()+ms.getNovembro()+ms.getDezembro();
						mesalidadeTotal.setTotalMes(totalMes);
						this.mensalidadesLiquidadasRepository.save(mesalidadeTotal);
					}
				}
			}
		} 
		
		mensalidade=null;
		mensalidade = this.mensalidadesLiquidadasRepository.findByNumeroDeAlunoAndAnoLectivo(numero, anoLectivo);
		
		SimulacaoFinanceira simulacao=new SimulacaoFinanceira();
		
		simulacao.setNome(aluno.getNome());
		simulacao.setCurso(aluno.getCurso().getPlano());
		simulacao.setGrau(aluno.getCurso().getGrau().getDescricao());
		simulacao.setNumero(Integer.parseInt(aluno.getNumeroDeAluno()));
		simulacao.setContencioso(aluno.isContencioso());
		
		MensalidadesLiquidadasResumo mensal;
		List<MensalidadesLiquidadasResumo> mensals=new ArrayList<MensalidadesLiquidadasResumo>();
		for (MensalidadesLiquidadas ms : mensalidade) {
			mensal=new MensalidadesLiquidadasResumo();
			
			BeanUtils.copyProperties(ms, mensal);
			 
			mensals.add(mensal);
		}
		
		simulacao.setMensalidadeLiquidadas(mensals);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(simulacao);
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}


	private void gerarGuiaEmFalta(Emolumento emolumento, EmolumentoHistorico historicoEmolumento, Aluno aluno,
			AnoLectivo anoLectivo, Moeda moeda, Instituicao instituicao) {
		Guia guia = new Guia();
		guia.setAluno(aluno);
		guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
		guia.setAutomaticamente(true);
		guia.setDataEmicao(new Date());
		guia.setLiquidada(false);
		//guia.setUsuarioEmitiu(usuario);
		guia.setMoeda(moeda);
		guia.setInstituicao(instituicao);
		// guia.setValor(emolumento.getValor());
		// guia.setEmolumento(emolumento);
		guia.setAnoLectivo(anoLectivo);
		guia.setValor(historicoEmolumento.getValor());
		guia.setDataVencimento(new Date());
		guia.setUltimaModificacao(new Date());
		this.guiaPagamentoRepository.save(guia);

		String definitivo = "";
		Integer lectivo = anoLectivo.getAnoLectivo();
		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);
		Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
		// metódo gerar numero de guia chamado
		// definitivo = gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
		definitivo = GerarNumeroDeGuia.gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
		Guia guiaExistente = this.guiaPagamentoRepository.findByNumeroGuia(definitivo);
		if (guiaExistente != null) {
			do {
				proximoNumeroInteiro++;
				// metódo gerar numero de guia chamado
				definitivo = GerarNumeroDeGuia.gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
				guiaExistente = this.guiaPagamentoRepository.findByNumeroGuia(definitivo);
			} while (guiaExistente != null);
		}
		// setar o valor da guia
		guia.setNumeroGuia(definitivo);

		// atualizador os dados da ultima guia e a proxima guia
		numeroGerado.setUltimoNumero(proximoNumeroInteiro);
		numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
		this.numeroGeradoRepository.save(numeroGerado);

		// final geração do número da guia
		guia.setUltimaModificacao(new Date());
		Guia gSalva = this.guiaPagamentoRepository.save(guia);
		GuiaPagamentoHistorico gHistorico = new GuiaPagamentoHistorico();

		gHistorico.setAluno(aluno);
		gHistorico.setAnoLectivo(anoLectivo);
		gHistorico.setEmolumento(emolumento);
		gHistorico.setGuia(gSalva);
		gHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
		gHistorico.setValor(historicoEmolumento.getValor());

		this.historicoGuiaPagamentoRepository.save(gHistorico);
		this.guiaService.gerarHistoricoAudit(gHistorico);
	}


	private void gerarMultaPorDisciplina(SimpleDateFormat formatar, MensalidadesLiquidadas mLiquidadas,
			float valorPrimeiroSemestre, float valorSegundoSemestre, Date dataHoje,
			List<GuiaPagamentoHistorico> hisoricosGuia, Integer pego) {
		Date dataVemcimentoGuia;
		float valorMulta1;
		float valorMulta2;
		String fDataVemcimentoGuia;
		String fdataHoje;
		if(!hisoricosGuia.isEmpty()) {
			dataVemcimentoGuia=hisoricosGuia.get(0).getGuia().getDataVencimento();
			mLiquidadas.setDataVencientoPropina(dataVemcimentoGuia);
			mLiquidadas.setMesPago(hisoricosGuia.get(0).getGuia().isLiquidada()? true:false);
			
			if(dataVemcimentoGuia.getTime()<dataHoje.getTime()) {
				fDataVemcimentoGuia = formatar.format(dataVemcimentoGuia);
				fdataHoje           = formatar.format(dataHoje);
				
				fDataVemcimentoGuia.substring(0,2);
				fdataHoje.substring(0,2);
				
				//Integer.parseInt(fDataVemcimentoGuia.substring(0,2))>Integer.parseInt(fDataVemcimentoGuia.substring(0,2))
				if(Integer.parseInt(fDataVemcimentoGuia.substring(6))<Integer.parseInt(fdataHoje.substring(6))) {
					//{113,114,115,116,117,118,119,120,121,122}
					valorMulta1=valorPrimeiroSemestre/5;
					valorMulta2=valorSegundoSemestre /5;
					
					if(pego==113) {
						mLiquidadas.setMarco(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setAbril(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setMaio(    pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setJunho(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setJulho(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setAgosto(  pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setSetembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setOutubro( pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setNovembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setDezembro(pego<=117 ? valorMulta1:valorMulta2);
					}
					
					if(pego==114) {
						mLiquidadas.setAbril(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setMaio(    pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setJunho(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setJulho(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setAgosto(  pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setSetembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setOutubro( pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setNovembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setDezembro(pego<=117 ? valorMulta1:valorMulta2);
					}
					
					if(pego==115) {
						mLiquidadas.setMaio(    pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setJunho(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setJulho(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setAgosto(  pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setSetembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setOutubro( pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setNovembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setDezembro(pego<=117 ? valorMulta1:valorMulta2);
					}
					
					if(pego==116) {
						mLiquidadas.setJunho(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setJulho(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setAgosto(  pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setSetembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setOutubro( pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setNovembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setDezembro(pego<=117 ? valorMulta1:valorMulta2);
					}
					
					if(pego==117) {
						mLiquidadas.setJulho(   pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setAgosto(  pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setSetembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setOutubro( pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setNovembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setDezembro(pego<=117 ? valorMulta1:valorMulta2);
					}
					if(pego==118) {
						mLiquidadas.setAgosto(  pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setSetembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setOutubro( pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setNovembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setDezembro(pego<=117 ? valorMulta1:valorMulta2);
					}
					if(pego==119) {
						mLiquidadas.setSetembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setOutubro( pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setNovembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setDezembro(pego<=117 ? valorMulta1:valorMulta2);
					}
					if(pego==120) {
						mLiquidadas.setOutubro( pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setNovembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setDezembro(pego<=117 ? valorMulta1:valorMulta2);
					}
					if(pego==121) {
						mLiquidadas.setNovembro(pego<=117 ? valorMulta1:valorMulta2);
						mLiquidadas.setDezembro(pego<=117 ? valorMulta1:valorMulta2);
					}
					if(pego==122) {
						mLiquidadas.setDezembro(pego<=117 ? valorMulta1:valorMulta2);
					}
				}
			}
		}//FINALIZEI
	}
	
	private void gerarMultaAnoCompleto(SimpleDateFormat formatar, MensalidadesLiquidadas mLiquidadas,float valorMensalidade, Date dataHoje,
			List<GuiaPagamentoHistorico> hisoricosGuia, Integer pego) {
		Date dataVemcimentoGuia;
		float valorMulta;
		String fDataVemcimentoGuia;
		String fdataHoje;
		if(!hisoricosGuia.isEmpty()) {
			dataVemcimentoGuia=hisoricosGuia.get(0).getGuia().getDataVencimento();
			mLiquidadas.setDataVencientoPropina(dataVemcimentoGuia);
			mLiquidadas.setMesPago(hisoricosGuia.get(0).getGuia().isLiquidada()? true:false);
			
			if(dataVemcimentoGuia.getTime()<dataHoje.getTime()) {
				fDataVemcimentoGuia = formatar.format(dataVemcimentoGuia);
				fdataHoje           = formatar.format(dataHoje);
				
				fDataVemcimentoGuia.substring(0,2);
				fdataHoje.substring(0,2);
				
				//Integer.parseInt(fDataVemcimentoGuia.substring(0,2))>Integer.parseInt(fDataVemcimentoGuia.substring(0,2))
				if(Integer.parseInt(fDataVemcimentoGuia.substring(6))<Integer.parseInt(fdataHoje.substring(6))) {
					//{113,114,115,116,117,118,119,120,121,122}
					valorMulta=valorMensalidade/5;
				
					//{55,59,60,61,62,63,64,65,66,67};
					if(pego==55) {
						mLiquidadas.setMarco(valorMulta);
						mLiquidadas.setAbril(valorMulta);
						mLiquidadas.setMaio(valorMulta);
						mLiquidadas.setJunho(valorMulta);
						mLiquidadas.setJulho(valorMulta);
						mLiquidadas.setAgosto(valorMulta);
						mLiquidadas.setSetembro(valorMulta);
						mLiquidadas.setOutubro(valorMulta);
						mLiquidadas.setNovembro(valorMulta);
						mLiquidadas.setDezembro(valorMulta);
					}
					
					if(pego==59) {
						mLiquidadas.setAbril(valorMulta);
						mLiquidadas.setMaio(valorMulta);
						mLiquidadas.setJunho(valorMulta);
						mLiquidadas.setJulho(valorMulta);
						mLiquidadas.setAgosto(valorMulta);
						mLiquidadas.setSetembro(valorMulta);
						mLiquidadas.setOutubro(valorMulta);
						mLiquidadas.setNovembro(valorMulta);
						mLiquidadas.setDezembro(valorMulta);
					}
					if(pego==60) {
						mLiquidadas.setMaio(valorMulta);
						mLiquidadas.setJunho(valorMulta);
						mLiquidadas.setJulho(valorMulta);
						mLiquidadas.setAgosto(valorMulta);
						mLiquidadas.setSetembro(valorMulta);
						mLiquidadas.setOutubro(valorMulta);
						mLiquidadas.setNovembro(valorMulta);
						mLiquidadas.setDezembro(valorMulta);
					}
					
					if(pego==61) {
						mLiquidadas.setJunho(valorMulta);
						mLiquidadas.setJulho(valorMulta);
						mLiquidadas.setAgosto(valorMulta);
						mLiquidadas.setSetembro(valorMulta);
						mLiquidadas.setOutubro(valorMulta);
						mLiquidadas.setNovembro(valorMulta);
						mLiquidadas.setDezembro(valorMulta);
					}
					
					if(pego==62) {
						mLiquidadas.setJulho(valorMulta);
						mLiquidadas.setAgosto(valorMulta);
						mLiquidadas.setSetembro(valorMulta);
						mLiquidadas.setOutubro(valorMulta);
						mLiquidadas.setNovembro(valorMulta);
						mLiquidadas.setDezembro(valorMulta);
					}
					if(pego==63) {
						mLiquidadas.setAgosto(valorMulta);
						mLiquidadas.setSetembro(valorMulta);
						mLiquidadas.setOutubro(valorMulta);
						mLiquidadas.setNovembro(valorMulta);
						mLiquidadas.setDezembro(valorMulta);
					}
					if(pego==64) {
						mLiquidadas.setSetembro(valorMulta);
						mLiquidadas.setOutubro(valorMulta);
						mLiquidadas.setNovembro(valorMulta);
						mLiquidadas.setDezembro(valorMulta);
					}
					if(pego==65) {
						mLiquidadas.setOutubro(valorMulta);
						mLiquidadas.setNovembro(valorMulta);
						mLiquidadas.setDezembro(valorMulta);
					}
					if(pego==66) {
						mLiquidadas.setNovembro(valorMulta);
						mLiquidadas.setDezembro(valorMulta);
					}
					if(pego==67) {
						mLiquidadas.setDezembro(valorMulta);
					}
				}
			}
		}//FINALIZEI
	}
}
