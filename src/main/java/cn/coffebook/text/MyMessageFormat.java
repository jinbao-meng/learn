package cn.coffebook.text;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * <a href=https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/text/MessageFormat.html>MessageFormat</a>
 *
 * @author jinbao
 * @since 2022/8/2
 */
public class MyMessageFormat {


    /**
     * 格式化日期
     * 时间格式化字符含义参见以下类描述
     *
     * @see java.text.SimpleDateFormat
     */
    public void formatDate() {
        final Date current = new Date();
        System.out.println(current); // 输出 Wed Aug 03 17:45:56 CST 2022
        System.out.println(MessageFormat.format("{0,date}", current)); // 输出 2022年8月3日
        System.out.println(MessageFormat.format("{0,date,short}", current)); // 输出 2022/8/3
        System.out.println(MessageFormat.format("{0,date,medium}", current)); // 输出 2022年8月3日
        System.out.println(MessageFormat.format("{0,date,long}", current)); // 输出 2022年8月3日
        System.out.println(MessageFormat.format("{0,date,full}", current)); // 输出 2022年8月3日星期三
        System.out.println(MessageFormat.format("{0,date,YYYY-MM-dd HH:mm:ss SSS}", current)); // 输出 2022-08-03 17:45:56 751
    }

    public void formatTime() {
        final Date current = new Date();
        System.out.println(current); // 输出 Wed Aug 03 17:46:49 CST 2022
        System.out.println(MessageFormat.format("{0,time}", current)); // 输出 下午5:46:49
        System.out.println(MessageFormat.format("{0,time,short}", current)); // 输出 下午5:46
        System.out.println(MessageFormat.format("{0,time,medium}", current)); // 输出 下午5:46:49
        System.out.println(MessageFormat.format("{0,time,long}", current)); // 输出 CST 下午5:46:49
        System.out.println(MessageFormat.format("{0,time,full}", current)); // 输出 中国标准时间 下午5:46:49
        System.out.println(MessageFormat.format("{0,time,YYYY-MM-dd HH:mm:ss SSS}", current)); // 输出 2022-08-03 17:46:49 855
    }

    /**
     * 格式化数字，符号参见 <a href=https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html>Special Pattern Characters</a>
     *
     * @see java.text.DecimalFormat Special Pattern Characters
     */
    public void formatDecimal() throws ParseException {
        double a = 12345.6789;
        System.out.println(a); // 12345.6789
        // 所有格式化末位四舍五入
        System.out.println(MessageFormat.format("{0,number} \t 千分位", a)); // 输出 12,345.679
        System.out.println(MessageFormat.format("{0,number,integer} \t 整数", a)); // 输出 12,346
        System.out.println(MessageFormat.format("{0,number,currency} \t 货币符号和千分符", a)); // 输出 ￥12,345.68
        System.out.println(MessageFormat.format("{0,number,percent} \t 千分位和百分比", a));  // 输出 1,234,568%
        System.out.println(MessageFormat.format("{0,number,#.###} \t \"#\" 表示一个数字不存在则不显示", a)); // 输出 12345.679
        System.out.println(MessageFormat.format("{0,number,00000.00} \t \"0\" 表示一个数字不存在则\"0\"补位", a)); // 输出 12345.68
        System.out.println(MessageFormat.format("{0,number,#.0%} \t 百分比且保留一位小数，不存在则\"0\"补位", a)); // 输出 1234567.9%
        System.out.println(MessageFormat.format("{0,number,###,###,###.##} \t 自定义千分符且保留2位小数", a)); // 输出 12,345.68

        // 格式化字符串为数字
        System.out.println("========================================================================");
        final DecimalFormat decimalFormat = new DecimalFormat("#.0%");
        final Number parse = decimalFormat.parse("1234568%");
        System.out.println(parse.doubleValue()); // 输出 12345.68

    }

    public void i18n() {
        MessageFormat mf = new MessageFormat("{0,number,¤}", Locale.US);
        String content = mf.format(new Object[]{10000});
        System.out.println(content);
    }

}
