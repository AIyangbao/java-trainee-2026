package hello;

import java.util.Scanner;

/**
 * 实验1 - 第10题
 * 面积计算程序，使用方法重载实现正方形、长方形、圆形的面积计算。
 *
 * 重载的区分依据：参数个数不同 或 参数类型不同
 *   area(int side)              正方形 —— 1 个 int 参数
 *   area(double length, double width)  长方形 —— 2 个 double 参数
 *   area(double radius)         圆形 —— 1 个 double 参数
 * 正方形和圆形都是"一个数"，靠 int / double 的类型差异来区分重载。
 */
public class AreaOverload {

    public static double area(int side) {
        return side * side;
    }

    public static double area(double length, double width) {
        return length * width;
    }

    public static double area(double radius) {
        return Math.PI * radius * radius;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请选择要计算面积的图形：1-正方形  2-长方形  3-圆形");
        System.out.print("请输入序号：");
        int choice = sc.nextInt();

        double result;
        switch (choice) {
            case 1:
                System.out.print("请输入正方形的边长（整数）：");
                result = area(sc.nextInt());
                break;
            case 2:
                System.out.print("请输入长方形的长和宽（空格分隔）：");
                result = area(sc.nextDouble(), sc.nextDouble());
                break;
            case 3:
                System.out.print("请输入圆形的半径：");
                result = area(sc.nextDouble());
                break;
            default:
                System.out.println("输入有误，程序结束");
                sc.close();
                return;
        }

        System.out.printf("该图形的面积为：%.2f%n", result);

        sc.close();
    }
}
