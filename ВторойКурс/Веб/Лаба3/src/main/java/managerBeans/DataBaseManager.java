package managerBeans;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import models.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DataBaseManager implements Serializable {

    @PersistenceContext(name = "myApp")
    private EntityManager entityManager;

    @Transactional
    public void insertIntoTable(Point point) {
        if (point != null) {
            entityManager.persist(point);
        }
    }

    @Transactional
    public ArrayList<Point> getPoints() {
        TypedQuery<Point> query = entityManager.createQuery("SELECT p FROM Point p", Point.class);
        if(!query.getResultList().isEmpty())  return (ArrayList<Point>) query.getResultList();
        return new ArrayList<>();
    }

}