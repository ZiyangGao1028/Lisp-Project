import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Stack;


public class LispEval
{ 
   //Set up mainstack and tempstack by the input 
   //file with lisp expresion. Finish by mathematical 
   //operand and opeation. 
   public static void main(String[] args) throws IOException
   {
      // Initializes two empty stacks that take string types.
      Stack<String> mainstack = new Stack<String>();
      Stack<String> tempstack = new Stack<String>();
      
      Scanner equation = new Scanner(new File(args[0]));
      // Goes until there's nothing left in the file
      while(equation.hasNext())
      {
         String symbol = equation.next();
         // Pushes symbols onto the mainstack until it reaches a ).
         while(!symbol.equals(")"))
         {
            mainstack.push(symbol);
            symbol = equation.next();
         }
         // Pops items off the mainstack until it reaches a (
         while(!mainstack.peek().equals("("))
         { 
            tempstack.push(mainstack.pop());
         }
         //gets rid of (.
         mainstack.pop();
         // finds the type of math that needs to be computed and the
         // first number.
         String sign = tempstack.pop();
         String first = tempstack.pop();
         double answer = Double.parseDouble(first);
         //perform differnt operations based on the sign.
         if (sign.equals("-"))
         {
            while(!tempstack.empty())
            {
               answer -= Double.parseDouble(tempstack.pop());
            }  
         }
         else if (sign.equals("+"))
         {
            while(!tempstack.empty())
            {
               answer += Double.parseDouble(tempstack.pop());
            }  
         }
         else if (sign.equals("*"))
         {
            while(!tempstack.empty())
            {
               answer *= Double.parseDouble(tempstack.pop());
            }  
         }
         else if (sign.equals("/"))
         {
            while(!tempstack.empty())
            {
               answer /= Double.parseDouble(tempstack.pop());
            }  
         }
         else
         {
            System.out.println(
               "Please input a .txt file with a lisp expression.");
         }
         // Converts the number to a string and pushes it back onto the
         // mainstack.
         String result = String.valueOf(answer);
         mainstack.push(result);
      }
      //Prints out the answer (the top of the stack).
      System.out.println(mainstack.peek());   
   }
}         

   
        
