package com.line.lamp.funcInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @desc Java四大内置函数式接口
 *  Consumer<T>：消费型接口
 *      void accept(T t);
 *  Suppler<T>：供给型接口
 *      T get();
 *  Function<T, R>：函数型接口
 *      R apply(T t);
 *  Predicate<T>：断言型接口
 *      boolean test(T t);
 * @Author xw
 * @Date 2019/9/9
 */
public class JDKFuncInterfaceDemo {
    public static void main(String[] args) {
        // Consumer<T>：消费型接口
        printConsumer("abcd", (x) -> System.out.println(x));

        // Suppler<T>：供给型接口；
        // 案例：产生指定个数的整数，并放入集合中
        int num = 5;
        getNumList(num, () -> new Random().nextInt(100)).stream().forEach(System.out::println);

        // Function<T, R>：函数型接口；
        // 案例：字符串处理（转大小写、截取等）
        System.out.println(strHandler("abcdefg", str -> str.toUpperCase()));
        System.out.println(strHandler("abcdefg", str -> str.substring(0,3)));

        // Predicate<T>：断言型接口（见MyFuncInterfaceDemo）
    }

    private static void printConsumer(String str, Consumer<String> func) {
        func.accept(str);
    }

    private static String strHandler(String str, Function<String, String> func) {
        return func.apply(str);
    }

    private static List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }
}
