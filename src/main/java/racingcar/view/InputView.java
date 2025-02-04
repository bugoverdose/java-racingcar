package racingcar.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String requestCarNameInput() {
        OutputView.printRequestCarNamesInputMessage();
        return InputView.requestUserInput();
    }

    public static String requestTotalRoundsInput() {
        OutputView.printRequestTotalRoundsInputMessage();
        return InputView.requestUserInput();
    }

    public static String requestRestartGameInput() {
        OutputView.printRequestRestartGameInputMessage();
        return InputView.requestUserInput();
    }

    private static String requestUserInput() {
        return scanner.nextLine();
    }
}
