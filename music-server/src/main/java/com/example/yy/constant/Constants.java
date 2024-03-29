package com.example.yy.constant;

public class Constants {
    /*歌曲图片，歌手图片，歌曲文件，歌单图片等资源文件的存放路径*/
    public static String PROJECT_PATH = System.getProperty("user.dir");
    public static String AVATAR_IMAGES_PATH = "file:"+PROJECT_PATH+"/img/avatarImages/";
    public static String SONGLIST_PIC_PATH = "file:" + PROJECT_PATH + "/img/songListPic/";
    public static String SONG_PIC_PATH = "file:" + PROJECT_PATH + "/img/songPic/";
    public static String SONG_PATH = "file:" + PROJECT_PATH + "/song/";
    public static String SINGER_PIC_PATH = "file:" + PROJECT_PATH + "/img/singerPic/";
    public static String BANNER_PIC_PATH = "file:" + PROJECT_PATH + "/img/bannerPic/";

    /*设置盐值加密*/
    public static String SALT = "lcf";
}
