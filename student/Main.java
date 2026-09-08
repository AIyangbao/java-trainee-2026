package student;

import java.util.List;
import java.util.Scanner;

/**
 * 学生信息管理系统 —— 控制台入口。
 * 对应课程设计中的「视图层（View）」。
 */
public class Main {

    private static final StudentManager manager = new StudentManager();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        manager.load();
        System.out.println("数据已加载，当前共 " + manager.list().size() + " 条记录。");

        if (manager.list().isEmpty()) {
            System.out.print("暂无数据，是否加载示例数据？(y/n)：");
            if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                loadDemoData();
            }
        }

        while (true) {
            printMenu();
            int choice = readInt("请选择操作：");
            System.out.println();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    findById();
                    break;
                case 5:
                    findByName();
                    break;
                case 6:
                    showAll();
                    break;
                case 7:
                    sortMenu();
                    break;
                case 8:
                    showStats();
                    break;
                case 9:
                    manager.save();
                    System.out.println("已保存到 students.txt");
                    break;
                case 0:
                    manager.save();
                    System.out.println("数据已保存，系统退出，再见！");
                    return;
                default:
                    System.out.println("无效的选择，请输入 0-9 之间的数字。");
            }
            System.out.println();
        }
    }

    // ---------------- 菜单与工具方法 ----------------

    private static void printMenu() {
        System.out.println("========== 学生信息管理系统 ==========");
        System.out.println("  1. 添加学生");
        System.out.println("  2. 删除学生");
        System.out.println("  3. 修改学生");
        System.out.println("  4. 查询学生（按学号）");
        System.out.println("  5. 查询学生（按姓名）");
        System.out.println("  6. 显示所有学生");
        System.out.println("  7. 排序");
        System.out.println("  8. 成绩统计");
        System.out.println("  9. 保存数据");
        System.out.println("  0. 退出系统");
        System.out.println("=====================================");
    }

    /** 统一用 nextLine 再解析，避免 Scanner 混用 nextInt/nextLine 时吞掉回车的经典坑 */
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("输入有误，请输入整数。");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("输入有误，请输入数字。");
            }
        }
    }

    private static void printTableHeader() {
        System.out.println("+------------+----------+------+------+--------+");
        System.out.println("| 学号       | 姓名     | 年龄 | 性别 | 成绩   |");
        System.out.println("+------------+----------+------+------+--------+");
    }

    private static void printTableFooter() {
        System.out.println("+------------+----------+------+------+--------+");
    }

    // ---------------- 各功能 ----------------

    private static void addStudent() {
        System.out.println("【添加学生】");
        String id = readLine("学号：");
        if (manager.findById(id) != null) {
            System.out.println("该学号已存在，添加失败。");
            return;
        }
        String name = readLine("姓名：");
        int age = readInt("年龄：");
        String gender = readLine("性别：");
        double score = readDouble("成绩：");

        if (manager.add(new Student(id, name, age, gender, score))) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败。");
        }
    }

    private static void removeStudent() {
        System.out.println("【删除学生】");
        String id = readLine("请输入要删除的学号：");
        if (manager.remove(id)) {
            System.out.println("删除成功！");
        } else {
            System.out.println("未找到该学号的学生。");
        }
    }

    private static void updateStudent() {
        System.out.println("【修改学生】");
        String id = readLine("请输入要修改的学号：");
        if (manager.findById(id) == null) {
            System.out.println("未找到该学号的学生。");
            return;
        }
        String name = readLine("新姓名：");
        int age = readInt("新年龄：");
        String gender = readLine("新性别：");
        double score = readDouble("新成绩：");

        if (manager.update(id, name, age, gender, score)) {
            System.out.println("修改成功！");
        }
    }

    private static void findById() {
        System.out.println("【按学号查询】");
        String id = readLine("学号：");
        Student s = manager.findById(id);
        if (s == null) {
            System.out.println("未找到该学号的学生。");
            return;
        }
        printTableHeader();
        System.out.println(s);
        printTableFooter();
    }

    private static void findByName() {
        System.out.println("【按姓名查询】");
        String keyword = readLine("请输入姓名关键字：");
        List<Student> result = manager.findByName(keyword);
        if (result.isEmpty()) {
            System.out.println("没有匹配的学生。");
            return;
        }
        printTableHeader();
        for (Student s : result) {
            System.out.println(s);
        }
        printTableFooter();
        System.out.println("共找到 " + result.size() + " 条记录。");
    }

    private static void showAll() {
        System.out.println("【所有学生】");
        List<Student> all = manager.list();
        if (all.isEmpty()) {
            System.out.println("暂无学生信息。");
            return;
        }
        printTableHeader();
        for (Student s : all) {
            System.out.println(s);
        }
        printTableFooter();
        System.out.println("共 " + all.size() + " 条记录。");
    }

    private static void sortMenu() {
        System.out.println("【排序】1. 按成绩降序   2. 按学号升序");
        int c = readInt("请选择：");
        if (c == 1) {
            manager.sortByScoreDesc();
            System.out.println("已按成绩从高到低排序。");
        } else if (c == 2) {
            manager.sortById();
            System.out.println("已按学号升序排序。");
        } else {
            System.out.println("无效选择。");
            return;
        }
        showAll();
    }

    private static void showStats() {
        System.out.println("【成绩统计】");
        if (manager.list().isEmpty()) {
            System.out.println("暂无学生信息。");
            return;
        }
        Student top = manager.topStudent();
        Student low = manager.lowStudent();
        System.out.printf("学生人数：%d%n", manager.list().size());
        System.out.printf("平均成绩：%.2f%n", manager.averageScore());
        System.out.printf("最高分：%s（%.2f 分）%n", top.getName(), top.getScore());
        System.out.printf("最低分：%s（%.2f 分）%n", low.getName(), low.getScore());
    }

    private static void loadDemoData() {
        manager.add(new Student("2023001", "张三", 20, "男", 88.5));
        manager.add(new Student("2023002", "李四", 21, "女", 92.0));
        manager.add(new Student("2023003", "王五", 19, "男", 76.5));
        manager.add(new Student("2023004", "赵六", 22, "女", 65.0));
        manager.add(new Student("2023005", "孙七", 20, "男", 95.5));
        System.out.println("已加载 5 条示例数据。");
    }
}
