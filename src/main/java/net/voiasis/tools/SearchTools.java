package net.voiasis.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchTools {
    public static String lineSearch(String search, String file) throws IOException { //returns entire line the search result was found on
        //BotLog.log(search, file, 3);
        FileReader fileReader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(fileReader);
        Scanner in = null;
        in = new Scanner(buffReader);
        Boolean found=false;
        while(in.hasNext()) {
        String line = in.nextLine();
            if(booleanRegexSearch(search, line)) {
                found=true;
                //BotLog.log("lineSearch: Search result found: \"" + search + "\"", "SearchTools", 1);
                in.close();
                buffReader.close();
                return line;
            }           
        }
        if(found==false) {
            String nope = "?";
            //BotLog.log("lineSearch failed: Search result not found: \"" + search + "\"", "SearchTools", 3);
            in.close();
            buffReader.close();
            return nope;
        }
        in.close();
        buffReader.close();
        return search;
    }
    public static String lineSearch2(String search, String file) throws IOException { //returns entire line the search result was found on
        //BotLog.log(search, file, 3);
        FileReader fileReader = new FileReader(file);
        BufferedReader buffReader = new BufferedReader(fileReader);
        Scanner in = null;
        in = new Scanner(buffReader);
        Boolean found=false;
        while(in.hasNext()) {
        String line = in.nextLine();
            if(line.contains(search)) {
                found=true;
                //BotLog.log("lineSearch2: Search result found: \"" + search + "\"", "SearchTools", 1);
                in.close();
                buffReader.close();
                return line;
            }           
        }
        if(found==false) {
            String nope = "?";
            //BotLog.log("lineSearch failed: Search result not found: \"" + search + "\"", "SearchTools", 3);
            in.close();
            buffReader.close();
            return nope;
        }
        in.close();
        buffReader.close();
        return search;
    }
    public static String lineSearch3(String search, String content) throws IOException { //returns entire line the search result was found on
        //BotLog.log(search, file, 3);
        Scanner in = null;
        in = new Scanner(content);
        Boolean found=false;
        while(in.hasNext()) {
        String line = in.nextLine();
            if(booleanRegexSearch(search, line)) {
                found=true;
                //BotLog.log("lineSearch: Search result found: \"" + search + "\"", "SearchTools", 1);
                in.close();
                return line;
            }           
        }
        if(found==false) {
            String nope = "?";
            //BotLog.log("lineSearch failed: Search result not found: \"" + search + "\"", "SearchTools", 3);
            in.close();
            return nope;
        }
        in.close();
        return search;
    }
    public static boolean booleanRegexSearch(String search, String searchContent) { //returns boolean if search result was found
        Pattern pattern = Pattern.compile(search.replace("?", ""), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(searchContent);
        boolean matchFound = matcher.find();
        if(matchFound) {
            //BotLog.log("booleanRegexSearch: Search result found: \"" + matcher.group() + "\"", "SearchTools", 1);
            return true;
        } else {
            //BotLog.log("booleanRegexSearch: Search result not found: \"" + pattern.toString() + "\"", "SearchTools", 3);
            return false;
        }
    }
    public static int intRegexSearchStart(String search, String searchContent) { //returns start int of search result
        Pattern pattern = Pattern.compile(search.replace("?", ""), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(searchContent);
        boolean matchFound = matcher.find();
        if(matchFound) {
            //BotLog.log("intRegexSearch: Search result found: \"" + matcher.group() + "\"", "SearchTools", 1);
            return matcher.start();
        } else {
            //BotLog.log("intRegexSearch failed: Search result not found: \"" + pattern.toString() + "\"", "SearchTools", 3);
            return 0;
        }
    }
    public static int intRegexSearchEnd(String search, String searchContent) { //returns end int of search result
        Pattern pattern = Pattern.compile(search.replace("?", ""), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(searchContent);
        boolean matchFound = matcher.find();
        if(matchFound) {
            //BotLog.log("intRegexSearch: Search result found: \"" + matcher.group() + "\"", "SearchTools", 1);
            return matcher.end();
        } else {
            //BotLog.log("intRegexSearch: Search result not found: \"" + pattern.toString() + "\"", "SearchTools", 3);
            return 0;
        }
    }
    public static int countChar(String str, char c) {
        int count = 0;
        for(int i=0; i < str.length(); i++) {
            if(str.charAt(i) == c) count++;
        }
        return count;
    }
    public static int countString(String in, String search) {
        int i = 0;
        Pattern p = Pattern.compile(search);
        Matcher m = p.matcher( in );
        while (m.find()) {
            i++;
        }
        return i;
    }
}