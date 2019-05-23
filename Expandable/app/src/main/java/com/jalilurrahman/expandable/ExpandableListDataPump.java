package com.jalilurrahman.expandable;

/**
 * Created by Murtuza Rahman on 2019-05-20.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");
        expandableListDetail.put("CRICKET TEAMS", cricket);
        return expandableListDetail;
    }
}
