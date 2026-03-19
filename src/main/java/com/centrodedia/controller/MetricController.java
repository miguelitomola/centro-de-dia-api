package com.centrodedia.controller;

import com.centrodedia.model.Metric;
import com.centrodedia.model.MetricCategory;
import com.centrodedia.service.MetricService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping
    public List<Metric> getMetrics() {
        return metricService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metric> getMetricById(@PathVariable Long id) {
        return metricService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{id}")
    public List<Metric> getMetricsByUserId(@PathVariable Long id) {
        return metricService.findByUserId(id);
    }

    @GetMapping("/date")
    public List<Metric> getMetricsByDateTime(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return metricService.findByDateTime(dateTime);
    }

    @GetMapping("/category/{category}")
    public List<Metric> getMetricsByCategory(@PathVariable MetricCategory category) {
        return metricService.findByMetricCategory(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Metric createMetric(@Valid @RequestBody Metric metric) {
        return metricService.create(metric);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Metric> updateMetric(@PathVariable Long id, @Valid @RequestBody Metric metric) {
        try {
            Metric updatedMetric = metricService.update(id, metric);
            return ResponseEntity.ok(updatedMetric);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMetric(@PathVariable Long id) {
        metricService.delete(id);
    }
}
