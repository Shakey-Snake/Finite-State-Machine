//kb172, 1505627
//The REcompiler takes a string from the command line input and creates a regular expression.
//This expression is sent to standard output in this format "X X X X".
import java.util.*;

public class REcompile {

    //----------------------------------------------- ExpressionRecord class -------------------------------------------
    private static class ExpressionRecord
    {
        //------------------------------------------- Variables and Constructors ---------------------------------------

        //Stores the state number.
        private int stateNumber;

        //Stores the literal.
        private String literal = null;

        //Stores the state number if the next state to move too.
        private int branchOne;

        //Stores the secondary state location if this is a branching node.
        private int branchTwo;

        //Empty constructor if you want to initialise a empty record.
        public ExpressionRecord()
        {

        }

        //The general constructor.
        public ExpressionRecord(int stateNumber, String literal, int branchOne, int branchTwo)
        {
            //Initialise the records variables.
            this.stateNumber = stateNumber;
            this.literal = literal;
            this.branchOne = branchOne;

            if(branchTwo != -1)
                this.branchTwo = branchTwo;

        }

        //---------------------------------------------------- Methods -------------------------------------------------

        //
        public String[] findExpression(String expression, int startingLocation)
        {
            int r;

            r = findTerm(expression, startingLocation);

            if(isVocab(expression[startingLocation]) || expression[startingLocation] == "[")
                findExpression(expression, startingLocation);

            return(r);

        }

        //
        private String[] findTerm(String expression, int startingLocation)
        {
            int r, t1, t2, f;

            f = state - 1;

            r = t1 = findFactor();

            if(expression[startingLocation]=='*')
            {
                set_state(state,' ',state+1,t1);
                j++;
                r = state;
                state++;
            }

            if(expression[startingLocation]=='+')
            {
                if(next1[f] == next2[f])
                    next2[f] = state;

                next1[f] = state;

                f = state - 1;

                j++;
                r = state;
                state++;

                t2 = term();

                set_state(r,' ',t1,t2);

                if(next1[f] == next2[f])
                    next2[f] = state;

                next1[f] = state;

            }

            return(r);

        }

        //
        private String[] findFactor(String expression, int startingLocation)
        {
            int r;

            if(isvocab(expression[startingLocation]))
            {
                set_state(state,p[j],state+1,state+1);

                j++;
                r = state;
                state++;

            }
            else
                if(expression[startingLocation]=='[')
                {
                    j++;
                    r = expression();

                    if(expression[startingLocation]==']')
                        j++;
                    else
                        error();

            }
            else
                error();

            return(r);

        }

        //
        private boolean isVocab(String character)
        {


        }

        //Overrides the toString method for easy formatting.
        @Override
        public String toString() {
            return stateNumber + " " + literal + " " + branchOne + " " + branchTwo;
        }

    }

    //------------------------------------------ End of the ExpressionRecord class -------------------------------------

    //---------------------------------------------------- REcomplie class ---------------------------------------------

    //

