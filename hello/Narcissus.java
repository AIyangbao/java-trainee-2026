package hello;

/**
 * 实验1 - 第6题
 * 找出所有的水仙花数。
 * 水仙花数：一个三位数，其各位数字立方和等于该数本身。
 * 例如：153 = 1³ + 5³ + 3³
 */
public class Narcissus {
    public static void main(String[] args) {
        System.out.println("100~999 之间的水仙花数有：");

        int count = 0;
        for (int n = 100; n <= 999; n++) {
            int ge = n % 10;          // 个位
            int shi = n / 10 % 10;    // 十位
            int bai = n / 100;        // 百位

            int sum = ge * ge * ge + shi * shi * shi + bai * bai * bai;

            if (sum == n) {
                System.out.println(n);
                count++;
            }
        }

        System.out.println("共 " + count + " 个水仙花数");
    }
}
