public class Clock extends Thread {
    private int timestamp;
    private String owner;

    public Clock(int timestamp, String owner) {
        this.timestamp = timestamp;
        this.owner = owner;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                this.timestamp++;
                System.out.println(this.owner + " - " + this.timestamp + " seconds");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
