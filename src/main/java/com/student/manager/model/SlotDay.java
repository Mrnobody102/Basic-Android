package com.student.manager.model;

public class SlotDay {
    private int slotDayId;
    private String slotDay; // 246am, 246ev, 357am, 357ev, 7pm+8am+8ev

    public SlotDay(int slotDayId, String slotDay) {
        this.slotDayId = slotDayId;
        this.slotDay = slotDay;
    }

    public int getSlotDayId() {
        return slotDayId;
    }

    public void setSlotDayId(int slotDayId) {
        this.slotDayId = slotDayId;
    }

    public String getSlotDay() {
        return slotDay;
    }

    public void setSlotDay(String slotDay) {
        this.slotDay = slotDay;
    }
}
