public class Timer {
    //class for the time elapsing
    private double startTime;
    private double endTime;
    private double timeElapsed;

    public double timeElapsed()
    {
        timeElapsed = endTime - startTime;
        return timeElapsed;
    }

    public void start()
    {
        startTime = System.currentTimeMillis();
    }

    public void stop()
    {
        endTime = System.currentTimeMillis();
    }
}
