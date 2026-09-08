package hello;

import java.util.Scanner;

/**
 * 实验1 - 第4题
 * 键盘输入圆柱体的半径和高，计算圆柱体的面积和体积，结果保留 2 位小数。
 * 面积 = 半径 × 半径 × π
 * 体积 = 面积 × 高
 */
public class Cylinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入圆柱体的半径：");
        double radius = sc.nextDouble();

        System.out.print("请输入圆柱体的高：");
        double height = sc.nextDouble();

        double area = radius * radius * Math.PI;
        double volume = area * height;

        System.out.printf("圆柱体的面积为：%.2f%n", area);
        System.out.printf("圆柱体的体积为：%.2f%n", volume);

        sc.close();
    }
}
