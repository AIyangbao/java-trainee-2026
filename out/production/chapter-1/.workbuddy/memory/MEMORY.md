# 工作区长期记忆

## 这个工作区是什么
`F:\新建文件夹\` = 学校《Java程序设计》实验1 的 IDEA 项目，项目名 `chapter-1`，包名 `hello`。
不是 my_blog，也不是长期开发项目，属于**课程作业交付区**。

## 用户对本 Java 项目的立场（2026-09-01 明确表态）
> "Java 我不细学"，"按照作业做了先"

**策略：快速交付，不做深度教学。**

- 直接给**完整可运行代码**，不要只给骨架让他自己补（他主栈不是 Java，不想在这上面耗时间）
- 不主动推工程化改进（src 标准布局、命名规范、设计模式等）——他已明确拒绝过一次 src 重构
- 遇到环境问题：能绕就绕，只在他跑不起来时才深入排查
- 交付前自己先编译 + 跑通 + 验证输出结果正确，再给他截图/交差

## 项目结构约定
```
F:\新建文件夹\          ← 项目根，同时是 Sources Root（非标准，但已定，不再改）
├── hello\             ← 包 hello
│   ├── HelloWorld.java    第2题
│   ├── Cylinder.java      第4题 圆柱体面积体积
│   ├── GradeLevel.java    第5题 成绩分等级
│   ├── Narcissus.java     第6题 水仙花数
│   ├── GuessNumber.java   第7题 猜数字
│   ├── ScoreAverage.java  第8题 10人成绩均分
│   ├── Array2DSum.java    第9题 二维数组行和
│   └── AreaOverload.java  第10题 方法重载求面积
├── .idea\
└── out\
```

**已知副作用（他说不修，别再提）**：源码根 = 项目根，导致 Rebuild 时 `.idea\` 和 `.workbuddy\` 会被复制进 `out\production\chapter-1\`。不报错，只是脏。

## 验证过的正确输出（回归基线）
| 题 | 输入 | 期望输出 |
|---|---|---|
| 4 | 半径3 高5 | 面积 28.27，体积 141.37 |
| 5 | 85、92 | 平均 88.50，良好 |
| 6 | 无 | 153 370 371 407，共4个 |
| 8 | 60 70 80 90 50 65 75 85 95 55 | 平均 72.50，5人高于平均（第3、4、7、8、9名） |
| 9 | 无 | 第1行10，第2行26，第3行42 |
| 10 | 1→5 / 3→2.0 / 2→3.5 4.2 | 25.00 / 12.57 / 14.70 |

## 构建/验证命令（AI 可跑）
```bash
cd "/f/新建文件夹"
J="/d/Java/jdk-26.0.2.1/bin/java.exe"
mkdir -p _verify && "/d/Java/jdk-26.0.2.1/bin/javac.exe" -encoding UTF-8 -d _verify hello/*.java
$J -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp _verify hello.Cylinder
rm -rf _verify
```
需要 stdin 的程序用 `printf "3\n5\n" | $J ...` 喂输入。
