package domain;

public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void checkMove(int number) {
        if (number >= 4) {
            position++;
        }
    }

    public boolean isWinner(int maxPosition) {
        return this.position == maxPosition;
    }
}
