import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CSCAN {
    public static void main(String[] args) {
        List<Integer> reqSeq = new ArrayList<>(Arrays.asList(0, 14, 41, 53, 65, 67, 98, 122, 124, 183, 199));
        List<Integer> output = new ArrayList<>();
        int head = 53;
        int seekOp = 0;

        // Print initial inputs
        System.out.print("Request Sequence: ");
        for (int n : reqSeq) {
            System.out.print(n + ", ");
        }
        System.out.println("\nHead is at: " + head);

        // Sort the request sequence
        Collections.sort(reqSeq);

        // Process requests from head to end (right side)
        List<Integer> rightSide = new ArrayList<>();
        for (int seq : reqSeq) {
            if (seq >= head) {
                rightSide.add(seq);
            }
        }
        output.addAll(rightSide);
        seekOp += (rightSide.get(rightSide.size() - 1) - head);

        // Add full disk traversal (end to start)
        seekOp += (reqSeq.get(reqSeq.size() - 1) - reqSeq.get(0));

        // Process requests from start to head (left side)
        List<Integer> leftSide = new ArrayList<>();
        for (int seq : reqSeq) {
            if (seq < head) {
                leftSide.add(seq);
            }
        }
        output.addAll(leftSide);
        if (!leftSide.isEmpty()) {
            seekOp += (leftSide.get(leftSide.size() - 1) - leftSide.get(0));
        }

        // Print results
        System.out.println("\nOutput:");
        System.out.println("Total Seek Operations for C-SCAN Disk Schedule: " + seekOp);
        System.out.print("Sequence Order: ");
        for (int n : output) {
            System.out.print(n + " ");
        }
    }
}
