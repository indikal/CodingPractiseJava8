package lk.inli.hackerrank;

import java.io.ByteArrayInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

class Solution{

  public static void main(String[] args){
    Scanner in = new Scanner(new ByteArrayInputStream("121.234.12.12".getBytes()));
    while(in.hasNext()){
      String IP = in.next();
      System.out.println(IP.matches(new MyRegex().pattern));
    }

  }
}
public class MyRegex {
  //"^[0,1,2]\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$"
  public String pattern = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";
}
