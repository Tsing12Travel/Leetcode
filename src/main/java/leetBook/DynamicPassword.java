package leetBook;

public class DynamicPassword {
    public String dynamicPassword(String password, int target) {
        return password.substring(target, password.length()) + password.substring(0, target);  // 此方法需要使用 切片函数 substring()
//        return (password + password).substring(target, target + password.length())  // 此方法空间复杂度更高
    }

    public String dynamicPassword2(String password, int target) {
        StringBuilder res = new StringBuilder();

        for (int i = target; i < password.length(); i++) {
            res.append(password.charAt(i));
        }

        for (int i = 0; i < target; i++) {
            res.append(password.charAt(i));
        }

        return res.toString();

        /*
        求余运算可简化代码：
        StringBuilder res = new StringBuilder();
        for(int i = target; i < target + password.length(); i++)
            res.append(password.charAt(i % password.length()));
        return res.toString();
        */
    }

    public String dynamicPassword3(String password, int target) {
        String res = "";

        for (int i = target; i < password.length(); i++) {
            res += password.charAt(i);
        }

        for (int i = 0; i < target; i++) {
            res += password.charAt(i);
        }

        return res;

        /*
        利用求余特性简化：
        String res = "";
        for(int i = target; i < target + password.length(); i++)
            res += password.charAt(i % password.length());
        return res;
        */
    }
}
