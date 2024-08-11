package tech.gdev.javabasicexplore.javabasic;

import java.util.Locale;

public class JavaLocaleExplore {
    public static void main(String[] args) {
        JavaLocaleExplore explore = new JavaLocaleExplore();
        explore.testLocale();
    }

    public void testLocale() {
        String usTag = Locale.US.toLanguageTag();
        System.out.println(usTag);
    }
}
