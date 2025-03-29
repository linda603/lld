package meetingscheduler;

import java.util.*;

public class Room {
    private String roomId;
    private String name;
    private int capacity;
    private RoomType type;

    public Room(String roomId, String name, int capacity, RoomType type) {
        this.roomId = roomId;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    public String getId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public RoomType getRoomType() {
        return type;
    }
}