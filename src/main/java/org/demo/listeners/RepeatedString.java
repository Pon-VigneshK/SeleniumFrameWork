package org.demo.listeners;

import java.util.ArrayList;
import java.util.Arrays;

public class RepeatedString {

    public static void main(String[] args) {
        String s = "abcac";//"abcac";//abcacabcac
        long n = 10;
        long finalcount;
        int count=0;
        // first need to know the / and % value
        long rem = n%s.length(), qu = n/s.length();
        char []chararr = s.toCharArray();
        // calculate occurrence of 'a' in given s
        for(int i=0; i<chararr.length;i++){
            if (chararr[i] =='a')
                count++;

        }
        if(rem!=0){
            rem = rem;
        }
        else
            rem=0;

        finalcount = qu* count + rem;





//        char[] ch;
//        ch = new char[(int) n];
//
//        for (int i =0; i<n;i++){
//            ch[i] = val.charAt(i % val.length()); // 0%5 - 1%5 - 2%5 - 3%5 - 4%5 - 5%5-0
//            if(ch[i] =='a'){
//                count++;
//            }
//        }
//
//        System.out.println(count);

//        ArrayList<Character> characters =  new ArrayList<>();
//
//        for (int i =0; i<n;i++){
//            characters.add( val.charAt(i % val.length())); // 0%5 - 1%5 - 2%5 - 3%5 - 4%5 - 5%5-0
//            if(characters.get(i) =='a'){
//                count++;
//            }
//        }

        System.out.println(finalcount);



//        int chlen = ch.length;
//        for (int i=0; i<ch.length;i++){
//
//            if( chlen> 0){
//                ch[i] = ch1[i];
//                chlen--;
//                System.out.println(ch[i]);
//            }
//        }


//        String newval="";
//        String finalval="";
//        int count=0;
//        int strlen = 0;
//
//        for(int i=1;i<=n;i++){
//            strlen = newval.length();
//
//            if( strlen < n ) {
//                newval = newval.concat(val);
//
//            }
//        }
//        System.out.println(newval.length());
////        if(newval.length()>n){
////            finalval = newval.substring(0, newval.length()-2);
////        }
////        else
////            finalval = newval;
////
////        System.out.println(finalval);
////
////        char chararr[]  = finalval.toCharArray();
////
////        for (int j=0;j<chararr.length;j++){
////            char val1 = chararr[j];
////
////            if (val1=='a'){
////                count++;
////            }
////        }
//        System.out.println(count);

    }
}
