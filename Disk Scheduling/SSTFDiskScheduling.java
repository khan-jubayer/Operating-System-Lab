import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SSTFDiskScheduling {
    public static void main(String[] args) {
        List<Integer> reqSeq = new ArrayList<>(Arrays.asList(176, 79, 34, 60, 92, 11, 41, 114));
        int head = 50;
        int currentPosition = head;
        int closedSeq = 0;
        int seekOp = 0;
        List<Integer> output = new ArrayList<>();

        // Print initial inputs
        System.out.print("Request Sequence: ");
        for (int n : reqSeq) {
            System.out.print(n + " ");
        }
        System.out.println("\nInitial Head Position: " + head);

        // Add head to sequence
        reqSeq.add(head);

        while (reqSeq.size() > 1) {
            Collections.sort(reqSeq);
            
            int headIndex = reqSeq.indexOf(head);
            int leftDiff = Integer.MAX_VALUE;
            int rightDiff = Integer.MAX_VALUE;
            
            if (headIndex > 0) {
                leftDiff = reqSeq.get(headIndex) - reqSeq.get(headIndex - 1);
            }
            if (headIndex < reqSeq.size() - 1) {
                rightDiff = reqSeq.get(headIndex + 1) - reqSeq.get(headIndex);
            }
            
            if (leftDiff < rightDiff) {
                closedSeq = reqSeq.get(headIndex - 1);
            } else {
                closedSeq = reqSeq.get(headIndex + 1);
            }
            
            output.add(closedSeq);
            reqSeq.remove(headIndex);
            head = closedSeq;
        }

        // Calculate seek operations
        currentPosition = head;
        for (int n : output) {
            seekOp += Math.abs(currentPosition - n);
            currentPosition = n;
        }

        // Print results
        System.out.println("Total Seek Operations: " + seekOp);
        System.out.print("Service Sequence: ");
        for (int i = 0; i < output.size(); i++) {
            System.out.print(output.get(i));
            if (i < output.size() - 1) {
                System.out.print(" -> ");
            }
        }
    }
}