    //Main program
    public static void main(String[] args)
    {
        //Generalised test that, if something went wrong, it breaks to the end of the program and throws an exception.
        try
        {
            //Checks to see if there was an expression given to process. If none, break from the program.
            if(args.length == 0)
            {
                System.out.println("Error: No command line arguments given. Please enter a regular expression.");
                return;

            }

            //-------------------------------------------- Expression validation ---------------------------------------

            //The stack that does the bracket balancing.
            Stack<String> stack = new Stack<>();

            //Counts open/closed parentheses in the regular expression.
            List<String> parenthesesBalance = new ArrayList<>();

            //Keeps track of whether the parentheses are balanced.
            int balancedParentheses = 0;

            //Joins multiple expressions into one if multiple are given.
            String regularExpression = String.join("", args);

            //Splits the expression into an array so they can be processed.
            String[] splitExpression = regularExpression.split("", 2147483647);

            //Storage for the expressions being worked on.
            String currentExpression, nextExpression;

            //Where the broken expression is reformed.
            String reformedExpression = "";

            //Counts how many of each parentheses there are.
            for (int i = 0; i <= splitExpression.length; i++)
            {
                //Checks to see if the bracket is escaped.
                if (splitExpression[i].equals("\\"))
                    i++;
                else
                {
                    //Creates an array of all of the parentheses.
                    if (splitExpression[i].equals("(") || splitExpression[i].equals(")") || splitExpression[i].equals("[") || splitExpression[i].equals("]"))
                        parenthesesBalance.add(splitExpression[i]);

                }

            }

            //Loops though all of the parentheses.
            for (String current : parenthesesBalance) {
                //Gets the current parentheses.
                String parse;

                //Immediately break if the first one is not an opening bracket, and thus, .
                if (parenthesesBalance.get(0).equals(")") || parenthesesBalance.get(0).equals("]")) {
                    balancedParentheses--;
                    break;

                }

                //Lifo stack use for balancing parentheses.
                if (current.equals("(") || current.equals("[")) {
                    stack.push(current);

                    balancedParentheses++;

                }

                switch (current) {
                    case ")": {
                        parse = stack.pop();

                        if (parse.equals("(") || parse.equals("["))
                            balancedParentheses--;

                    }

                    case "]": {
                        parse = stack.pop();

                        if (parse.equals("(") || parse.equals("["))
                            balancedParentheses--;

                    }

                }

            }

            //If the parentheses are unbalanced, break from the program.
            if (balancedParentheses != 0)
                throw new Exception("Error: The parentheses in the regular expression are unbalanced. Please balances them before continuing.");

            //Loop through the expression to check its validity.
            for(int i = 0; i <= splitExpression.length; i++)
            {
                //The current character.
                currentExpression = splitExpression[i];

                //The next character.
                nextExpression = splitExpression[i + 1];

                //Mainly removing double ups of special characters.
                switch(currentExpression)
                {
                    case("*"):
                    {
                        if(nextExpression.equals("*"))
                        {
                            reformedExpression += splitExpression[i];
                            i++;

                        }

                    }
                    case("+"):
                    {
                        if(nextExpression.equals("+"))
                        {
                            reformedExpression += splitExpression[i];
                            i++;

                        }

                    }
                    case("?"):
                    {
                        if(nextExpression.equals("?"))
                        {
                            reformedExpression += splitExpression[i];
                            i++;

                        }

                    }
                    case("["):
                    {
                        for (int j = i + 1; j <= splitExpression.length; j++)
                        {
                            //Keep i in line with j.
                            i = j;

                            //If the next character is the closing parentheses, break.
                            if (splitExpression[j].equals("]"))
                                break;

                            //If there is an escaped character, skip over it.
                            if (splitExpression[j].equals("\\"))
                            {
                                reformedExpression += splitExpression[i];
                                j++;

                            }
                            //Else, add it as a literal on the reformed expression.
                            else
                                reformedExpression += splitExpression[i];

                        }

                    }
                    case("|"):
                    {
                        if(nextExpression.equals("|"))
                        {
                            reformedExpression += splitExpression[i];
                            i++;

                        }

                    }
                    case("."):
                    {
                        if(nextExpression.equals("."))
                        {
                            reformedExpression += splitExpression[i];
                            i++;

                        }

                    }
                    default:
                        reformedExpression += splitExpression[i];

                }

            }

            //---------------------------------------------- FSM calculations ------------------------------------------

            //The list that stores the FSM output, to be later outputted.
            List<ExpressionRecord> FSMOutput = new ArrayList<>();

            //Adds the 0's state to the FSM.
            FSMOutput.add(new ExpressionRecord(0, "", 1, -1));

            //Keeps track if the last character seen was an \.
            boolean escaped = false;

            //Loop through the expression and formulate the FSM.
            for(int i = 0; i <= reformedExpression.length(); i++)
            {
                String[] parse = FSMOutput.get(0).findExpression(reformedExpression, i);

                FSMOutput.add(new ExpressionRecord(i, parse[0], Integer.parseInt(parse[1]), Integer.parseInt(parse[2])));

            }

            //--------------------------------------------------- Output -----------------------------------------------

            //Prints the entire FSM to System.out.
            for(ExpressionRecord output : FSMOutput)
            {
                System.out.println(output);

            }

        }
        catch(Exception exception)
        {
            exception.printStackTrace();

        }

    }

}
