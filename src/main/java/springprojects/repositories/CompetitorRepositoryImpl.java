package springprojects.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springprojects.entities.Competitor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public class CompetitorRepositoryImpl implements CompetitorRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void createCompetitor(Competitor competitor) {
        entityManager.persist(competitor);
    }

    @Override
    public Optional<Competitor> findById(int competitorId) {
        return entityManager.createQuery("SELECT c from Competitor c where id = :id", Competitor.class)
        .setParameter("id", competitorId).getResultStream().findAny();
    }

    @Override
    public List<Competitor> findAll() {
        return entityManager
                .createQuery("select new Competitor(c.id,c.firstName,c.lastName,c.country, SUM(e.value),c.gender)" +
                        "from Competitor c join Evaluation e on c.id = e.competitor.id GROUP BY c", Competitor.class)
                .getResultList();
    }

}
