import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class REsearch {

    char[] textFile = new char[100];
    int top = 0;

    FSMpoint[] arr = new FSMpoint[0];

    int[] currState = new int[0];
    int[] queue = new int[0];

    // boolean[] mark = new boolean[stateNum.length];

    String line;
    String scline;

    String file = "";

    int scan = -1;

    int lineCount = 0;
    int count = 0;

    String comp = "";

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
        search();
    }
    
    /*private void searchAlt() throws IOException {
        for (String i : test) {
            String[] lineSplit = i.split(",");
            FSMpoint item = new FSMpoint(Integer.parseInt(lineSplit[0]), lineSplit[1], Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3]));
            arr = arrAdd(item, arr);
        }
        (for (FSMpoint i : arr) {
            System.out.println(i.stateNum + i.charMatch + i.bState1 + i.bState2);
        }

        BufferedReader in = new BufferedReader(new FileReader(file));

        while ((line=in.readLine())!=null)  {
            count = 0;
            //increment array to show what line the string was found on
            lineCount++;
            
            char[] lineArray = new char[line.length()];

            //convert the 
            for (int i = 0; i < line.length(); i++) {
                lineArray[i] = line.charAt(i);
            }

            int innerCount = 0;

            //loop to check if the whole line has been searched.
            outer:  while (count < lineArray.length){

                //System.out.println("Checking line: " + lineCount);
                queue = new int[0];
                currState = new int[0];
                comp = "";
                innerCount = count;
                // adds first state
                currState = arrAdd(arr[0].stateNum, currState);

                inner: while (currState.length > 0) {

                    int checkState = currState[currState.length - 1];

                    //System.out.println("current checkstate " + checkState);

                    if (arr[checkState].charMatch.equals("-1")) {
                        if (arr[checkState].bState1 == -1) {
                            System.out.println("Match: " + comp + " on line: " + lineCount);
                            break outer;
                        }
                        int b1 = arr[checkState].bState1;
                        int b2 = arr[checkState].bState2;
                        
                        currState = stackPop(currState);
                        
                        currState = arrAdd(b1, currState);
                        currState = arrAdd(b2, currState);

                        checkState = currState[currState.length - 1];
                        //System.out.println("new checkstate from branch command " + checkState);
                    }

                    //System.out.println("match: " + arr[checkState].charMatch + " with " + String.valueOf(lineArray[innerCount]));

                    if (arr[checkState].charMatch.equals(String.valueOf(lineArray[innerCount]))) {
                        //System.out.println("equal on " + checkState);
                        int nextState = arr[checkState].bState1;
                        // add next state to queue
                        queue = arrAdd(nextState, queue);
                        currState = new int[0];
                        innerCount++;
                        comp = comp.concat(arr[checkState].charMatch);
                    }
                    else{
                        currState = stackPop(currState);
                    }

                    if (queue.length > 0 && currState.length == 0) {
                        currState = queue;
                        queue = new int[0];
                    } 
                    else if (queue.length == 0 && currState.length == 0) {
                        break inner;
                    }
                }
                count++;
            }
        }

        in.close();
    }*/

    private void search() throws IOException {

        Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			scline = sc.nextLine();
			String[] lineSplit = scline.split(",");
            FSMpoint item = new FSMpoint(Integer.parseInt(lineSplit[0]), lineSplit[1], Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3]));
            arr = arrAdd(item, arr);
		}
        
        BufferedReader in = new BufferedReader(new FileReader(file));

        while ((line=in.readLine())!=null)  {
            count = 0;
            //increment array to show what line the string was found on
            lineCount++;
            
            char[] lineArray = new char[line.length()];

            //convert the 
            for (int i = 0; i < line.length(); i++) {
                lineArray[i] = line.charAt(i);
            }

            int innerCount = 0;

            //loop to check if the whole line has been searched.
            outer:  while (count < lineArray.length){

                //System.out.println("Checking line: " + lineCount);
                queue = new int[0];
                currState = new int[0];
                comp = "";
                innerCount = count;
                // adds first state
                currState = arrAdd(arr[0].stateNum, currState);

                inner: while (currState.length > 0) {

                    int checkState = currState[currState.length - 1];

                    //System.out.println("current checkstate " + checkState);

                    if (arr[checkState].charMatch.equals("-1")) {
                        if (arr[checkState].bState1 == -1) {
                            System.out.println("Match: " + comp + " on line: " + lineCount);
                            break outer;
                        }
                        int b1 = arr[checkState].bState1;
                        int b2 = arr[checkState].bState2;
                        
                        currState = stackPop(currState);
                        
                        currState = arrAdd(b1, currState);
                        currState = arrAdd(b2, currState);

                        checkState = currState[currState.length - 1];
                        //System.out.println("new checkstate from branch command " + checkState);
                    }

                    //System.out.println("match: " + arr[checkState].charMatch + " with " + String.valueOf(lineArray[innerCount]));

                    if (arr[checkState].charMatch.equals(String.valueOf(lineArray[innerCount]))) {
                        //System.out.println("equal on " + checkState);
                        int nextState = arr[checkState].bState1;
                        // add next state to queue
                        queue = arrAdd(nextState, queue);
                        currState = new int[0];
                        innerCount++;
                        comp = comp.concat(arr[checkState].charMatch);
                    }
                    else{
                        currState = stackPop(currState);
                    }

                    if (queue.length > 0 && currState.length == 0) {
                        currState = queue;
                        queue = new int[0];
                    } 
                    else if (queue.length == 0 && currState.length == 0) {
                        break inner;
                    }
                }
                count++;
            }
        }

        in.close();
    }

    private FSMpoint[] arrAdd(FSMpoint item, FSMpoint[] arr) {
        FSMpoint[] temp = arr;
        arr = new FSMpoint[temp.length + 1];
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

        if(temp.length == 1){
            arr = new int[0];
            return arr;
        }

        arr = new int[temp.length - 1];
        for (int i = 0; i < temp.length - 1; i++) {
            arr[i] = temp[i];
        }

        return arr;
    }

    class FSMpoint {
        public int stateNum;
        public String charMatch;
        public int bState1;
        public int bState2;

        FSMpoint (int stateNum, String charMatch, int bState1, int bState2){
            this.stateNum = stateNum;
            this.charMatch = charMatch;
            this.bState1 = bState1;
            this.bState2 = bState2;
        }
    }
}
