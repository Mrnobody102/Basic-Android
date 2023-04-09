package com.student.manager.model;

public class LecturerClassSkill {
    private int lecturerId;
    private int classId;
    private int skillId;

    public LecturerClassSkill(int lecturerId, int classId, int skillId) {
        this.lecturerId = lecturerId;
        this.classId = classId;
        this.skillId = skillId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
}
