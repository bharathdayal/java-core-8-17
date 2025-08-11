package com.example.java_all.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

@RestController
@RequestMapping("/deadlock-details")
public class DeadlockDetailsController {

    @GetMapping()
    public ResponseEntity<String> getDeadlockDetails() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] ids = bean.findDeadlockedThreads();

        if (ids != null && ids.length > 0) {
            StringBuilder report = new StringBuilder("Deadlock detected:\n\n");
            ThreadInfo[] infos = bean.getThreadInfo(ids, true, true);

            for (ThreadInfo info : infos) {
                report.append("🔸 Thread: ").append(info.getThreadName()).append("\n")
                        .append("  - State: ").append(info.getThreadState()).append("\n")
                        .append("  - Waiting to lock: ").append(info.getLockName()).append("\n")
                        .append("  - Owned by: ").append(info.getLockOwnerName()).append("\n")
                        .append("  - Stack Trace:\n");

                for (StackTraceElement e : info.getStackTrace()) {
                    report.append("    at ").append(e).append("\n");
                }
                report.append("\n");
            }

            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(report.toString());
        }

        return ResponseEntity.ok("✅ No deadlocks detected");
    }
}
