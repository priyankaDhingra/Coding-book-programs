import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Q3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {
			System.out.println(checkParenthesesBalance(scanner.next()));
		}

		scanner.close();
	}

	private static boolean checkParenthesesBalance(String string) {
		// For even cases check
		HashMap<Character, Character> bracketMap = new HashMap<>();
		bracketMap.put('{', '}');
		bracketMap.put('(', ')');
		bracketMap.put('[', ']');
		String str = new String();

		for (int i = 0; i < string.length(); i++) {
			if (bracketMap.containsKey(string.charAt(i))
					|| bracketMap.containsValue(string.charAt(i))) {
				

			}
		}

		if (str.length() == 0)
			return true;
		if (str.length() % 2 == 1) {
			return false;
		}

		Stack<Character> checker = new Stack<Character>();

		for (int i = 0; i < str.length(); i++) {
			if (bracketMap.containsKey(str.charAt(i))) {
				checker.push(str.charAt(i));
			} else {
				if (checker.empty())
					return false;

				char c = checker.pop();
				if (bracketMap.get(c) != str.charAt(i)) {
					return false;
				}

			}
		}
		return true;
	}

}
