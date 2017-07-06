package tech.loyd.ds.stack;

/*
 * 计算后缀表达式
 */
public class CalPostfix {

	private static double calPostfix(String str) {
		if ("".equals(str)) {
			return 0;
		}

		String[] array = str.split(" ");
		ArrayStack<Double> stack = new ArrayStack<Double>();

		for (int i = 0; i < array.length; i++) {
			if (array[i].equals("+")) {
				double b = stack.pop();
				double a = stack.pop();
				stack.push(a + b);
			} else if (array[i].equals("-")) {
				double b = stack.pop();
				double a = stack.pop();
				stack.push(a - b);
			} else if (array[i].equals("*")) {
				double b = stack.pop();
				double a = stack.pop();
				stack.push(a * b);
			} else if (array[i].equals("/")) {
				double b = stack.pop();
				double a = stack.pop();
				stack.push(a / b);
			} else {
				stack.push(Double.parseDouble(array[i]));
			}
		}
		return stack.pop();
	}

	public static void main(String[] args) {

		System.out.println(calPostfix("1.66 7.5 * 2.3 1.3 / +"));
	}

}
