package com.cck.common.consts;

import com.google.gson.Gson;

import java.io.File;

/**
 * @author cck
 */
public class GlobDefine {


    /**
     * gson
     */
    public static   Gson gson = new Gson();
    /**
     * 查询条件类型
     */
    public enum FilterType {
        EQUAL("="),// 等于
        GREATER(">"),//大于
        LESS("<"),//小于
        GREATER_EQUAL(">="),//大于等于
        NOT_EQUAL("<>"),  // 不等于
        IN("in"),// in
        NOT_IN("not in"),// NOT_IN
        LIKE("like"),// like
        //BETWEEN("between"),// between 不用了 用大于等于与小于等于结合
        IS_NULL("IS NULL"),// is null
        IS_NOT_NULL("IS NOT NULL");// is not null

        private String type;

        FilterType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    /**
     * 表关联类型
     */
    public static final class JoinType {
        // left join
        public static final int LEFT = 0;
        // inner join
        public static final int INNER = 1;
        // outer join
        public static final int OUTER = 2;
    }
    /**
     * 分页条数
     */

    public static final int PAGE_SIZE = 10;


    /**
     * 字符串“value”
     */
    public static final String VALUE_STRING = "value";

    /**
     * 字符串“key”
     */
    public static final String KEY_STRING = "key";


    /**
     * 分隔符“/”
     */
    public static final String SPLASH_STRING = "/";


    /**
     * 用户的主目录
     */
    public static final String USER_HOME = System.getProperty("user.home");

    /**
     * 系统分隔符
     */
    public static final String SEPARATOR = File.separator;


    /**
     * 数字 2
     */
    public static final int TWO_INT = 2;

    /**
     * 数字 3
     */
    public static final int THREE_INT = 3;


    /**
     * 英文点号
     */
    public static final String DOT_SIGN = ".";


    /**
     * 符号“-.”
     */
    public static final String NEGATIVE_DOT_SIGN = "-.";

    /**
     * URL本地路径的前缀
     */
    public static final String LOCAL_FILE_URL = "file:";

    /**
     * 空格
     */
    public static final String SPACE = " ";

    /**
     * 脚本匹配模式
     */
    public static final String SCRIPT_FILTER_PATTERN = "<script.*?>.*?</script>";

    /**
     * 桌面路径
     */
    public static final String USER_DESKTOP = USER_HOME + SEPARATOR + "Desktop";

    private GlobDefine() {
    }
}
