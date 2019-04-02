package springprojects.services;

import springprojects.entities.Competitor;

import java.util.List;
import java.util.Optional;

public interface CompetitorService {
    public void createCompetitor (Competitor competitor);
    public Optional<Competitor> findById (int competitorId);
    public List<Competitor> getAllCompetitors ();
}
