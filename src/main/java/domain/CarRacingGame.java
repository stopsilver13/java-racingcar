
/*
 * CarRacingGame
 *
 * ver 1.0
 *
 * 2019/04/24
 *
 * Copyright 2019. Jieun Jeong. All ringts reserved.
 */

package domain;

import java.util.ArrayList;
import java.util.Scanner;

public class CarRacingGame {
    private static boolean checkNameLength(String[] carName) {
        boolean checkResult = true;

        for (String e : carName) {
            if (e.length() > 5) {
                checkResult = false;
            }
        }

        return checkResult;
    }

    public static void showTrace(Car car) {
        int oldPosition = car.getPosition();
        int newPosition = oldPosition;

        if (car.tryRun()) {
            newPosition = oldPosition + 1;
            car.setPosition(newPosition);
        }

        String progress = new String(new char[newPosition]).replace("\0", "-");

        System.out.println(String.format("%s : %s", car.getName(), progress));
    }

    private static String getWinner(ArrayList<Car> carList) {
        int maxPosition = 0;

        for (Car e : carList) {
            if (e.getPosition() > maxPosition) {
                maxPosition = e.getPosition();
            }
        }

        String winner = "";

        for (Car e : carList) {
            if (e.getPosition() == maxPosition) {
                winner = winner + e.getName() + " ";
            }
        }

        return winner;
    }


    public static void main(String[] args) {
        boolean isRightNames = false;
        String[] carName = new String[0];

        while (!isRightNames) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("경주할 자동차의 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분) \n");
            String input = scanner.nextLine();

            carName = input.split(",");

            isRightNames = checkNameLength(carName);
        }

        ArrayList<Car> carList = new ArrayList<>();

        for (String e : carName) {
            Car car = new Car(e);

            carList.add(car);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("시도할 횟수는 몇회인가요? \n");
        int trialNumber = scanner.nextInt();

        System.out.println("\n실행 결과");

        for (int i = 0; i < trialNumber; i++) {
            for (Car e : carList) {
                showTrace(e);
            }

            System.out.println("\n");
        }

        String winner = getWinner(carList);

        System.out.println(winner + "가 최종 우승했습니다.");
    }
}
