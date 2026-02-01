package itmo.lab.web4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import itmo.lab.web4.models.Point;
import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByUser_Id(long id);

    Long countAllByStatus(boolean status);
}
