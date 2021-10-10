package Threads;

public class Jucator extends Main implements Runnable {

    private static String echipa;
    public static boolean isPlaying = false;
    private int nr;
    private String s = "";

//    Scanner sc = new Scanner(System.in);

    public Jucator(String name, int nr) {
//        isPlaying=new AtomicBoolean(true);
        this.echipa = name;
        this.nr = nr;
//        this.isPlaying=isPlaying;

    }


    public int getNr() {
        return nr;
    }

    public String getEchipa() {
        return echipa;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "echipa='" + echipa + '\'' +
                ", nr=" + nr +
                '}';
    }

    @Override
    public void run() {

        synchronized (this) {
            while (isActive) {

                    System.out.println("Thread " + Thread.currentThread().getId() +
                            " | Playing " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            try {
                System.out.println("Stopping!");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!Thread.currentThread().isAlive() && reload) notifyAll();

    }


    public void reloadGame() {
        if (reload) {
            synchronized (this) {
                notifyAll();
            }
        }
    }

}






