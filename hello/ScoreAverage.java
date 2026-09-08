package hello;

import java.util.Scanner;

/**
 * 实验1 - 第8题
 * 从键盘输入 10 名学生的成绩，计算平均成绩，
 * 并输出高于平均分的学生成绩及人数。
 */
public class ScoreAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double[] scores = new double[10];
        double sum = 0;

        for (int i = 0; i < scores.length; i++) {
            System.out.print("请输入第 " + (i + 1) + " 名学生的成绩：");
            scores[i] = sc.nextDouble();
            sum += scores[i];
        }

        double avg = sum / scores.length;
        System.out.printf("10 名学生的平均成绩为：%.2f%n", avg);

        int count = 0;
        System.out.println("高于平均分的学生成绩：");
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > avg) {
                System.out.printf("第 %d 名学生：%.1f%n", i + 1, scores[i]);
                count++;
            }
        }

        System.out.println("高于平均分的人数为：" + count);

        sc.close();
    }
}
