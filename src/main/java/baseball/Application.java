package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        //TODO: 숫자 야구 게임 구현
        start();
    }

    public static String Randmns() {
        List<Integer> ints = new ArrayList<>();

        for (int i=0;i<3;i++) {
            int num = Randoms.pickNumberInRange(1,9);
            if (!ints.contains(num)) {
                ints.add(num);
            }
        }
        String randmns = "";
        for (int randmn : ints) {
            randmns += String.valueOf(randmn);
        }

        return randmns;
    }

    public static String Console() {
        String input = Console.readLine();
        return input;
    }

    public static void start() {
        String randmns = Randmns();
        check(randmns);
        ReStart();
    }

    public static void ReStart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String input = Console();
        if (input.matches("1")) {
            start();
        } else if (input.matches("2")) {
            System.exit(0);
        }
    }

    public static void check(String randmns) {
        while (1==1) {
            System.out.print("숫자를 입력해주세요 : ");
            String  input = Console();
            inputcheck(input);
            int a = strike(randmns,input);
            int b = ball(randmns,input);
            int c = notSing(randmns,input);
            String total= "";
            if (a>0 && a<3) {
                total+= a+"스트라이크 ";
            }
            if (b>0) {
                total+= b+"볼 ";
            }
            if (c==3) {
                total+= "낫싱";
            }
            if (a==3) {
                total+= a+"스트라이크 ";
                System.out.println(total);
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                break;
            }
            System.out.println(total);
        }
    }

    public static void inputcheck(String input) {
        if (input.length()==3) {

        }else {
            throw new IllegalArgumentException();
        }
    }

    public static int strike(String randmn , String input) {

        int strike = 0;
        for (int i=0;i<randmn.length();i++) {
            if (randmn.charAt(i)==input.charAt(i)) {
                strike++;
            }
        }
        return strike;

    }

    public static int ball(String randmn , String input ) {
        int ball = 0;
        String[] arr = input.split("");
        for (int i=0;i<randmn.length();i++) {
            if (randmn.contains(arr[i]) && randmn.indexOf(arr[i])!=i) {
                ball++;
            }
        }
        return ball;
    }

    public static int notSing(String randmn , String input) {
        int notSing = 0;
        for (int i=0;i<randmn.length();i++) {
            if (randmn.indexOf(input.charAt(i))==-1) {
                notSing++;
            }
        }
        return notSing;
    }
}
