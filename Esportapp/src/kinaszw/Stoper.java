package kinaszw;

class Stoper {
    private long start;
    private long stop;

    void start(){
        start = System.currentTimeMillis();
    }

    void stop(){
        stop = System.currentTimeMillis();
    }

    double getWynik(){
        return (stop - start) / 1000.0;
    }

}