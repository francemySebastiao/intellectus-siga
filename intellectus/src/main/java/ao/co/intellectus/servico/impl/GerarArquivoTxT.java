package ao.co.intellectus.servico.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ao.co.intellectus.model.Factura;
import ao.co.intellectus.model.FacturaDetalhe;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.GuiaCandidaturaHistorico;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.HistoricoCreditoEmpresa;
import ao.co.intellectus.model.NotaCredito;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.repository.FacturaDetalheRepository;
import ao.co.intellectus.repository.FacturaRepository;
import ao.co.intellectus.repository.GuiaCandidaturaHistoricoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoHistoricoRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoCreditoEmpresaRepository;
import ao.co.intellectus.repository.NotaCreditoRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.util.FormataData;

@Service
public class GerarArquivoTxT implements GeradorDeArquivo {

	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepo;
	@Autowired
	private GuiaPagamentoRepository guiaPagamentoRepo;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private NotaCreditoRepository notaCreditoRepo;
	@Autowired
	private FacturaRepository facturaRepo;
	@Autowired
	private FacturaDetalheRepository facturaDetalheRepo;
	@Autowired
	private HistoricoCreditoEmpresaRepository historicoCreditoRepo;
	@Autowired
	private GuiaPagamentoHistoricoRepository guiaPagHistRepo;
	@Autowired
	private GuiaCandidaturaHistoricoRepository guiaCandHisRepo;

	private String privateKey = "ChavePrivada.pem";
	private String nomeArquivo = "Registo.txt";
	private String signedOutputFile = "Registo1.sha1";
	private String base64OutputFile = "Registo1.b64";

