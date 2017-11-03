package util;

import org.stringtemplate.v4.*;

import java.util.ArrayList;
import java.util.List;

public class EmailTemplates {

    public static ST setTemplate(String str) {
        return new ST(str);
    }

    private static String birthday = "Dear <name>! Wishing you a happy birthday!" +
            "Another year, another set of blessings. May you always remain happy and cheerful " +
            "all the days of your life. Happy Birthday!";
    private static String new_year = "Dear <name>, on this joyous occasion of New Year, cherish our memories and feel " +
            "the warmth of my wishes. Happy New Year!";

    public static String getBirthday() {
        return birthday;
    }

    public static String getNewYear() {
        return new_year;
    }

    /*public static List<String> getTemplates () {
        List<String> templates = new ArrayList<>();

            *//*templates.getAndSet(EmailTemplates.getBirthday().add("name", name).render())*//**//*.add(EmailTemplates.getBirthday().add("name", name).render())*//**//*;
            templates.get().add(EmailTemplates.getNewYear().add("name", name).render());*//*
            templates.add(EmailTemplates.getBirthday().render());
            templates.add(EmailTemplates.getNewYear().render());

        return templates;
    }*/
}
