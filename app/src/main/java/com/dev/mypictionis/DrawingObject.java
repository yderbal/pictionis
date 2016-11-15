package com.dev.mypictionis;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Madhow on 15/11/2016.
 */

public class DrawingObject {

    int id;
    ArrayList<Coordinates> points;
    Coordinates origin;
    int pointId;

    public DrawingObject()
    {
        id = 0;
        points = new ArrayList<Coordinates>();
        origin = new Coordinates();
        pointId = 0;
    }

    public DrawingObject(int id)
    {
        this.id = id;
        points = new ArrayList<Coordinates>();
        origin = new Coordinates();
        pointId = 0;
    }

    public void addPoint(Coordinates c)
    {
        points.add(c);
        pointId++;
    }

    public void addOrigin(Coordinates c)
    {
        this.origin = c;
    }
}
