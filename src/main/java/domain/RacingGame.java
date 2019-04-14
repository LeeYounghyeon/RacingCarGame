package domain;

import java.util.*;
import java.util.regex.Pattern;

public class RacingGame {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final String INPUT_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String EMPTY_ERROR_MESSAGE = "아무것도 입력되지않았습니다.";
    private static final String OVER_ERROR_MESSAGE = "중복값이 있습니다.";
    private static final String COMMA_ERROR_MESSAGE = ",가 포함되지 않았습니다.";
    private static final String CAR_NUMBER_ERROR_MESSAGE = ",가 포함되지 않았습니다.";
    private static final String INPUT_ROUND_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "문자가 입력되었습니다.";
    private static final String CAR_NAME_LENGTH_ERROR_MESSAGE = "자동차 이름은 다섯글자이하입니다.";
    private static final String WINNER_MESSAGE = "가 최종 우승했습니다.";
    private static final String GAME_RESULT = "실행 결과";
    private static final String PATTERN = "^[0-9]*$";
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";
    private static final int CAR_NAME_MIN_LENGTH = 5;
    private static final int MAX_NUMBER = 9;

    public void run() {
        Winner winner;
        List<Car> carList = getCarNameList();
        int round = getRound();

        printAllResult(round, carList);
        winner = new Winner(carList);

        printWinResult(winner);
    }

    private void printWinResult(Winner winner) {
        List<String> winnerList = winner.getWinners();
        System.out.println(String.join(COMMA, winnerList) + WINNER_MESSAGE);
    }


    private void printAllResult(int round, List<Car> carList) {
        System.out.println(GAME_RESULT);

        for (int i = 0; i < round; i++) {
            getMove(carList);
            printMoveResult(carList);
        }
    }

    private String getHyphen(int position) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < position; i++) {
            sb.append(HYPHEN);
        }

        return sb.toString();
    }

    private void printMoveResult(List<Car> carList) {
        for (Car car : carList) {
            System.out.println(car.getName() + " : " + getHyphen(car.getPosition()));
        }

        System.out.println();
    }

    private void getMove(List<Car> carList) {
        for (Car car : carList) {
            car.checkMove(getRandomNumber());
        }
    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(MAX_NUMBER);
    }

    private boolean checkCarNameLength(String[] input) {
        List<Boolean> result = addResult(input);

        if (result.contains(true)) {
            System.err.println(CAR_NAME_LENGTH_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private List<Boolean> addResult(String[] input) {
        List<Boolean> result = new ArrayList<>();

        for (String name : input) {
            result.add(checkLength(name));
        }

        return result;
    }

    private boolean checkLength(String name) {
        return name.length() > CAR_NAME_MIN_LENGTH;
    }

    private boolean checkInteger(String input) {
        if (!Pattern.matches(PATTERN, input)) {
            System.err.println(NOT_INTEGER_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private int getRound() {
        System.out.println(INPUT_ROUND_MESSAGE);
        String round = SCAN.nextLine();

        if (checkInteger(round) || isEmpty(round)) {
            return getRound();
        }

        return Integer.parseInt(round);
    }

    private boolean checkCarLength(String[] input) {
        if (input.length == 1) {
            System.err.println(CAR_NUMBER_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private boolean checkSplit(String input) {
        if (!input.contains(COMMA)) {
            System.err.println(COMMA_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private List<Car> addCarList(String[] nameArray) {
        List<Car> carNameList = new ArrayList<>();

        for (String name : nameArray) {
            carNameList.add(new Car(name));
        }

        return carNameList;
    }

    private boolean isContains(String[] carArray) {
        Set<String> carSet = new HashSet<>(Arrays.asList(carArray));

        if (carSet.size() != carArray.length) {
            System.err.println(OVER_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private List<Car> getCarNameList() {
        String[] nameArray = inputName().split(COMMA);
        List<Car> carNameList = addCarList(nameArray);

        if (isContains(nameArray) || checkCarNameLength(nameArray) || checkCarLength(nameArray)) {
            return getCarNameList();
        }

        return carNameList;
    }

    private boolean isEmpty(String input) {
        if (input.isEmpty()) {
            System.err.println(EMPTY_ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private String inputName() {
        System.out.println(INPUT_NAME_MESSAGE);
        String names = SCAN.nextLine();

        if (isEmpty(names) || checkSplit(names)) {
            return inputName();
        }

        return names;
    }
}
