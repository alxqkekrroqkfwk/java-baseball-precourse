package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
        boolean again = true;
        int strikeCount = 0;
        int ballCount = 0;
        int notSingCount = 0;
        while (again) {
            String randomNumbers = random();
            System.out.println(randomNumbers);
            while (strikeCount != 3) {
                System.out.print("숫자를 입력해주세요 : ");
                String input = console();
                inPutCheck(input);
                strikeCount = strike(randomNumbers, input);
                ballCount = ball(randomNumbers, input);
                notSingCount = notSing(randomNumbers, input);
                check(strikeCount,ballCount,notSingCount);
            }
            again = reStart();
        }
    }

    public static String random() {
        List<Integer> randomNumbers = new ArrayList<>();

        for (int i = 3; i > randomNumbers.size();) {
            int num = Randoms.pickNumberInRange(1, 9);
            if (!randomNumbers.contains(num)) {
                randomNumbers.add(num);
                System.out.println(randomNumbers.toString());
            }
        }
        String random = "";
        for (int randmn : randomNumbers) {
            random += String.valueOf(randmn);
        }

        return random;
    }

    public static String console() {
        String input = Console.readLine();
        return input;
    }


    public static boolean reStart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String input = console();
        if (input.matches("1")) {
            return true;
        }
        if (input.matches("2")) {
            return false;
        }
        return true;
    }

    public static void check(int strikeCount , int ballCount , int notSingCount) {
        String total = "";
        if (strikeCount > 0 && strikeCount < 3) {
            total += String.valueOf(strikeCount) + "스트라이크 ";
        }
        if (ballCount > 0) {
            total += String.valueOf(ballCount) + "볼 ";
        }
        if (notSingCount == 3) {
            total += "낫싱";
        }
        if (strikeCount == 3) {
            total += String.valueOf(strikeCount) + "스트라이크 ";
            System.out.println(total);
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        }
        System.out.println(total);
    }


    public static void inPutCheck(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException("잘못된 값을 입력하셨습니다. 3자리의 숫자만 입력해주세요.");
        }
    }

    public static int strike(String randomNumbers, String input) {

        int strike = 0;
        for (int i = 0; i < randomNumbers.length(); i++) {
            if (randomNumbers.charAt(i) == input.charAt(i)) {
                strike++;
            }
        }
        return strike;

    }

    public static int ball(String randomNumbers, String input) {
        int ball = 0;
        List<String > arr = Arrays.stream(input.split("")).collect(Collectors.toList());
        for (int i = 0; i < randomNumbers.length(); i++) {
            if (randomNumbers.contains(arr.get(i)) && randomNumbers.indexOf(arr.get(i)) != i) {
                ball++;
            }
        }
        return ball;
    }

    public static int notSing(String randomNumbers, String input) {
        int notSing = 0;
        List<String > arr = Arrays.stream(input.split("")).collect(Collectors.toList());
        for (int i = 0; i < randomNumbers.length(); i++) {
            if (!randomNumbers.contains(arr.get(i))) {
                notSing++;
            }
        }
        return notSing;
    }
}
