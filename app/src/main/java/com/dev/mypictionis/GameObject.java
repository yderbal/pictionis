package com.dev.mypictionis;

import java.util.ArrayList;

/**
 * Created by Madhow on 15/11/2016.
 */

public class GameObject {
    ArrayList<DrawingObject> dwobjList;
    ChatObject chatobj;
    int gameId;

    public GameObject(int gameId)
    {
        dwobjList = new ArrayList<DrawingObject>();
        chatobj = new ChatObject(gameId);
    }
}
