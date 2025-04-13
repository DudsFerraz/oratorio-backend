package com.oratorio.springBackEnd.Models.Oratorio;

class OratorianoIdGenerator {
    private static int id = 1;

    private OratorianoIdGenerator() {
    }

    public static int getId(){
        return id++;
    }

}
