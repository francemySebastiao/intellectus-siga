package ao.co.intellectus.controller.secretaria_virtual;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;



@RestController
@RequestMapping("/documento")
public class ControllerDocumento {
	
	@Autowired
	private HttpResponse httpResponse;
	
	@GetMapping(value = "/ocr/upload", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> upload(){
		@SuppressWarnings("unused")
		String caminho = "https://cdn.pensador.com/img/frase/al/be/albert_einstein_duas_coisas_sao_infinitas_o_universo_e_lkr1on2.jpg";
		String textoimagem = "Erro ao validar imagem";
		try {
			URL url = new URL("https://cdn.pensador.com/img/frase/al/be/albert_einstein_duas_coisas_sao_infinitas_o_universo_e_lkr1on2.jpg");
			BufferedImage image = ImageIO.read(url);
			Tesseract tesseract = new Tesseract();
			tesseract.setDatapath("C:\\Program Files (x86)\\tessdata");
			tesseract.setLanguage("por");
			textoimagem = tesseract.doOCR(image);
			this.pesquisarPalavra(textoimagem.toLowerCase(), "eLa".toLowerCase());
		} catch (TesseractException e) {
			return this.httpResponse.MensagemDeRetorno(2, e.getMessage());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return this.httpResponse.MensagemDeRetorno(0, textoimagem);
	}
	
	private void pesquisarPalavra(String frase, String palavra) {
		if(frase.contains(palavra)) {
			System.err.println("Palavra encontrada");
		}else {
			System.err.println("Palavra não encontrada");
		}
	}
	
}
