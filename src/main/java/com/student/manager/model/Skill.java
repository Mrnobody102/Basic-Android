package com.student.manager.model;

public class Skill {
    private int skillId;
    private int skillName;

    public Skill(int skillId, int skillName) {
        this.skillId = skillId;
        this.skillName = skillName;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getSkillName() {
        return skillName;
    }

    public void setSkillName(int skillName) {
        this.skillName = skillName;
    }
}
