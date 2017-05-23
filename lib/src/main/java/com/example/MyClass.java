package com.example;

import io.reactivex.Observable;

public class MyClass {
    public static void main(String [] args) {
        Integer[] numbers = {0, -1, -2, 1, 2, 3};
        Observable.fromArray(numbers)
                .filter(number -> number > 2)
                .subscribe(number -> System.out.println("that "+number));

        Observable.fromArray(numbers)
                .forEach(number -> System.out.println("->" + number));
    }
}
