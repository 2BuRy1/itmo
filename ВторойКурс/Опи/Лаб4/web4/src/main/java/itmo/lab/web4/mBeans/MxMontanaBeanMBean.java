package itmo.lab.web4.mBeans;

public interface MxMontanaBeanMBean {
    long getOverall();
    long getHits();
    String getPercentage();
    void setIsOutOfBounds(boolean b);
}