package ao.co.intellectus.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/biometrico")
public class BiometricoController {
	/*
	@Autowired
	private BiometricoService servicos;

	@RequestMapping(value = "/salvar", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar() {
		NBioBSPJNI bsp = new NBioBSPJNI();
		if (servicos.temErro(bsp)) {
			return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Erro ao verificar disposivo !");
		} else {
			if(servicos.temDispositivo(bsp)) {
				bsp.OpenDevice();
				if (servicos.salvarImpressao(bsp)) {
					return ObjectoDeRetorno.MensagemDeRetorno(null, 0, "Registado com sucesso !");
				} else {
					return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Erro ao registar impressão !");
				}
			}else {
				return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Dispositivo não encontrado !");
			}
		}
	}
	
	@RequestMapping(value = "/pesquisar", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pesquisar() {
		NBioBSPJNI bsp = new NBioBSPJNI();
		if (servicos.temErro(bsp)) {
			return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Erro ao verificar disposivo !");
		} else {
			if(servicos.temDispositivo(bsp)) {
				bsp.OpenDevice();
				Biometrico biometrico = servicos.pesquisarImpressao(bsp);
				if (biometrico == null) {
					return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Impressão não encontradao !");
				} else {
					return ObjectoDeRetorno.MensagemDeRetorno(null, 0, "Impressão encontrada !");
				}
			}else {
				return ObjectoDeRetorno.MensagemDeRetorno(null, 2, "Dispositivo não encontrado !");
			}
		}
	}

	 */
}