	@Transactional
	@Override
	public void gerarFileProformaCandidato(GuiaCandidatura guia) {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String dataEmissao = formato.format(guia.getDataEmicao());
		
		FormataData forma = new FormataData();
		Integer ano = forma.anoLectivo();

		try {

			File arquivo = new File(nomeArquivo);
			FileWriter fileWriter = new FileWriter(arquivo, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
			Long ultimoNumero = numeroGeradoFP.getUltimoNumero();
			Long proximoNumero = numeroGeradoFP.getProximoNumero();

			String proformaPesquisa = "";
			GuiaCandidatura proforma;
			Guia prof;

			String pp = "PP IGSL"+ ano +"/" + ultimoNumero;
			proforma = guiaCandidaturaRepo.buscarProforma(pp);
			prof = guiaPagamentoRepo.findProforma(pp);
			List<GuiaCandidaturaHistorico> guiaCandHist = guiaCandHisRepo.buscarIdGuia(guia.getId());

			double valorBruto = 0.0;
			for (GuiaCandidaturaHistorico PagCand : guiaCandHist) {

				valorBruto += PagCand.getValor();
			}

			if (proforma == null && prof == null) {

				if (proximoNumero == 1) {

					bufferedWriter.write(dataEmissao + ";" + guia.getDataSistema() + ";"
							+ guia.getNumeroFacturaProforma() + ";" + valorBruto + ";");
					bufferedWriter.close();

					executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
							nomeArquivo));
					executeCommand(
							String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

					String base64Data = readFileAsString(base64OutputFile);
					guia.setHashProforma(base64Data);
					guiaCandidaturaRepo.save(guia);
				}
			}else if (proforma != null) {

				proformaPesquisa = proforma.getHashProforma();
			} else if (prof != null) {
				proformaPesquisa = prof.getHashProforma();
			} else {
				bufferedWriter.close();
				throw new RuntimeException("Proforma-Candidato não encontrada para o número gerado.");
			}
			
			bufferedWriter.write(dataEmissao + ";" + guia.getDataSistema() + ";" + guia.getNumeroFacturaProforma() + ";"
					+ valorBruto + ";" + proformaPesquisa);
			bufferedWriter.close();

			executeCommand(
					String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile, nomeArquivo));
			executeCommand(String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

			String base64Data = readFileAsString(base64OutputFile);
			guia.setHashProforma(base64Data);
			guiaCandidaturaRepo.save(guia);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void gerarFileProformaAluno(Guia guia) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String dataEmissao = formato.format(guia.getDataEmicao());
		
		FormataData forma = new FormataData();
		Integer ano = forma.anoLectivo();
		
		System.err.println("Bleza");

		try {

			File arquivo = new File(nomeArquivo);
			FileWriter fileWriter = new FileWriter(arquivo, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
			Long ultimoNumero = numeroGeradoFP.getUltimoNumero();
			Long proximoNumero = numeroGeradoFP.getProximoNumero();

			String proformaPesquisa = "";
			GuiaCandidatura proforma;
			Guia prof;

			System.out.println("Ultimo número " + ultimoNumero);
			String pp = "PP IGSL" + ano + "/" + ultimoNumero;
			System.out.println("AQui estamos todos " + pp);
			proforma = guiaCandidaturaRepo.buscarProforma(pp);
			prof = guiaPagamentoRepo.findProforma(pp);
			List<GuiaPagamentoHistorico> guiaPagHist = guiaPagHistRepo.buscarIdGuia(guia.getId());
			
			System.out.println("Sextou");

			double valorBruto = 0.0;
			for (GuiaPagamentoHistorico PagHist : guiaPagHist) {

				valorBruto += PagHist.getValorTotal();
			}

			if (proforma == null && prof == null) {

				if (proximoNumero == 1) {

					bufferedWriter.write(dataEmissao + ";" + guia.getDataSistema() + ";"
							+ guia.getNumeroFacturaProforma() + ";" + valorBruto + ";");
					bufferedWriter.close();

					executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
							nomeArquivo));
					executeCommand(
							String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

					String base64Data = readFileAsString(base64OutputFile);
					guia.setHashProforma(base64Data);
					guiaPagamentoRepo.save(guia);
				}
			} else if (proforma != null) {

				proformaPesquisa = proforma.getHashProforma();
			} else if (prof != null) {
				proformaPesquisa = prof.getHashProforma();
			} else {
				bufferedWriter.close();
				throw new RuntimeException("Proforma-Aluno não encontrada para o número gerado.");
			}

			bufferedWriter.write(dataEmissao + ";" + guia.getDataSistema() + ";" + guia.getNumeroFacturaProforma() + ";"
					+ valorBruto + ";" + proformaPesquisa);
			bufferedWriter.close();

			executeCommand(
					String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile, nomeArquivo));
			executeCommand(String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

			String base64Data = readFileAsString(base64OutputFile);
			guia.setHashProforma(base64Data);
			guiaPagamentoRepo.save(guia);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void gerarFileFacturaReciboCandidato(GuiaCandidatura guia) {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String dataEmissao = formato.format(guia.getDataEmissaoFr());
		
		FormataData forma = new FormataData();
		Integer ano = forma.anoLectivo();

		try {

			File arquivo = new File(nomeArquivo);
			FileWriter fileWriter = new FileWriter(arquivo, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(7);
			Long ultimoNumero = numeroGerado.getUltimoNumero();
			Long proximoNumero = numeroGerado.getProximoNumero();

			String facturaReciboPesquisa = "";
			GuiaCandidatura facturaRecibo;
			Guia factRecibo;

			String fr = "FR IGSL" + ano + "/" + ultimoNumero;
			facturaRecibo = guiaCandidaturaRepo.buscarRecibo(fr);
			factRecibo = guiaPagamentoRepo.findFacturaRecibo(fr);
			List<GuiaCandidaturaHistorico> guiaCandHist = guiaCandHisRepo.buscarIdGuia(guia.getId());

			double valorBruto = 0.0;
			for (GuiaCandidaturaHistorico PagCand : guiaCandHist) {

				valorBruto += PagCand.getValor();
			}

			if (facturaRecibo == null && factRecibo == null ) {

				if (proximoNumero == 1) {

					bufferedWriter.write(dataEmissao + ";" + guia.getDataSistemaFr() + ";"
							+ guia.getNumeroFacturaRecibo() + ";" + valorBruto + ";");
					bufferedWriter.close();

					executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
							nomeArquivo));
					executeCommand(
							String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

					String base64Data = readFileAsString(base64OutputFile);
					guia.setHashFacturaRecibo(base64Data);
					guiaCandidaturaRepo.save(guia);
				}
			} else {

				if (facturaRecibo != null) {

					facturaReciboPesquisa = facturaRecibo.getHashFacturaRecibo();

				} else if (factRecibo != null) {

					facturaReciboPesquisa = factRecibo.getHashFacturaRecibo();

				} else {
					bufferedWriter.close();
					throw new RuntimeException("FR-Candidato não encontrada para o número gerado.");
				}

				bufferedWriter.write(dataEmissao + ";" + guia.getDataSistemaFr() + ";" + guia.getNumeroFacturaRecibo()
						+ ";" + valorBruto + ";" + facturaReciboPesquisa);
				bufferedWriter.close();

				executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
						nomeArquivo));
				executeCommand(
						String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

				String base64Data = readFileAsString(base64OutputFile);
				guia.setHashFacturaRecibo(base64Data);
				guiaCandidaturaRepo.save(guia);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Transactional
	@Override
	public void gerarFileFacturaReciboAluno(Guia guia) {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String dataEmissao = formato.format(guia.getDataEmissaoFr());
		
		FormataData forma = new FormataData();
		Integer ano = forma.anoLectivo();

		try {

			File arquivo = new File(nomeArquivo);
			FileWriter fileWriter = new FileWriter(arquivo, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(7);
			Long ultimoNumero = numeroGerado.getUltimoNumero();
			Long proximoNumero = numeroGerado.getProximoNumero();

			String facturaReciboPesquisa = "";
			GuiaCandidatura facturaRecibo;
			Guia factRecibo;
			HistoricoCreditoEmpresa historicoCredito;

			String fr = "FR  IGSL" + ano + "/" + ultimoNumero;
			facturaRecibo = guiaCandidaturaRepo.buscarRecibo(fr);
			factRecibo = guiaPagamentoRepo.findFacturaRecibo(fr);
			historicoCredito = historicoCreditoRepo.findFacturaRecibo(fr);
			List<GuiaPagamentoHistorico> guiaPagHist = guiaPagHistRepo.buscarIdGuia(guia.getId());

			double valorBruto = 0.0;
			for (GuiaPagamentoHistorico PagHist : guiaPagHist) {

				valorBruto += PagHist.getValorTotal();
			}

			if (facturaRecibo == null && factRecibo == null && historicoCredito == null) {

				if (proximoNumero == 1) {

					bufferedWriter.write(dataEmissao + ";" + guia.getDataSistemaFr() + ";"
							+ guia.getNumeroFacturaRecibo() + ";" + FormataData.formatarValor(valorBruto) + ";");
					bufferedWriter.close();

					executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
							nomeArquivo));
					executeCommand(
							String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

					String base64Data = readFileAsString(base64OutputFile);
					guia.setHashFacturaRecibo(base64Data);
					guiaPagamentoRepo.save(guia);
				}
			} else {

				if (facturaRecibo != null) {

					facturaReciboPesquisa = facturaRecibo.getHashFacturaRecibo();

				} else if (factRecibo != null) {

					facturaReciboPesquisa = factRecibo.getHashFacturaRecibo();

				} else if (historicoCredito != null) {

					facturaReciboPesquisa = historicoCredito.getHashFacturaRecibo();

				} else {
					bufferedWriter.close();
					throw new RuntimeException("FR-Aluno não encontrada para o número gerado.");
				}

				bufferedWriter.write(dataEmissao + ";" + guia.getDataSistemaFr() + ";" + guia.getNumeroFacturaRecibo()
						+ ";" + FormataData.formatarValor(valorBruto) + ";" + facturaReciboPesquisa);
				bufferedWriter.close();

				executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
						nomeArquivo));
				executeCommand(
						String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

				String base64Data = readFileAsString(base64OutputFile);
				guia.setHashFacturaRecibo(base64Data);
				guiaPagamentoRepo.save(guia);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Transactional
	@Override
	public void gerarFileNotaCredito(NotaCredito nota) {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String dataEmissao = formato.format(nota.getDataEmissao());
		
		FormataData forma = new FormataData();
		Integer ano = forma.anoLectivo();

		try {

			File arquivo = new File(nomeArquivo);
			FileWriter fileWriter = new FileWriter(arquivo, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(10);
			Long ultimoNumero = numeroGerado.getUltimoNumero();
			Long proximoNumero = numeroGerado.getProximoNumero();

			String nc = "NC IGSL"+ ano +"/" + ultimoNumero;
			NotaCredito notaCredito = notaCreditoRepo.buscarNumeroNotaCredito(nc);
			
			if(notaCredito == null) {
				
				if (proximoNumero == 1) {

					bufferedWriter.write(dataEmissao + ";" + nota.getDataSistema() + ";" + nota.getNumeroNotaCredito() + ";"
							+ nota.getValor() + ";");
					bufferedWriter.close();

					executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
							nomeArquivo));
					executeCommand(
							String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

					String base64Data = readFileAsString(base64OutputFile);
					nota.setHash(base64Data);
					notaCreditoRepo.save(nota);

				}
			}else {

				bufferedWriter.write(dataEmissao + ";" + nota.getDataSistema() + ";" + nota.getNumeroNotaCredito() + ";"
						+ nota.getValor() + ";" + notaCredito.getHash());
				bufferedWriter.close();

				executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
						nomeArquivo));
				executeCommand(
						String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

				String base64Data = readFileAsString(base64OutputFile);
				nota.setHash(base64Data);
				notaCreditoRepo.save(nota);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void gerarFileFactura(Factura factura) {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String dataEmissao = formato.format(factura.getDataEmissao());
		
		FormataData forma = new FormataData();
		Integer ano = forma.anoLectivo();

		try {

			File arquivo = new File(nomeArquivo);
			FileWriter fileWriter = new FileWriter(arquivo, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(8);
			Long ultimoNumero = numeroGerado.getUltimoNumero();
			Long proximoNumero = numeroGerado.getProximoNumero();

			String ft = "FT IGSL"+ ano +"/" + ultimoNumero;
			Factura facturaPesquisca = facturaRepo.buscarNumeroFactura(ft);
			List<FacturaDetalhe> factDetalhe = facturaDetalheRepo.buscarIdFactura(factura.getId());

			double valorBruto = 0.0;
			for (FacturaDetalhe facturaDetalhe : factDetalhe) {

				valorBruto += facturaDetalhe.getValorTotal();
			}
			
			if(facturaPesquisca == null) {
				
				if (proximoNumero == 1) {

					bufferedWriter.write(dataEmissao + ";" + factura.getDataSistema() + ";" + factura.getNuneroFactura()
							+ ";" + valorBruto + ";");
					bufferedWriter.close();

					executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
							nomeArquivo));
					executeCommand(
							String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

					String base64Data = readFileAsString(base64OutputFile);
					factura.setHash(base64Data);
					facturaRepo.save(factura);

				}
			}else {

				bufferedWriter.write(dataEmissao + ";" + factura.getDataSistema() + ";" + factura.getNuneroFactura()
						+ ";" + valorBruto + ";" + facturaPesquisca.getHash());
				bufferedWriter.close();

				executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
						nomeArquivo));
				executeCommand(
						String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

				String base64Data = readFileAsString(base64OutputFile);
				factura.setHash(base64Data);
				facturaRepo.save(factura);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void gerarFileFacturaReciboCreditoAluno(HistoricoCreditoEmpresa credito) {

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String dataEmissao = formato.format(credito.getDataEmissaoFr());
		
		FormataData forma = new FormataData();
		Integer ano = forma.anoLectivo();

		try {

			File arquivo = new File(nomeArquivo);
			FileWriter fileWriter = new FileWriter(arquivo, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(7);
			Long ultimoNumero = numeroGerado.getUltimoNumero();
			Long proximoNumero = numeroGerado.getProximoNumero();


			String facturaReciboPesquisa = "";
			GuiaCandidatura facturaRecibo;
			Guia factRecibo;
			HistoricoCreditoEmpresa historicoCredito;

			String fr = "FR IGSL"+ ano +"/" + ultimoNumero;
			facturaRecibo = guiaCandidaturaRepo.buscarRecibo(fr);
			factRecibo = guiaPagamentoRepo.findFacturaRecibo(fr);
			historicoCredito = historicoCreditoRepo.findFacturaRecibo(fr);
			
			if(facturaRecibo == null && factRecibo == null && historicoCredito == null) {
				
				if (proximoNumero == 1) {

					bufferedWriter.write(dataEmissao + ";" + credito.getDataSistemaFr() + ";"
							+ credito.getNumeroFacturaRecibo() + ";" + 0.0 + ";");
					bufferedWriter.close();

					executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
							nomeArquivo));
					executeCommand(
							String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

					String base64Data = readFileAsString(base64OutputFile);
					credito.setHashFacturaRecibo(base64Data);
					historicoCreditoRepo.save(credito);

				}
			}else {

				if (facturaRecibo != null) {

					facturaReciboPesquisa = facturaRecibo.getHashFacturaRecibo();

				} else if (factRecibo != null) {

					facturaReciboPesquisa = factRecibo.getHashFacturaRecibo();

				} else if (historicoCredito != null) {
					facturaReciboPesquisa = historicoCredito.getHashFacturaRecibo();
				} else {
					bufferedWriter.close();
					throw new RuntimeException("FR-credito não encontrada para o número gerado.");
				}

				bufferedWriter.write(dataEmissao + ";" + credito.getDataSistemaFr() + ";"
						+ credito.getNumeroFacturaRecibo() + ";" + 0.0 + ";" + facturaReciboPesquisa);
				bufferedWriter.close();

				executeCommand(String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey, signedOutputFile,
						nomeArquivo));
				executeCommand(
						String.format("openssl enc -base64 -in %s -out %s -A", signedOutputFile, base64OutputFile));

				String base64Data = readFileAsString(base64OutputFile);
				credito.setHashFacturaRecibo(base64Data);
				historicoCreditoRepo.save(credito);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Transactional
	public static void executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);

			// Ler a saída do processo
			InputStream inputStream = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Aguardar a finalização do processo
			int exitCode = process.waitFor();
			System.out.println("Comando executado. Código de saída: " + exitCode);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public static String readFileAsString(String filePath) throws FileNotFoundException {
		StringBuilder contentBuilder = new StringBuilder();
		Scanner scanner = new Scanner(new FileInputStream(filePath));

		while (scanner.hasNextLine()) {
			contentBuilder.append(scanner.nextLine());
		}
		scanner.close();
		return contentBuilder.toString();
	}
}
