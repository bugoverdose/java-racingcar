package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static common.DisplayFormat.PARAMETERIZED_TEST_DISPLAY_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private static final int TOTAL_ROUNDS = 5;

    private final CarRepository carRepository = new CarRepository();
    private Game game;

    @BeforeEach
    void beforeEach() {
        clearDatabase();
        game = new Game(new String[]{"pobi", "jeong", "roma"}, TOTAL_ROUNDS);
    }

    @DisplayName("getWinners 메서드는 가장 position 값이 큰 car 객체들을 반환한다")
    @Test
    void getWinners() {
        for (int i = 0; i < TOTAL_ROUNDS; i++) {
            game.playRound();
        }

        List<Car> winners = game.getWinners();
        int maxPosition = winners.get(0).getPosition();

        for (Car car : game.getCars()) {
            assertThat(car.getPosition()).isLessThanOrEqualTo(maxPosition);
        }
    }

    @DisplayName("getWinners 메서드는 cars 인스턴스가 존재하지 않는 경우 예외를 발생시키지 않는다")
    @Test
    void getWinners_noErrorOnNoCars() {
        clearDatabase();

        List<Car> winners = game.getWinners();
        List<Car> emptyList = new ArrayList<>();

        assertThat(winners).isEqualTo(emptyList);
    }

    @DisplayName("isOver 메서드는 totalRounds와 currentRounds의 일치 여부를 반환한다.")
    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @CsvSource(value = {"4,false", "5,true"}, delimiter = ',')
    void isOver(String roundNumString, String resultString) {
        int roundNum = Integer.parseInt(roundNumString);
        boolean result = Boolean.parseBoolean(resultString);

        for (int i = 0; i < roundNum; i++) {
            game.playRound();
        }

        assertThat(game.isOver()).isEqualTo(result);
    }

    @DisplayName("clearCars 메서드는 carsRepository의 cars 배열을 비운다.")
    @Test
    void clearCars() {
        assertThat(game.getCars().size()).isPositive();

        game.clearCars();

        assertThat(game.getCars().size()).isZero();
    }

    private void clearDatabase() {
        carRepository.clear();
    }
}
