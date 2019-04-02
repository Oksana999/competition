package springprojects.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluationRequest {
    private int competitorId;
    private double value;
}
