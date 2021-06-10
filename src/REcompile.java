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

            if(branchTwo == -1)
                this.branchTwo = null;
            else
                this.branchTwo = branchTwo;

        }

        //---------------------------------------------------- Get-Set -------------------------------------------------

        //Gets the state number.
        public int getStateNumber() {
            return stateNumber;
        }

        //Sets the state number.
        public void setStateNumber(int stateNumber) {
            this.stateNumber = stateNumber;
        }

        //Gets the literal.
        public String getLiteral() {
            return literal;
        }

        //Sets the literal.
        public void setLiteral(String literal) {
            this.literal = literal;
        }

        //Gets the first branch.
        public int getBranchOne() {
            return branchOne;
        }

        //Sets the first branch.
        public void setBranchOne(int branchOne) {
            this.branchOne = branchOne;
        }

        //Gets the second branch.
        public int getBranchTwo() {
            return branchTwo;
        }

        //Sets the second branch.
        public void setBranchTwo(int branchTwo) {
            this.branchTwo = branchTwo;
        }

        //---------------------------------------------------- Methods -------------------------------------------------

        //
        public String findExpression(String[] expression, int startingLocation)
        {
            int r;

            r = findTerm();

            if(isVocab(expression[startingLocation]) || expression[startingLocation]=='[')
                findExpression();

            return(r);

        }

        //
        private String findTerm(String[] expression, int startingLocation
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
        private String findFactor(String[] expression, int startingLocation)
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

            //------------------------------------------------- Variables ----------------------------------------------

            //None yet*.

            //-------------------------------------------- Expression validation ---------------------------------------

            //Joins multiple expressions into one if multiple are given.
            String regularExpression = String.join("", args);

            //Splits the expression into an array so they can be processed.
            String[] splitExpression = regularExpression.split("", 2147483647);

            //Storage for the expressions being worked on.
            String currentExpression, nextExpression = "";

            //Where the broken expression is reformed.
            String reformedExpression = "";

            //Loop through the expression to check its validity.
            for(int i = 0; i <= splitExpression.length; i++)
            {
//                //Counts open/closed parentheses in the regular expression.
//                int open, close;
//
//                //Counts how many of each parentheses there are.
//                for (int j = 0; j <= splitExpression.length; j++)
//                {
//                    //Checks to see if the bracket is escaped.
//                    if (c == '\\')
//                    {
//                        j++;
//
//                    }
//                    else
//                    {
//                        if (c == "(")
//                            open++;
//
//                        if (c == ")")
//                            close++;
//                    }
//
//                }
//
//                //If the parentheses are uneven then there is no point to see if they are correctly formated, because they can not be.
//                if (open.equals(close) == false)
//                {
//                    System.err.println("Error: Unbalanced parentheses within the regular expression.");
//                    break;
//
//                }

                //
                int stateNumber = i++;

                //
                currentExpression = splitExpression[i];

                //
                nextExpression = splitExpression[i++];

                //
                switch(currentExpression)
                {
                    case ('('):
                    {

                        break;
                    }
                    case ('{'):
                    {

                        break;
                    }
                    case (''):
                    {

                        break;
                    }

                }

            }

            //---------------------------------------------- FSM calculations ------------------------------------------

            //The list that stores the FSM output, to be later outputted.
            List<ExpressionRecord> FSMOutput = new ArrayList<ExpressionRecord>();

            //Adds the 0's state to the FSM.
            FSMOutput.add(new ExpressionRecord(0, "", 1, -1));

            //Keeps track if the last character seen was an \
            boolean escaped = false;

            //Loop through the expression and formulate the FSM.
            for(int i = 0; i <= splitExpression.length; i++)
            {
                //
                int stateNumber = i++;

                //
                ExpressionRecord currentExpression;

                //
                if(escaped = false)
                {
                    currentExpression.findExpression(splitExpression, i)

                }
                else
                {
                    currentExpression.findExpression(splitExpression, i)

                    escaped = false;

                }

            }

            //--------------------------------------------------- Output -----------------------------------------------

            //Checks if the string is correctly reformed.
            if(reformedExpression.contentEquals(regularExpression))
            {
                System.out.println("String correctly reformed!");

            }

            //Prints the entire FSM to System.out.
            for(ExpressionRecord output : FSMOutput)
            {
                System.out.println(output.toString()); //Yes I know it would call it anyway but it just LOOKS better, ya know?

            }

        }
        catch(Exception exception)
        {
            exception.printStackTrace();

        }

    }

}
