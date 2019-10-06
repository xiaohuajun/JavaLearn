package learn.streamuse;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/8/19 16:49
 * @description
 */
public class StreamTest {


    public static List<Integer> assembleList() {
        List<Integer> integerList = new ArrayList<>();
        Integer[] ints = {1, 2, 3, 7, 10, 11, 14, 2, 29, 23, 11, 18, 33};
        integerList = Arrays.asList(ints);
        return integerList;
    }


    public static void main(String[] args) {
        List<Integer> integerList = StreamTest.assembleList();
        long rs = integerList.stream().filter(Objects::nonNull).count();
        //创建stream
        Stream<String> stream = Stream.of("1", "12", "190", "156", "23", "789", "234", "578");
        //转换stream
        //去重
        Stream.of(1, 13, 1, 4, 14).distinct().forEach(System.out::println);
        //过滤
        Stream.of(1, 13, 1, 4, 14, 4).filter(s -> s != 1).forEach(System.out::println);
        //元素的映射--此外还有：mapToInt() mapToDouble() mapToLong()方法
        Stream.of(1, 13, 1, 4, 14, 4).map(integer -> integer * 10).forEach(System.out::println);
        //flatMap() 与 map() 区别是---flatMap转换函数对象是一个Stream对象
        //不会新创建stream,而是将原来的取代为新的stream
        //分别是：flatMapToInt，flatMapToLong和flatMapToDouble。
        Stream.of(1, 13, 1, 4, 14, 4).flatMap(integer -> Stream.of(integer * 10)).forEach(System.out::println);
        //peek--生成一个新的stream，这个stream包含了原stream元素和消费函数产生的元素。
        Stream.of(1, 13, 1, 4, 14, 4).peek(s -> System.out.println("accept:" + s)).forEach(System.out::println);
        //跳过前面N个元素
        Stream.of(1, 13, 1, 4, 14, 4).skip(4).forEach(System.out::println);
        //默认是升序
        Stream.of(1, 4, 5, 7, 14, 3, 19, 8).sorted().forEach(System.out::println);
        //可以使用比较自定义排序规则
        Stream.of(1, 4, 5, 7, 14, 3, 19, 8).sorted(((o1, o2) -> o2 - o1)).forEach(System.out::println);
        //min 取第一个  max 取最后一个
        Optional<Integer> v = Stream.of(1, 4, 5, 7, 14, 3, 19, 8).min((o1, o2) -> o1 - o2);
        System.out.println(v.get());
        //limit(N) 取前n个元素
        Stream.of(1, 4, 5, 7, 14, 3, 19, 8).limit(2).forEach(System.out::println);
        //integerList.stream().forEach(integer -> System.out.println(integer));
        /*long cs = Stream.of(1, 4, 5, 7, 14, 3, 19, 8).count();
        System.out.println(cs);*/

        /*Optional<Integer> min = Stream.of(1, 2, 3, 4, 5).min((o1, o2) -> o1 - o2);
        System.out.println("min:" + min.get());*/
        List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());

    }


}
