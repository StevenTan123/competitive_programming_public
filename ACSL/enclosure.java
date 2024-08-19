import java.util.*;
import java.io.*;

public class enclosure {
    public static void main(String[] args) throws IOException {

        //setting up i/o
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        for(int t = 1; t <= 5; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            line.nextToken();

            //text stores the expression that is missing a bracket
            String text = line.nextToken();
            char[] brackets = new char[] {'{', '(', '[', '}', ')', ']'};

            //indices where a bracket can be inserted
            ArrayList<Integer> indices = new ArrayList<Integer>();

            //loop through every position in the text and every possible bracket to insert
            for(int i = 0; i <= text.length(); i++) {
                for(int j = 0; j < 6; j++) {
                    String inserted = text.substring(0, i) + brackets[j] + text.substring(i);

                    //if a valid expression is formed after insertion, add this index to the answer
                    if(valid(inserted)) {
                        indices.add(i + 1);
                    }
                }
            }
            
            //printing indices
            out.print(t + ". ");
            for(int i = 0; i < indices.size(); i++) {
                out.print(indices.get(i));
                if(i < indices.size() - 1) {
                    out.print(",");
                }
            }
            out.println();
        }
        in.close();
        out.close();
    }

    //checks if expression is valid
    static boolean valid(String text) {

        //stack stores running open brackets
        ArrayDeque<Character> stack = new ArrayDeque<Character>();

        //loop through text character by character
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            //if character is an open bracket
            if(c == '{' || c == '[' || c == '(') {

                //making sure the bracket is in a valid position (placed after an opening bracket or operator)
                if(i > 0) {
                    char prev = text.charAt(i - 1);
                    if(!(prev == '{' || prev == '[' || prev == '(' || prev == '+' || prev == '-' || prev == '*' || prev == '/')) {
                        return false;
                    }
                }

                //push open bracket onto stack
                stack.push(c);

            //if character is a close bracket
            }else if(c == '}' || c == ']' || c == ')') {

                //convert to corresponding open bracket for comparison
                if(c == '}') c = '{';
                else if(c == ']') c = '[';
                else c = '(';

                //if stack is empty or doesn't match the most recent open bracket, expression is not valid
                if(stack.size() == 0 || stack.peek() != c) {
                    return false;
                }else {

                    //make sure bracket is placed in a valid position (placed before a closing bracket or operator)
                    if(i < text.length() - 1) {
                        char next = text.charAt(i + 1);
                        if(!(next == '}' || next == ']' || next == ')' || next == '+' || next == '-' || next == '*' || next == '/')) {
                            return false;
                        }
                    }

                    //make sure encloses an actual expression, not just a single integer, by checking if there is an operator enclosed
                    boolean seen_operator = false;
                    int ind = i - 1;
                    while(true) {
                        char cur = text.charAt(ind);
                        if(cur == c) break;
                        else if(cur == '+' || cur == '-' || cur == '/' || cur == '*') seen_operator = true;
                        ind--;
                    }
                    if(!seen_operator) return false;

                    //if all is good, the current brackets match so we can pop it off
                    stack.pop();
                }
            }
        }

        //if stack is empty, then expression is valid, otherwise too many opening brackets
        if(stack.size() == 0) {
            return true;
        }else {
            return false;
        }
    }
}
