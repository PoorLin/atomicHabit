package org.atomicHabit.constance;

public class habitConst {

    //習慣log的初始狀態
    public static final Integer HABITRECORD_INIT = 0;

    public static final Integer HABITRECORD_SUCCESS = 1;

    public static final Integer HABITRECORD_FAIL = 2;

    // 成功

    public static final Integer SUCCESS = 200;


    // common fault
    public static final Integer DATA_ERROR = 8001;

    // User Constance


    public static final Integer EMAIL_ALREADY_EXIST = 2001;
    public static final Integer USER_NOT_EXIST = 2002;

    public static final Integer EMAIL_NOT_EXIST = 2003;


    //Habit Constance

    public static final Integer HABIT_TYPE_NUMBER = 0;

    public static final Integer HABIT_TYPE_NOT_NUMBER = 1;
    public static final Integer HABIT_ALREADY_EXIST = 3001;
    public static final Integer HABIT_NOT_EXIST = 3002;

    public static final Integer HABIT_VALUE_INVALID = 3002;


}
