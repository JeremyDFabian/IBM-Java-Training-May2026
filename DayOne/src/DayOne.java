
import java.util.Scanner;

public class DayOne {

    private static int blackjack(int a, int b) {
        int winner = (a <= 21 && (b > 21 || a > b)) ? a : b;
        return winner;
    }

    private static void dayChecker(int d) {
        switch (d) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid number");
        }
    }
    
    private static void dayCheckerTwo(int d) {
        switch (d) {
            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wednesday");
            case 4 -> System.out.println("Thursday");
            case 5 -> System.out.println("Friday");
            case 6 -> System.out.println("Saturday");
            case 7 -> System.out.println("Sunday");
            default -> System.out.println("Invalid number");
        }
    }

    private static void pyramid(int p) {
        for (int i = 0; i < p; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(j + 1 + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Lists of Assignments");
        System.out.println("1. Blackjack");
        System.out.println("2. Day Checker");
        System.out.println("3. Day Checker (Pattern Matching)");
        System.out.println("4. Pyramid");
        System.out.print("What assignment do you want to check (1-4): ");

        int assignment = sc.nextInt();

        if (assignment == 1) {
            System.out.println(blackjack(20, 19));
            System.out.println(blackjack(21, 22));
            System.out.println(blackjack(10, 11));
            System.out.println(blackjack(5, 4));
        }

        if (assignment == 2) {
            System.out.print("Enter day number: ");
            int day = sc.nextInt();

            dayChecker(day);
        }
        
        if (assignment == 3) {
        	System.out.print("Enter day number: ");
        	int day = sc.nextInt();
        	dayCheckerTwo(day);
        }

        if (assignment == 4) {
            System.out.print("Pyramid Size (1-20): ");
            int pyramidHeight = sc.nextInt();

            while (!(pyramidHeight > 0 && pyramidHeight <= 20)) {
                System.out.print("Input only numbers 1-20: ");
                pyramidHeight = sc.nextInt();
            }

            pyramid(pyramidHeight);
        }

        sc.close();
    }
}