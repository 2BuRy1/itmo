package Interfaces;

public interface ForEngine {
    void testEngine();
    default String smoothAndReliable(){
        return "ровно и надежно";
    }
    void buzz();
    void rattle();
}

