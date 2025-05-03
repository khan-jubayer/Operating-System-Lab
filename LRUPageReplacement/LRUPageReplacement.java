import java.util.*;

public class LRUPageReplacement {
    public static void main(String[] args) {
        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3};
        int frameSize = 4;
        List<Integer> frames = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pages) {
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() >= frameSize) {
                    frames.remove(0); // Remove LRU
                }
            } else {
                frames.remove((Integer) page); // Remove and re-add
            }
            frames.add(page);
        }

        System.out.println("Total Page Faults (LRU): " + pageFaults);
    }
}
