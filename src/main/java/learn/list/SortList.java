package learn.list;


import java.util.List;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/8/15 22:19
 * @description list排序
 * 1、
 */
public class SortList {


    public static void main(String[] args) {
        List<Student> studentList = FilterList.stuList();
        //1.8 写法
        studentList.sort((o1,o2)-> o2.getAge()-o1.getAge());
        //1.7 写法
        /*Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });*/
        System.out.println(studentList);
    }


}
