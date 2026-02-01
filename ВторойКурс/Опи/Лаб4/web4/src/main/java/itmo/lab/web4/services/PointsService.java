package itmo.lab.web4.services;


import itmo.lab.web4.mBeans.MxMontanaBean;
import jakarta.annotation.PostConstruct;
import org.apache.coyote.BadRequestException;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itmo.lab.web4.models.Point;
import itmo.lab.web4.models.User;
import itmo.lab.web4.repositories.PointRepository;
import itmo.lab.web4.repositories.UserRepository;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PointsService {

    private final Validator validator;


    private final AreaChecker areaChecker;


    private final PointRepository pointRepository;


    private final UserRepository userRepository;

    private final MxMontanaBean mxMontanaBean;

    private List<byte[]> buffs;


    @Autowired
    public PointsService(Validator validator, AreaChecker areaChecker, PointRepository pointRepository, UserRepository userRepository, MxMontanaBean mxMontanaBean){
        this.validator = validator;
        this.areaChecker = areaChecker;
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
        this.mxMontanaBean = mxMontanaBean;
    }


    @PostConstruct
    void init() throws InterruptedException {
        buffs = new ArrayList<>();

        Thread.sleep(500);


        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleAtFixedRate(() -> {
            try {
                for (int i = 0; i < 2; i++) {


                    buffs.add(new byte[1024 * 1024 * 4]);
                }


                System.out.println("Freeing memory...");
                buffs.clear();

              System.gc();


            } catch (OutOfMemoryError e) {
                System.err.println("OOM caught. Clearing everything.");
                buffs.subList(0, 10).clear();

            }
        }, 0, 2, TimeUnit.SECONDS);
    }






    public Point checkHit(Point point, String username) throws BadRequestException {


        if(!validator.validate(point)) {


            mxMontanaBean.setIsOutOfBounds(true);


            throw new BadRequestException("Data is out of range");

        }

        else {




            User user = userRepository.findByUsername(username).get();

            point.setUser(user);

            point.setStatus(areaChecker.isInTheSpot(point));

            mxMontanaBean.update(point.getStatus());


            pointRepository.save(point);

            return point;
        }
    }



    public Map<String, ArrayList<Point>> getAllUserPoints(String username){

        User user = userRepository.findByUsername(username).get();

        ArrayList<Point> points = (ArrayList<Point>) pointRepository.findAllByUser_Id(user.getId());


        Map<String, ArrayList<Point>> map = new HashMap<>();

        map.put("points", points);

        return map;


    }

}
