package kinaszw;

class Stoper {
    private long start;
    private long stop;
    private String nazwa;


    public void start(){
        start = System.currentTimeMillis();
    }

    public void stop(){
        stop = System.currentTimeMillis();
    }

    public double getWynik(){
        return (stop - start) / 1000.0;
    }

}