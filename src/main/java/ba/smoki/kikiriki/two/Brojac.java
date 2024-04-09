package ba.smoki.kikiriki.two;

public class Brojac {
    private int count;

    public synchronized void increment(){
        count++;
    }

    public int getCount() {
        return count;
    }
}
