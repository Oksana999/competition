package springprojects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springprojects.entities.Competitor;
import springprojects.repositories.CompetitorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CompetitorServiceImpl implements CompetitorService {
    @Autowired
    private CompetitorRepository competitorRepository;
    @Autowired
    private  CompetitorService competitorService;
    @Override
    public void createCompetitor(Competitor competitor) {
        competitorRepository.createCompetitor(competitor);
    }

    @Override
    public Optional<Competitor> findById(int competitorId) {
        return competitorRepository
                .findById(competitorId);
    }

    @Override
    public List<Competitor> getAllCompetitors() {

        return competitorRepository.findAll();

    }

//    @Override
//    public List<Competitor> getAllCompetitors() {
//       // List<Competitor>competitors = new ArrayList<>();
//        return competitorRepository.findAll();
//    }
}
