package alg;

import alg.template.Base;

public class T4 extends Base {
    public static void main(String[] args) {
        System.out.println(power(2, 9));
    }

    static int power(int x, int time) {
        if (time == 0) {
            return 1;
        }
        if (time % 2 == 0) {
            return (int) Math.pow(power(x, time / 2), 2);
        } else {
            return (int) Math.pow(power(x, time / 2), 2) * x;
        }
    }
}
