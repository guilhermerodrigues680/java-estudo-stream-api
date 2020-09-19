package com.estudo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("OK!");

        List<Pessoa> list = new ArrayList<>();

        Pessoa p0 = new Pessoa("Maria", 20);
        list.add(p0);

        for (int i = 0; i < 10; i++) {
            Pessoa p = new Pessoa("Guilherme", 22 + i);
            list.add(p);
        }

        List<Pessoa> filtrada1 = list.stream()
                .filter(distinctByKey(p -> p.getNome()))
                .collect(Collectors.toList());

        System.out.println(filtrada1.size());
        filtrada1.forEach(System.out::println);

        List<Pessoa> filtrada2 = list.stream()
                .filter(p -> p.getNome().toUpperCase().equals("GUILHERME"))
                .collect(Collectors.toList());

        System.out.println(filtrada2.size());
        filtrada2.forEach(System.out::println);

    }

    // Seleciona somente os repetidos
    public static <T> Predicate<T> indistinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) != null;
    }

    // Seleciona distintos
    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
