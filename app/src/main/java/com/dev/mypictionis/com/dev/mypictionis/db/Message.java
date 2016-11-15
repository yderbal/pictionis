package com.dev.mypictionis.com.dev.mypictionis.db;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Madhow on 14/11/2016.
 */

public class Message {

    String message;
    String author;

    public Message()
    {
        message = "";
        author="Mike";
    }

    public Message(String message,String author)
    {
        this.author = author;
        this.message = message;
    }

    public static JSONObject createMessageJSONObject(String text,String author) throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put("text",text);
        json.put("author",author);
        return json;
    }

    public String getMessage()
    {
        return message;
    }

    public String getAuthor()
    {
        return author;
    }
}
