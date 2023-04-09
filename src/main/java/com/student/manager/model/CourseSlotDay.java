package com.student.manager.model;

public class CourseSlotDay {
    private int courseId;
    private int slotDayId;

    public CourseSlotDay(int courseId, int slotDayId) {
        this.courseId = courseId;
        this.slotDayId = slotDayId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSlotDayId() {
        return slotDayId;
    }

    public void setSlotDayId(int slotDayId) {
        this.slotDayId = slotDayId;
    }
}
