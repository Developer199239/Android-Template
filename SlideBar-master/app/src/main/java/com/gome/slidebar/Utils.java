package com.gome.slidebar;

import android.graphics.Color;
import android.util.Log;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by admin on 2018/2/28.
 */

public class Utils {

    private final static Object object = new Object();

//    private static final String[] sContacts = {"阿大", "777", "菜3", "飞7", "菜4", "菜5", "菜6", "飞1", "阿二", "阿三", "巴1", "巴2", "巴3", "巴4", "巴5",
//            "巴6", "巴7", "巴9", "菜1", "小5", "小6", "#1", "菜2", "指8", "菜7", "小1", "小2", "小3", "小4", "#3","aaa", "#5"};

    private static final String[] sContacts = {"ali","abbus","abu","abul","akhan","Jackeline","Hoa","Marquerite","Kirstin",
            "Sandie","Yasmin","Lashawnda","Helaine"
    };

//    public static String getPingYin(String inputString) {
//        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
//        char[] ch = inputString.trim().toCharArray();
//        StringBuffer buffer = new StringBuffer("");
//        try {
//            for (int i = 0; i < ch.length; i++) {
//                // unicode，bytes应该也可以.
//                if (Character.toString(ch[i]).matches("[\u4e00-\u9fa5]+")) {
//                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(ch[i], format);
//                    buffer.append(temp[0]);
//                } else {
//                    buffer.append(Character.toString(ch[i]));
//                }
//            }
//        } catch (BadHanyuPinyinOutputFormatCombination e) {
//
//        }
//        return buffer.toString();
//    }


    public synchronized static TreeMap<String,List<String>> getOrderContactMap() {

        TreeMap<String,List<String>> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char key1 = o1.charAt(0);
                char key2 = o2.charAt(0);
                if (Character.isLetter(key1) && Character.isLetter(key2)) {
                    return key1 - key2;
                } else if (Character.isLetter(key1)) {
                    return -1;
                } else if (Character.isLetter(key2)) {
                    return 1;
                }
                return 0;
            }
        });

        for (int i = 0; i < sContacts.length; i++) {
//            StringBuffer stringBuffer = new StringBuffer(getPingYin(sContacts[i]));
            StringBuffer stringBuffer = new StringBuffer(sContacts[i]);
            if (stringBuffer.length() > 0) {
                String firstLetter = stringBuffer.substring(0, 1).toUpperCase();
                if (!Sidebar.SideBarUtil.isContains(firstLetter)) {
                    firstLetter = "#";
                }
                List<String> contacts = treeMap.get(firstLetter);
                if (contacts == null) {
                    contacts = new ArrayList<>();
                }
                contacts.add(sContacts[i]);
                treeMap.put(firstLetter, contacts);
            }
        }

//        Iterator iterator = treeMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            HashMap.Entry entry = (HashMap.Entry) iterator.next();
//            String firstLetter = (String) entry.getKey();
//            List<String> list = (List<String>) entry.getValue();
//            Log.e("wjq", "firstLetter = " + firstLetter);
//            for (int i = 0; i < list.size(); i++) {
//                Log.e("wjq", "contact = " + list.get(i));
//            }
//        }
        return treeMap;
    }

    public static int calculateColor(int startColor, int endColor, float percentage){
        String strStartColor = "#" + Integer.toHexString(startColor);
        String strEndColor = "#" + Integer.toHexString(endColor);
        return Color.parseColor(calculateColor(strStartColor, strEndColor, percentage));
    }

    //格式为#FFFFFFFF
    public static String calculateColor(String startColor, String endColor, float percentage){

        int startAlpha = Integer.parseInt(startColor.substring(1, 3), 16);
        int startRed = Integer.parseInt(startColor.substring(3, 5), 16);
        int startGreen = Integer.parseInt(startColor.substring(5, 7), 16);
        int startBlue = Integer.parseInt(startColor.substring(7), 16);

        int endAlpha = Integer.parseInt(endColor.substring(1, 3), 16);
        int endRed = Integer.parseInt(endColor.substring(3, 5), 16);
        int endGreen = Integer.parseInt(endColor.substring(5, 7), 16);
        int endBlue = Integer.parseInt(endColor.substring(7), 16);

        int currentAlpha = (int) ((endAlpha - startAlpha) * percentage + startAlpha);
        int currentRed = (int) ((endRed - startRed) * percentage + startRed);
        int currentGreen = (int) ((endGreen - startGreen) * percentage + startGreen);
        int currentBlue = (int) ((endBlue - startBlue) * percentage + startBlue);

        return "#" + getHexString(currentAlpha) + getHexString(currentRed)
                + getHexString(currentGreen) + getHexString(currentBlue);

    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    public static String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }

}
