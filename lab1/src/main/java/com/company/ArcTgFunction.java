package com.company;

/**
 *
 * Author: Farrukh Karimov
 * Last modification: 05/10/2020
 */
public class ArcTgFunction {
    private double M_PI = ((float)3.141592653589793);
    private double M_PI12 = (M_PI/12.F);
    private double M_PI6 = (M_PI/6.F);
    private double M_PI2 = (M_PI/2.F);
    private double SQRT3 = ((float)1.732050807569);

    public double calc(double x) {
        int sta = 0,sp = 0;
        double x2, a;
        /* check up the sign change */
        if(x<0.F) {
            x = -x;
            sta |= 1;
        }
        /* check up the invertation */
        if(x>1.F) {
            x = 1.F / x;
            sta |= 2;
        }
        /* process shrinking the domain until x<PI/12 */
        while(x > M_PI12) {
            sp++;
            a = x + SQRT3;
            a = 1.F / a;
            x *= SQRT3;
            x -= 1.F;
            x *= a;
        }
        /* calculation core */
        x2 = x * x;
        a = x2 + 1.4087812F;
        a = 0.55913709F / a;
        a += 0.60310579F;
        a -= 0.05160454F * x2;
        a *= x;
        /* process until sp=0 */
        while(sp > 0) {
            a += M_PI6;
            sp--;
        }
        /* invertation took place */
        if((sta & 2) != 0) {
            a = M_PI2 - a;
        }
        /* sign change took place */
        if((sta & 1) != 0) {
            a = -a;
        }
        return a;
    }
}
