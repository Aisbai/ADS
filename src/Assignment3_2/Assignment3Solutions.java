package Assignment3_2;
import java.util.*;
public class Assignment3Solutions {

    // =========================================================
    // Task 1: Last Stone Weight
    // Uses a max-heap (PriorityQueue in reverse order) to always
    // extract the two heaviest stones and smash them.
    // =========================================================
    static int lastStoneWeight(int[] stones) {
        // Max-heap: largest element at the top
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int s : stones) maxHeap.offer(s);

        while (maxHeap.size() > 1) {
            int y = maxHeap.poll(); // heaviest
            int x = maxHeap.poll(); // second heaviest
            if (x != y) maxHeap.offer(y - x); // remainder goes back
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    // =========================================================
    // Task 2: Kth Largest Element in a Stream
    // Maintains a min-heap of size k. The root is always the
    // k-th largest element seen so far.
    // =========================================================
    static class KthLargest {
        private final PriorityQueue<Integer> minHeap; // min-heap of size k
        private final int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.minHeap = new PriorityQueue<>();
            for (int n : nums) add(n);
        }

        public int add(int val) {
            minHeap.offer(val);
            // Keep only the k largest elements
            while (minHeap.size() > k) minHeap.poll();
            return minHeap.peek(); // k-th largest is the minimum of the heap
        }
    }

    // =========================================================
    // Task 3: Design Twitter
    // Each tweet is stamped with a global timestamp. getNewsFeed
    // merges tweets from the user and all followees using a
    // max-heap sorted by timestamp, then returns the top 10.
    // =========================================================
    static class Twitter {
        private int timestamp = 0;
        // userId -> list of [timestamp, tweetId]
        private final Map<Integer, List<int[]>> tweets = new HashMap<>();
        // followerId -> set of followeeIds
        private final Map<Integer, Set<Integer>> following = new HashMap<>();

        public void postTweet(int userId, int tweetId) {
            tweets.computeIfAbsent(userId, k -> new ArrayList<>())
                    .add(new int[]{timestamp++, tweetId});
        }

        public List<Integer> getNewsFeed(int userId) {
            // Max-heap: [timestamp, tweetId, userIndex, tweetIndex]
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

            // Collect relevant users: self + followees
            Set<Integer> users = new HashSet<>();
            users.add(userId);
            users.addAll(following.getOrDefault(userId, Collections.emptySet()));

            for (int uid : users) {
                List<int[]> userTweets = tweets.get(uid);
                if (userTweets != null && !userTweets.isEmpty()) {
                    int idx = userTweets.size() - 1;
                    int[] latest = userTweets.get(idx);
                    // [timestamp, tweetId, uid, index]
                    heap.offer(new int[]{latest[0], latest[1], uid, idx});
                }
            }

            List<Integer> feed = new ArrayList<>();
            while (!heap.isEmpty() && feed.size() < 10) {
                int[] top = heap.poll();
                feed.add(top[1]);
                int uid = top[2];
                int idx = top[3] - 1;
                if (idx >= 0) {
                    int[] prev = tweets.get(uid).get(idx);
                    heap.offer(new int[]{prev[0], prev[1], uid, idx});
                }
            }
            return feed;
        }

        public void follow(int followerId, int followeeId) {
            following.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (following.containsKey(followerId))
                following.get(followerId).remove(followeeId);
        }
    }

