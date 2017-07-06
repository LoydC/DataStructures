package tech.loyd.ds.stack;

/*
 * 将中缀表达式转换成后缀表达式
 */
public class ConvertInfixToPostfix {

	private static String convert(String str) {
		StringBuffer sb = new StringBuffer();

		if ("".equals(str)) {
			return "";
		}

		String[] array = str.split(" ");
		ArrayStack<String> stack = new ArrayStack<String>();

		for (int i = 0; i < array.length; i++) {
			if (array[i].equals("+") || array[i].equals("-")) {
				while (!stack.isEmpty()) {
					if(!stack.peek().equals("(")){
						sb.append(stack.pop());
					}else{
						break;
					}
				}
				stack.push(array[i]);
			} else if (array[i].equals("*") || array[i].equals("/")) {
				while (!stack.isEmpty()) {
					if(stack.peek().equals("/") || stack.peek().equals("*")){
						sb.append(stack.pop());	
					}else{
						break;
					}
				}
				stack.push(array[i]);
			} else if (array[i].equals("(")) {
				stack.push(array[i]);
			} else if (array[i].equals(")")) {
				while (!stack.peek().equals("(")) {
					sb.append(stack.pop());
				}
				stack.pop();
			} else {
				sb.append(array[i]);
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(convert("a + b * c + ( d * e + f ) * g"));
	}

}
