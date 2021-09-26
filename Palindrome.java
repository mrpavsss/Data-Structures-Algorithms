package Lab1;

public class Palindrome {

    public static String reverse(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        } else {
            return reverse(input.substring(1)) + input.charAt(0);
        }
    }

    public static boolean palindrome(String input) {
        // abcaacba
        String halfInput = input.substring(0, input.length()/2);
        System.out.println("halfInput: " + halfInput);
        String reverseInput = reverse(halfInput);
        System.out.println("reversed first half: " + reverseInput);
        if (reverseInput.equals(input.substring(input.length()/2))) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String test = "abbaabba";
        System.out.println(palindrome(test));
    }


}
