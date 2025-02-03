package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.ProgDisciplinaTurma;

public interface ProgDisciplinaTurmaRepository extends JpaRepository<ProgDisciplinaTurma,Integer>{
    public List<ProgDisciplinaTurma> findByAnoLectivoAndDisciplinaId(Integer anoLectivo,Integer disciplina);
}
