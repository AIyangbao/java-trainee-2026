package hello;

import java.util.Scanner;

/**
 * 实验1 - 第5题
 * 键盘输入一个学生两门课程的成绩，按平均成绩分等级显示考核结果。
 * 优秀：平均成绩 >= 90
 * 良好：80 <= 平均成绩 < 90
 * 中等：70 <= 平均成绩 < 80
 * 及格：60 <= 平均成绩 < 70
 * 不及格：平均成绩 < 60
 */
public class GradeLevel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入第一门课程的成绩：");
        double score1 = sc.nextDouble();

        System.out.print("请输入第二门课程的成绩：");
        double score2 = sc.nextDouble();

        double avg = (score1 + score2) / 2;

        String level;
        if (avg >= 90) {
            level = "优秀";
        } else if (avg >= 80) {
            level = "良好";
        } else if (avg >= 70) {
            level = "中等";
        } else if (avg >= 60) {
            level = "及格";
        } else {
            level = "不及格";
        }

        System.out.printf("两门课程成绩分别为：%.1f、%.1f%n", score1, score2);
        System.out.printf("平均成绩为：%.2f%n", avg);
        System.out.println("考核结果等级：" + level);

        sc.close();
    }
}
