package springprojects.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springprojects.dto.*;
import springprojects.entities.Competitor;
import springprojects.entities.Evaluation;
import springprojects.services.CompetitorService;
import springprojects.services.EvaluationService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@RestController
public class ApiController {
    @Autowired
    CompetitorService competitorService;
    @Autowired
    EvaluationService evaluationService;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/createCompetitor")
    public ApiResponse createCompetitor(@RequestBody CompetitorRequest competitorRequest) {
        Competitor competitor = new Competitor(competitorRequest.getFirstName(), competitorRequest.getLastName(),
                competitorRequest.getCountry(), competitorRequest.getGender());
        competitorService.createCompetitor(competitor);

        return new ApiResponse(ResponseCode.SUCCESS, "Competitor has been successfully created");
    }

    @PostMapping("/evaluate")
    public ApiResponse createEvaluation(@RequestBody EvaluationRequest evaluationRequest) {
        Optional<Evaluation> evaluation = evaluationService.createEvaluation(evaluationRequest.getCompetitorId(),
                evaluationRequest.getValue());
        if (evaluation.isPresent()) {
            return new ApiResponse(ResponseCode.SUCCESS, "Evaluation has been created!");
        } else {
            return new ApiResponse(ResponseCode.FAIL, "Something wrong!");
        }
    }

    @GetMapping("/total/{competitorId}")
    public String totalEvaluations(@PathVariable String competitorId) throws JsonProcessingException {
        Optional<Competitor> competitor = competitorService.findById(Integer.valueOf(competitorId));

        if (!competitor.isPresent()) {
            return new ApiResponse(ResponseCode.FAIL, "Something is wrong").toString();
        }
        TotalResponse totalResponse = new TotalResponse();
        totalResponse.setFirstName(competitor.get().getFirstName());
        totalResponse.setLastName(competitor.get().getLastName());

        double totalRate = 0;
        for (Evaluation evaluation : competitor.get().getEvaluation()) {
            totalRate += evaluation.getValue();
        }
        totalResponse.setTotal(totalRate);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(totalResponse);
    }

    @GetMapping("/getAllCompetitors")
    public String getAllCompetitors() {
        List<Competitor> competitors =
                competitorService.getAllCompetitors();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(competitors);
        } catch (JsonProcessingException e) {
            return "FAIL";
        }
    }

    @GetMapping("/getWinner")
    public String getWinner() throws JsonProcessingException {
        List<Competitor> competitors =
                competitorService.getAllCompetitors();

        WinnerResponse winnerResponse = new WinnerResponse();
        //  Competitor winner = new Competitor();

//        for (Competitor competitor : competitors) {
//            if (competitor.getTotalRate() > maxTotalRate) {
//                maxTotalRate = competitor.getTotalRate();
//            }
//        }
        //competitors.stream().forEach(competitor->System.out.println(competitor));

        //competitors.stream().sorted(Comparator.comparingDouble(Competitor::getTotalRate)).collect(Collectors.toList());


       /* List<Double> doubleList = new ArrayList<>();

        for (Competitor competitor:competitors
             ) {
            doubleList.add(competitor.getTotalRate());
        }*/

       /* double maxTotalRate =
                competitors.stream()
                .mapToDouble(value -> value.getTotalRate())
                .max()
                .orElse(0);*/

        double maxTotalRate =
                competitors.stream()
                        .mapToDouble(new ToDoubleFunction<Competitor>() {
                            @Override
                            public double applyAsDouble(Competitor competitor) {
                                return competitor.getTotalRate();
                            }
                        })
                        .max()
                        .orElse(0);


             /* competitors
                      .stream()
                      .map(Competitor::getTotalRate)
                      .max(Comparator.naturalOrder())
                      .orElse((double) 0);*/

            return competitors
                    .stream()
                    .filter(
                            //competitor->competitor.getTotalRate() == matTotalRate;
                            new Predicate<Competitor>() {
                        @Override
                        public boolean test(Competitor competitor) {
                            return competitor.getTotalRate() == maxTotalRate;
                        }
                    })
                    .sorted(Comparator.comparingInt(Competitor::getId))
                    .collect(Collectors.toList()).toString();
      /*  for (Competitor competitor : competitors) {
            if ( competitor.getTotalRate() == maxTotalRate) {
                winnerResponse.setFirstName(competitor.getFirstName());
                winnerResponse.setLastName(competitor.getLastName());
                winnerResponse.setCountry(competitor.getCountry());
                winnerResponse.setMaxTotalRate(maxTotalRate);


            }
        }*/
        //return winnerResponse.toString();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            return mapper.writeValueAsString(winnerResponse);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return "FAIL";
//        }
    }
}


