package ao.co.intellectus.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.CartaoMatrizBloqueio;
import ao.co.intellectus.model.ControladorMapaMatriz;
import ao.co.intellectus.model.Docente;
import ao.co.intellectus.model.MatrizDocente;
import ao.co.intellectus.model.NumeroParaMatriz;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.CartaoMatrizBloqueioRepository;
import ao.co.intellectus.repository.ControladorMapaMatrizRepository;
import ao.co.intellectus.repository.DocenteRepository;
import ao.co.intellectus.repository.MatrizDocenteRepository;
import ao.co.intellectus.repository.NumeroParaMatrizRepository;

@RestController
@RequestMapping("/cartaoMatriz")
public class ControllerCartaoMatriz {
	@Autowired
	private NumeroParaMatrizRepository numeroParaMatrizRepository;
	@Autowired
    private MatrizDocenteRepository matrizDocenteRepository;
	@Autowired
	private DocenteRepository docenteRepository;
	@Autowired
	private ControladorMapaMatrizRepository controladorMapaMatrizRepository;
	@Autowired
	private CartaoMatrizBloqueioRepository cartaoMatrizBloqueioRepository;
	
	
	// cartaoMatriz/gerarNumero
	@RequestMapping(value = "/gerarNumero", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> gerarNumero() {
		ResponseCliente c = new ResponseCliente();

		NumeroParaMatriz numero;
		for (int i = 1; i <= 5000; i++) {
			NumeroParaMatriz numeroParaMatriz = this.numeroParaMatrizRepository.findByValor(i);
			if (numeroParaMatriz == null) {
				numero = new NumeroParaMatriz();
				numero.setValor(i);
				this.numeroParaMatrizRepository.save(numero);
			}
		}

		c.setResultado(null);
		c.setMensagem("Números gerados com sucesso!");
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/verificarReange/{numeroDocente}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> verificarReange(@PathVariable Integer numeroDocente) {
		ResponseCliente c = new ResponseCliente();

		Iterable<NumeroParaMatriz> numeros = this.numeroParaMatrizRepository.findAll();
		
		Docente docente                    = this.docenteRepository.findOne(numeroDocente);
		List<MatrizDocente> matrizDocente=null;
		if(docente!=null) {			
			matrizDocente  = this.matrizDocenteRepository.findByDocente(docente);
		}
		
		
		Integer maior = 0;
		Integer menor = 0;
		boolean primeiraVolta = true;
		for (NumeroParaMatriz n : numeros) {
			if(primeiraVolta) {				
				menor = n.getValor();
				maior = n.getValor();
			}else {
				if (n.getValor() > maior) {
					maior = n.getValor();
				}

				if (n.getValor() < menor) {
                   menor=n.getValor();
				}
			}
			primeiraVolta = false;
		}

		
       if(!matrizDocente.isEmpty()) {
		    	
		}
       
       if(matrizDocente.isEmpty()) {
    	   MatrizDocente A=new MatrizDocente();
    	   A.setLinha("A");
    	   A.setDocente(docente);
    	   MatrizDocente B=new MatrizDocente();
    	   B.setLinha("B");
    	   B.setDocente(docente);
    	   MatrizDocente C=new MatrizDocente();
    	   C.setLinha("C");
    	   C.setDocente(docente);
    	   MatrizDocente D=new MatrizDocente();
    	   D.setLinha("D");
    	   D.setDocente(docente);
    	   MatrizDocente E=new MatrizDocente();
    	   E.setLinha("E");
    	   E.setDocente(docente);
    	   MatrizDocente F=new MatrizDocente();
    	   F.setLinha("F");
    	   F.setDocente(docente);
    	   MatrizDocente G=new MatrizDocente();
    	   G.setLinha("G");
    	   G.setDocente(docente);
    	   
    	   this.matrizDocenteRepository.save(A);
    	   this.matrizDocenteRepository.save(B);
    	   this.matrizDocenteRepository.save(C);
    	   this.matrizDocenteRepository.save(D);
    	   this.matrizDocenteRepository.save(E);
    	   this.matrizDocenteRepository.save(F);
    	   this.matrizDocenteRepository.save(G);
       }
		
		
		
		
		Random gerador=new Random();
		//Math.random() * 
		boolean naoPodeSair=true;
		int cont=1;
		while(naoPodeSair) {
			
		
			
			List<MatrizDocente> matriz = this.matrizDocenteRepository.findByDocenteOrderByLinha(docente);
			Integer gerado;
			NumeroParaMatriz nz;
			ControladorMapaMatriz cz;
			for (MatrizDocente m : matriz) {
				
				MatrizDocente md = this.matrizDocenteRepository.findOne(m.getId());
				
				//C1
				if(md.getC1()==null) {
					{
						gerado = gerador.nextInt(maior);
						nz = this.numeroParaMatrizRepository.findByValor(gerado);
					}while(nz==null);

					if(nz!=null) {
						md.setC1(gerado);
					    this.matrizDocenteRepository.save(md);
					    
					    cz=new ControladorMapaMatriz();
					    cz.setColuna("1");
					    cz.setLinha(m.getLinha());
					    cz.setDocente(docente);
					    cz.setValor(gerado);
					    
					    this.controladorMapaMatrizRepository.save(cz);
					    this.numeroParaMatrizRepository.delete(nz);
					 }
				}

				//C2
				if(md.getC2()==null) {
					{
						gerado = gerador.nextInt(maior);
						nz = this.numeroParaMatrizRepository.findByValor(gerado);
					}while(nz==null);

					if(nz!=null) {
						md.setC2(gerado);
					    this.matrizDocenteRepository.save(md);
					    
					    cz=new ControladorMapaMatriz();
					    cz.setColuna("2");
					    cz.setLinha(m.getLinha());
					    cz.setDocente(docente);
					    cz.setValor(gerado);
					    
					    this.controladorMapaMatrizRepository.save(cz);
					    this.numeroParaMatrizRepository.delete(nz);
					 }
				}
				
				//C3
				if(md.getC3()==null) {
					{
						gerado = gerador.nextInt(maior);
						nz = this.numeroParaMatrizRepository.findByValor(gerado);
					}while(nz==null);

					if(nz!=null) {
						md.setC3(gerado);
					    this.matrizDocenteRepository.save(md);
					    
					    cz=new ControladorMapaMatriz();
					    cz.setColuna("3");
					    cz.setLinha(m.getLinha());
					    cz.setDocente(docente);
					    cz.setValor(gerado);
					    
					    this.controladorMapaMatrizRepository.save(cz);
					    this.numeroParaMatrizRepository.delete(nz);
					 }
				}
				
				//C4
				if(md.getC4()==null) {
					{
						gerado = gerador.nextInt(maior);
						nz = this.numeroParaMatrizRepository.findByValor(gerado);
					}while(nz==null);

					if(nz!=null) {
						md.setC4(gerado);
					    this.matrizDocenteRepository.save(md);
					    
					    cz=new ControladorMapaMatriz();
					    cz.setColuna("4");
					    cz.setLinha(m.getLinha());
					    cz.setDocente(docente);
					    cz.setValor(gerado);
					    
					    this.controladorMapaMatrizRepository.save(cz);
					    this.numeroParaMatrizRepository.delete(nz);
					 }
				}
				
				//C5
				if(md.getC5()==null) {
					{
						gerado = gerador.nextInt(maior);
						nz = this.numeroParaMatrizRepository.findByValor(gerado);
					}while(nz==null);

					if(nz!=null) {
						md.setC5(gerado);
					    this.matrizDocenteRepository.save(md);
					    
					    cz=new ControladorMapaMatriz();
					    cz.setColuna("5");
					    cz.setLinha(m.getLinha());
					    cz.setDocente(docente);
					    cz.setValor(gerado);
					    
					    this.controladorMapaMatrizRepository.save(cz);
					    this.numeroParaMatrizRepository.delete(nz);
					 }
				}
				
				//C6
				if(md.getC6()==null) {
					{
						gerado = gerador.nextInt(maior);
						nz = this.numeroParaMatrizRepository.findByValor(gerado);
					}while(nz==null);

					if(nz!=null) {
						md.setC6(gerado);
					    this.matrizDocenteRepository.save(md);
					    
					    cz=new ControladorMapaMatriz();
					    cz.setColuna("6");
					    cz.setLinha(m.getLinha());
					    cz.setDocente(docente);
					    cz.setValor(gerado);
					    
					    this.controladorMapaMatrizRepository.save(cz);
					    this.numeroParaMatrizRepository.delete(nz);
					 }
				}
				
				//C7
				if(md.getC7()==null) {
					{
						gerado = gerador.nextInt(maior);
						nz = this.numeroParaMatrizRepository.findByValor(gerado);
					}while(nz==null);

					if(nz!=null) {
						md.setC7(gerado);
					    this.matrizDocenteRepository.save(md);
					    
					    cz=new ControladorMapaMatriz();
					    cz.setColuna("7");
					    cz.setLinha(m.getLinha());
					    cz.setDocente(docente);
					    cz.setValor(gerado);					    
					    this.controladorMapaMatrizRepository.save(cz);
					    this.numeroParaMatrizRepository.delete(nz);
					 }
				}
			}
			cont++;
			if(cont==7)
				naoPodeSair=false;
		}
		
	
		
		
		c.setResultado(null);
		c.setMensagem("Números gerados com sucesso!");
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rastrearMatriz/{numeroDocente}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> rastrearMatriz(@PathVariable Integer numeroDocente) {
		ResponseCliente c = new ResponseCliente();
        
		Docente docente = this.docenteRepository.findOne(numeroDocente);
		
		List<MatrizDocente> md=null;
		if(docente!=null) {
			md = this.matrizDocenteRepository.findByDocente(docente);			
		}
		
		
		Map<Integer, Integer> mapa=new HashMap<Integer,Integer>();
		
		
		Integer chave=1;
		for (MatrizDocente m : md) {
			
			
	
			mapa.put(chave, m.getC1());
			chave++;
			
			//C2			
			mapa.put(chave, m.getC2());
			chave++;
			
			//C3			
			mapa.put(chave, m.getC3());
			chave++;
			
			//C4
			mapa.put(chave, m.getC4());
			chave++;

			//C5			
			mapa.put(chave, m.getC5());
			chave++;
		
			//C6
			mapa.put(chave, m.getC6());
			chave++;

			
			//C7
			mapa.put(chave, m.getC7());
			chave++;

		}

		Random gerador=new Random();
		
		gerador.nextInt(mapa.size());
		
		
		int gerado = gerador.nextInt(mapa.size());
	
		
		Integer valorPego = mapa.get(gerado);
		
		
		//PESQUISAR VALOR DA MATRIZ NO MAPA
		List<ControladorMapaMatriz> controlador = this.controladorMapaMatrizRepository.findByDocente(docente);
		for (ControladorMapaMatriz ctrl : controlador) {
		    ControladorMapaMatriz dezativar = this.controladorMapaMatrizRepository.findOne(ctrl.getId());
		    dezativar.setAtivo(false);
		    this.controladorMapaMatrizRepository.save(dezativar);
		}
		
		
		ControladorMapaMatriz matriz = this.controladorMapaMatrizRepository.findByValorAndDocente(valorPego, docente);
		matriz.setAtivo(true);
		this.controladorMapaMatrizRepository.save(matriz);
		
		
		c.setResultado(matriz.getLinha()+matriz.getColuna());
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/validarNumeroMatriz/{numero}/{numeroDocente}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> validarNumeroMatriz(@PathVariable Integer numero,@PathVariable Integer numeroDocente) {
		ResponseCliente c = new ResponseCliente();
        String mensagem="";
		
		
		Docente docente = this.docenteRepository.findOne(numeroDocente);
		CartaoMatrizBloqueio bloqueio = this.cartaoMatrizBloqueioRepository.findByDocente(docente);
		
		if(bloqueio!=null) {
			if(bloqueio.isBloqueiado()) {
				c.setMensagem("O cartão está bloqueado.");
				c.setCodigo(200);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		}
		
		
		
		ControladorMapaMatriz ctrlMatriz = this.controladorMapaMatrizRepository.findByDocenteAndAtivo(docente,true);
		
		if(ctrlMatriz!=null) {
			if(ctrlMatriz.getValor().equals(numero)) {
				mensagem="Número validado com sucesso!";
			}else {
				mensagem="Número informado é invalido";
	
				
	
				if(bloqueio!=null) {
					Integer vezesErros = bloqueio.getVezesErros();
					vezesErros++;
					bloqueio.setVezesErros(vezesErros);
					if(vezesErros>=4) {
						bloqueio.setBloqueiado(true);
						bloqueio.setDataBloqueio(new Date());
						
					}
					
					this.cartaoMatrizBloqueioRepository.save(bloqueio);
				}else {
					bloqueio=new CartaoMatrizBloqueio();
					bloqueio.setBloqueiado(false);
					bloqueio.setDocente(docente);
					bloqueio.setVezesErros(1);
					this.cartaoMatrizBloqueioRepository.save(bloqueio);
				}
			}
		}else {
			mensagem="Solicite uma chamada a matriz";
		}
		
		
		c.setResultado(null);
		c.setMensagem(mensagem);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}
