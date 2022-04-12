package hu.petrik.bookclubdesktop;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Member {
    private int id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private boolean banned;

    //region Getter / Setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    //endregion

    public Member(int id, String name, String gender, LocalDate birthDate, boolean banned) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.banned = banned;
    }
}
