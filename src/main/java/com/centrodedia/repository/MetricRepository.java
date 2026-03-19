package com.centrodedia.repository;

import com.centrodedia.model.Metric;
import com.centrodedia.model.MetricCategory;
import com.centrodedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
    List<Metric> findByDateTime(LocalDateTime datetime);

    List<Metric> findByMetricCategory(MetricCategory metricCategory);

    List<Metric> findByUser(User user);
}
