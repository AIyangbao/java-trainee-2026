package hello;

/**
 * 实验1 - 第9题
 * 定义一个三行四列的二维整数数组，程序中直接赋值，求每行元素之和并输出。
 */
public class Array2DSum {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        for (int i = 0; i < arr.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < arr[i].length; j++) {
                rowSum += arr[i][j];
            }
            System.out.println("第 " + (i + 1) + " 行元素之和为：" + rowSum);
        }
    }
}
