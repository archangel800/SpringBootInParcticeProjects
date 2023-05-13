package org.example.fluxlist;

import org.example.helper.NameGenerator;

import java.util.List;

public class FluxVSList {
    public static void main(String[] args) {
      //  List<String> names = NameGenerator.getNames(5);
        //  System.out.println(names);

        NameGenerator.getNames(5)
                .subscribe(System.out::println);
    }
}
