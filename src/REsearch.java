import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class REsearch {

    char[] textFile = new char[100];
    int top = 0;

    int[] stateNum = new int[0];
    String[] charC = new String[0];
    int[] branchState = new int[0];
    int[] branchState2 = new int[0];

    int[] currState = new int[0];
    int[] queue = new int[0];

    // boolean[] mark = new boolean[stateNum.length];

    String line;
    String file = "";

    int scan = -1;

    String comp;

    String[] test = { "0,a,3,-1", "1,b,4,-1", "2,a,4,-1", "3,-1,1,2", "4,-1,-1,-1" };
    // String[] test = {"0,a,1", "1,-1,-1"};
    // String[] test = {"0,a,1", "1,b,2","2,-1,-1"};
    // String[] test = {"0,a,1", "1,b,2", "2,a,4", "4,-1,-1"};

    public static void main(String args[]) throws IOException {
        REsearch search = new REsearch(args[0]);
        search.run();
    }

    public REsearch(String fileName) {
        file = fileName;
    }

    private void run() throws IOException {
        // read line from console.
        /*
         * Scanner sc = new Scanner(System.in); while (sc.hasNext()) { line =
         * sc.nextLine(); String[] lineSplit = line.split(","); stateNum =
         * arrAdd(Integer.parseInt(lineSplit[0]), stateNum); charC =
         * arrAdd(lineSplit[1].charAt(0), charC); branchState =
         * arrAdd(Integer.parseInt(lineSplit[2]), branchState); if (lineSplit.length ==
         * 4){ branchState2 = arrAdd(Integer.parseInt(lineSplit[3]), branchState2); } }
         * sc.close();
         */
        searchAlt();
    }

    /*
     * COULD HAVE SEPERATE VARIABLE CALLED SCAN!!!!!! First state gets added to the
     * currState. Mark first state as 1. Push firstState stateNum onto currState,
     * then checks that checkChar[currState] == character. If it's equal, then add
     * the next state to botQueue and set the state as 1. Pop off the state and
     * check if there are other states. If only state remaining is scan then pop it
     * off. Check if botQueue contains states, if it does, transfer it to currState.
     * 
     * Pop off first state on currState, if it is a branch state, then add the 2
     * states onto currState. Otherwise check char
     */

    /* It ends when, end of file, mismatch, */

    private void searchAlt() throws IOException {
        for (String i : test) {
            String[] lineSplit = i.split(",");
            stateNum = arrAdd(Integer.parseInt(lineSplit[0]), stateNum);
            charC = arrAdd(lineSplit[1], charC);
            branchState = arrAdd(Integer.parseInt(lineSplit[2]), branchState);
            branchState2 = arrAdd(Integer.parseInt(lineSplit[3]), branchState2);
        }

        BufferedReader in = new BufferedReader(new FileReader(file));
        int c = 0;

        // reads in txt file to array
        while ((c = in.read()) != -1) {
            textFile[top] = (char) c;
            top++;
        }

        in.close();

        // count indicates place in array
        int count = 0;

        // loop to check if file has ended
        while (count < top) {
            queue = new int[0];
            currState = new int[0];
            // innerCount to increase where the
            int innerCount = count;
            comp = "";
            // adds first state
            currState = arrAdd(stateNum[0], currState);

            // System.out.print("count: " + count + innerCount);
            // loop to check if there are no more remaining current states
            outer: while (currState.length > 0) {

                // gets the next place in the array that needs to be checked
                int checkState = currState[currState.length - 1];

                // System.out.println(charC[checkState]);
                System.out.println(checkState);

                // on branch, push both to array
                if (charC[checkState].equals("-1")) {
                    if (branchState[checkState] == -1) {
                        System.out.println("FINAL: " + comp);
                        // on success, increase count by inner count
                        // System.out.println("count: " + count + innerCount);
                        count = innerCount - 1;
                        break outer;
                    }
                    int b1 = branchState[checkState];
                    int b2 = branchState2[checkState];
                    for (int i : currState) {
                        System.out.print("CurrState: ");
                        System.out.print(i);
                        System.out.println(" End Curr");
                    }
                    currState = stackPop(currState);
                    // add next state to queue
                    // System.out.println("b's: " + b1 + b2);
                    currState = arrAdd(b1, currState);
                    currState = arrAdd(b2, currState);

                    for (int i : currState) {
                        System.out.print("CurrState: ");
                        System.out.print(i);
                        System.out.println(" End Curr");
                    }
                }

                // System.out.println("equal" + charC[checkState] + " " +
                // String.valueOf(textFile[innerCount]));

                if (charC[checkState].equals(String.valueOf(textFile[innerCount]))) {
                    // System.out.print("equal");
                    // find the next state by getting the branch state number which relates to the
                    // place in the array
                    int nextState = branchState[checkState];
                    currState = stackPop(currState);
                    // add next state to queue
                    queue = arrAdd(nextState, queue);
                    innerCount++;
                    comp = comp.concat(charC[checkState]);
                }

                /*
                 * for (int i : queue) { System.out.print("Q: "); System.out.print(i);
                 * System.out.println(" End Q"); }
                 */

                if (queue.length > 0) {
                    for (int i : currState) {
                        System.out.print("CurrState: ");
                        System.out.print(i);
                        System.out.println(" End Curr");
                    }
                    currState = queue;
                    queue = new int[0];
                } else if (queue.length == 0) {
                    break outer;
                }
            }
            count++;
        }
    }

    private void search() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(file));
        int c = 0;

        while ((c = in.read()) != -1) {
            currState = arrAdd(stateNum[0], currState);

            while (currState.length > 0) {

                char character = (char) c;

                int checkState = currState[currState.length];
                if (charC[checkState] == String.valueOf(character)) {
                    // find the next state by getting the branch state number which relates to the
                    // place in the array
                    int nextState = branchState[checkState];
                    currState = stackPop(currState);
                    // add next state to queue
                    queue = arrAdd(nextState, queue);
                }
                if (queue.length == 0) {
                    break;
                }
            }
        }
    }

    private String[] arrAdd(String item, String[] arr) {
        String[] temp = arr;
        arr = new String[temp.length + 1];
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }
        arr[arr.length - 1] = item;
        return arr;
    }

    private int[] arrAdd(int item, int[] arr) {
        int[] temp = arr;
        arr = new int[temp.length + 1];
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }
        arr[arr.length - 1] = item;
        return arr;
    }

    private int[] stackPop(int[] arr) {
        int[] temp = arr;
        arr = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }
        return arr;
    }
}
