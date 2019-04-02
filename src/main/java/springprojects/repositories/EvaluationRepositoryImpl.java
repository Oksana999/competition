package springprojects.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springprojects.entities.Evaluation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class EvaluationRepositoryImpl implements EvaluationRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void createEvaluation(Evaluation evaluation) {
        entityManager.persist(evaluation);
    }

    @Override
    public List<Evaluation> evaluations(int competitorId) {
        //e.competitor.id = competitor_id
        return entityManager.createQuery("SELECT e from Evaluation e where e.competitor.id = : id",
                Evaluation.class).setParameter("id", competitorId).getResultList();
    }
}
