package org.example;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println(new HorseClass().getStepsCount(
                3,
                Stream.of("S..", "F..", "...").toArray(String[]::new)
        ));
    }
}