import java.util.*;

public class FIFOPageReplacement {
    public static void main(String[] args) {
        int[] pages = {1, 3, 0, 3, 5, 6, 3};
        int frameSize = 3;
        List<Integer> frames = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pages) {
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() >= frameSize) {
                    frames.remove(0); // Remove oldest
                }
                frames.add(page);
            }
        }

        System.out.println("Total Page Faults (FIFO): " + pageFaults);
    }
}
