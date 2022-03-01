package com.example.terminkalender.adapter;

public class MonthNamesList {
    String name;
    String[] monthNames = {"Пьянварь", "Февральчик", "Мартишка",
            "Апрелька", "Оймай", "Июнька", "Жарюль", "Отпуск",
            "Сентябрик", "Октябрь", "Гноябрь", "Декабрище"};
    public String chooseName (int m) {
        if(m>=0 && m <12) {
            name = monthNames[m];
        }  return name;
    };
}
