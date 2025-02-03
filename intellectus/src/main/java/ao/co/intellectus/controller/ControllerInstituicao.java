package ao.co.intellectus.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.LogoMarca;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.LogoMarcaRepository;

@RestController
@RequestMapping("/instituicao")
public class ControllerInstituicao {
	 @Autowired
	 private InstituicaoRepository repositoryinstituicao;
	 @Autowired
	 private LogoMarcaRepository logoMarcaRepository;

	 @RequestMapping(value = "/salvar", method = RequestMethod.POST)
	 @CrossOrigin(origins = "*")
	 @ResponseBody
	 public ResponseEntity<ResponseCliente> salvar(@RequestBody Instituicao iRecibido) {
	  ResponseCliente c=new ResponseCliente();
	  Instituicao instituicao = new Instituicao();
	  
	  instituicao.getDescricao();
	  instituicao.getSigla();
	  
      this.repositoryinstituicao.save(iRecibido);
	  c.setResultado(iRecibido);
	  c.setCodigo(ResponseCode.values()[0].getDescricao());
	  c.setMensagem("Instituição adcionada com sucesso");
	  return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
		@ResponseBody
		@CrossOrigin(origins = "*")
		public Iterable<Instituicao> matriculas() {
		    Iterable<Instituicao> todas = this.repositoryinstituicao.findAll();
			return todas;
		}
	 
	 @RequestMapping(value = "/salvar/empresa", method = RequestMethod.POST)
	 @CrossOrigin(origins = "*")
	 @ResponseBody
	 public ResponseEntity<ResponseCliente> salvarInst(@RequestBody Instituicao empresa) throws IOException {
	  ResponseCliente c=new ResponseCliente();
      this.repositoryinstituicao.save(empresa); 
	  c.setResultado(empresa);
	  c.setCodigo(ResponseCode.values()[0].getDescricao());
	  c.setMensagem("Instituição adcionada com sucesso");
	  return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(value = "/editar/empresa", method = RequestMethod.PUT)
	 @CrossOrigin(origins = "*")
	 @ResponseBody
	 public ResponseEntity<ResponseCliente> editarEmpresa(@RequestBody Instituicao empresa) throws IOException {
	  ResponseCliente c=new ResponseCliente();
      this.repositoryinstituicao.save(empresa);
      
      
	  c.setResultado(empresa);
	  c.setCodigo(ResponseCode.values()[0].getDescricao());
	  c.setMensagem("Dados da Instituição actualizado com sucesso");
	  return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/home", method = RequestMethod.PUT)
	 @CrossOrigin(origins = "*")
	 @ResponseBody
	 public ResponseEntity<ResponseCliente> home(@RequestBody Instituicao empresa) throws IOException {
	  ResponseCliente c=new ResponseCliente();
      this.repositoryinstituicao.save(empresa);
	  c.setResultado(empresa);
	  c.setCodigo(ResponseCode.values()[0].getDescricao());
	  c.setMensagem("Dados da Instituição actualizado com sucesso");
	  return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	 }
	 
	 

	 @RequestMapping(value = "/anexo", method = RequestMethod.PUT)
		@ResponseBody
		@CrossOrigin(origins = "*")
		public ResponseEntity<ResponseCliente> uploadFoto(@RequestParam MultipartFile file)
				throws IOException {
			ResponseCliente c = new ResponseCliente();
			try {
				
				LogoMarca logoMarca = this.logoMarcaRepository.findOne(1);
				logoMarca.setFoto(file.getBytes());
				this.logoMarcaRepository.save(logoMarca);
				
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setMensagem("Imagem atualizada com sucesso.");
				
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} catch (Exception ex) {
				
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		}
}