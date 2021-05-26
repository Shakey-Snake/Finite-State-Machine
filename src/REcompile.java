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
            this.branchTwo = branchTwo;

        }

        //---------------------------------------------------- Methods -------------------------------------------------

        //

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

        //

        //Overrides the toString method for easy formatting.
        @Override
        public String toString() {
            return stateNumber + " " + literal + " " + branchOne + " " + branchTwo;
        }

    }

    //------------------------------------------ End of the ExpressionRecord class -------------------------------------

    //---------------------------------------------------- REcomplie class ----------------------------------------------

    //https://www.geeksforgeeks.org/split-string-java-examples/

    //

    //Main program
    public static void main(String[] args)
    {
        //Generalised test that, if something went wrong, it breaks to the end of the program and throws an exception.
        try
        {
            //Checks to see if there was a valid expression given to process. If none, break from the program.
            if(args.length == 0)
            {
                System.err.println("Error: No command line arguments given. Please enter a regular expression.");
                return;

            }
            else if (args.length > 1)
            {
                System.err.println("Error: Invalid regular expression as it contains spaces. Please enter a valid regular expression.");
                return;

            }

            //------------------------------------------------- Variables ----------------------------------------------

            //Splits the expression into an array so they can be processed.
            String[] splitExpression = args[0].split("", 2147483647);

            //Storage for the expressions being worked on.
            String currentExpression, nextExpression = "";

            //The list that stores the FSM output, to be later outputted.
            List<ExpressionRecord> FSMOutput = new ArrayList<>();

            //Adds the 0's state to the FSM.
            FSMOutput.add(new ExpressionRecord(0, "-1", 1, -1));

            //---------------------------------------------- FSM calculations ------------------------------------------

            //Loop through the expression and formulate the FSM.
            for(int i = 0; i < splitExpression.length; i++)
            {
                //


            }

            //--------------------------------------------------- Output -----------------------------------------------

            //Prints the entire FSM to System.out.
            for (ExpressionRecord output : FSMOutput)
            {
                System.out.println(output.toString()); //Yes I know it would call it anyway but it just LOOKS better, ya know?

            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();

        }

    }

}
