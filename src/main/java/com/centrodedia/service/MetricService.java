package com.centrodedia.service;
import com.centrodedia.model.Metric;
import com.centrodedia.model.MetricCategory;
import com.centrodedia.model.User;
import com.centrodedia.repository.CentreResponsibleRepository;
import com.centrodedia.repository.MetricRepository;
import com.centrodedia.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MetricService {

    private final MetricRepository metricRepository;
    private final UserRepository userRepository;

    public MetricService(MetricRepository metricRepository, UserRepository userRepository) {
        this.metricRepository = metricRepository;
        this.userRepository = userRepository;
    }

    public List<Metric> listAll() {
        return metricRepository.findAll();
    }

    public Optional<Metric> findById(Long id) {
        return metricRepository.findById(id);

    }

    public List<Metric> findByDateTime(LocalDateTime datetime) {
        return metricRepository.findByDateTime(datetime);
    }

    public List<Metric> findByMetricCategory(MetricCategory metricCategory) {
        return metricRepository.findByMetricCategory(metricCategory);
    }

    public List<Metric> findByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return metricRepository.findByUser(user);
    }

    @Transactional
    public Metric create(Metric metric) {
        return metricRepository.save(metric);
    }

    @Transactional
    public Metric update(Long id, Metric datosNuevos) {
        Metric existente = metricRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No metric found with id: " + id));
        existente.setDateTime(datosNuevos.getDateTime());
        existente.setMetricCategory(datosNuevos.getMetricCategory());
        existente.setValue(datosNuevos.getValue());
        return metricRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        if (!metricRepository.existsById(id)) {
            throw new RuntimeException("No metric found with id: " + id);
        }
        metricRepository.deleteById(id);
    }
}
