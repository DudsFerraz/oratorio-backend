package com.oratorio.springBackEnd.Models.Oratorio;

class VoluntarioIdGenerator {
    private static int id = 1;

    private VoluntarioIdGenerator() {}

    public static int getId(){
        return id++;
    }
}
