package student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 学生信息管理类：负责增删改查、排序、统计以及数据持久化。
 * 对应课程设计中的「业务层（Service / DAO）」。
 */
public class StudentManager {

    private final List<Student> students = new ArrayList<>();

    /** 数据文件，放在程序运行目录下（IDEA 里就是项目根目录） */
    private static final Path DATA_FILE = Paths.get("students.txt");

    public List<Student> list() {
        return students;
    }

    /** 添加学生，学号重复返回 false */
    public boolean add(Student s) {
        if (findById(s.getId()) != null) {
            return false;
        }
        students.add(s);
        return true;
    }

    /** 按学号精确查找 */
    public Student findById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    /** 按姓名模糊查找 */
    public List<Student> findByName(String keyword) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().contains(keyword)) {
                result.add(s);
            }
        }
        return result;
    }

    /** 按学号删除 */
    public boolean remove(String id) {
        Student s = findById(id);
        return s != null && students.remove(s);
    }

    /** 修改学生信息，学号不存在返回 false */
    public boolean update(String id, String name, int age, String gender, double score) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }
        s.setName(name);
        s.setAge(age);
        s.setGender(gender);
        s.setScore(score);
        return true;
    }

    /** 按成绩从高到低排序 */
    public void sortByScoreDesc() {
        students.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
    }

    /** 按学号升序排序 */
    public void sortById() {
        students.sort(Comparator.comparing(Student::getId));
    }

    /** 平均分；没有学生时返回 0 */
    public double averageScore() {
        if (students.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (Student s : students) {
            sum += s.getScore();
        }
        return sum / students.size();
    }

    /** 最高分学生；没有学生时返回 null */
    public Student topStudent() {
        Student top = null;
        for (Student s : students) {
            if (top == null || s.getScore() > top.getScore()) {
                top = s;
            }
        }
        return top;
    }

    /** 最低分学生；没有学生时返回 null */
    public Student lowStudent() {
        Student low = null;
        for (Student s : students) {
            if (low == null || s.getScore() < low.getScore()) {
                low = s;
            }
        }
        return low;
    }

    /** 从文件加载数据 */
    public void load() {
        if (!Files.exists(DATA_FILE)) {
            return;
        }
        try {
            List<String> lines = Files.readAllLines(DATA_FILE, StandardCharsets.UTF_8);
            students.clear();
            for (String line : lines) {
                if (line == null || line.isBlank()) {
                    continue;
                }
                Student s = Student.fromFileString(line);
                if (s != null) {
                    students.add(s);
                }
            }
        } catch (IOException e) {
            System.out.println("读取数据失败：" + e.getMessage());
        }
    }

    /** 保存数据到文件 */
    public void save() {
        try {
            List<String> lines = new ArrayList<>();
            for (Student s : students) {
                lines.add(s.toFileString());
            }
            Files.write(DATA_FILE, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("保存数据失败：" + e.getMessage());
        }
    }
}
