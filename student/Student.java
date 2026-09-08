package student;

/**
 * 学生实体类：封装学生的基本信息。
 * 对应课程设计中的「实体层（Entity）」。
 */
public class Student {

    private String id;      // 学号（唯一）
    private String name;    // 姓名
    private int age;        // 年龄
    private String gender;  // 性别
    private double score;   // 成绩

    public Student() {
    }

    public Student(String id, String name, int age, String gender, double score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    /** 控制台表格展示用 */
    @Override
    public String toString() {
        return "| " + padRight(id, 10) + " | " + padRight(name, 8) + " | "
                + padRight(String.valueOf(age), 4) + " | " + padRight(gender, 4)
                + " | " + padRight(String.format("%.2f", score), 6) + " |";
    }

    /**
     * 按「显示宽度」右侧补空格：中文等全角字符算 2 列，英文数字算 1 列。
     * 直接用 String.format("%-8s") 对中文会算错宽度，导致表格错位，所以自己实现。
     */
    private static String padRight(String s, int width) {
        int displayWidth = 0;
        for (char c : s.toCharArray()) {
            displayWidth += (c > 127) ? 2 : 1;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = displayWidth; i < width; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    /** 转成文件里的一行（CSV 格式，用记事本也能打开看） */
    public String toFileString() {
        return id + "," + name + "," + age + "," + gender + "," + score;
    }

    /** 从文件里的一行还原成学生对象；格式不对返回 null */
    public static Student fromFileString(String line) {
        String[] p = line.split(",");
        if (p.length != 5) {
            return null;
        }
        try {
            return new Student(p[0], p[1], Integer.parseInt(p[2]), p[3], Double.parseDouble(p[4]));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
