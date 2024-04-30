package com.james.api.common.proxy;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MathProxy {
    public static Function<Integer, Integer> absInt = Math::abs;
    public static Function<Long,Long> absLong = Math::abs;
    public static Function<Float,Float> absFloat = Math::abs;
    public static Function<Double,Double> absDouble = Math::abs;
    // ^ 입력받은 값을 토대로 반환 < 같은 타입

    public static BiFunction<Integer,Integer,Integer> maxInt = Math::max;
    public static BiFunction<Long,Long,Long> maxLong = Math::max;
    public static BiFunction<Float,Float,Float> maxFloat = Math::max;
    public static BiFunction<Double,Double,Double> maxDouble = Math::max;
    // ^ 두수를 입력받아 큰수 출력

    public static BiFunction<Integer,Integer,Integer> minInt = Math::min;
    public static BiFunction<Long,Long,Long> minLong = Math::min;
    public static BiFunction<Float,Float,Float> minFloat = Math::min;
    public static BiFunction<Double,Double,Double> minDouble = Math::min;
    // ^ 두수 입력받아 작은수 출력
    public static Supplier<Double> randomDouble = Math::random;
    // ^ 주어지는값없이 랜덤으로 던져지는 값
    public static BiFunction<Integer,Integer,Integer> randomInt = (a,b) -> (int)(Math.random() * (b-a) + a);
    public static BiFunction<Double,Double,Double> randmDouble = (a,b) -> (double)(Math.random() * (b-a) + a);
    // ^ 주어지는 값이 있는 랜덤값 출력

    public static Function<Double,Double> ceilInt = Math::ceil;

    public static Function<Double,Double> floorInt = Math::floor;

    public static Function<Double,Long> roundInt = Math::round;

    public static Function<String,Integer> parsInt = Integer::parseInt;

    public static BiFunction<Integer,Integer,Integer> powInt = (a,b) -> (int) Math.pow(a,b);
}

