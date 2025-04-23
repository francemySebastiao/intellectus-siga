package ao.co.intellectus.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AuditFileDTO;
import ao.co.intellectus.DTO.BillingAddressDTO;
import ao.co.intellectus.DTO.CompanyAddressDTO;
import ao.co.intellectus.DTO.CustomerDTO;
import ao.co.intellectus.DTO.DocumentStatusDTO;
import ao.co.intellectus.DTO.DocumentTotalsDTO;
import ao.co.intellectus.DTO.HeaderDTO;
import ao.co.intellectus.DTO.InvoiceDTO;
import ao.co.intellectus.DTO.LineDTO;
import ao.co.intellectus.DTO.MasterFileDTO;
import ao.co.intellectus.DTO.OrderReferenceDTO;
import ao.co.intellectus.DTO.PaymentDTO;
import ao.co.intellectus.DTO.PaymentsDTO;
import ao.co.intellectus.DTO.ProductDTO;
import ao.co.intellectus.DTO.ReferencesDTO;
import ao.co.intellectus.DTO.SalesInvoiceDTO;
import ao.co.intellectus.DTO.SourceDocumentIdDTO;
import ao.co.intellectus.DTO.SourceDocumentsDTO;
import ao.co.intellectus.DTO.SpecialRegimesDTO;
import ao.co.intellectus.DTO.TaxDTO;
import ao.co.intellectus.DTO.TaxTableDTO;
import ao.co.intellectus.DTO.TaxTableEntryDTO;
import ao.co.intellectus.model.BillingAddress;
import ao.co.intellectus.model.CompanyAddress;
import ao.co.intellectus.model.Customer;
import ao.co.intellectus.model.DocumentStatus;
import ao.co.intellectus.model.Factura;
import ao.co.intellectus.model.FacturaDetalhe;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.GuiaCandidaturaHistorico;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.Header;
import ao.co.intellectus.model.Invoice;
import ao.co.intellectus.model.NotaCredito;
import ao.co.intellectus.model.NotaCreditoDetalhe;
import ao.co.intellectus.model.OrderReference;
import ao.co.intellectus.model.Payment;
import ao.co.intellectus.model.PaymentDocumentStatus;
import ao.co.intellectus.model.PaymentDocumentTotals;
import ao.co.intellectus.model.PaymentLine;
import ao.co.intellectus.model.Payments;
import ao.co.intellectus.model.Product;
import ao.co.intellectus.model.Reference;
import ao.co.intellectus.model.SourceDocumentId;
import ao.co.intellectus.model.SpecialRegimes;
import ao.co.intellectus.model.TaxTableEntry;
import ao.co.intellectus.model.enumeracao.TipoDoc;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.BillingAddressRepository;
import ao.co.intellectus.repository.CompanyAddressRepository;
import ao.co.intellectus.repository.CustomerRepository;
import ao.co.intellectus.repository.DocumentStatusRepository;
import ao.co.intellectus.repository.DocumentTotalsRepository;
import ao.co.intellectus.repository.FacturaDetalheRepository;
import ao.co.intellectus.repository.FacturaRepository;
import ao.co.intellectus.repository.GuiaCandidaturaHistoricoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoHistoricoRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HeaderRepository;
import ao.co.intellectus.repository.InvoiceRepository;
import ao.co.intellectus.repository.LineRepository;
import ao.co.intellectus.repository.LogSaftRepository;
import ao.co.intellectus.repository.NotaCreditoDetalheRepository;
import ao.co.intellectus.repository.NotaCreditoRepository;
import ao.co.intellectus.repository.OrderReferencesRepository;
import ao.co.intellectus.repository.PaymentDocumentStatusRepository;
import ao.co.intellectus.repository.PaymentDocumentTotalRepository;
import ao.co.intellectus.repository.PaymentLineRepository;
import ao.co.intellectus.repository.PaymentRepository;
import ao.co.intellectus.repository.PaymentsRepository;
import ao.co.intellectus.repository.ProductRepository;
import ao.co.intellectus.repository.ReferencesRepository;
import ao.co.intellectus.repository.SalesInvoiceRepository;
import ao.co.intellectus.repository.SourceDocumentIDRepository;
import ao.co.intellectus.repository.SpecialRegimeRepository;
import ao.co.intellectus.repository.TaxRepository;
import ao.co.intellectus.repository.TaxTableEntryRepository;
import ao.co.intellectus.repository.WorkDocumentRepository;
import ao.co.intellectus.repository.WorkDocumentStatusRepository;
import ao.co.intellectus.repository.WorkDocumentsRepository;
import ao.co.intellectus.repository.WorkingDocumentTotalsRepository;
import ao.co.intellectus.repository.WorkingLineRepository;
import ao.co.intellectus.repository.WorkingTaxRepository;

@RestController
@RequestMapping("/saft")
public class TesteSaf_T {

	@Autowired
	private HeaderRepository headerRepo;
	@Autowired
	private CompanyAddressRepository companyAddressRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private BillingAddressRepository billingAddressRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private TaxTableEntryRepository taxTableEntryRepo;
	@Autowired
	private SalesInvoiceRepository salesInvoiceRepo;
	@Autowired
	private InvoiceRepository invoiceRepo;
	@Autowired
	private DocumentStatusRepository documentStatusRepo;
	@Autowired
	private WorkDocumentStatusRepository workDocumentStatusRepo;
	@Autowired
	private PaymentDocumentStatusRepository paymentDocumentStatusRepo;
	@Autowired
	private SpecialRegimeRepository specialRegimeRepo;
	@Autowired
	private LineRepository lineRepo;
	@Autowired
	private ReferencesRepository referencesRepo;
	@Autowired
	private TaxRepository taxRepo;
	@Autowired
	private DocumentTotalsRepository documentTotalsRepo;
	@Autowired
	private OrderReferencesRepository orderReferencesRepo;
	@Autowired
	private WorkDocumentRepository workDocumentRepo;
	@Autowired
	private WorkDocumentsRepository workDocumenstRepo;
	@Autowired
	private PaymentRepository paymentRepo;
	@Autowired
	private SourceDocumentIDRepository sourceDocumentRepo;
	@Autowired
	private PaymentsRepository paymentsRepo;
	@Autowired
	private WorkingLineRepository workingLineRepo;
	@Autowired
	private WorkingTaxRepository workingTaxRepo;
	@Autowired
	private PaymentLineRepository paymentLineRepo;
	@Autowired
	private WorkingDocumentTotalsRepository workingDocumentsTotalRepo;
	@Autowired
	private PaymentDocumentTotalRepository paymentDocumentTotalRepo;
	@Autowired
	private LogSaftRepository logSaftRepo;
	@Autowired
	private GuiaPagamentoRepository guiaRepo;
	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepo;
	@Autowired
	private FacturaRepository facturaRepo;
	@Autowired
	private FacturaDetalheRepository facturaDetalheRepo;
	@Autowired
	private GuiaPagamentoHistoricoRepository guiaHistoricoRepo;
	@Autowired
	private GuiaCandidaturaHistoricoRepository guiaCandidaturaHistoricoRepo;
	@Autowired
	private NotaCreditoRepository notaCreditoRepo;
	@Autowired
	private NotaCreditoDetalheRepository notaCreditoDetalheRepo;

