package bullscows;
import java.util.*;

public class Main {

    private static String code;
    private static int size;
    private static int range;

    public static String grade(String guessedCode) {
        int bull = 0;
        int cow = 0;
        String ans = "";
        for (int i = 0; i < guessedCode.length(); i++) {
            if (code.charAt(i) == guessedCode.charAt(i)) {
                bull++;
            }
            else if (code.indexOf(guessedCode.charAt(i)) > -1) {
                cow++;
            }
        }
        if (bull > 0) {
            ans = bull + " bull";
            ans = (bull > 1) ? ans + "s " : ans + " ";
        }
        if (cow > 0) {
            ans = (ans.length() > 0) ? ans+"and " : ans;
            ans += cow + " cow";
            ans = (cow > 1) ? ans + "s " : ans + " ";
        }
            ans = ans.isEmpty() ? "None" : ans;
        return ans;
    }
    
    public static String generateSecretCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < size) {
            int digit = random.nextInt(range+1);
            if (sb.length() == 0 && digit == 0) {
                continue;
            }
            if (sb.indexOf(String.valueOf(digit)) == -1) {
                if (digit < 10)
                    sb.append(digit);
                else {
                    char ch = (char)(97 + digit - 10);
                    if (sb.indexOf(String.valueOf(ch)) == -1)
                        sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    public static void runTurns(Scanner sc) {
        boolean status = false;
        int turn = 1;
        String input;
        while (!status) {
            System.out.println("Turn " + turn + ":");
            input = sc.next();
            String grade = grade(input);
            System.out.println("Grade: " + grade);
            status = grade.contains(size + " bulls");
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private static int getGoodInput(String prompt, Scanner sc) {
        boolean notValid = true;
        String input;
        int ret = -1;
        while (notValid) {
            System.out.println(prompt);
            input = sc.nextLine();
            try {
                ret = Integer.parseInt(input);
                if (ret < 1) {
                    System.out.println("Error: Invalid input size.");
                } else if (ret > 36) {
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                } else {
                    notValid = false;
                }
            } catch (Exception e) {
                System.out.println("Error: " + input + " isn't a valid number.");
            }
        }
        return ret;
    }

    public static void askSize(Scanner sc) {
        do {
            size = getGoodInput("Input the length of the secret code:", sc);
            range = getGoodInput("Input the number of possible symbols in the code:", sc);
            if (size > range) {
                System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
            }
        } while (size > range);

        code = generateSecretCode();
        String possibleValues = "0-";
        int digit = Math.min(range, 9);
        possibleValues += digit;
        if (range > 9) {
            String chars = "a";
            if (range > 10) {
                chars = chars + "-" + String.valueOf((char) (97 + range - 11));
                possibleValues = possibleValues + ", "+chars + ")";
            }
        }
        StringBuilder secretCode = new StringBuilder();
        secretCode.append("*".repeat(Math.max(0, size)));
        System.out.println("The secret is prepared: "+secretCode+" " + possibleValues + ".");
        System.out.println("Okay, let's start a game!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        askSize(sc);
        runTurns(sc);
        sc.close();
    }
}
