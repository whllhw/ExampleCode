package offer;

public class T49 {
    public static void test() {
        T49 x = new T49();
        assert x.StrToInt("0") == 0;
        assert x.StrToInt("1234") == 1234;
        assert x.StrToInt("-123") == -123;
        assert x.StrToInt("+123") == 123;
        assert x.StrToInt("  123") == 0;
        assert x.StrToInt("  -123") == 0;
        assert x.StrToInt("+-123") == 0;
        assert x.StrToInt("1 23") == 0;
        assert x.StrToInt(" -1 23") == 0;
        assert x.StrToInt("  1a33") == 0;
        assert x.StrToInt("1a33") == 0;
        assert x.StrToInt("2147483647") == 2147483647;
        assert x.StrToInt("2147483648") == 0;
        assert x.StrToInt("-2147483648") == -2147483648;
        assert x.StrToInt("-2147483649") == 0;
        assert x.StrToInt("2222222222") == 0;
        assert x.StrToInt("-2222222222") == 0;
        assert x.StrToInt("9223372036854775807") == 0;
        assert x.StrToInt("-9223372036854775807") == 0;
        assert x.StrToInt("-9223372036854775806") == 0;
        assert x.StrToInt("9223372036854775806") == 0;
    }

    public static void main(String[] args) {
        test();
    }

    public int StrToInt(String str) {
        boolean nav = false;
        int pos = 0;
        char first;
        if (pos < str.length()) {
            first = str.charAt(pos);
        } else {
            return 0;
        }
        if (first == '-') {
            nav = true;
            pos++;
        } else if (first == '+') {
            pos++;
        }
        long num = 0;
        int ans = 0;
        for (int i = pos; i < str.length()
                && str.charAt(i) >= '0'
                && str.charAt(i) <= '9'; i++, pos++) {
            num *= 10;
            num += str.charAt(i) - '0';
        }
        if (pos != str.length()) {
            num = 0;
        }
        ans = (int) num;
        if (ans < 0) {
            if (!nav) {
                return 0;
            } else if (ans != -2147483648) {
                // 2147483647 + 1溢出到负数区域 -2147483648
                // 但是当为负数时，这个是可以到的，只不过int存储正数不了2147483648
                // 所以这里判断是否等于-2147483648，
                // 不等于说明原数已经超过了int能表示的范围
                return 0;
            }
        }
        return (int) (nav ? -num : num);
    }
}
