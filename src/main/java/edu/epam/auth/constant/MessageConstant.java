package edu.epam.auth.constant;

public class MessageConstant {

    public static final String INVALID_LOGIN = "Невалидный логин.";
    public static final String INVALID_PASSWORD = "Невалидный пароль.";
    public static final String INVALID_NEW_PASSWORD = "Невалидный новый пароль.";
    public static final String INVALID_EMAIL = "Невалидный адрес электронной почты.";
    public static final String REPEATED_LOGIN = "Пользователь с таким Логином уже существует. Выберите другой.";
    public static final String INVALID_ACTIVATION_LINK = "Некорректная ссылка активации пользователя.";
    public static final String INVALID_ACTIVATION_LINK_LOGIN = "Не найден пользователь для данной ссылки. Пожалуйста зарегистрируйтесь.";
    public static final String NOT_FIND_USER_BY_LOGIN = "Пользователя с таким логином не существует.";

    public final static String WRONG_LOGIN_ALREADY_EXIST = "Пользователь с таким логином уже существует. Используйте другой.";
    public final static String WRONG_REPEAT_PASSWORD = "Введенные пароли не совпадают!";
    public static final String WRONG_ACTIVATION_KEY = "Такой ключ активации пользователя не найден. Возможно пользоваетль уже активен.";
    public static final String USER_STATUS_INACTIVE = "Невозможно выполнить вход. Ваш аккаунт не активный.";
    public static final String USER_STATUS_DELETE = "Невозможно выполнить вход. Ваш аккаунт был удален.";
    public static final String USER_STATUS_BLOCK = "Невозможно выполнить вход. Ваш аккаунт заблакирован.";
    public static final String USERS_NOT_FOUND = "Запрашиваемый список пуст";
    public static final String WRONG_PASSWORD = "Вы ввели неправильный пароль.";


    public static final String SUCCESSFULLY_ACTIVATION = "Пользователь активирован. Выполните вход.";
    public static final String SUCCESSFULLY_CHANGE_PASSWORD = "Пароль изменен.";
    public static final String SUCCESSFULLY_CHANGE_RECORD = "Запись была изменена.";
    public static final String DELETED_NUMBER = "Количество удаленных записей: ";
    public static final String WRONG_DATE = "Невозможно добавить объявление. Вы ввели неверные данные.";

    private MessageConstant() {
    }
}
