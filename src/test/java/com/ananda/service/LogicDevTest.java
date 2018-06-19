package com.ananda.service;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogicDevTest {


    //mvn -Dspring.profiles.active=dev -Dtest=LogicDevTest#testConvertMapToObject test
//    @Test
    public void testConvertMapToObject() throws Exception {
        try {
            Map<String, Object> maps = new HashMap<>();
            maps.put("userName", "karoons");
            maps.put("password", "test");

            Map<String, Object> mapItem = new HashMap<>();
            mapItem.put("email", "Email_1");
            mapItem.put("tel", "Tel_1");
            List<Map<String, Object>> mapItems = new ArrayList<>();
            mapItems.add(mapItem);

            mapItem = new HashMap<>();
            mapItem.put("email", "Email_2");
            mapItem.put("tel", "Tel_2");

            mapItems.add(mapItem);
            List<Map<String, Object>> mapItems2 = new ArrayList<>();
            Map<String, Object> mapItem2 = new HashMap<>();
            mapItem2.put("email", "Email_1.1");
            mapItem2.put("tel", "Tel_1.2");
            mapItem = new HashMap<>();
            mapItems2.add(mapItem2);
            mapItem.put("sub1", mapItems2);

            mapItems.add(mapItem);
            maps.put("attr", mapItems);

            printMapDynamic(maps);
//            System.out.println("-- " + maps.get("attr").getClass().getTypeName());

//            for (Map<String, Object> _item : (ArrayList<Map<String, Object>>) maps.get("attr")) {
//                printMap(_item);
//            }

        } catch (Exception e) {
            throw e;
        }
    }

    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public static void printMapDynamic(Map mp) {
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
//            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
