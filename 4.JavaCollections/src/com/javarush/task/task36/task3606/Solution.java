package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File[] files = new File(packageName).listFiles();
        for (File file :
                files) {
            String finalPath = Paths.get(packageName).toAbsolutePath() + File.separator;
            ClassLoader loader = new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    try {
                        byte[] temp = Files.readAllBytes(Paths.get(finalPath + name + ".class"));
                        return defineClass(null, temp, 0, temp.length);
                    } catch (IOException e) {
                        return super.findClass(name);
                    }
                }
            };
            String className = file.getName();
            Class aClass = loader.loadClass(className.substring(0, className.length() - 6));
            if (HiddenClass.class.isAssignableFrom(aClass)) {
                hiddenClasses.add(aClass);
            }
        }
    }


    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz : hiddenClasses){
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())){
                int count = 0;
                int none = 0;
                try{
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for (Constructor constructor : constructors) {
                        if (constructor.getParameterTypes().length == 0) {
                            none = count;
                        }
                        count++;
                    }
                    constructors[none].setAccessible(true);
                    return (HiddenClass) constructors[none].newInstance();
                }
                catch (Exception e){
                }
            }
        }
        return null;
    }
}

