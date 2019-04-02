package springprojects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import springprojects.entities.Competitor;
import springprojects.entities.Evaluation;
import springprojects.repositories.EvaluationRepository;

import java.util.Optional;

@Service
public class EvaluationServiceImpl implements EvaluationService {

        @Autowired
        CompetitorService competitorService;
        @Autowired
        EvaluationRepository evaluationRepository;

        @Value("${max.evaluations}")
                private String maxEvaluations;

    @Override
    public Optional<Evaluation> createEvaluation(int competitorId, double value) {
        if(competitorId == 0 || value == 0){
            return  Optional.empty();
        }
        Optional<Competitor> competitorOpt = competitorService.findById(competitorId);

        if(!competitorOpt.isPresent()){
            return Optional.empty();
        }
        if (evaluationRepository.evaluations(competitorId).size()>=(Integer.valueOf(maxEvaluations))){
            return Optional.empty();
        }
        Evaluation evaluation = new Evaluation(competitorOpt.get(), value);
        evaluationRepository.createEvaluation(evaluation);

        return Optional.of(evaluation);
    }
}
