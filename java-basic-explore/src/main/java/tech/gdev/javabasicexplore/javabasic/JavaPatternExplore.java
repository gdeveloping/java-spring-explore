package tech.gdev.javabasicexplore.javabasic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaPatternExplore {
    public static void main(String[] args) {
        JavaPatternExplore explore = new JavaPatternExplore();
//        explore.testMatch();
//        explore.testFind();
//        explore.testMultiLine();
        explore.testPairMatch();
    }

    public void testMatch() {
        String input = "first line\r\nsecond line\r\nlast line\r\n";
        String patternString = "^(.*)$";

        // not matched
//        Pattern pattern = Pattern.compile(patternString);

        // not matched
//        Pattern pattern = Pattern.compile(patternString, Pattern.MULTILINE);

        // group content: first line\r\nsecond line\r\nlast line\r\n
        Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL);

        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            System.out.println("group content: " + matcher.group());
        } else {
            System.out.println("not matched");
        }
    }

    public void testFind() {
        String input = "first line\r\nsecond line\r\nlast line\r\n";
        String patternString = "^(.*)$";

        // find nothing
//        Pattern pattern = Pattern.compile(patternString);

        // group content: first line\r\nsecond line\r\nlast line\r\n
//        Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL);

        // group content: first line
        // group content: second line
        // group content: last line
        Pattern pattern = Pattern.compile(patternString, Pattern.MULTILINE);

        Matcher matcher = pattern.matcher(input);

        int count = 0;
        while (matcher.find()) {
            count += 1;
            System.out.println("group content: " + matcher.group());
        }
        if (count == 0) {
            System.out.println("find nothing");
        }
    }

    public void testMultiLine() {
        String text = "Hello\nWorld";

        findTool(text, "^World", Pattern.MULTILINE);  // find content: World

        findTool(text, "\\AWorld", Pattern.MULTILINE);  // find nothing

        findTool(text, "Hello$", Pattern.MULTILINE);  // find content: Hello

        findTool(text, "Hello\\Z", Pattern.MULTILINE);  // find nothing

        findTool(text, "Hello\\z", Pattern.MULTILINE);  // find nothing
    }

    public void testPairMatch() {
        matchTool("\"Fred\"", "(['\"]).*\\1", 0);  // match content: \"Fred\"
        matchTool("'Fred'", "(['\"]).*\\1", 0);  // match content: 'Fred'
        matchTool("\"Fred'", "(['\"]).*\\1", 0);  // match nothing
        matchTool("'Fred\"", "(['\"]).*\\1", 0);  // match nothing

        matchTool("'Fred'", "(?<quote>['\"]).*\\k<quote>", 0);  // match content: 'Fred'
        matchTool("'Fred\"", "(?<quote>['\"]).*\\k<quote>", 0);  // match nothing
    }

    public void matchTool(String content, String patternRegex, int patternFlag) {
        Pattern pattern = Pattern.compile(patternRegex, patternFlag);
        Matcher matcher = pattern.matcher(content);
        if (matcher.matches()) {
            System.out.println("match content: " + matcher.group());
        } else {
            System.out.println("match nothing");
        }
    }

    public void findTool(String content, String patternRegex, int patternFlag) {
        Pattern pattern = Pattern.compile(patternRegex, patternFlag);
        Matcher matcher = pattern.matcher(content);
        int count = 0;
        while (matcher.find()) {
            count += 1;
            System.out.println("find content: " + matcher.group());
        }
        if (count == 0) {
            System.out.println("find nothing");
        }
    }
}
