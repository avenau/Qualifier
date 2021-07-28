package com.fdm.qualifier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SkillLevel {
    @Id
    @GeneratedValue
    private int skillLevelId;
    @ManyToOne
    @JoinColumn(name = "FK_SKILL")
    private Skill skill;
    @ManyToOne
    @JoinColumn(name = "FK_QUIZ")
    private Quiz quiz;
    private String level;
    
    public SkillLevel() {
        super();
    }

    public SkillLevel(Skill skill, Quiz quiz, String level) {
        this.skill = skill;
        this.quiz = quiz;
        this.level = level;
    }

    public int getSkillLevelId() {
        return skillLevelId;
    }

    public void setSkillLevelId(int skillLevelId) {
        this.skillLevelId = skillLevelId;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "SkillLevel [" + (level != null ? "level=" + level + ", " : "")
                + (quiz != null ? "quiz=" + quiz + ", " : "") + (skill != null ? "skill=" + skill + ", " : "")
                + "skillLevelId=" + skillLevelId + "]";
    }

    
}
