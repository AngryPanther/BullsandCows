import java.util.Scanner;

class EmployeeManagement {

    public static String createEmail(String name, String surname) {
        // write your code here
        StringBuilder result = new StringBuilder();
        result.append(name);
        result.append(surname);
        result.append("@work.net");
        return result.toString();
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        String surname = scanner.next();

        String completeEmail = createEmail(name, surname);

        System.out.println(completeEmail);
    }
}