package tech.loyd.ds.stack;

import java.util.HashMap;
import java.util.Map;

/*
 * 检测括号是否成对出现
 */
public class BalanceBrackets {

	private static final Map<Character, Character> map = new HashMap<Character, Character>();

	static {
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		map.put('>', '<');
	}

	private static Boolean judge(String str) {
		char[] array = str.toCharArray();

		ArrayStack2<Character> stack = new ArrayStack2<Character>();

		for (int i = 0; i < array.length; i++) {
			if (map.containsKey(array[i])) {
				if (stack.isEmpty()) {
					return false;
				}
				char c = stack.peek();
				if (c == map.get(array[i])) {
					stack.pop();
				} else {
					stack.push(array[i]);
				}
			} else if (map.containsValue(array[i])) {
				stack.add(array[i]);
			}
		}

		if (stack.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	public static void main(String[] args) {
		System.out.println(judge("(17[]{})"));
		System.out.println(judge("(17>]{})"));
		System.out.println(judge("(17[]{}"));
	}
}