    // =========================================================
    // Task 4: Task Scheduler
    // Uses a max-heap of task frequencies. In each cooling slot
    // (of size n+1), we greedily pick the most frequent task.
    // Formula: max(tasks.length, (maxFreq-1)*(n+1) + maxFreqCount)
    // =========================================================
    static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char t : tasks) freq[t - 'A']++;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) if (f > 0) maxHeap.offer(f);

        int time = 0;
        while (!maxHeap.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            // Try to fill one cooling interval of (n+1) slots
            for (int i = 0; i <= n; i++) {
                if (!maxHeap.isEmpty()) temp.add(maxHeap.poll());
            }
            // Decrement counts and re-add non-zero ones
            for (int f : temp) if (f - 1 > 0) maxHeap.offer(f - 1);

            // If heap is empty, we may finish before the full interval
            time += maxHeap.isEmpty() ? temp.size() : n + 1;
        }
        return time;
    }

    // =========================================================
    // Task 5: Kth Largest Element in an Array
    // Uses QuickSelect (average O(n)) to find the k-th largest
    // element without fully sorting the array.
    // =========================================================
    static int findKthLargest(int[] nums, int k) {
        // k-th largest = (n-k)-th smallest (0-indexed)
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private static int quickSelect(int[] nums, int left, int right, int targetIdx) {
        if (left == right) return nums[left];

        // Pick a random pivot to avoid worst-case O(n²)
        int pivotIdx = left + new Random().nextInt(right - left + 1);
        int pivotVal = nums[pivotIdx];

        // Move pivot to end
        swap(nums, pivotIdx, right);
        int store = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotVal) swap(nums, i, store++);
        }
        // Place pivot in its final sorted position
        swap(nums, store, right);

        if (store == targetIdx) return nums[store];
        else if (store < targetIdx) return quickSelect(nums, store + 1, right, targetIdx);
        else return quickSelect(nums, left, store - 1, targetIdx);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }

    // =========================================================
    // Task 6: K Closest Points to Origin
    // Uses a max-heap of size k, keeping the k nearest points.
    // Heap key = squared Euclidean distance (no sqrt needed).
    // =========================================================
    static int[][] kClosest(int[][] points, int k) {
        // Max-heap by distance: we evict the farthest when size > k
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])
        );
        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > k) maxHeap.poll(); // remove farthest
        }
        return maxHeap.toArray(new int[k][]);
    }

    // =========================================================
    // Task 7: Find Median from Data Stream
    // Maintains two heaps:
    //   - maxHeap (left): smaller half of numbers
    //   - minHeap (right): larger half of numbers
    // They are kept balanced so sizes differ by at most 1.
    // =========================================================
    static class MedianFinder {
        private final PriorityQueue<Integer> maxHeap; // left half, largest at top
        private final PriorityQueue<Integer> minHeap; // right half, smallest at top

        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // Always add to left half first
            maxHeap.offer(num);
            // Balance: max of left must be <= min of right
            if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                minHeap.offer(maxHeap.poll());
            }
            // Keep sizes balanced (left can have at most 1 more)
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
            // Left half has one more element
            return maxHeap.peek();
        }
    }

    // =========================================================
    // Main: demonstration of all tasks
    // =========================================================
    public static void main(String[] args) {

        System.out.println("=== Task 1: Last Stone Weight ===");
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1})); // 1
        System.out.println(lastStoneWeight(new int[]{1}));                 // 1
        System.out.println(lastStoneWeight(new int[]{2, 2}));              // 0

        System.out.println("\n=== Task 2: Kth Largest in Stream ===");
        KthLargest kth = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kth.add(3));  // 4
        System.out.println(kth.add(5));  // 5
        System.out.println(kth.add(10)); // 5
        System.out.println(kth.add(9));  // 8
        System.out.println(kth.add(4));  // 8

        System.out.println("\n=== Task 3: Design Twitter ===");
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 2);
        twitter.postTweet(2, 6);
        twitter.follow(1, 2);
        twitter.follow(2, 1);
        System.out.println(twitter.getNewsFeed(1)); // [2, 3, 5] (or includes 6)
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1)); // [2, 3, 5]

        System.out.println("\n=== Task 4: Task Scheduler ===");
        System.out.println(leastInterval(new char[]{'A','A','A','A','B','B','B','B'}, 2)); // 8 (wait A -> B -> idle -> A -> B -> idle...)
        System.out.println(leastInterval(new char[]{'A','A','A','A','B','B','B','B'}, 0)); // 8 (all tasks, no wait)
        System.out.println(leastInterval(
                new char[]{'A','A','A','A','A','A','A','A','B','C','D','E','F','G'}, 2));       // 16

        System.out.println("\n=== Task 5: Kth Largest in Array ===");
        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));              // 5
        System.out.println(findKthLargest(new int[]{3, 2, 3, 3, 1, 2, 2, 4, 5, 5, 6}, 4)); // 4

        System.out.println("\n=== Task 6: K Closest Points to Origin ===");
        System.out.println(Arrays.deepToString(
                kClosest(new int[][]{{1, 3}, {-2, 2}}, 1)));               // [[-2,2]]
        System.out.println(Arrays.deepToString(
                kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2)));      // [[3,3],[-2,4]]

        System.out.println("\n=== Task 7: Find Median from Data Stream ===");
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian()); // 1.5
        mf.addNum(3);
        System.out.println(mf.findMedian()); // 2.0
    }
}