
/*
 * CarRacingGame
 *
 * ver 1.0
 *
 * 2019/04/04
 *
 * Copyright 2019. Jieun Jeong. All ringts reserved.
 */

package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRacingGame {
    public static String[] inputCarNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("경주할 자동차의 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분) \n");
        String input = scanner.nextLine();
        String[] carNames = input.split(",");

        return carNames;
    }

    public static List<Car> generateCarInstance() {
        String[] carNames = inputCarNames();
        List<Car> cars = new ArrayList<>();

        for (String name : carNames) {
            try {
                Car car = new Car(name);
                cars.add(car);
            } catch (IllegalArgumentException e) {
                return generateCarInstance();
            }
        }
        return cars;
    }

    public static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static int inputTrialNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("시도할 횟수는 몇회인가요? \n");
        String trialNumber = scanner.nextLine();

        if (isNumber(trialNumber)) {
            return Integer.parseInt(trialNumber);
        } else {
            System.out.println("숫자로 입력해주세요.");
            return inputTrialNumber();
        }
    }

    public static void showResult(List<Car> cars, int trialNumber) {
        for (int i = 0; i < trialNumber; i++) {
            for (Car car : cars) {
                car.showTrace(car.tryRun());
            }
            System.out.println();
        }
    }

    public static int getMaxPosition(List<Car> cars) {
        int maxPosition = 0;

        for (Car car : cars) {
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        }
        return maxPosition;
    }

    public static void showWinner(List<Car> cars) {
        int maxPosition = getMaxPosition(cars);
        List<String> winners = new ArrayList<>();

        for (Car car : cars) {
            if (car.isMaxPosition(maxPosition)) {
                winners.add(car.getName());
            }
        }

        String winner = String.join(",", winners);
        System.out.println(String.format("\n%s가 최종 우승했습니다.", winner));
    }

    public static void main(String[] args) {
        List<Car> cars = generateCarInstance();
        int trialNumber = inputTrialNumber();

        System.out.println("\n실행 결과");
        showResult(cars, trialNumber);
        showWinner(cars);
    }
}
