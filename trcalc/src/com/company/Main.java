package com.company;
import java.util.Scanner;
import java.lang.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        String str, a, b, c, d, res, oper = null;
        char aa, ch;
        int intA, intB, intResult,l,y;
        for(int i = 1;i > 0;i++){
            System.out.println("Input:");
            str = scan.nextLine();
            String[] first = str.split(" ");
            a = first[0];
            c = first[1];
            aa = str.charAt(0);
            b = String.valueOf(aa);
            l = str.length();
                if (a.matches("[-+]?\\d+")) {
                    intA = Integer.parseInt(a);
                    if (c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*")) {
                        d = first[2];
                        if (d.matches("[-+]?\\d+")) {
                            intB = Integer.parseInt(d);
                            Calc calc1 = new Calc();
                            calc1.s = intA;
                            calc1.zn = c;
                            calc1.s2 = intB;
                            intResult = calc1.calc();
                            System.out.println("Output:\n" + intResult);
                        } else {
                            throw new IllegalArgumentException("The third element must be number if the first one was a number.");
                        }
                    } else {
                        throw new IllegalArgumentException("The second element must be a sign (+,-,/.*).");
                    }
                } else if (b.equals("\"")) {
                    for (int ci = 0; ci < l; ci++) {
                        ch = str.charAt(ci);
                        oper = String.valueOf(ch);
                    if (oper.equals("+") || oper.equals("-")) {
                        String[] words = str.split("\"");
                        CalcStr calcStr1 = new CalcStr();
                        for (i = 1; i < words.length; i++) {
                            words[i].trim();
                        }
                        a = words[1];
                        b = words[2];
                        c = words[3];
                        if (a.length() < 11 && c.length() < 11) {
                            calcStr1.s = a;
                            calcStr1.s2 = c;
                            calcStr1.zn = b;
                            res = calcStr1.calcStr();
                            System.out.println("Output:\n" + res);
                        } else {
                            throw new IllegalArgumentException("There should be no more than 10 per line .");
                        }
                    } else if (oper.equals("/") || oper.equals("*")) {
                        aa = str.charAt(str.length() - 1);
                        String[] words = str.split("\"");
                        CalcStr calcStr1 = new CalcStr();
                        y = Character.getNumericValue(aa);
                        for (i = 1; i < words.length; i++) {
                            words[i].trim();
                        }
                        for (int s = 0; s < l; s++) {
                            ch = str.charAt(ci);
                            oper = String.valueOf(ch);
                        }
                            a = words[1];
                            if (a.length() <= 10) {
                                calcStr1.a = y;
                                calcStr1.s = a;
                                calcStr1.zn = oper;
                                res = calcStr1.calcStr();
                                System.out.println("Output:\n" +"\""+res+"\"");
                            } else {
                                throw new IllegalArgumentException("There should be no more than 10 per line .");
                            }
                    }
                    }
                } else {
                    throw new IllegalArgumentException("The first element must be in \" or number.");
                }
        }
    }
}
class Calc{
    int s;
    int s2;
    String zn;
    int result;
    int calc(){
        if(s>=0&&s<11&&s2>=0&&s2<11){
            if (zn.equals("+")) {
            result = s + s2;
            return result;
            }
            if(zn.equals("-")){
            result=s-s2;
            return result;
            }
            if (zn.equals("/")){
            result=s/s2;
            return result;
            }
            if(zn.equals("*")){
            result=s*s2;
            return result;
            }
            return 0;
        }else{
            throw new IllegalArgumentException("Numbers isn't included in 1-10");
        }
    }
}
class CalcStr {
    String s;
    String zn;
    String s2;
    String result;
    int a, b, c;
    StringBuilder strB;
    public String calcStr() {
        if (zn.equals(" + ")) {
            result = s.concat(s2);
            return result;
        }
        if (zn.equals(" - ")) {
                result = s.replace (s2, "");
            return result;
            }
        if (zn.equals("/")) {
                if (a >= 0 && a <= 10) {
                    b = s.length();
                    c = b / a;
                    strB = new StringBuilder(s);
                    strB.replace(c, 11, "");
                    result = strB.toString();
                    return result;
                } else {
                    throw new IllegalArgumentException("Numbers isn't included in 1-10");
                }
            }
        if (zn.equals("*")) {
                if (a >= 0 && a < 11) {
                   s2=s.repeat(a);
                    b = s2.length();
                    if(b>=40){
                        strB = new StringBuilder(s2);
                        strB.replace(40, 111, "...");
                        result = strB.toString();
                        return result;
                    }else {
                        result=s2;
                        return result;
                    }
                } else {
                    throw new IllegalArgumentException("Numbers isn't included in 1-10");
                }
            }else{
            throw new IllegalArgumentException("The second element must be a sign (+,-,/.*).");
        }
    }
}