package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class DinnerConstructor {
    private final HashMap<String, ArrayList<String>> dishes;

    public DinnerConstructor() {
        dishes = new HashMap<>();
    }

    private String formatDishName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public void addDish(String dishType, String dishName) {
        dishType = formatDishName(dishType);
        dishName = formatDishName(dishName);
        dishes.computeIfAbsent(dishType, t -> new ArrayList<>()).add(dishName);
    }

    public void generateDishCombo(Random random, Scanner scanner) {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem;

        ArrayList<String> dishesTypes = new ArrayList<>();

        while (!(nextItem = scanner.nextLine()).isEmpty()) {
            String dishType = formatDishName(nextItem);
            if (dishes.containsKey(dishType)) {
                dishesTypes.add(dishType);
            }
        }

        for (int i = 1; i <= numberOfCombos; i++) {
            System.out.printf("Комбо %d\n", i);
            ArrayList<String> tempDishes = new ArrayList<>();
            for (String dishName : dishesTypes) {
                ArrayList<String> dishesInType = dishes.get(dishName);
                tempDishes.add(dishesInType.get(random.nextInt(dishesInType.size())));
            }
            System.out.println(tempDishes);
        }

        System.out.println();
    }
}
