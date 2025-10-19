import java.util.*;

public class MaximalRectangle {

    // Main function to compute maximal rectangle
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols]; // histogram heights
        int maxArea = 0;

        // Traverse each row
        for (int i = 0; i < rows; i++) {
            // Step 1: Update histogram heights
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1; // add to consecutive 1s
                } else {
                    heights[j] = 0; // reset if it's '0'
                }
            }

            // Step 2: Compute largest rectangle area for this histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    // Helper function: Largest Rectangle in Histogram
    private int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // Use 0 at the end to flush out remaining bars
            int h = (i == n) ? 0 : heights[i];

            // Maintain increasing stack
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    // -----------------------------
    // Example usage
    // -----------------------------
    public static void main(String[] args) {
        MaximalRectangle solver = new MaximalRectangle();

        char[][] matrix1 = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };

        char[][] matrix2 = { {'0'} };
        char[][] matrix3 = { {'1'} };

        System.out.println(solver.maximalRectangle(matrix1)); // Output: 6
        System.out.println(solver.maximalRectangle(matrix2)); // Output: 0
        System.out.println(solver.maximalRectangle(matrix3)); // Output: 1
    }
}
