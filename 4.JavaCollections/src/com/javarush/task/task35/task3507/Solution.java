package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;

import java.lang.reflect.Modifier;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/* 
ClassLoader - что это такое?
*/
public class Solution {

    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> animals = new HashSet<>();
        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/")) pathToAnimals += "/";
        File[] files = new File(pathToAnimals).listFiles((dir, name) -> name.toLowerCase().endsWith(".class"));

        Arrays.stream(files).forEach(file -> {
            ClassLoader classLoader = new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    try {
                        byte[] buff = Files.readAllBytes(file.toPath());
                        return defineClass(null, buff, 0, buff.length);
                    } catch (IOException e) {
                        return super.findClass(name);
                    }

                }
            };
            try {
                Class clazz = classLoader.loadClass(file.getName().replaceAll(".class", ""));
                Arrays.stream(clazz.getInterfaces()).filter(Predicate.isEqual(Animal.class)).forEach(interfaze -> {
                    Arrays.stream(clazz.getConstructors()).forEach(constructor -> {
                        if (Modifier.isPublic(constructor.getModifiers())
                                && constructor.getParameterCount() == 0) {
                            try {
                                animals.add((Animal) clazz.newInstance());
                            } catch (InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                });
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return animals;
    }
}
