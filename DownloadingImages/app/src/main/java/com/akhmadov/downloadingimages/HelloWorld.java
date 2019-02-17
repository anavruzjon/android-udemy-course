package com.akhmadov.downloadingimages;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloWorld {
    public static void main(String[] args) {
        /*String myString = "Rob x Kristen x Tommy x Ralph";
        String [] splitString = myString.split(" x ");
        System.out.println(Arrays.toString(splitString));

        String mississippi = "Mississippi";
        String [] splitMississippi = mississippi.split("s");
        System.out.println(Arrays.toString(splitMississippi));

        for (String word : splitMississippi){
            System.out.print(word.length() + " " );
        }

        System.out.println();

        String river = "Mississippi";
        String riverPart = river.substring(4, 8);
        System.out.println(riverPart);*/
        /*
        String river = "MississippiMississippi";
        Pattern p = Pattern.compile("Mi(.*?)pi");
        Matcher m = p.matcher(river);

        while (m.find()){
            System.out.println(m.group(1));
        }*/

        String celebrity = "<div class=\"image\">\n" +
                "\t\t\t\t\t\t<img src=\"http://cdn.posh24.se/images/:profile/c/467787\" alt=\"Calle Schulman\"/>\n" +
                "\t\t\t\t\t</div><div class=\"image\">\n" +
                "\t\t\t\t\t\t<img src=\"http://cdn.posh24.se/images/:profile/15d9c9ad25296af137ed4455a9a2dbfb0\" alt=\"Liam Payne\"/>\n" +
                "\t\t\t\t\t</div><div class=\"image\">\n" +
                "\t\t\t\t\t\t<img src=\"http://cdn.posh24.se/images/:profile/00ba31c63a734192ae28ce838e01b9664\" alt=\"Naomi Campbell\"/>\n" +
                "\t\t\t\t\t</div><div class=\"image\">\n" +
                "\t\t\t\t\t\t<img src=\"http://cdn.posh24.se/images/:profile/09bd8ba96c471ecd93343b69de668399d\" alt=\"Ariana Grande\"/>\n" +
                "\t\t\t\t\t</div><div class=\"image\">\n" +
                "\t\t\t\t\t\t<img src=\"http://cdn.posh24.se/images/:profile/0c0a0c119a1d107c149fabd0eb559d229\" alt=\"Kim Kardashian\"/>\n" +
                "\t\t\t\t\t</div>";


        Pattern p = Pattern.compile("src=\"(.*?)\"");

        Matcher m = p.matcher(celebrity);
        while (m.find()){
            System.out.println(m.group(1));
        }

        p = Pattern.compile("alt=\"(.*?)\"");

        m = p.matcher(celebrity);
        while (m.find()){
            System.out.println(m.group(1));
        }

    }
}
