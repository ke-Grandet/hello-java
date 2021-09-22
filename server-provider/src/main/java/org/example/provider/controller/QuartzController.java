package org.example.provider.controller;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    Scheduler scheduler;

    @GetMapping("/pause/{triggerGroup}/{triggerName}")
    public void pause(@PathVariable String triggerGroup, @PathVariable String triggerName) {
        try {
            scheduler.pauseTrigger(new TriggerKey(triggerName, triggerGroup));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
