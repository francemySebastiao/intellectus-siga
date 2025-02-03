package ao.co.intellectus.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ao.co.intellectus.model.ImagensSistema;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.ImagensSistemaRepository;

@RestController
@RequestMapping("/layoutCartao")
public class ControllerLayoutCartao {
	@Autowired
	private ImagensSistemaRepository imagensSistemaRepository;

	
	@RequestMapping(value = "/anexo", method = RequestMethod.PUT)
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> uploadFoto(@RequestParam MultipartFile file, @RequestParam String idAluno) throws IOException{
		ResponseCliente c = new ResponseCliente();
		
		ImagensSistema imagem = this.imagensSistemaRepository.findOne(1);
		
		imagem.setFoto(file.getBytes());
		
		this.imagensSistemaRepository.save(imagem);
		
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}
