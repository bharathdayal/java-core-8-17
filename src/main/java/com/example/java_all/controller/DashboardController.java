package com.example.java_all.controller;

import com.example.java_all.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private MetricsService metricsService;


    @GetMapping("/dashboard")
    public String showDashboard(Model model) {

        model.addAttribute("memoryUsed", metricsService.getMetric("jvm.memory.used"));
        model.addAttribute("memoryMax", metricsService.getMetric("jvm.memory.max"));
        model.addAttribute("threadsLive", metricsService.getMetric("jvm.threads.live"));
        model.addAttribute("gcPause", metricsService.getMetric("jvm.gc.pause"));
        model.addAttribute("classesLoaded", metricsService.getMetric("jvm.classes.loaded"));
        model.addAttribute("classesUnloaded", metricsService.getMetric("jvm.classes.unloaded"));
        model.addAttribute("processCpu", metricsService.getMetric("process.cpu.usage"));
        model.addAttribute("systemCpu", metricsService.getMetric("system.cpu.usage"));
        model.addAttribute("uptime", metricsService.getMetric("process.uptime"));
        return "dashboard";

    }

}
