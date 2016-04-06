package com;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2016/2/29.
 *
 */

public class JsonToList {

    public static void main(String args[]) {
        String json = "{\"groups\":[{\"id\":10,\"name\":\"hello\"},{\"id\":11,\"name\":\"world\"}]}";

        JSONObject jsonObject = new JSONObject(json);

        JSONArray jsonArray = new JSONArray(jsonObject.get("groups").toString());

        List<Person> list = new ArrayList<>();

        for(int i = 0;i<jsonArray.length();i++){
            Person person = new Person();
            person.setId(jsonArray.getJSONObject(i).getInt("id"));
            person.setName(jsonArray.getJSONObject(i).getString("name"));
            list.add(person);
        }

        for (int i = 0;i<list.size();i++){
            Person person = list.get(i);
            System.out.println(person.getId());
            System.out.println(person.getName());
        }
    }
}


