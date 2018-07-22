package com.game;

import java.util.*;

public class Constants {

    public static final String EMPTY_STRING = "";
    public static final String DOT_SYMBOL = ".";
    public static final String COMMA_SYMBOL = ",";
    public static final String COMMAS_SYMBOL = "\'";
    public static final String SPACE_SYMBOL = " ";
    public static final String START_TYPE_SYMBOL = "> ";

    public static final String START_CMD = "старт";
    public static final String EXIT_CMD = "выйти";
    public static final String GET_CMD = "взять";
    public static final String GO_CMD = "идти";
    public static final String USE_CMD = "использовать";
    public static final String INVENTORY_CMD = "инвентарь";
    public static final String LOOK_CMD = "осмотреться";
    public static final String DESCR_CMD = "описание";
    public static final String UNKNOWN_CMD = "Неизвестная команда.";

    public static final String LIVING_ROOM_LOC = "гостинная";
    public static final String ATTIC_LOC = "чердак";
    public static final String GARDEN_LOC = "сад";

    public static final String WEST_DIRECTION = "запад";
    public static final String UPWARD_DIRECTION = "наверх";
    public static final String DOWNWARD_DIRECTION = "вниз";
    public static final String EASTERN_DIRECTION = "восток";

    public static final String BUCKET_ITEM_NAME = "ведро";
    public static final String CHAIN_ITEM_NAME = "цепь";
    public static final String WHISKY_ITEM_NAME = "виски";
    public static final String WELL_ITEM_NAME = "колодец";
    public static final String WIZARD_ITEM_NAME = "волшебник";
    public static final String BURNER_ITEM_NAME = "горелка";
    public static final String FROG_ITEM_NAME = "лягушка";

    public static final String BUCKET_ITEM_DESCRIPTION = "Пустое ведро.";
    public static final String BUCKET_ITEM_WITH_CHAIN_DESCRIPTION = "Пустое ведро c привареной цепью.";
    public static final String BUCKET_ITEM_WITH_WATER_DESCRIPTION = "Полное ведро воды.";
    public static final String WHISKY_ITEM_DESCRIPTION = "Пустая бутылка виски.";
    public static final String WIZARD_ITEM_DESCRIPTION = "Спящий Волшебник.";
    public static final String BURNER_ITEM_DESCRIPTION = "Гигантская горелка.";
    public static final String FROG_ITEM_DESCRIPTION = "Ква.";
    public static final String WELL_ITEM_DESCRIPTION = "Колодец с водой.";
    public static final String CHAIN_ITEM_DESCRIPTION = "Длинная цепь.";

    public static final String FIRST_ACTION = "Введите 'старт', чтобы начать, либо 'выйти', чтобы завершить.";
    public static final String INVALID_ACTION = "Недопустимое действие.";
    public static final String INVALID_MOVE_ACTION = "Вы не можете туда пойти.";
    public static final String YOU_HAVE_ACTION = "У Вас есть ";
    public static final String CAN_NOT_TAKE_ACTION = "Нельзя взять ";
    public static final String NO_SUBJECT_ACTION = "Нет предмета с названием ";
    public static final String CAN_NOT_USE_BUCKET_ON_BUCKET_ACTION = "Нельзя использовать ведро на ведро.";

    public static final String STUFF = "Доступные вещи: ";
    public static final String INVENTORY_STUFF = "Доступные вещи в инвенторе: ";
    public static final String EMPTY_INVENTORY_STUFF = "У Вас нет вещей в инвенторе.";

    public static final String START_GAME_DESCRIPTION = "Игра началась.\nОсмотритесь. Необходимо понять где вы находитесь.";
    public static final String END_GAME_DESCRIPTION = "Игра окончена. Нажмите Ввод, чтобы выйти.";
    public static final String GAME_OVER_DESCRIPTION = "Вы вышли из игры.";
    public static final String EXIT_GAME_DESCRIPTION = "Нажмите Ввод, чтобы выйти.";

    public static final String DEFAULT_LIVING_ROOM_DESCRIPTION =
            "Вы находитесь в гостиной в доме волшебника. А вот и он сам - громко храпит на кровати.\n"
                    + "На западе от вас есть дверь, рядом - лестница наверх.\n";
    public static final String DEFAULT_ATTIC_DESCRIPTION =
            "Вы на чердаке старого дома. В углу видна гигантская горелка. Вниз ведет лестница.";
    public static final String DEFAULT_GARDEN_DESCRIPTION =
            "Вы в прекрасном саду. Прямо по курсу находится колодец. На востоке - дверь в дом.\n";

    public static final List<String> AVAILABLE_FIRST_ITEM = Arrays.asList(
            BUCKET_ITEM_NAME,
            CHAIN_ITEM_NAME
    );

    public static final List<String> AVAILABLE_SECOND_ITEM = Arrays.asList(
            BUCKET_ITEM_NAME,
            WELL_ITEM_NAME,
            WIZARD_ITEM_NAME
    );

    private static final String BUCKET_ITEM_WITH_CHAIN_ACTION_DESCRIPTION = "Теперь цепь надежно приварена к ведру.";
    private static final String GET_BUCKET_WITH_WATER_ACTION_DESCRIPTION =
            "Держа ведро за цепь, вы опускаете его в колодец и поднимаете полным до краев.";
    private static final String CAN_NOT_GET_WATER_ACTION_DESCRIPTION = "Вода слишком далеко. Не достать.";
    private static final String BUCKET_WITHOUT_WATER_ACTION_DESCRIPTION = "В ведре пусто.";
    private static final String AWAKE_WIZARD_ACTION_DESCRIPTION = "Волшебник вскакивает и начинает отряхиваться. Приведя себя в порядок,\n"
            + "он благодарит вас за помощь и протягивает вам магический кристалл. Вы выиграли!";

    public static final Integer FIRST_RESULT_OF_USE_CMD = 1;
    public static final Integer SECOND_RESULT_OF_USE_CMD = 2;
    public static final Integer THIRD_RESULT_OF_USE_CMD = 3;
    public static final Integer FOURTH_RESULT_OF_USE_CMD = 4;
    public static final Integer FIFTH_RESULT_OF_USE_CMD = 5;
    public static final Integer SIXTH_RESULT_OF_USE_CMD = 6;
    public static final Integer SEVENTH_RESULT_OF_USE_CMD = 7;

    public static final Map<Integer, String> RESULT_OF_USE_CMD_MAPPING = new HashMap<Integer, String>() {
        {
            put(FIRST_RESULT_OF_USE_CMD, GET_BUCKET_WITH_WATER_ACTION_DESCRIPTION);
            put(SECOND_RESULT_OF_USE_CMD, CAN_NOT_GET_WATER_ACTION_DESCRIPTION);
            put(THIRD_RESULT_OF_USE_CMD, AWAKE_WIZARD_ACTION_DESCRIPTION);
            put(FOURTH_RESULT_OF_USE_CMD, BUCKET_WITHOUT_WATER_ACTION_DESCRIPTION);
            put(FIFTH_RESULT_OF_USE_CMD, CAN_NOT_USE_BUCKET_ON_BUCKET_ACTION);
            put(SIXTH_RESULT_OF_USE_CMD, BUCKET_ITEM_WITH_CHAIN_ACTION_DESCRIPTION);
            put(SEVENTH_RESULT_OF_USE_CMD, INVALID_ACTION);
        }
    };

}
