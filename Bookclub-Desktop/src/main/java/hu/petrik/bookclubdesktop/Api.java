package hu.petrik.bookclubdesktop;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Api {
    private static String apiUrl = "http://localhost:8000/api/";

    public static List<Member> getMembers() throws IOException, ParseException {
        URL url = new URL(apiUrl + "members");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        List<Member> memberList = new ArrayList<>();

        InputStreamReader is = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(is);

        StringBuilder s = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            s.append(line).append("\n");
            line = br.readLine();
        }

        JSONParser parser = new JSONParser();
        JSONArray jsonArr = (JSONArray) parser.parse(s.toString());

        for (Object obj: jsonArr) {
            JSONObject o = (JSONObject) obj;

            int id = Integer.parseInt(o.get("id").toString());
            String name = o.get("name").toString();
            String gender;
            if (o.get("gender") == null) gender = "O";
            else gender = o.get("gender").toString();
            LocalDate birthDate = LocalDate.parse(o.get("birth_date").toString());
            boolean banned = Boolean.parseBoolean(o.get("banned").toString());

            memberList.add(new Member(id, name, gender, birthDate, banned));
        }

        br.close();
        is.close();

        return memberList;
    }
}
