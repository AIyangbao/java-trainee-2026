package hello;

import java.util.Scanner;

/**
 * 实验1 - 第7题
 * 猜数字游戏：程序随机生成一个 1~100 的整数，用户反复输入猜测，
 * 程序提示"猜大了""猜小了""猜对了"，直到猜对为止。
 */
public class GuessNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Math.random() 生成 [0,1) 的小数，乘 100 得 [0,100)，转 int 得 0~99，再 +1 得 1~100
        int target = (int) (Math.random() * 100) + 1;

        int guess;
        int times = 0;

        System.out.println("程序已随机生成一个 1~100 之间的整数，请开始猜：");

        do {
            System.out.print("请输入你的猜测：");
            guess = sc.nextInt();
            times++;

            if (guess > target) {
                System.out.println("猜大了");
            } else if (guess < target) {
                System.out.println("猜小了");
            } else {
                System.out.println("猜对了");
            }
        } while (guess != target);

        System.out.println("你一共猜了 " + times + " 次");

        sc.close();
    }
}