	@RequestMapping(value = "/gerarSaFT", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> gerarSAFT(String dataInicio, String dataFim) {

		ResponseCliente c = new ResponseCliente();

		/*
		 * LogSaft logSaft = new LogSaft(); LogSaft saft =
		 * logSaftRepo.buscarStartDate(dataInicio); if(saft != null) {
		 * c.setMensagem("Já foi retirado um saft deste dia"); return new
		 * ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
		 * 
		 * logSaft.setStartDate(dataInicio); logSaft.setEndDate(dataFim);
		 * logSaft.setRetirado(true);
		 * 
		 * logSaftRepo.save(logSaft);
		 */

		Date dataAtual = new Date();

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String dataCriacao = formato.format(dataAtual);

		AuditFileDTO auditFile = new AuditFileDTO();

		HeaderDTO header = new HeaderDTO();// 1

		CompanyAddressDTO companyAddress = new CompanyAddressDTO();

		MasterFileDTO masterFile = new MasterFileDTO();// 2

		CustomerDTO customerDTO = new CustomerDTO();
		List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();

		BillingAddressDTO billingAddressDTO = new BillingAddressDTO();
		List<BillingAddressDTO> billingAddressDTOList = new ArrayList<BillingAddressDTO>();

		ProductDTO productDTO = new ProductDTO();
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();

		TaxTableDTO taxTableDTO = new TaxTableDTO();
		// List<TaxTableDTO> taxTableDTOList = new ArrayList<TaxTableDTO>();

		TaxTableEntryDTO taxTableEntryDTO = new TaxTableEntryDTO();
		List<TaxTableEntryDTO> taxTableEntryDTOList = new ArrayList<TaxTableEntryDTO>();

		SourceDocumentsDTO sourceDocuments = new SourceDocumentsDTO();

		Header headerPesq = headerRepo.buscarHeader();
		CompanyAddress compAddressPesq = companyAddressRepo.buscarCompanyAddress();

		if (headerPesq == null) {
			c.setMensagem("Header nulo");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		header.setAuditFileVersion(headerPesq.getAuditFileVersion());
		header.setCompanyID(headerPesq.getCompanyID());
		header.setTaxRegistrationNumber(headerPesq.getTaxRegistrationNumber());
		header.setTaxAccountingBasis(headerPesq.getTaxAccountingBasis());
		header.setCompanyName(headerPesq.getCompanyName());

		if (compAddressPesq == null) {
			c.setMensagem("Company Address nulo");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		companyAddress.setCountry(compAddressPesq.getCountry());
		companyAddress.setCity(compAddressPesq.getCity());
		companyAddress.setAddressDetail(compAddressPesq.getAddressDetail());

		header.setCompanyAddress(companyAddress);
		header.setFiscalYear(headerPesq.getFiscalYear());
		header.setStartDate(dataInicio);
		header.setEndDate(dataFim);
		header.setCurrencyCode(headerPesq.getCurrencyCode());
		header.setDateCreated(dataCriacao);
		header.setTaxEntity(headerPesq.getTaxEntity());
		header.setProductCompanyTaxId(headerPesq.getProductCompanyTaxId());
		header.setSoftwareValidationNumber(headerPesq.getSoftwareValidationNumber());
		header.setProductId(headerPesq.getProductId());
		header.setProductVersion(headerPesq.getProductVersion());
		header.setTelephone(headerPesq.getTelephone());
		header.setFax(headerPesq.getFax());
		header.setEmail(headerPesq.getEmail());
		header.setWebSite(headerPesq.getWebSite());

		Set<String> addedCustomerIDs = new HashSet<>();
		Set<String> addedProductCode = new HashSet<>();
		Set<String> addedBilling = new HashSet<>();
		
		List<Customer> customerList = this.customerRepo.buscarCustomer(dataInicio, dataFim);
		if (!customerList.isEmpty()) {

			for (Customer cliente : customerList) {

				if (!addedCustomerIDs.contains(cliente.getCustomerID())) {
					addedCustomerIDs.add(cliente.getCustomerID());

					customerDTO = new CustomerDTO();
					customerDTO.setCustomerID(cliente.getCustomerID());
					customerDTO.setAccountId(cliente.getAccountId());
					customerDTO.setCustomerTaxID(cliente.getCustomerTaxID());
					customerDTO.setCompanyName(cliente.getCompanyName());

					List<BillingAddress> billingAddressList = billingAddressRepo
							.buscarBillingAddress(cliente.getCustomerID());
					billingAddressDTOList = new ArrayList<BillingAddressDTO>();

					BillingAddress b = billingAddressList.get(0);
					if (!billingAddressList.isEmpty()) {
		                billingAddressDTO = new BillingAddressDTO();
		                billingAddressDTO.setCountry(b.getCountry());
		                billingAddressDTO.setCity(b.getCity());
		                billingAddressDTO.setStreetName(b.getStreetName());
		                if(b.getAddressDetail() != null) {
		                	billingAddressDTO.setAddressDetail(b.getAddressDetail());
		                }
		                billingAddressDTO.setAddressDetail("DESCONHECIDO");
		                billingAddressDTOList.add(billingAddressDTO);
		            }
					customerDTO.setBillingAddress(billingAddressDTOList);
					customerDTO.setSelfBillingIndicator(cliente.getSelfBillingInficator());
					customerDTOList.add(customerDTO);
				}
			}
		}

		List<Product> product = this.productRepo.buscarProduct(dataInicio, dataFim);
		if (product.isEmpty()) {
			c.setMensagem("Product nulo");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		for (Product prod : product) {

			if (!addedProductCode.contains(prod.getProductCode())) {
				addedProductCode.add(prod.getProductCode());

				productDTO = new ProductDTO();
				productDTO.setProductCode(prod.getProductCode());
				productDTO.setProductDescription(prod.getProductDescription());
				productDTO.setProductNumberCode(prod.getProductNumberCode());
				productDTO.setProductType(prod.getProductType());

				productDTOList.add(productDTO);
			}
		}

		List<TaxTableEntry> taxTableEntry = this.taxTableEntryRepo.buscarTaxTableEntry();
		if (taxTableEntry.isEmpty()) {
			c.setMensagem("TaxTableEntry nulo");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		for (TaxTableEntry TTE : taxTableEntry) {
			taxTableEntryDTO = new TaxTableEntryDTO();
			taxTableEntryDTO.setTaxCode(TTE.getTaxCode());
			taxTableEntryDTO.setDescription(TTE.getDescription());
			taxTableEntryDTO.setTaxType(TTE.getTaxType());
			taxTableEntryDTO.setTaxPercentage(TTE.getTaxPercentage());

			taxTableEntryDTOList.add(taxTableEntryDTO);
		}

		taxTableDTO.setTaxTableEntry(taxTableEntryDTOList);

		masterFile.setCustomer(customerDTOList);
		masterFile.setProduct(productDTOList);
		masterFile.setTaxTable(taxTableDTO);

		SalesInvoiceDTO salesInvoiceDTO = new SalesInvoiceDTO();
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		List<InvoiceDTO> invoiceDTOList = new ArrayList<InvoiceDTO>();

		DocumentStatusDTO documentStatusDTO = new DocumentStatusDTO();
		List<DocumentStatusDTO> documentStatusDTOList = new ArrayList<DocumentStatusDTO>();

		DocumentStatusDTO workDocumentStatusDTO = new DocumentStatusDTO();
		List<DocumentStatusDTO> workDocumentStatusDTOList = new ArrayList<DocumentStatusDTO>();

		DocumentStatusDTO paymentDocumentStatusDTO = new DocumentStatusDTO();
		List<DocumentStatusDTO> paymentDocumentStatusDTOList = new ArrayList<DocumentStatusDTO>();

		SpecialRegimesDTO specialRegimeDTO = new SpecialRegimesDTO();
		List<SpecialRegimesDTO> specialRegimeDTOList = new ArrayList<SpecialRegimesDTO>();

		LineDTO lineDTO = new LineDTO();
		List<LineDTO> lineDTOList = new ArrayList<LineDTO>();

		LineDTO workingLineDTO = new LineDTO();
		List<LineDTO> workingLineDTOList = new ArrayList<LineDTO>();

		LineDTO paymentLineDTO = new LineDTO();
		List<LineDTO> paymentLineDTOList = new ArrayList<LineDTO>();

		ReferencesDTO referencesDTO = new ReferencesDTO();
		List<ReferencesDTO> referencesDTOList = new ArrayList<ReferencesDTO>();

		OrderReferenceDTO orderReferencesDTO = new OrderReferenceDTO();
		List<OrderReferenceDTO> orderReferencesDTOList = new ArrayList<OrderReferenceDTO>();

		TaxDTO taxDTO = new TaxDTO();
		List<TaxDTO> taxDTOList = new ArrayList<TaxDTO>();

		//TaxDTO workingTaxDTO = new TaxDTO();
		//List<TaxDTO> workingTaxDTOList = new ArrayList<TaxDTO>();

		DocumentTotalsDTO documentTotalDTO = new DocumentTotalsDTO();
		List<DocumentTotalsDTO> documentTotalDTOList = new ArrayList<DocumentTotalsDTO>();

		//DocumentTotalsDTO workingDocumentTotalDTO = new DocumentTotalsDTO();
		//List<DocumentTotalsDTO> workingDocumentTotalDTOList = new ArrayList<DocumentTotalsDTO>();

		DocumentTotalsDTO paymentDocumentTotalDTO = new DocumentTotalsDTO();
		List<DocumentTotalsDTO> paymentDocumentTotalDTOList = new ArrayList<DocumentTotalsDTO>();

		List<Invoice> invoiceList = this.invoiceRepo.buscarInvoice(dataInicio, dataFim);
		if (invoiceList.isEmpty()) {
			c.setMensagem("Invoice nulo");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		int count = 0;
		for (Invoice invoice : invoiceList) {
			invoiceDTO = new InvoiceDTO();
			invoiceDTO.setInvoiceNo(invoice.getInvoiceNo());

			List<DocumentStatus> documentStatusList = this.documentStatusRepo
					.buscarDocumentStatus(invoice.getInvoiceNo());
			documentStatusDTOList = new ArrayList<DocumentStatusDTO>();

			if (documentStatusList.isEmpty()) {
				System.out.println(invoice.getInvoiceNo());
				c.setMensagem("document status nulo");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			for (DocumentStatus documentStatus : documentStatusList) {
				
				documentStatusDTO = new DocumentStatusDTO();
				documentStatusDTO.setInvoiceStatus(documentStatus.getInvoiceStatus());
				documentStatusDTO.setInvoiceStatusDate(invoice.getSystemEntryDate());
				documentStatusDTO.setSourceId(documentStatus.getSourceId());
				documentStatusDTO.setSourceBilling(documentStatus.getSourceBilling());

				documentStatusDTOList.add(documentStatusDTO);
			}

			List<SpecialRegimes> specialRegimeList = this.specialRegimeRepo
					.buscarSpecialRegimes(invoice.getInvoiceNo());
			specialRegimeDTOList = new ArrayList<SpecialRegimesDTO>();

			for (SpecialRegimes specialRegime : specialRegimeList) {
				specialRegimeDTO = new SpecialRegimesDTO();
				specialRegimeDTO.setSelfBillingIndicator(specialRegime.getSelfBillingIndicator());
				specialRegimeDTO.setCashVatSchemeIndicator(specialRegime.getCashVatSchemeIndicator());
				specialRegimeDTO.setThirdPartiesBillingIndicator(specialRegime.getThirdPartiesBillingIndicator());

				specialRegimeDTOList.add(specialRegimeDTO);
			}

			lineDTOList = new ArrayList<LineDTO>();
			
			if(invoice.getInvoiceType().equals("FR")) {
				System.out.println("ENTROU NO PRIMEIRO");
				
				Guia guia = this.guiaRepo.findFacturaRecibo(invoice.getInvoiceNo());
				if(guia != null) {
					List<GuiaPagamentoHistorico> listaGuiaHistorico = guiaHistoricoRepo.buscarIdGuia(guia.getId());
					
					for (int i = 0; i < listaGuiaHistorico.size(); i++) {
					    GuiaPagamentoHistorico guiaPagamentoHist = listaGuiaHistorico.get(i);
					    lineDTO = new LineDTO();
					    
					    
				        System.out.println("AQUI VAMOS NOS");
				        lineDTO.setQuantity(Integer.parseInt(guiaPagamentoHist.getQuantidade()));
				        lineDTO.setUnitOfMeasure("UN");
				        lineDTO.setUnitPrice(new BigDecimal(Double.toString(guiaPagamentoHist.getValorTotal())).setScale(2, RoundingMode.HALF_UP));
				        lineDTO.setCreditAmount(new BigDecimal(Double.toString(guiaPagamentoHist.getValorTotal())).setScale(2, RoundingMode.HALF_UP));
				        lineDTO.setTaxExemptionReason("Isento nos termos da alínea I) do nº1 do artigo 12.º do CIVA");
				        lineDTO.setTaxExemptionCode(guiaPagamentoHist.getCodigoIva());
				        System.out.println("AQUI VAMOS NOS 2");
					    
					    
					    // Preenchendo o taxDTO com base no mesmo item de guiaPagamentoHist
					    System.out.println("ENTREI");
					    taxDTOList = new ArrayList<TaxDTO>();
					    taxDTO = new TaxDTO();
					    
					    if(!guiaPagamentoHist.getPercentagemIva().equals(0)) {
					        taxDTO.setTaxType("NS");
					        taxDTO.setTaxCountryRegion("AO");
					        taxDTO.setTaxCode("NS");
					        taxDTO.setTaxPercentage(new BigDecimal(guiaPagamentoHist.getPercentagemIva()));
					    } else {
					        // REVER COM O ARISTEU
					    }
					    taxDTOList.add(taxDTO);
					    
					    System.out.println("BELEZA");
					    
					    lineDTO.setLineNumber(i + 1);
					    lineDTO.setProductCode(guiaPagamentoHist.getEmolumento().getId().toString());
					    lineDTO.setProductDescription(guiaPagamentoHist.getEmolumento().getEmolumento());
					    lineDTO.setDescription(guiaPagamentoHist.getEmolumento().getEmolumento());
					    lineDTO.setTaxPointDate(guia.getDataEmissaoFr().toString());
					    lineDTO.setTax(taxDTOList);
					    
					    lineDTOList.add(lineDTO);
					    
					    double netTotal = 0.0;
						double grossTotal = 0.0;
						

						for (GuiaPagamentoHistorico documentTotals : listaGuiaHistorico) {
							System.out.println("Entrou aqui " + (documentTotals.getValor() - documentTotals.getDesconto()));

							netTotal += documentTotals.getValor() - documentTotals.getDesconto();
							grossTotal += documentTotals.getValorTotal();

							documentTotalDTO = new DocumentTotalsDTO();
							documentTotalDTOList = new ArrayList<DocumentTotalsDTO>();
							
							//ANALISAR COM O ARISTEU
							documentTotalDTO.setTaxPayable(documentTotals.getValorImposto());
							documentTotalDTO.setNetTotal(FormataData.formatarValor(netTotal));
							documentTotalDTO.setGrossTotal(FormataData.formatarValor(grossTotal));

							documentTotalDTOList.add(documentTotalDTO);
						}
					    
					    System.out.println("FIM");
					}
				}else {
					
					GuiaCandidatura guiaCandidatura = this.guiaCandidaturaRepo.buscarRecibo(invoice.getInvoiceNo());
					List<GuiaCandidaturaHistorico> listaGuiaCandidaturaHistorico = guiaCandidaturaHistoricoRepo.buscarIdGuia(guiaCandidatura.getId());
					
					for (int i = 0; i < listaGuiaCandidaturaHistorico.size(); i++) {
						GuiaCandidaturaHistorico guiaPagamentoHist = listaGuiaCandidaturaHistorico.get(i);
					    lineDTO = new LineDTO();
					    
				        System.out.println("AQUI VAMOS NOS");
				        lineDTO.setQuantity(Integer.parseInt(guiaPagamentoHist.getQuantidade()));
				        lineDTO.setUnitOfMeasure("UN");
				        lineDTO.setUnitPrice(new BigDecimal(Double.toString(guiaPagamentoHist.getValor())).setScale(2, RoundingMode.HALF_UP));
				        lineDTO.setCreditAmount(new BigDecimal(Double.toString(guiaPagamentoHist.getValor())).setScale(2, RoundingMode.HALF_UP));
				        lineDTO.setTaxExemptionReason("Isento nos termos da alínea I) do nº1 do artigo 12.º do CIVA");
				        lineDTO.setTaxExemptionCode(guiaPagamentoHist.getCodigoIva());
				        System.out.println("AQUI VAMOS NOS 2");
					    
					    
					    // Preenchendo o taxDTO com base no mesmo item de guiaPagamentoHist
					    System.out.println("ENTREI");
					    taxDTOList = new ArrayList<TaxDTO>();
					    taxDTO = new TaxDTO();
					    
					    if(!guiaPagamentoHist.getPercentagemIva().equals(0)) {
					        taxDTO.setTaxType("NS");
					        taxDTO.setTaxCountryRegion("AO");
					        taxDTO.setTaxCode("NS");
					        taxDTO.setTaxPercentage(new BigDecimal(guiaPagamentoHist.getPercentagemIva()));
					    } else {
					        // REVER COM O ARISTEU
					    }
					    taxDTOList.add(taxDTO);
					    
					    System.out.println("BELEZA");
					    
					    lineDTO.setLineNumber(i + 1);
					    lineDTO.setProductCode(guiaPagamentoHist.getEmolumento().getId().toString());
					    lineDTO.setProductDescription(guiaPagamentoHist.getEmolumento().getEmolumento());
					    lineDTO.setDescription(guiaPagamentoHist.getEmolumento().getEmolumento());
					    lineDTO.setTaxPointDate(guiaCandidatura.getDataEmissaoFr().toString());
					    lineDTO.setTax(taxDTOList);
					    
					    lineDTOList.add(lineDTO);
					    
					    double netTotal = 0.0;
						double grossTotal = 0.0;
						

						for (GuiaCandidaturaHistorico documentTotals : listaGuiaCandidaturaHistorico) {

							netTotal += documentTotals.getValor();
							grossTotal += documentTotals.getValor();

							documentTotalDTO = new DocumentTotalsDTO();
							documentTotalDTOList = new ArrayList<DocumentTotalsDTO>();
							
							//ANALISAR COM O ARISTEU
							documentTotalDTO.setTaxPayable(documentTotals.getValorImposto());
							documentTotalDTO.setNetTotal(FormataData.formatarValor(netTotal));
							documentTotalDTO.setGrossTotal(FormataData.formatarValor(grossTotal));

							documentTotalDTOList.add(documentTotalDTO);
						}
					    
					    System.out.println("FIM");
					}
					
				}
				
			}else if (invoice.getInvoiceType().equals("FT")) {
				
				System.out.println("ENTROU NO SEGUNDO");
				
				Factura fatura = this.facturaRepo.buscarNumeroFactura(invoice.getInvoiceNo());
				List<FacturaDetalhe> listaFactura = this.facturaDetalheRepo.buscarIdFactura(fatura.getId());
				
				for (int i = 0; i < listaFactura.size(); i++) {
					FacturaDetalhe facturaDetalhe = listaFactura.get(i);
				    lineDTO = new LineDTO();
				    
			        
			        lineDTO.setQuantity(facturaDetalhe.getQuantidade());
			        lineDTO.setUnitOfMeasure("UN");
			        lineDTO.setUnitPrice(new BigDecimal(Double.toString(facturaDetalhe.getPrecoUnitario())).setScale(2, RoundingMode.HALF_UP));
			        lineDTO.setCreditAmount(new BigDecimal(Double.toString(facturaDetalhe.getValorSemDesconto())).setScale(2, RoundingMode.HALF_UP));
			        lineDTO.setTaxExemptionReason("Isento nos termos da alínea I) do nº1 do artigo 12.º do CIVA");
			        lineDTO.setTaxExemptionCode(facturaDetalhe.getCodigoIva());
			       
				    
				    
				    taxDTOList = new ArrayList<TaxDTO>();
				    taxDTO = new TaxDTO();
				    
				    if(!facturaDetalhe.getPercentagemIva().equals(0)) {
				        taxDTO.setTaxType("NS");
				        taxDTO.setTaxCountryRegion("AO");
				        taxDTO.setTaxCode("NS");
				        taxDTO.setTaxPercentage(new BigDecimal(facturaDetalhe.getPercentagemIva()));
				    } else {
				        // REVER COM O ARISTEU
				    }
				    taxDTOList.add(taxDTO);
				    
				    System.out.println("BELEZA");
				    
				    lineDTO.setLineNumber(i + 1);
				    lineDTO.setProductCode(facturaDetalhe.getNumeroAluno().toString());
				    lineDTO.setProductDescription(facturaDetalhe.getDescricao());
				    lineDTO.setDescription(facturaDetalhe.getDescricao());
				    lineDTO.setTaxPointDate(fatura.getDataEmissao().toString());
				    lineDTO.setTax(taxDTOList);
				    
				    lineDTOList.add(lineDTO);
				    
				    double netTotal = 0.0;
					double grossTotal = 0.0;
				    
				    for (FacturaDetalhe documentTotals : listaFactura) {
						System.out.println("Entrou aqui " + (documentTotals.getPrecoUnitario() - documentTotals.getDesconto()));

						netTotal += documentTotals.getValorSemDesconto() - documentTotals.getDesconto();
						grossTotal += documentTotals.getValorTotal();

						documentTotalDTO = new DocumentTotalsDTO();
						documentTotalDTOList = new ArrayList<DocumentTotalsDTO>();
						
						//ANALISAR COM O ARISTEU
						documentTotalDTO.setTaxPayable(documentTotals.getValorImposto());
						documentTotalDTO.setNetTotal(FormataData.formatarValor(netTotal));
						documentTotalDTO.setGrossTotal(FormataData.formatarValor(grossTotal));

						documentTotalDTOList.add(documentTotalDTO);
					}
				    
				    System.out.println("FIM");
				}
			}else if(invoice.getInvoiceType().equals("NC")) {
				
				System.out.println("ENTROU NO TERCEIRO");
				NotaCredito notaCredito = this.notaCreditoRepo.buscarNumeroNotaCredito(invoice.getInvoiceNo());
				if(notaCredito.getTipoDoc() == TipoDoc.FACTURA_RECIBO) {
					List<GuiaPagamentoHistorico> listaGuiaHistorico = guiaHistoricoRepo.buscarIdGuia(notaCredito.getIdGuia().getId());
					
					for (int i = 0; i < listaGuiaHistorico.size(); i++) {
					    GuiaPagamentoHistorico guiaPagamentoHist = listaGuiaHistorico.get(i);
					    lineDTO = new LineDTO();
					    
					    List<Reference> referencesList = this.referencesRepo.buscarReferences(invoice.getInvoiceNo());
					    referencesDTOList = new ArrayList<ReferencesDTO>();
					    System.out.println("referencesRepo");

					    List<OrderReference> orderReferencesList = this.orderReferencesRepo
					            .buscarOrderReference(invoice.getInvoiceNo());
					    orderReferencesDTOList = new ArrayList<OrderReferenceDTO>();
					    System.out.println("orderReferencesRepo");
					    
					    if (invoice.getInvoiceType().equals("NC")) {
					        System.out.println("NC");
					        for (Reference references : referencesList) {
					            referencesDTO = new ReferencesDTO();
					            referencesDTO.setReference(references.getReference());
					            referencesDTOList.add(referencesDTO);
					        }

					        for (OrderReference orderReferences : orderReferencesList) {
					            System.out.println("orderReferencesList");
					            orderReferencesDTO = new OrderReferenceDTO();
					            orderReferencesDTO.setOriginatingON(orderReferences.getOriginatingON());
					            orderReferencesDTO.setOrderDate(orderReferences.getOrderDate());
					            orderReferencesDTOList.add(orderReferencesDTO);
					        }
					        
					        System.out.println("TERMINANDO A LINE");
					        lineDTO.setQuantity(Integer.parseInt(guiaPagamentoHist.getQuantidade()));
					        lineDTO.setUnitOfMeasure("UN");
					        lineDTO.setUnitPrice(new BigDecimal(Double.toString(guiaPagamentoHist.getValor())).setScale(2, RoundingMode.HALF_UP));
					        lineDTO.setOriginationOn(orderReferencesDTOList);
					        lineDTO.setReference(referencesDTOList);
					        lineDTO.setDebitAmount(new BigDecimal(Double.toString(guiaPagamentoHist.getValor())).setScale(2, RoundingMode.HALF_UP));
					        lineDTO.setTaxExemptionReason("Isento nos termos da alínea I) do nº1 do artigo 12.º do CIVA");
					        lineDTO.setTaxExemptionCode(guiaPagamentoHist.getCodigoIva());
					    }
					    
					    // Preenchendo o taxDTO com base no mesmo item de guiaPagamentoHist
					    System.out.println("ENTREI");
					    taxDTOList = new ArrayList<TaxDTO>();
					    taxDTO = new TaxDTO();
					    
					    if(!guiaPagamentoHist.getPercentagemIva().equals(0)) {
					        taxDTO.setTaxType("NS");
					        taxDTO.setTaxCountryRegion("AO");
					        taxDTO.setTaxCode("NS");
					        taxDTO.setTaxPercentage(new BigDecimal(guiaPagamentoHist.getPercentagemIva()));
					    } else {
					        // REVER COM O ARISTEU
					    }
					    taxDTOList.add(taxDTO);
					    
					    System.out.println("BELEZA");
					    
					    lineDTO.setLineNumber(i + 1);
					    lineDTO.setProductCode(guiaPagamentoHist.getEmolumento().getId().toString());
					    lineDTO.setProductDescription(guiaPagamentoHist.getEmolumento().getEmolumento());
					    lineDTO.setDescription(guiaPagamentoHist.getEmolumento().getEmolumento());
					    lineDTO.setTaxPointDate(notaCredito.getDataEmissao().toString());
					    lineDTO.setTax(taxDTOList);
					    
					    lineDTOList.add(lineDTO);
					    
					    double netTotal = 0.0;
						double grossTotal = 0.0;
					    
					    for (GuiaPagamentoHistorico documentTotals : listaGuiaHistorico) {
							System.out.println("Entrou aqui " + (documentTotals.getValor() - documentTotals.getDesconto()));

							netTotal += documentTotals.getValor() - documentTotals.getDesconto();
							grossTotal += documentTotals.getValorTotal();

							documentTotalDTO = new DocumentTotalsDTO();
							documentTotalDTOList = new ArrayList<DocumentTotalsDTO>();
							
							//ANALISAR COM O ARISTEU
							documentTotalDTO.setTaxPayable(documentTotals.getValorImposto());
							documentTotalDTO.setNetTotal(FormataData.formatarValor(netTotal));
							documentTotalDTO.setGrossTotal(FormataData.formatarValor(grossTotal));

							documentTotalDTOList.add(documentTotalDTO);
						}
					    
					    System.out.println("FIM");
					}
				}else if(notaCredito.getTipoDoc() == TipoDoc.FACTURA) {
					
					List<FacturaDetalhe> listaFactura = this.facturaDetalheRepo.buscarIdFactura(notaCredito.getIdFactura().getId());
					
					for (int i = 0; i < listaFactura.size(); i++) {
						FacturaDetalhe facturaDetalhe = listaFactura.get(i);
					    lineDTO = new LineDTO();
					    
					    List<Reference> referencesList = this.referencesRepo.buscarReferences(invoice.getInvoiceNo());
					    referencesDTOList = new ArrayList<ReferencesDTO>();
					    System.out.println("referencesRepo");

					    List<OrderReference> orderReferencesList = this.orderReferencesRepo
					            .buscarOrderReference(invoice.getInvoiceNo());
					    orderReferencesDTOList = new ArrayList<OrderReferenceDTO>();
					    System.out.println("orderReferencesRepo");
					    
					    
				        
				        for (Reference references : referencesList) {
				            referencesDTO = new ReferencesDTO();
				            referencesDTO.setReference(references.getReference());
				            referencesDTOList.add(referencesDTO);
				        }

				        for (OrderReference orderReferences : orderReferencesList) {
				            System.out.println("orderReferencesList");
				            orderReferencesDTO = new OrderReferenceDTO();
				            orderReferencesDTO.setOriginatingON(orderReferences.getOriginatingON());
				            orderReferencesDTO.setOrderDate(orderReferences.getOrderDate());
				            orderReferencesDTOList.add(orderReferencesDTO);
				        }
					        
				        System.out.println("TERMINANDO A LINE");
				        lineDTO.setQuantity(facturaDetalhe.getQuantidade());
				        lineDTO.setUnitOfMeasure("UN");
				        lineDTO.setUnitPrice(new BigDecimal(Double.toString(facturaDetalhe.getPrecoUnitario())).setScale(2, RoundingMode.HALF_UP));
				        lineDTO.setOriginationOn(orderReferencesDTOList);
				        lineDTO.setReference(referencesDTOList);
				        
				        if(notaCredito.getIdFactura().isAlterada()) {
				        	List<NotaCreditoDetalhe> notaDetalhe = this.notaCreditoDetalheRepo.buscarNumeroNotaCredito(notaCredito.getNumeroNotaCredito());
				        	
				        	for (NotaCreditoDetalhe notaDet : notaDetalhe) {
				        		lineDTO.setDebitAmount(new BigDecimal(Double.toString(notaDet.getValorTotal())).setScale(2, RoundingMode.HALF_UP));
							}
				        }
				        
				        lineDTO.setDebitAmount(new BigDecimal(Double.toString(facturaDetalhe.getValorSemDesconto())).setScale(2, RoundingMode.HALF_UP));
				        lineDTO.setTaxExemptionReason("Isento nos termos da alínea I) do nº1 do artigo 12.º do CIVA");
				        lineDTO.setTaxExemptionCode(facturaDetalhe.getCodigoIva());
					    
					    
					    // Preenchendo o taxDTO com base no mesmo item de guiaPagamentoHist
					    System.out.println("ENTREI");
					    taxDTOList = new ArrayList<TaxDTO>();
					    taxDTO = new TaxDTO();
					    
					    if(!facturaDetalhe.getPercentagemIva().equals(0)) {
					        taxDTO.setTaxType("NS");
					        taxDTO.setTaxCountryRegion("AO");
					        taxDTO.setTaxCode("NS");
					        taxDTO.setTaxPercentage(new BigDecimal(facturaDetalhe.getPercentagemIva()));
					    } else {
					        // REVER COM O ARISTEU
					    }
					    taxDTOList.add(taxDTO);
					    
					    System.out.println("BELEZA");
					    
					    lineDTO.setLineNumber(i + 1);
					    lineDTO.setProductCode(facturaDetalhe.getNumeroAluno().toString());
					    lineDTO.setProductDescription(facturaDetalhe.getDescricao());
					    lineDTO.setDescription(facturaDetalhe.getDescricao());
					    lineDTO.setTaxPointDate(notaCredito.getDataEmissao().toString());
					    lineDTO.setTax(taxDTOList);
					    
					    lineDTOList.add(lineDTO);
					    
					    double netTotal = 0.0;
						double grossTotal = 0.0;
					    
					    for (FacturaDetalhe documentTotals : listaFactura) {

							netTotal += documentTotals.getValorSemDesconto() - documentTotals.getDesconto();
							grossTotal += documentTotals.getValorTotal();

							documentTotalDTO = new DocumentTotalsDTO();
							documentTotalDTOList = new ArrayList<DocumentTotalsDTO>();
							
							//ANALISAR COM O ARISTEU
							documentTotalDTO.setTaxPayable(documentTotals.getValorImposto());
							documentTotalDTO.setNetTotal(FormataData.formatarValor(netTotal));
							documentTotalDTO.setGrossTotal(FormataData.formatarValor(grossTotal));

							documentTotalDTOList.add(documentTotalDTO);
						}
					    
					    System.out.println("FIM");
					}
					
				}
			}

				

			/*for (Line line : LineList) {
				lineDTO = new LineDTO();

				List<Reference> referencesList = this.referencesRepo.buscarReferences(invoice.getInvoiceNo());
				referencesDTOList = new ArrayList<ReferencesDTO>();

				List<OrderReference> orderReferencesList = this.orderReferencesRepo
						.buscarOrderReference(invoice.getInvoiceNo());
				orderReferencesDTOList = new ArrayList<OrderReferenceDTO>();

				if (invoice.getInvoiceType().equals("NC")) {

					for (Reference references : referencesList) {
						referencesDTO = new ReferencesDTO();
						referencesDTO.setReference(references.getReference());

						referencesDTOList.add(referencesDTO);
					}

					for (OrderReference orderReferences : orderReferencesList) {
						orderReferencesDTO = new OrderReferenceDTO();
						orderReferencesDTO.setOriginatingON(orderReferences.getOriginatingON());
						orderReferencesDTO.setOrderDate(orderReferences.getOrderDate());

						orderReferencesDTOList.add(orderReferencesDTO);
					}

					//lineDTO.setQuantity(line.getQuantity());
					lineDTO.setUnitOfMeasure(line.getUnitOfMeasure());
					lineDTO.setUnitPrice(line.getUnitPrice().setScale(2, RoundingMode.HALF_UP));
					lineDTO.setOriginationOn(orderReferencesDTOList);
					lineDTO.setReference(referencesDTOList);
					lineDTO.setDebitAmount(line.getDebitAmount().setScale(2, RoundingMode.HALF_UP));

				} else {

					lineDTO.setUnitOfMeasure(line.getUnitOfMeasure());

					//lineDTO.setQuantity(line.getQuantity());
					lineDTO.setUnitPrice(line.getUnitPrice().setScale(2, RoundingMode.HALF_UP));
					lineDTO.setCreditAmount(line.getCreditAmount().setScale(2, RoundingMode.HALF_UP));
				}

				List<Tax> taxList = this.taxRepo.buscarTax(invoice.getInvoiceNo(), line.getLineNumber());
				taxDTOList = new ArrayList<TaxDTO>();

				for (Tax tax : taxList) {
					taxDTO = new TaxDTO();
					taxDTO.setTaxType(tax.getTaxType());
					taxDTO.setTaxCountryRegion(tax.getTaxCountryRegion());
					taxDTO.setTaxCode(tax.getTaxCode());
					taxDTO.setTaxPercentage(tax.getTaxPercentage());
					// taxDTO.setTaxAmount(tax.getTaxAmount());

					taxDTOList.add(taxDTO);
				}

				lineDTO.setLineNumber(line.getLineNumber());
				lineDTO.setProductCode(line.getProductCode());
				lineDTO.setProductDescription(line.getProductDescription());
				lineDTO.setDescription(line.getDescription());
				lineDTO.setTaxPointDate(line.getTaxPointDate());

				lineDTO.setTax(taxDTOList);
				lineDTO.setTaxExemptionReason(line.getTaxExemptionReason());
				lineDTO.setTaxExemptionCode(line.getTaxExemptionCode());
				lineDTO.setTaxPointDate(line.getTaxPointDate());

				lineDTOList.add(lineDTO);
			}*/

			/*List<DocumentTotals> documentTotalList = this.documentTotalsRepo
					.buscarDocumentTotals(invoice.getInvoiceNo());
			documentTotalDTOList = new ArrayList<DocumentTotalsDTO>();*/


			invoiceDTO.setDocumentStatus(documentStatusDTOList);
			invoiceDTO.setHash(invoice.getHash());
			invoiceDTO.setHashControl(invoice.getHashControl());
			invoiceDTO.setPeriod(invoice.getPeriod());
			invoiceDTO.setInvoiceDate(invoice.getInvoiceDate());
			invoiceDTO.setInvoiceType(invoice.getInvoiceType());
			invoiceDTO.setSpecialRegimes(specialRegimeDTOList);
			invoiceDTO.setSourceId(invoice.getSourceId());
			invoiceDTO.setSystemEntryDate(invoice.getSystemEntryDate());
			invoiceDTO.setCustomerId(invoice.getCustomerId());
			invoiceDTO.setLine(lineDTOList);
			invoiceDTO.setDocumentTotals(documentTotalDTOList);

			invoiceDTOList.add(invoiceDTO);
			
			System.out.println("Invoice " + count++);
		}
		
		List<Guia> listNumberOfEntriesFR = this.guiaRepo.BUSCAR_NUMBER_OF_ENTRIES(dataInicio, dataFim);
		List<GuiaCandidatura> listNumberOfEntriesCandidaturaFR = this.guiaCandidaturaRepo.BUSCAR_NUMBER_OF_ENTRIES(dataInicio, dataFim);
		if (listNumberOfEntriesFR.isEmpty()) {
			c.setMensagem("NumberOfEntries nulo");
			//return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		
		List<Factura> listNumberOfEntriesFT = this.facturaRepo.BUSCAR_NUMBER_OF_ENTRIES(dataInicio, dataFim);
		if (listNumberOfEntriesFT.isEmpty()) {
			c.setMensagem("NumberOfEntries nulo");
			//return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		List<NotaCredito> listNumberOfEntriesNC = this.notaCreditoRepo.BUSCAR_NUMBER_OF_ENTRIES(dataInicio, dataFim);
		if (listNumberOfEntriesNC.isEmpty()) {
			c.setMensagem("NumberOfEntries nulo");
			//return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		/*Double listaDebit = this.notaCreditoRepo.buscarDebits(dataInicio, dataFim);
		if(listaDebit != null) {
			salesInvoiceDTO.setTotalDebit(FormataData.formatarValor(listaDebit));
		}

		List<SalesInvoices> salesInvoiceList = this.salesInvoiceRepo.buscarSalesInvoces(dataInicio, dataFim);
		if (salesInvoiceList.isEmpty()) {
			c.setMensagem("SalesInvoice nulo");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<SalesInvoices> salesInvoiceDebitList = this.salesInvoiceRepo.buscarDebits(dataInicio, dataFim);
		if (salesInvoiceList.isEmpty()) {
			c.setMensagem("SalesInvoiceDebit nulo");
			// return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		List<SalesInvoices> salesInvoiceCreditList = this.salesInvoiceRepo.buscarCredit(dataInicio, dataFim);
		if (salesInvoiceList.isEmpty()) {
			c.setMensagem("SalesInvoiceDebit nulo");
		}*/

		int numberOfEntries = 0;
		double totalDebit = 0.0;
		double totalCredit = 0.0;

					// FACTURA RECIBO //
		
		for (GuiaCandidatura salesInvoices : listNumberOfEntriesCandidaturaFR) {

			numberOfEntries++;
		}
		
		for (GuiaCandidatura creditAmountFR : listNumberOfEntriesCandidaturaFR) {
			List<GuiaCandidaturaHistorico> gHistorico = guiaCandidaturaHistoricoRepo.buscarIdGuia(creditAmountFR.getId());
			for (GuiaCandidaturaHistorico historico : gHistorico) {
				
				totalCredit += historico.getValor();
			}
		}
		
		for (Guia salesInvoices : listNumberOfEntriesFR) {

			numberOfEntries++;
		}
		
		for (Guia creditAmountFR : listNumberOfEntriesFR) {
			List<GuiaPagamentoHistorico> gHistorico = guiaHistoricoRepo.buscarIdGuia(creditAmountFR.getId());
			for (GuiaPagamentoHistorico historico : gHistorico) {
				
				totalCredit += historico.getValorTotal();
			}
		}
		
						// FACTURA //
		for (Factura salesInvoices : listNumberOfEntriesFT) {

			numberOfEntries++;
		}
		
		for (Factura factura : listNumberOfEntriesFT) {
			totalCredit += factura.getValor();
		}
		
		
		for (NotaCredito salesInvoices : listNumberOfEntriesNC) {

			numberOfEntries++;
		}
		
		for (NotaCredito notaCredito : listNumberOfEntriesNC) {
			totalDebit += notaCredito.getValor();
		}


		/*for (NotaCredito salesInvoices : listaDebit) {

			totalDebit = totalDebit + salesInvoices.getTotalDebit();
		}
		
		for (SalesInvoices salesInvoices : salesInvoiceCreditList) {

			totalCredit = totalCredit + salesInvoices.getTotalCredit();
		}*/

		salesInvoiceDTO.setNumberOfEntries(numberOfEntries);
		salesInvoiceDTO.setTotalDebit(FormataData.formatarValor(totalDebit));
		salesInvoiceDTO.setTotalCredit(FormataData.formatarValor(totalCredit));
		salesInvoiceDTO.setInvoice(invoiceDTOList);

		sourceDocuments.setSalesInvoices(salesInvoiceDTO);

		/*WorkDocumentsDTO workDocumentsDTO = new WorkDocumentsDTO();

		WorkDocumentDTO workDocumentDTO = new WorkDocumentDTO();
		List<WorkDocumentDTO> workDocumentDTOList = new ArrayList<WorkDocumentDTO>();

		List<WorkDocument> workDocumentList = workDocumentRepo.buscarWorkDocument(dataInicio, dataFim);
		if (workDocumentList.isEmpty()) {
			c.setMensagem("work document nulo");
			// return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {

			for (WorkDocument workDocument : workDocumentList) {
				workDocumentDTO = new WorkDocumentDTO();
				workDocumentDTO.setDocumentNumber(workDocument.getDocumentNumber());

				List<WorkDocumentsStatus> workDocumentStatusList = this.workDocumentStatusRepo
						.buscarWorkDocumentStatus(workDocument.getDocumentNumber());
				workDocumentStatusDTOList = new ArrayList<DocumentStatusDTO>();

				if (workDocumentStatusList.isEmpty()) {
					c.setMensagem("workdocument status nulo");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}

				for (WorkDocumentsStatus workDocumentStatus : workDocumentStatusList) {
					workDocumentStatusDTO = new DocumentStatusDTO();

					workDocumentStatusDTO.setWorkStatus(workDocumentStatus.getWorkStatus());
					workDocumentStatusDTO.setWorkStatusDate(workDocumentStatus.getWorkStatusDate());
					workDocumentStatusDTO.setSourceId(workDocumentStatus.getSourceId());
					workDocumentStatusDTO.setSourceBilling(workDocumentStatus.getSourceBilling());

					workDocumentStatusDTOList.add(workDocumentStatusDTO);
				}

				List<WorkingLine> workingLineList = this.workingLineRepo
						.buscarWorkingLine(workDocument.getDocumentNumber());
				workingLineDTOList = new ArrayList<LineDTO>();

				for (WorkingLine line : workingLineList) {
					workingLineDTO = new LineDTO();

					List<WorkingTax> workingTaxList = this.workingTaxRepo
							.buscarWorkingTax(workDocument.getDocumentNumber(), line.getLineNumber());
					workingTaxDTOList = new ArrayList<TaxDTO>();

					for (WorkingTax tax : workingTaxList) {
						workingTaxDTO = new TaxDTO();
						workingTaxDTO.setTaxType(tax.getTaxType());
						workingTaxDTO.setTaxCountryRegion(tax.getTaxCountryRegion());
						workingTaxDTO.setTaxCode(tax.getTaxCode());
						workingTaxDTO.setTaxPercentage(tax.getTaxPercentage());
						// workingTaxDTO.setTaxAmount(tax.getTaxAmount());

						workingTaxDTOList.add(workingTaxDTO);
					}

					workingLineDTO.setLineNumber(line.getLineNumber());
					workingLineDTO.setProductCode(line.getProductCode());
					workingLineDTO.setProductDescription(line.getProductDescription());
					workingLineDTO.setQuantity(line.getQuantity());
					workingLineDTO.setUnitOfMeasure(line.getUnitOfMeasure());
					workingLineDTO.setUnitPrice(line.getUnitPrice());
					workingLineDTO.setTaxPointDate(line.getTaxPointDate());
					workingLineDTO.setDescription(line.getDescription());
					workingLineDTO.setCreditAmount(line.getCreditAmount());
					workingLineDTO.setTax(workingTaxDTOList);
					workingLineDTO.setTaxExemptionCode(line.getTaxExemptionCode());
					workingLineDTO.setTaxExemptionReason(line.getTaxExemptionReason());

					workingLineDTOList.add(workingLineDTO);
				}

				List<WorkingDocumentTotals> workingDocumentTotalList = this.workingDocumentsTotalRepo
						.buscarWorkingDocumentTotals(workDocument.getDocumentNumber());
				workingDocumentTotalDTOList = new ArrayList<DocumentTotalsDTO>();

				double WorknetTotal = 0.0;
				double WorkgrossTotal = 0.0;

				for (WorkingDocumentTotals documentTotals : workingDocumentTotalList) {
					System.out.println("Entrou aqui " + documentTotals.getNetTotal());

					WorknetTotal += documentTotals.getNetTotal();
					WorkgrossTotal += documentTotals.getGrossTotal();

					workingDocumentTotalDTO = new DocumentTotalsDTO();
					workingDocumentTotalDTO.setTaxPayable(documentTotals.getTaxPayable());
					workingDocumentTotalDTO.setNetTotal(WorknetTotal);
					workingDocumentTotalDTO.setGrossTotal(WorkgrossTotal);

					workingDocumentTotalDTOList.add(workingDocumentTotalDTO);
				}

				workDocumentDTO.setDocumentStatus(workDocumentStatusDTOList);
				workDocumentDTO.setHash(workDocument.getHash());
				workDocumentDTO.setHashControl(workDocument.getHashControl());
				workDocumentDTO.setWorkDate(workDocument.getWorkDate());
				workDocumentDTO.setWorkType(workDocument.getWorkType());
				workDocumentDTO.setSourceId(workDocument.getSourceId());
				workDocumentDTO.setSystemEntryDate(workDocument.getSystemEntryDate());
				workDocumentDTO.setCustomerId(workDocument.getCustomerId());
				workDocumentDTO.setLine(workingLineDTOList);
				workDocumentDTO.setDocumentTotals(workingDocumentTotalDTOList);

				workDocumentDTOList.add(workDocumentDTO);
			}
		}

		List<WorkingDocuments> workDocumentsList = this.workDocumenstRepo.buscarWorkDocuments(dataInicio, dataFim);
		if (salesInvoiceList.isEmpty()) {
			c.setMensagem("SalesInvoice nulo");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		int worknumberOfEntries = 0;
		double worktotalDebit = 0.0;
		double worktotalCredit = 0.0;
		for (WorkingDocuments workDocuments : workDocumentsList) {

			worknumberOfEntries += workDocuments.getNumberOfEntries();
			worktotalDebit += workDocuments.getTotalDebit();
			worktotalCredit += workDocuments.getTotalCredit();
		}

		workDocumentsDTO.setNumberOfEntries(worknumberOfEntries);
		workDocumentsDTO.setTotalDebit(worktotalDebit);
		workDocumentsDTO.setTotalCredit(worktotalCredit);
		workDocumentsDTO.setWorkDocument(workDocumentDTOList);

		sourceDocuments.setWorkingDocuments(workDocumentsDTO);*/

		PaymentsDTO paymentsDTO = new PaymentsDTO();

		PaymentDTO paymentDTO = new PaymentDTO();
		List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();

		SourceDocumentIdDTO sourceDocumentsIDDTO = new SourceDocumentIdDTO();
		List<SourceDocumentIdDTO> sourceDocumentIDDTOList = new ArrayList<SourceDocumentIdDTO>();

		List<Payment> paymentList = paymentRepo.buscarPayment(dataInicio, dataFim);
		if (paymentList.isEmpty()) {
			c.setMensagem("payment nulo");
		} else {

			for (Payment payment : paymentList) {
				paymentDTO = new PaymentDTO();
				paymentDTO.setPaymentRefNo(payment.getPaymentRefNo());

				List<PaymentDocumentStatus> paymentDocumentStatusList = this.paymentDocumentStatusRepo
						.buscarPaymentDocumentStatus(payment.getPaymentRefNo());
				paymentDocumentStatusDTOList = new ArrayList<DocumentStatusDTO>();

				if (paymentDocumentStatusList.isEmpty()) {
					c.setMensagem("payment document status nulo");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}

				for (PaymentDocumentStatus paymentDocumentStatus : paymentDocumentStatusList) {
					paymentDocumentStatusDTO = new DocumentStatusDTO();
					paymentDocumentStatusDTO.setPaymentStatus(paymentDocumentStatus.getPaymentStatus());
					paymentDocumentStatusDTO.setPaymentStatusDate(paymentDocumentStatus.getPaymentStatusDate());
					paymentDocumentStatusDTO.setSourceId(paymentDocumentStatus.getSourceId());
					paymentDocumentStatusDTO.setSourcePayment(paymentDocumentStatus.getSourcePayment());

					paymentDocumentStatusDTOList.add(paymentDocumentStatusDTO);
				}

				List<PaymentLine> paymentLineList = this.paymentLineRepo.buscarPaymentLine(payment.getPaymentRefNo());
				paymentLineDTOList = new ArrayList<LineDTO>();

				for (PaymentLine line : paymentLineList) {
					paymentLineDTO = new LineDTO();

					List<SourceDocumentId> sourceDocumentIDList = this.sourceDocumentRepo
							.buscarSourceDocumentID(payment.getPaymentRefNo());
					sourceDocumentIDDTOList = new ArrayList<SourceDocumentIdDTO>();

					for (SourceDocumentId sourceDocumentsID : sourceDocumentIDList) {
						sourceDocumentsIDDTO = new SourceDocumentIdDTO();
						sourceDocumentsIDDTO.setOriginatingOn(sourceDocumentsID.getOriginatingOn());
						sourceDocumentsIDDTO.setInvoceDate(sourceDocumentsID.getInvoceDate());

						sourceDocumentIDDTOList.add(sourceDocumentsIDDTO);
					}

					paymentLineDTO.setLineNumber(line.getLineNumber());
					paymentLineDTO.setCreditAmount(line.getCreditAmount());
					paymentLineDTO.setSourceDocumentId(sourceDocumentsIDDTO);

					paymentLineDTOList.add(paymentLineDTO);
				}

				List<PaymentDocumentTotals> paymentDocumentTotalList = this.paymentDocumentTotalRepo
						.buscarPaymentDocumentTotals(payment.getPaymentRefNo());
				paymentDocumentTotalDTOList = new ArrayList<DocumentTotalsDTO>();

				double PaymentnetTotal = 0.0;
				double PaymentgrossTotal = 0.0;

				for (PaymentDocumentTotals documentTotals : paymentDocumentTotalList) {
					System.out.println("Entrou aqui " + documentTotals.getNetTotal());

					PaymentnetTotal += documentTotals.getNetTotal();
					PaymentgrossTotal += documentTotals.getGrossTotal();

					paymentDocumentTotalDTO = new DocumentTotalsDTO();
					paymentDocumentTotalDTO.setTaxPayable(documentTotals.getTaxPayable());
					paymentDocumentTotalDTO.setNetTotal(PaymentnetTotal);
					paymentDocumentTotalDTO.setGrossTotal(PaymentgrossTotal);

					paymentDocumentTotalDTOList.add(paymentDocumentTotalDTO);
				}

				paymentDTO.setCustomerId(payment.getCustomerId());
				paymentDTO.setSourceId(payment.getSourceId());
				paymentDTO.setSystemEntryDate(payment.getSystemEntryDate());
				paymentDTO.setPaymentType(payment.getPaymentType());
				paymentDTO.setTransactionDate(payment.getTransactionDate());
				paymentDTO.setDocumentStatus(paymentDocumentStatusDTOList);
				paymentDTO.setDocumentTotals(paymentDocumentTotalDTOList);
				paymentDTO.setLine(paymentLineDTOList);

				paymentDTOList.add(paymentDTO);
			}
		}

		System.out.println("Lista " + paymentDTOList.size());
		List<Payments> paymentsList = this.paymentsRepo.buscarPayments(dataInicio, dataFim);
		if (paymentsList.isEmpty()) {
			c.setMensagem("Payments nulo");
			// return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
			int paymentsnumberOfEntries = 0;
			double paymentstotalDebit = 0.0;
			double paymentstotalCredit = 0.0;
			for (Payments payments : paymentsList) {

				paymentsnumberOfEntries += payments.getNumberOfEntries();
				paymentstotalDebit += payments.getTotalDebit();
				paymentstotalCredit += payments.getTotalCredit();
			}

			paymentsDTO.setNumberOfEntries(paymentsnumberOfEntries);
			paymentsDTO.setTotalDebit(paymentstotalDebit);
			paymentsDTO.setTotalCredit(paymentstotalCredit);
			paymentsDTO.setPayment(paymentDTOList);

			sourceDocuments.setPayments(paymentsDTO);
		}

		auditFile.setHeader(header);
		auditFile.setMasterFiles(masterFile);
		auditFile.setSourceDocuments(sourceDocuments);

		Collections.sort(auditFile.getSourceDocuments().getSalesInvoices().getInvoice(), new Comparator<InvoiceDTO>() {

			@Override
			public int compare(InvoiceDTO o1, InvoiceDTO o2) {

				return o1.getInvoiceNo().compareTo(o2.getInvoiceNo());
			}
		});

		orderInvoice(auditFile.getSourceDocuments().getSalesInvoices().getInvoice());
		//orderWorkDocuments(auditFile.getSourceDocuments().getWorkingDocuments().getWorkDocument());
		//orderPaymets(auditFile.getSourceDocuments().getPayments().getPayment());
		
		try {
			// Inicializar o contexto JAXB para a classe AuditFileDTO
			JAXBContext context = JAXBContext.newInstance(AuditFileDTO.class);

			// Criar um Marshaller para transformar a instância em XML
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			String xmlFilePath = "arquivo.xml";
			marshaller.marshal(auditFile, new File(xmlFilePath));

			marshaller.marshal(auditFile, System.out);

			StringWriter writer = new StringWriter();
			marshaller.marshal(auditFile, writer);

			String xmlOutput = writer.toString();
			System.out.println(xmlOutput);

			System.out.println("NUMERO DE DOCUMETO " + numberOfEntries);
			System.out.println("Arquivo XML gerado com sucesso!");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		c.setResultado(auditFile);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@GetMapping("/downloadXml")
	public ResponseEntity<Resource> downloadXmlFile() throws IOException {
		// Carregue o arquivo XML gerado anteriormente (arquivo.xml) como um Resource
		Resource xmlFileResource = new FileSystemResource("arquivo.xml");

		// Verifique se o arquivo existe
		if (!xmlFileResource.exists()) {
			return ResponseEntity.notFound().build();
		}

		// Configurar o cabeçalho HTTP para a resposta
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=arquivo.xml");

		// Retornar o arquivo XML como um recurso para download
		return ResponseEntity.ok().headers(headers).contentLength(xmlFileResource.getFile().length())
				.contentType(MediaType.APPLICATION_XML).body(xmlFileResource);
	}

	

	private void orderInvoice(List<InvoiceDTO> invoice) {
		Collections.sort(invoice, new Comparator<InvoiceDTO>() {
			@Override
			public int compare(InvoiceDTO i1, InvoiceDTO i2) {
				String prefix1 = extractPrefix(i1.getInvoiceNo());
				int num1 = extractNumberAfterSlash(i1.getInvoiceNo());
				
				String prefix2 = extractPrefix(i2.getInvoiceNo());
	            int num2 = extractNumberAfterSlash(i2.getInvoiceNo());
	            
	            int prefixComparison = prefix1.compareTo(prefix2);
	            if (prefixComparison != 0) {
	                return prefixComparison;
	            }
	            
	            return Integer.compare(num1, num2);
			}

			private String extractPrefix(String invoiceNumber) {
	            int slashIndex = invoiceNumber.indexOf("/");
	            if (slashIndex != -1) {
	                return invoiceNumber.substring(0, slashIndex);
	            } else {
	                return "";
	            }
	        }
			
			private int extractNumberAfterSlash(String invoiceNumber) {
	            int lastSlashIndex = invoiceNumber.lastIndexOf("/");
	            if (lastSlashIndex != -1) {
	                String numberAfterSlash = invoiceNumber.substring(lastSlashIndex + 1);
	                return Integer.parseInt(numberAfterSlash);
	            } else {
	                return 0;
	            }
	        }
		});
	}
	
	/*private void orderWorkDocuments(List<WorkDocumentDTO> workDocuments) {
		Collections.sort(workDocuments, new Comparator<WorkDocumentDTO>() {
			@Override
			public int compare(WorkDocumentDTO w1, WorkDocumentDTO w2) {
				int num1 = extractNumberAfterSlash(w1.getDocumentNumber());
				int num2 = extractNumberAfterSlash(w2.getDocumentNumber());
				return Integer.compare(num1, num2);
			}

			private int extractNumberAfterSlash(String documentNumber) {
				int lastSlashIndex = documentNumber.lastIndexOf("/");
				if (lastSlashIndex != -1) {
					String numberAfterSlash = documentNumber.substring(lastSlashIndex + 1);
					return Integer.parseInt(numberAfterSlash);
				} else {
					return 0;
				}
			}
		});
	}*/
	
	/*private void orderPaymets(List<PaymentDTO> payment) {
		Collections.sort(payment, new Comparator<PaymentDTO>() {
			@Override
			public int compare(PaymentDTO p1, PaymentDTO p2) {
				int num1 = extractNumberAfterSlash(p1.getPaymentRefNo());
				int num2 = extractNumberAfterSlash(p2.getPaymentRefNo());
				return Integer.compare(num1, num2);
			}

			private int extractNumberAfterSlash(String documentNumber) {
				int lastSlashIndex = documentNumber.lastIndexOf("/");
				if (lastSlashIndex != -1) {
					String numberAfterSlash = documentNumber.substring(lastSlashIndex + 1);
					return Integer.parseInt(numberAfterSlash);
				} else {
					return 0;
				}
			}
		});
	}*/
}