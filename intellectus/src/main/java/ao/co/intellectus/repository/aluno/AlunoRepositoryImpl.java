package ao.co.intellectus.repository.aluno;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import ao.co.intellectus.config.filtro.AlunoFilter;
import ao.co.intellectus.model.Aluno;

public class AlunoRepositoryImpl implements AlunoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Aluno> filtrar(AlunoFilter alunoFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Aluno> criteria = builder.createQuery(Aluno.class);
		
		Root<Aluno> root = criteria.from(Aluno.class);
		
		Predicate [] predicates = criarRestricoes(alunoFilter,builder,root);
		criteria.where(predicates);
		
		TypedQuery<Aluno> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(AlunoFilter alunoFilter, CriteriaBuilder builder, Root<Aluno> root) {
		List<Predicate> predicates=new ArrayList<Predicate>();
		
		
		if(!StringUtils.isEmpty(alunoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%"+alunoFilter.getNome().toLowerCase()+"%"));
		}
       if(alunoFilter.getNumeroDeAluno() !=null) {
			
    	 //predicates.add(arg0);
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
