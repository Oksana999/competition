package springprojects.repositories;

import springprojects.entities.Evaluation;

import java.util.List;

public interface EvaluationRepository {
    public void createEvaluation (Evaluation evaluation);
    public List<Evaluation> evaluations (int competitorId);
}
