/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classrosterservicelayer.view;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

    private Scanner scan = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scan.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double d;
        do {
            System.out.print(prompt);
            d = Double.parseDouble(scan.nextLine());
        } while (d < min || d > max);
        //we want to continue this if (condition == true)

        return d;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.print(prompt);
        return Float.parseFloat(scan.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float f;
        do {
            System.out.print(prompt);
            f = Float.parseFloat(scan.nextLine());
        } while (f < min || f > max);
        //we want to continue this if (condition == true)

        return f;
    }

    @Override
    public int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scan.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int i;
        do {
            System.out.print(prompt);
            i = Integer.parseInt(scan.nextLine());
        } while (i < min || i > max);
        //we want to continue this if (condition == true)

        return i;
    }

    @Override
    public long readLong(String prompt) {
        System.out.print(prompt);
        return Long.parseLong(scan.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long l;
        do {
            System.out.print(prompt);
            l = Integer.parseInt(scan.nextLine());
        } while (l < min || l > max);
        //we want to continue this if (condition == true)

        return l;
    }

    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        return scan.nextLine();
    }

}
