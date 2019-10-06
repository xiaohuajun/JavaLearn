package learn.list;


/**
 * @author Danny.
 * @version 1.0
 * @date 2019/7/23 13:00
 * @description
 */
public class Student  {

    private int stuNum;
    private String name;
    private int age;


    public int getStuNum() {
        return stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
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


    @Override
    public String toString() {
        return "Student--->{" + "stuNum=" + stuNum +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

   /* @Override
    public int compareTo(Student o) {
        return o.getAge()-this.getAge();
    }*/
}
