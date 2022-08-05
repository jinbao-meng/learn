package cn.coffebook.text;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

/**
 * 字符相关学习
 *
 * @author jinbao
 * @since 2022/8/3
 */
public class MyCharacters {

    private void setSeparatorLine(String message) {
        if (null == message || message.trim().length() == 0) {
            message = "分割线";
        }
        final String out = MessageFormat.format("================{0}================", message);
        System.out.println(out);
    }

    /**
     * 转义字符解释参见 <a herf=https://docs.oracle.com/javase/tutorial/java/data/characters.html>官方文档</a>
     */
    @Test
    public void escapeCharacter() {
        System.out.println("\\ \t 转义字符使用单个反斜线(backslash)");
        System.out.println("aaa\b \t '\\b':删除键(Backspace)");
        System.out.println("aaa\fbbb \t '\\f':换页符(ASCII:FF 12)");
        System.out.println("aaa\nbbb \t '\\n':换行符(ASCII:LF 10)");
        System.out.println("aaa bbb\rccc \t '\\r':回车(ASCII:CR 13)");
    }

    /**
     * <a href=https://en.wikipedia.org/wiki/ASCII>Wiki-ASCII</a><br>
     * 十进制下<br>
     * 0-31、127 为控制字符<br>
     * 48-56 为 0-9<br>
     * 65-95 为 A-Z<br>
     * 97-122 为 a-z<br>
     */
    @Test
    public void ascii() {
        char c = 'a';
        System.out.println(c + "->ascii:\t" + (int) c);
        int i = 48;
        System.out.println(i + "->ascii:\t" + (char) i);
    }

    /**
     * <b>Unicode 是统一编码的信息技术标准</b><br>
     *
     * <a href=https://www.jcp.org/en/jsr/detail?id=204>JSR 204: Unicode Supplementary Character Support</a><br>
     * java 中以 "\u"跟上4位十六进制数字表示 Unicode 字符。例如 \u4E2D表示 '中'<br>
     * 一个 char(2-byte,16-bit) 长度可表示 0000-ffff 共65535个 Unicode 字符<br>
     * <a href=https://en.wikipedia.org/wiki/Unicode>Wiki-Unicode</a><br>
     * <a href=https://home.unicode.org/>Unicode查询网站</a><br>
     * <a href=https://www.fileformat.info/index.htm>Unicode查询网站</a><br>
     *
     * UTF-8编码格式示例
     */
    @Test
    public void singleCharUnicode() {
        String u_a = "\u0061";
        System.out.println(u_a); // 输出 a
        String u_A = "\u0041";
        System.out.println(u_A); // 输出 A
        String u_zhongwen = "\u6D4B\u8BD5";
        System.out.println(u_zhongwen); // 输出 测试

        //通过 org.apache.commons.text包下的 org.apache.commons.text.StringEscapeUtils
        System.out.println(u_zhongwen + "\t转义为Unicode:\t" + StringEscapeUtils.escapeJava(u_zhongwen));

        // 通过 String -> char[] -> number -> hex
        String text = "?! a 啊";
        for (int i = 0; i < text.length(); i++) {
            System.out.print(numberConversion(text.charAt(i), "hex") + "\t");
        }
        System.out.println();
    }

    /**
     * 2个 char 的 Unicode。<br>
     *
     * <a href=https://cloud.tencent.com/developer/article/1341908>UTF-8 转 UTF-16</a><br>
     *
     * UTF-16编码示例
     */
    @Test
    public void doubleCharUnicode() {
        // emoji 😀
        int smail = 0b11111011000000000; // 二进制表示
        System.out.println("字符表示:\t\uD83D\uDE00"); // 😀
        System.out.println("字符表示:\t" + Character.toString(smail));
        System.out.println("十进制表示:\t" + smail);
        System.out.println("十六进制表示:\t" + numberConversion(smail, "hex"));
        System.out.println("===========================================");
    }

    @Test
    public void a() {
        final String s_8 = new String("\u4E2D");
        System.out.println(s_8);
        final String s_16 = new String("\uD83D\uDE00");
        System.out.println(s_16);
        setSeparatorLine(null);
        System.out.println(new String(s_8.getBytes(StandardCharsets.UTF_8)));
        System.out.println(new String(s_8.getBytes(StandardCharsets.UTF_16)));
        System.out.println("=====================");
        System.out.println(new String(s_16.getBytes(StandardCharsets.UTF_8)));
        System.out.println(new String(s_16.getBytes(StandardCharsets.UTF_16)));
    }

    /**
     * 二进制速算 0b1010 = 0<sup>0</sup> + 2<sup>1</sup> + 0<sup>2</sup> + 2<sup>3</sup> = 0 + 2 + 0 +8 = 10 <br>
     * 八进制速算 01010 = 0<sup>0</sup> + 8<sup>1</sup> + 0<sup>2</sup> + 8<sup>3</sup> = 0 + 8 + 0 + 512 = 520 <br>
     * 八进制速算 07654 = 4＊8<sup>0</sup> + 5*8<sup>1</sup> + 6*8<sup>2</sup> + 7*8<sup>3</sup> = 4 + 40 + 384 + 3584 = 4012 <br>
     * 十六进制速算 0x1010 = 0<sup>0</sup> + 16<sup>1</sup> + 0<sup>2</sup> + 16<sup>3</sup> = 0 + 16 + 0 + 4096 =4112
     */
    @Test
    public void binary_oct_dec_hex() {

        int b = 0b1010; // 二进制以 0b 开头
        System.out.println(b); // 输出 10

        int o = 01010; // 八进制以 0 开头
        System.out.println(o); // 输出 520
        System.out.println(07654); // 输出 4012

        int h = 0x1010; // 十六进制以 0x 开头
        System.out.println(h); // 输出 4112

        int number = 673;
        System.out.println("========numberConversion input decimal========");
        System.out.println(numberConversion(number, "")); // 输出 673
        System.out.println(numberConversion(number, "binary")); // 输出 0b1010100001
        System.out.println(numberConversion(number, "oct")); // 输出 01241
        System.out.println(numberConversion(number, "hex")); // 输出 0x2A1

        int binaryNumber = 0b1010100001;
        System.out.println("========numberConversion input binary========");
        System.out.println(numberConversion(binaryNumber, "")); // 输出 673
        System.out.println(numberConversion(binaryNumber, "binary")); // 输出 0b1010100001
        System.out.println(numberConversion(binaryNumber, "oct")); // 输出 01241
        System.out.println(numberConversion(binaryNumber, "hex")); // 输出 0x2A1
    }

    /**
     * 参考 <a href=https://www.javatpoint.com/java-decimal-to-hex>Java Convert Decimal to Hexadecimal</a>
     *
     * @param number 原始数据可以是 二进制、八进制、十六进制
     * @param type   目标进制类型 二进制:binary、八进制:oct、十六进制:hex
     * @return 相应进制的字符串形式
     */
    private String numberConversion(long number, String type) {
        String starter;
        long scale;
        char[] chars;
        switch (type) {
            case "binary":
                starter = "0b";
                scale = 2;
                chars = new char[]{'0', '1'};
                break;
            case "oct":
                starter = "0";
                scale = 8;
                chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7'};
                break;
            case "hex":
                starter = "0x";
                scale = 16;
                chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                break;
            default:
                return String.valueOf(number);
        }
        StringBuilder result = new StringBuilder(starter);
        StringBuilder s = new StringBuilder();
        while (number > 0) {
            final char c = chars[(int) (number % scale)];
            s.insert(0, c);
            number = number / scale;
        }
        return result.append(s).toString();
    }

}
