/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_1;

import java.util.Stack;

/**
 *
 * @author xhu
 */
public class DataAnalysis<E extends Comparable> {

    private E[] data;

    public DataAnalysis(E[] data) {
        this.data = data;
    }

    public boolean bracketEvaluator() {
        Stack<Character> stack = new Stack<>();
      //  String input = Arrays.toString(data);

        for (int i = 0; i < data.length; i++) {
            char currentChar = (char) data[i];

            if (currentChar == '(' || currentChar == '[' || currentChar == '{'||currentChar == '<') {
                stack.push(currentChar);
            } else if (currentChar == ')' || currentChar == ']' || currentChar == '}'|| currentChar == '>') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char top = stack.pop();
                    if ((currentChar == ')' && top != '(')
                            || (currentChar == ']' && top != '[')
                            || (currentChar == '}' && top != '{')
                            || (currentChar == '>' && top != '<')) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
/*
    
    public static void main(String[] args) {

        String input = "List<List<E>>>";

        // Convert input string to a character array
        char[] chars = input.toCharArray();
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            characters[i] = chars[i];
        }

        DataAnalysis<Character> da = new DataAnalysis<>(characters);
        System.out.println(da.bracketEvaluator());
    }
*/
}
