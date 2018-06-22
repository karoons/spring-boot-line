package com.ananda.helper;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Service
public class Track {
    public void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public void printMapDynamic(Map mp) {
        String typeName = "java.util.ArrayList";
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getValue().getClass().getTypeName().equals(typeName)) {
                for (Map<String, Object> _item : (ArrayList<Map<String, Object>>) pair.getValue()) {
                    printMapDynamic(_item);
                }
            }
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }
}
