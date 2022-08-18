// Version: 20200917
// Handin done by:
//   202108331 Martin Pugdal Pedersen
// Contributions:
//   Martin Everything

import java.io.*;
import java.util.*;
public class LongestPalindromeSubsequence {
    public ArrayList<Character> longestPalindromeSubsequence(ArrayList<Character> input) {
        ArrayList<Character> result = new ArrayList<>();

        int[][] dataset = new int[input.size()][input.size()];

        for(int length = 1; length < input.size()+1; length++){
            for(int i = 0; i < dataset.length - length + 1; i++) {
                int j = i + length - 1;
                if (i == j) {
                    dataset[i][j] = 1;
                } else if(i < j && input.get(i) == input.get(j)){
                    dataset[i][j] = 2 + dataset[i+1][j-1];
                } else if(i < j && input.get(i) != input.get(j)){
                    dataset[i][j] = Math.max(dataset[i + 1][j], dataset[i][j - 1]);
                }
            }
        }
        int i = 0;
        int j = input.size()-1;
        while (i <= j){
            if(dataset[i][j] > dataset[i][j-1] && dataset[i][j] > dataset[i+1][j]) {
                result.add(input.get(i));
                i++;
                j--;
            } else if(dataset[i][j-1] > dataset[i+1][j]){
                j--;
            } else {
                i++;
            }
        }
        if(dataset[0][input.size()-1] % 2 == 1){
            for(i = result.size()-2; i >= 0 ; i--){
                result.add(result.get(i));
            }
        } else {
            for(i = result.size()-1; i >= 0 ; i--){
                result.add(result.get(i));
            }
        }
        return result;
    }

    public static void testAll() {
        clearTerminal();
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }

    public static void test1() {
        char[] input = { 'b', 'c', 'a', 'b', 'c', 'a', 'b', 'a' };
        char[] correctAnswer = { 'b', 'a', 'b', 'a', 'b' };

        try {
            checkPal("test1", input, correctAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            outputFail("test1", "Exception: " + e);
            return;
        }
    }

    public static void test2() {
        char[] input = { 'a', 'i', 'b', 'o', 'h', 'p', 'h', 'o', 'b', 'i', 'a' };
        char[] correctAnswer = { 'a', 'i', 'b', 'o', 'h', 'p', 'h', 'o', 'b', 'i', 'a' };

        try {
            checkPal("test2", input, correctAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            outputFail("test2", "Exception: " + e);
            return;
        }
    }

    public static void test3() {
        char[] input = { 'r', 'h', 'o', 't', 'a', 't', 'h', 'o', 'r', 'y' };
        char[] correctAnswer = { 'r', 'o', 't', 'a', 't', 'o', 'r' };

        try {
            checkPal("test3", input, correctAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            outputFail("test3", "Exception: " + e);
            return;
        }
    }

    public static void test4() {
        char[] input = { 'r', 'a', 'h', 'a', 'n', 'n', 'a', 'h' };
        char[] correctAnswer = { 'h', 'a', 'n', 'n', 'a', 'h' };

        try {
            checkPal("test4", input, correctAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            outputFail("test4", "Exception: " + e);
            return;
        }
    }

    public static void test5() {
        char[] input = { 'a', 'c', 'b', 'a', 'c' };
        char[] correctAnswer = { 'a', 'b', 'a' };

        try {
            checkPal("test5", input, correctAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            outputFail("test5", "Exception: " + e);
            return;
        }
    }

    public static void test6() {
        char[] input = "aebecdceeccbadce".toCharArray();
        char[] correctAnswer = "abcceeccba".toCharArray();

        try {
            checkPal("test6", input, correctAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            outputFail("test6", "Exception: " + e);
            return;
        }
    }

    public static void test7() {
        char[] input = "abcbda".toCharArray();
        char[] correctAnswer = "abcba".toCharArray();

        try {
            checkPal("test7", input, correctAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            outputFail("test7", "Exception: " + e);
            return;
        }
    }

    private static void checkPal(String testName, char[] input, char[] correctAnswer) {
        ArrayList<Character> list = new ArrayList<>();
        for (char c : input) list.add(c);

        ArrayList<Character> answer = (
            new LongestPalindromeSubsequence().longestPalindromeSubsequence(list));

        if (answer.size() != correctAnswer.length) {
            outputFail(testName,
                       "Expected palindrome of length " + correctAnswer.length + ", got " + answer);
            return;
        }

        int j = 0;
        for (int i : input) {
            if (j < answer.size() && answer.get(j) == i) j++;
        }
        if (j < answer.size()) {
            outputFail(testName, "Not a subsequence of the input: " + answer);
            return;
        }

        for (int i = 0; i < answer.size()-1-i; i++)
            if (answer.get(i) != answer.get(answer.size()-1-i)) {
                outputFail(testName, "Not a palindrome: " + answer);
                return;
            }

        outputPass(testName);
    }

    private static void clearTerminal() {
        System.out.print('\u000C');
    }

    private static void outputPass(String testName) {
        System.out.println("[Pass " + testName + "]");
    }

    private static void outputFail(String testName, String message) {
        System.out.println("[FAIL " + testName + "] " + message);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        if (testcases == 0) testAll();
        for (int t = 0; t < testcases; ++t) {
            int n = sc.nextInt();
            ArrayList<Character> list = new ArrayList<>();
            for (int i = 0; i < n; ++i) list.add(sc.next().charAt(0));
            LongestPalindromeSubsequence lis = new LongestPalindromeSubsequence();
            ArrayList<Character> pal = lis.longestPalindromeSubsequence(list);
            System.out.println(pal.size());
            for (int i = 0; i < pal.size(); i++)
                System.out.println(pal.get(i));
        }
    }
}
