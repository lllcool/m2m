package com.post.m2m.web.constant;


/**
 * web常量
 *
 * @author ljm
 * @date 2021/01/25
 */
public class WebConst {

    /**
     * 接口路径前缀
     */
    public static final String API_PATH = "/api";

    /**
     * 各个模块的路径
     */
    public static class ModulePath {

        public static final String TEACHER = API_PATH + "/teacher";

        public static final String STUDENT = API_PATH + "/student";

        public static final String STUREFTEACH = API_PATH + "/sturefteach";

        public static final String BALL = API_PATH + "/ball";

        public static final String TEAMA = API_PATH + "/teama";

        public static final String TEAMGROUP = API_PATH + "/teamgroup";

    }

}

