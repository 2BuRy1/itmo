package itmo.lab.web4.mBeans;

import itmo.lab.web4.repositories.PointRepository;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Setter
@Component
@Scope("singleton")
@ManagedResource(objectName = "itmo.lab.web4:type=MxMontanaBean")
public class MxMontanaBean extends NotificationBroadcasterSupport implements MxMontanaBeanMBean {

    public static final String OUT_OF_BOUNDS_NOTIFICATION = "itmo.lab.web4.mBeans.outOfBounds";

    @Autowired
    private PointRepository pointRepository;

    private AtomicLong hits = new AtomicLong();
    private AtomicLong overall = new AtomicLong();
    private AtomicBoolean isOutOfBounds = new AtomicBoolean();
    private long sequenceNumber = 1;

    @PostConstruct
    public void init() {
        overall.set(pointRepository.count());
        hits.set(pointRepository.countAllByStatus(true));

    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{OUT_OF_BOUNDS_NOTIFICATION};

        return new MBeanNotificationInfo[]{
                new MBeanNotificationInfo(
                        types,
                        Notification.class.getName(),
                        "Out of bounds notification"
                )
        };
    }

    @Override
    @ManagedAttribute
    public long getOverall() {
        return this.overall.get();
    }

    @Override
    @ManagedAttribute
    public long getHits() {
        return this.hits.get();
    }

    @Override
    @ManagedAttribute
    public String getPercentage() {
        long o = overall.get();
        long h = hits.get();
        long m = o - h;
        if (o == 0) return "No values available";
        return String.format("%d", (int)( (double) m/o * 100));
    }

    @ManagedAttribute
    public void setIsOutOfBounds(boolean b) {
        isOutOfBounds.set(b);
        if(isOutOfBounds.get()){
            sendOutOfBoundsNotification();
        }
        isOutOfBounds.set(false);

    }

    @ManagedAttribute
    public boolean isOutOfBounds() {
        return this.isOutOfBounds.get();
    }

    private void sendOutOfBoundsNotification() {
        try {
            Notification notification = new Notification(
                    OUT_OF_BOUNDS_NOTIFICATION,
                    this,
                    sequenceNumber++,
                    System.currentTimeMillis(),
                    "Oh now I can see, that there is a mistake!!: "
            );
            sendNotification(notification);
        } catch (Exception e) {
            System.err.println("Notification failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(boolean status) {
        if(status){
            this.hits.incrementAndGet();
        }
        this.overall.incrementAndGet();
    }
}