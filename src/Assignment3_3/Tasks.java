package Assignment3_3;

import java.util.*;

public class Tasks {

    // Task #1 - Valid Palindrome
    public static boolean isPalindrome(String s) {

        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // Task #2 - Two Sum II
    public static int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }

            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1};
    }

    // Task #3 - 3Sum
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    // Task #4 - Container With Most Water
    public static int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int maxWater = 0;

        while (left < right) {

            int width = right - left;

            int currentHeight = Math.min(height[left], height[right]);

            int area = width * currentHeight;

            maxWater = Math.max(maxWater, area);

            // Move smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }

    // Task #5 - Trapping Rain Water
    public static int trap(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int water = 0;

        while (left < right) {

            if (height[left] < height[right]) {

                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }

                left++;

            } else {

                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }

                right--;
            }
        }

        return water;
    }

    // Main method for testing
    public static void main(String[] args) {

        // Task #1
        System.out.println("Task #1 - Valid Palindrome");
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));

        // Task #2
        System.out.println("\nTask #2 - Two Sum II");
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(result));

        // Task #3
        System.out.println("\nTask #3 - 3Sum");
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));

        // Task #4
        System.out.println("\nTask #4 - Container With Most Water");
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height1));

        // Task #5
        System.out.println("\nTask #5 - Trapping Rain Water");
        int[] height2 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height2));
    }
}