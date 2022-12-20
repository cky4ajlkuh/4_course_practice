package course_paper;

import org.apache.commons.math3.special.BesselJ;

import java.util.ArrayList;
import java.util.Arrays;

public class Bessel {
    public static void main(String[] args) {
        System.out.println(getValue(0, 10));
        System.out.println(getRoot(1));
    }

    public static double getRoot(int index) {
        ArrayList<Double> roots = new ArrayList<>(Arrays.asList(2.404825557696, 5.520078110286, 8.653727912911, 11.79153443901,
                14.93091770849, 18.07106396791, 21.21163662988, 24.35247153075,
                27.49347913204, 30.63460646843, 33.77582021357, 36.91709835366,
                40.05842576463, 43.19979171318, 46.34118837166, 49.48260989740,
                52.62405184111, 55.76551075502, 58.90698392608, 62.04846919023,
                65.18996480021, 68.33146932986, 71.47298160359, 74.61450064370,
                77.75602563039, 80.89755587114, 84.03909077694));
        return roots.get(index);
    }

    public static double getValue(int order, double x) {
        return BesselJ.value(order, x);
    }
}
