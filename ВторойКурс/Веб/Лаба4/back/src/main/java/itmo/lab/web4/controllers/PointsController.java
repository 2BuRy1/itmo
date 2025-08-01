package itmo.lab.web4.controllers;


import itmo.lab.web4.models.Point;
import itmo.lab.web4.models.User;
import itmo.lab.web4.repositories.PointRepository;
import itmo.lab.web4.repositories.UserRepository;
import itmo.lab.web4.services.JwtUtil;
import itmo.lab.web4.services.PointsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
@RestController
public class PointsController {



    @Autowired
    private Logger logger;


    private final JwtUtil jwtUtil;

    private final PointsService pointsService;

    @Autowired
    public PointsController(JwtUtil jwtUtil, PointsService pointsService){
        this.pointsService = pointsService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/points")
    public ResponseEntity<Map<String, ArrayList<Point>>> rofl(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        String jwt = token.substring(7);

        String username = jwtUtil.extractUsername(jwt);

//        User user = userRepository.findByUsername(username).get();
//        //TODO putUseriMethod
//        ArrayList<Point> points = (ArrayList<Point>) pointRepository.findAllByUser_Id(user.getId());
//
//
//        Map<String, ArrayList<Point>> map = new HashMap<>();
//
//        map.put("points", points);




        return ResponseEntity.ok(pointsService.getAllUserPoints(username));

    }

    @PostMapping("/checkHit")
    public ResponseEntity<Point> addPoint(@RequestBody Point point, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws BadRequestException {
        logger.info(point.toString());

        String jwt = token.substring(7);


        String username = jwtUtil.extractUsername(jwt);


        Point checkedPoint = pointsService.checkHit(point, username);
        return ResponseEntity.ok(checkedPoint);


    }


}
