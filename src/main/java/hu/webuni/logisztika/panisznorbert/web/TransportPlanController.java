package hu.webuni.logisztika.panisznorbert.web;

import hu.webuni.logisztika.panisznorbert.model.Milestone;
import hu.webuni.logisztika.panisznorbert.model.TransportPlan;
import hu.webuni.logisztika.panisznorbert.service.MilestoneService;
import hu.webuni.logisztika.panisznorbert.service.TransportPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

    @Autowired
    TransportPlanService transportPlanService;

    @Autowired
    MilestoneService milestoneService;

    @PostMapping("/{id}/delay")
    public void delay(@PathVariable long transportPlanId, @RequestBody long milestoneId, @RequestBody int delay){

        TransportPlan transportPlan = transportPlanService.findById(transportPlanId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Milestone milestone = milestoneService.findById(milestoneId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!transportPlanService.transportPlanIncludeMilestone(transportPlan, milestoneId, delay)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        transportPlanService.setPriceFromDelay(transportPlan, delay);

    }
}
