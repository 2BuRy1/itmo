package itmo.lab.web4.services;

import itmo.lab.web4.models.Point;
import itmo.lab.web4.models.User;
import itmo.lab.web4.repositories.PointRepository;
import itmo.lab.web4.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PointsService {

    private final Validator validator;


    private final AreaChecker areaChecker;


    private final PointRepository pointRepository;


    private final UserRepository userRepository;


    @Autowired
    public PointsService(Validator validator, AreaChecker areaChecker, PointRepository pointRepository, UserRepository userRepository){
        this.validator = validator;
        this.areaChecker = areaChecker;
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
    }


    public Point checkHit(Point point, String username) throws BadRequestException {


        if(!validator.validate(point)) throw new BadRequestException();

        User user = userRepository.findByUsername(username).get();

        point.setUser(user);

        point.setStatus(areaChecker.isInTheSpot(point));

        pointRepository.save(point);

        return point;

    }



    public Map<String, ArrayList<Point>> getAllUserPoints(String username){

        User user = userRepository.findByUsername(username).get();

        ArrayList<Point> points = (ArrayList<Point>) pointRepository.findAllByUser_Id(user.getId());


        Map<String, ArrayList<Point>> map = new HashMap<>();

        map.put("points", points);

        return map;


    }

}
