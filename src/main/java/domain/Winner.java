package domain;

import java.util.ArrayList;
import java.util.List;

public class Winner {
    private final List<Car> carList;

    Winner(List<Car> carList) {
        this.carList = carList;
    }

    public List<String> getWinners() {
        List<String> winnerList = new ArrayList<>();

        for (Car car : carList) {
            addList(winnerList, car);
        }

        return winnerList;
    }

    private void addList(List<String> winner, Car car) {
        if (car.isWinner(getMaxDistance())) {
            winner.add(car.getName());
        }
    }

    private int findMaxPosition(int position, int maxPosition) {
        if (position > maxPosition) {
            return position;
        }

        return maxPosition;
    }

    private int getMaxDistance() {
        int maxDistance = 0;

        for (Car car : carList) {
            maxDistance = findMaxPosition(car.getPosition(), maxDistance);
        }

        return maxDistance;
    }
}
