package com.hexin.plat.android.assist;

import com.hexin.plat.android.assist.floating.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

public class Configurations {
    public final static String KEY_CLICK = "keyClick";
    public final static String KEY_INPUT = "keyInput";
    public final static String KEY_SCROLL = "keyScroll";
    public final static String KEY_ASSERT = "keyAssert";

    public final static String KEY_PROCESS = "keyProcess";
    public final static String KEY_CASES = "keyCases";
    public final static String KEY_DEVICE = "keyDevice";
    public final static String KEY_APP_SCROLL = "keyAppScroll";

    public final static String ACTION_CLICK = "click"; // 点击, 发现点击, 输入, 上滑, 下滑, 左滑, 右滑, 断言
    public final static String ACTION_CLICK_IF_EXISTS = "checkIfClick";
    public final static String ACTION_INPUT = "input";
    public final static String ACTION_SCROLL_UP = "scrollUp";
    public final static String ACTION_SCROLL_DOWN = "scrollDown";
    public final static String ACTION_SCROLL_LEFT = "scrollLeft";
    public final static String ACTION_SCROLL_RIGHT = "scrollRight";
    public final static String ACTION_ASSERT = "assert";

    public final static String OPERATION_START = "start"; // 开始, 暂停
    public final static String OPERATION_FINISH = "finish"; // 结束
    public final static String OPERATION_CASES = "cases"; // 所有用例
    public final static String ACTION_BACK = "back"; // 设备返回
    public final static String ACTION_SLEEP = "sleep"; // 设备Sleep
    public final static String ACTION_APP_SCROLL_UP = "appScrollUp";
    public final static String ACTION_APP_SCROLL_DOWN = "appScrollDown";
    public final static String ACTION_APP_SCROLL_LEFT = "appScrollLeft";
    public final static String ACTION_APP_SCROLL_RIGHT = "appScrollRight";

    public static ArrayList<MenuItem> ActionMenu = new ArrayList<>();
    public static HashMap<String, ArrayList<MenuItem>> ActionSubMenu = new HashMap<>();

    public static ArrayList<MenuItem> OperationMenu = new ArrayList<>();
    public static HashMap<String, ArrayList<MenuItem>> OperationSubMenu = new HashMap<>();

    static {
        ArrayList<MenuItem> clickActions = new ArrayList<>();
        clickActions.add(new MenuItem(ACTION_CLICK, "点击", KEY_CLICK, 1));
        clickActions.add(new MenuItem(ACTION_CLICK_IF_EXISTS, "发现点击", KEY_CLICK, 1));
        ActionSubMenu.put(KEY_CLICK, clickActions);
        ActionMenu.add(new MenuItem(KEY_CLICK, "点击", KEY_CLICK, 1));

        ArrayList<MenuItem> inputActions = new ArrayList<>();
        inputActions.add(new MenuItem(ACTION_INPUT, "输入", KEY_INPUT, 1));
        ActionSubMenu.put(KEY_INPUT, inputActions);
        ActionMenu.add(new MenuItem(KEY_INPUT, "输入", KEY_INPUT, 1));

        ArrayList<MenuItem> scrollActions = new ArrayList<>();
        scrollActions.add(new MenuItem(ACTION_SCROLL_UP, "上滑", KEY_SCROLL, 1));
        scrollActions.add(new MenuItem(ACTION_SCROLL_DOWN, "下滑", KEY_SCROLL, 1));
        scrollActions.add(new MenuItem(ACTION_SCROLL_LEFT, "左滑", KEY_SCROLL, 1));
        scrollActions.add(new MenuItem(ACTION_SCROLL_RIGHT, "右滑", KEY_SCROLL, 1));
        ActionSubMenu.put(KEY_SCROLL, scrollActions);
        ActionMenu.add(new MenuItem(KEY_SCROLL, "滑动", KEY_SCROLL, 1));

        ArrayList<MenuItem> assertActions = new ArrayList<>();
        assertActions.add(new MenuItem(ACTION_ASSERT, "断言", KEY_ASSERT, 1));
        ActionSubMenu.put(KEY_ASSERT, assertActions);
        ActionMenu.add(new MenuItem(KEY_ASSERT, "断言", KEY_ASSERT, 1));

        //
        ArrayList<MenuItem> processOperations = new ArrayList<>();
        processOperations.add(new MenuItem(OPERATION_START, "开始/暂停", KEY_PROCESS, 1));
        processOperations.add(new MenuItem(OPERATION_FINISH, "结束", KEY_PROCESS, 1));
        OperationSubMenu.put(KEY_PROCESS, processOperations);
        OperationMenu.add(new MenuItem(KEY_PROCESS, "流程", KEY_PROCESS, 1));

        //
        ArrayList<MenuItem> caseOperations= new ArrayList<>();
        caseOperations.add(new MenuItem(OPERATION_CASES, "所有用例", KEY_CASES,1));
        OperationSubMenu.put(KEY_CASES, caseOperations);
        OperationMenu.add(new MenuItem(KEY_CASES, "用例", KEY_CASES, 1));

        ArrayList<MenuItem> deviceOperations = new ArrayList<>();
        deviceOperations.add(new MenuItem(ACTION_BACK, "返回", KEY_DEVICE, 1));
        deviceOperations.add(new MenuItem(ACTION_SLEEP, "Sleep", KEY_DEVICE, 1));
        OperationSubMenu.put(KEY_DEVICE, deviceOperations);
        OperationMenu.add(new MenuItem(KEY_DEVICE, "设备", KEY_DEVICE, 1));

        ArrayList<MenuItem> appScrollOperations = new ArrayList<>();
        appScrollOperations.add(new MenuItem(ACTION_APP_SCROLL_DOWN, "上滑", KEY_APP_SCROLL, 1));
        appScrollOperations.add(new MenuItem(ACTION_APP_SCROLL_UP, "下滑", KEY_APP_SCROLL, 1));
        appScrollOperations.add(new MenuItem(ACTION_APP_SCROLL_LEFT, "左滑", KEY_APP_SCROLL, 1));
        appScrollOperations.add(new MenuItem(ACTION_APP_SCROLL_RIGHT, "右滑", KEY_APP_SCROLL, 1));
        OperationSubMenu.put(KEY_APP_SCROLL, appScrollOperations);
        OperationMenu.add(new MenuItem(KEY_APP_SCROLL, "应用滑动", KEY_APP_SCROLL, 1));
    }
}
