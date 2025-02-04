package racingcar.view;

import racingcar.domain.Car;

import java.util.List;
import java.util.StringJoiner;
import racingcar.domain.Cars;

public class OutputView {

    private static final String REQUEST_CAR_NAME_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String REQUEST_TOTAL_ROUNDS_INPUT_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final String REQUEST_RESTART_GAME_INPUT_MESSAGE = "새로운 게임을 시작하시겠습니까? (y/n)";
    private static final String ROUND_RESULT_TEXT = "실행 결과";
    private static final String CAR_INFO_DELIMITER = " : ";
    private static final String DISTANCE_SYMBOL = "-";
    private static final String WINNERS_JOINER_DELIMITER = ", ";
    private static final String ANNOUNCE_GAME_WINNER_TEXT = "가 최종 우승했습니다.";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private OutputView() {
    }

    public static void printRequestCarNamesInputMessage() {
        print(REQUEST_CAR_NAME_INPUT_MESSAGE);
    }

    public static void printRequestTotalRoundsInputMessage() {
        print(REQUEST_TOTAL_ROUNDS_INPUT_MESSAGE);
    }

    public static void printRequestRestartGameInputMessage() {
        printBlankLine();
        print(REQUEST_RESTART_GAME_INPUT_MESSAGE);
    }

    public static void printGameResult(List<Cars> roundResults) {
        StringJoiner gameResult = new StringJoiner(LINE_SEPARATOR);

        for (Cars roundResult : roundResults) {
            gameResult.add(formatRoundResult(roundResult.getCars()));
        }

        printRoundResultText();
        print(gameResult.toString());
    }

    private static void printRoundResultText() {
        printBlankLine();
        print(ROUND_RESULT_TEXT);
    }

    private static String formatRoundResult(List<Car> cars) {
        StringBuilder roundResult = new StringBuilder();

        for (Car car : cars) {
            roundResult.append(formatCarPositionData(car))
                    .append(LINE_SEPARATOR);
        }
        return roundResult.toString();
    }

    private static String formatCarPositionData(Car car) {
        return car.getName() + CAR_INFO_DELIMITER
                + DISTANCE_SYMBOL.repeat(car.getPosition());
    }

    public static void printWinners(List<Car> winners) {
        StringJoiner winnerNames = new StringJoiner(WINNERS_JOINER_DELIMITER);

        for (Car winner : winners) {
            winnerNames.add(winner.getName());
        }

        print(winnerNames + ANNOUNCE_GAME_WINNER_TEXT);
    }

    private static void print(String string) {
        System.out.println(string);
    }

    private static void printBlankLine() {
        System.out.println();
    }
}
