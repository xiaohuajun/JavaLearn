package learn.list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/23 12:54
 * @description 对list进行过滤操作
 */
public class FilterList {


    public static List<Student> stuList() {
        List<Student> stuList = new ArrayList<>();
        Student stu1 = new Student();
        stu1.setStuNum(20180823);
        stu1.setName("张三");
        stu1.setAge(28);
        stuList.add(stu1);

        Student stu2 = new Student();
        stu2.setStuNum(20180824);
        stu2.setName("李四");
        stu2.setAge(20);
        stuList.add(stu2);

        Student stu3 = new Student();
        stu3.setStuNum(20180825);
        stu3.setName("王二");
        stu3.setAge(24);
        stuList.add(stu3);

        Student stu4 = new Student();
        stu4.setStuNum(20180826);
        stu4.setName("张五");
        stu4.setAge(19);
        stuList.add(stu4);
        return stuList;
    }


    public static void main(String[] args) {
        List<Student> studentList = stuList();

        //定义筛选条件
        List<Integer> conditions = new ArrayList<>();
        conditions.add(22);
        conditions.add(21);

        List<Student> filterStudent;
        //过滤
        filterStudent = studentList.stream().filter(s -> s.getName().contains("张"))
                .collect(Collectors.toList());
        filterStudent.forEach((Student s) ->
                System.out.println("过滤后的list---->" + s));
        //System.out.println("过滤后的list---->" + filterStudent);
    }


}
