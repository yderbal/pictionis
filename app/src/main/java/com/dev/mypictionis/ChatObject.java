package com.dev.mypictionis;

import com.dev.mypictionis.com.dev.mypictionis.db.Message;

import java.util.ArrayList;

/**
 * Created by Madhow on 15/11/2016.
 */

public class ChatObject {

    int id;
    ArrayList<Message> messages;
    int messageId;

    public ChatObject(int gameId) {
        this.id = gameId;
        messages = new ArrayList<Message>();
        messageId = 0;
    }

    void addMessage(Message message)
    {
        messages.add(messageId,message);
        messageId++;
    }
}
