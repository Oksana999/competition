package springprojects.services;

import springprojects.entities.Evaluation;

import java.util.Optional;

public interface EvaluationService {
     Optional<Evaluation> createEvaluation (int competitorId, double value);
}
