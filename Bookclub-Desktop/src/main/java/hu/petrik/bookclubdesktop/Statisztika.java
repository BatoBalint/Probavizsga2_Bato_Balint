package hu.petrik.bookclubdesktop;

import javafx.util.converter.LocalDateStringConverter;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Statisztika {
    private List<Member> memberList;

    public Statisztika() {
        try {
            memberList = Api.getMembers();
            bannedMembers();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void bannedMembers() {
        long bannedMembersCount = memberList.stream().filter(Member::isBanned).count();
        System.out.println("Kitiltott tagok száma: " + bannedMembersCount);
    }

    private void youngerThan18() {
    }
}
