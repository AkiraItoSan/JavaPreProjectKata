package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
//        Создание таблицы User(ов)

        userService.createUsersTable();
//        Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль
//( User с именем – name добавлен в базу данных )
        userService.saveUser("1", "1-ый", (byte) 1);
        userService.saveUser("2", "2-ой", (byte) 2);
        userService.saveUser("3", "3-ий", (byte) 3);
        userService.saveUser("4", "4-ый", (byte) 4);

//        Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        userService.getAllUsers().forEach(System.out::println);

//        Очистка таблицы User(ов)
        userService.cleanUsersTable();

//        Удаление таблицы
        userService.dropUsersTable();
        Util.closeConnectToDataBase();
    }

}