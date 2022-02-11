package racingcar.domain;

import racingcar.util.RandomUtils;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static racingcar.util.ValidatorUtils.validateNoDuplicates;
import static racingcar.util.ValidatorUtils.validateNotBlank;
import static racingcar.util.ValidatorUtils.validateNotOverFiveCharacters;
import static racingcar.util.ValidatorUtils.validatePositiveInt;

public class GameManager {

    List<Car> cars = new ArrayList<>();
    int totalRounds;

    public void run() {
        String[] carNames = requestAndSplitCarNames();
        initCars(carNames);
        totalRounds = requestAndParseTotalRounds();

        playAllRounds();
        OutputView.printWinners(getWinners());
    }

    private List<Car> getWinners() {
        List<Car> winners = new ArrayList<>();
        int maxPosition = 0;

        for (Car car : cars) {
            int currentPosition = car.getPosition();
            if (maxPosition > currentPosition) {
                continue;
            }
            if (maxPosition < currentPosition) {
                winners.clear();
            }
            winners.add(car);
            maxPosition = currentPosition;
        }
        return winners;
    }

    private void playAllRounds() {
        OutputView.printRoundResultText();
        for (int i = 0; i < totalRounds; i++) {
            playRound();
            OutputView.printRoundResult(cars);
        }
    }

    private void playRound() {
        for (Car car : cars) {
            car.goOrNot(RandomUtils.generateNumber());
        }
    }

    private String[] requestAndSplitCarNames() {
        OutputView.printCarNamesInputRequestMessage();
        String userInput = InputView.requestUserInput();
        return getValidateCarNames(userInput);
    }

    private String[] getValidateCarNames(String userInput) {
        String[] carNames = splitUserInputByComma(userInput);

        for (String name : carNames) {
            validateNotBlank(name);
            validateNotOverFiveCharacters(name);
        }
        validateNoDuplicates(carNames);

        return carNames;
    }

    private String[] splitUserInputByComma(String userInput) {
        return userInput.split(",");
    }

    private void initCars(String[] carNames) {
        for (String name : carNames) {
            cars.add(new Car(name));
        }
    }

    private int requestAndParseTotalRounds() {
        OutputView.printTotalRoundsInputRequestMessage();
        String userInput = InputView.requestUserInput();
        return getValidateTotalRounds(userInput);
    }

    private int getValidateTotalRounds(String userInput) {
        int totalRoundsInput = Integer.parseInt(userInput);
        validatePositiveInt(totalRoundsInput);
        return totalRoundsInput;
    }
}
