package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.RegistroDocumentos;
import ao.co.intellectus.model.TipoDeDeclaracao;

public interface RegistroDocumentoRepository extends CrudRepository<RegistroDocumentos,Integer>{
	public List<RegistroDocumentos> findByAluno(Aluno aluno);
	public List<RegistroDocumentos> findByAnoDeclaracao(Integer anoDeclaracao);
	public RegistroDocumentos findByAlunoAndTipoDeclaracaoAndRetirado(Aluno aluno,TipoDeDeclaracao tipoDeclaracao,boolean estado);
	public List<RegistroDocumentos> findByGuiaPagamento(Guia guia);
	
	public List<RegistroDocumentos> findByNumeroDeclaracao(Integer numeroDeclaracao);
	
	public List<RegistroDocumentos> findByNumeroDeclaracaoAndAnoDeclaracao(Integer numeroDeclaracao,Integer anoDeclaracao);
	public Page<RegistroDocumentos> findByAlunoNumeroDeAluno(String numeroAluno, Pageable paginacao);
	public Page<RegistroDocumentos> findByAlunoNumeroDeAlunoAndTipoDeclaracaoDescricao(String numeroAluno,
			String tipoDocumento, Pageable paginacao);
	
	@Query(value="SELECT * FROM T_REGISTRO_DOCUMENTOS WHERE CODIGO_GUIA_PAGAMENTO = :guia_pagamento",nativeQuery=true)
    public RegistroDocumentos guiaPagamento(@Param("guia_pagamento")Integer guia_pagamento);
	
	/*@Query(value="SELECT * FROM V_LISTAGEM_DOCUMENTOS_MESTRADO WHERE DATA_EMISSAO BETWEEN :data1 AND :data2",nativeQuery=true)
	public List<RegistroDocumentos> buscarTodos(@Param("data1") Date data1, @Param("data2") Date data2);
	
	@Query(value="SELECT * FROM V_LISTAGEM_DOCUMENTOS_MESTRADO_LT WHERE DATA_EMISSAO BETWEEN :data1 AND :data2",nativeQuery=true)
	public List<RegistroDocumentos> Listagem_7(@Param("data1") Date data1, @Param("data2") Date data2);
	
	@Query(value="SELECT * FROM V_LISTAGEM_DOCUMENTOS_MESTRADO_LT  WHERE NOME LIKE :Nome% AND DATA_EMISSAO BETWEEN :data1 AND :data2",nativeQuery=true)
	public List<RegistroDocumentos> Listagem_7_2(@Param("data1") Date data1, @Param("data2") Date data2, @Param("Nome") String Nome);
	
	@Query(value="SELECT * FROM V_LISTAGEM_DOCUMENTOS_SUBCONJUNTO_MESTRADO WHERE DATA_EMISSAO BETWEEN :data1 AND :data2 AND SUBSTRING(NOME, 1,1)BETWEEN :letra1 AND :letra2 ",nativeQuery=true)
	public List<RegistroDocumentos> listagem_7_3(@Param("data1") Date data1, @Param("data2") Date data2, @Param("letra1") Character letra1, @Param("letra2") Character letra2);
	
	@Query(value="SELECT * FROM V_HISTORICO_CERTIFICADO_MESTRADO",nativeQuery=true)
	public List<RegistroDocumentos> buscarHistoricoCertificado();*/
	
	
	
}
