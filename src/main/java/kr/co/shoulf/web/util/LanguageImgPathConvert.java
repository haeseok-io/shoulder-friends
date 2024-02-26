package kr.co.shoulf.web.util;

public class LanguageImgPathConvert {
    public static String getImgPath(String languageName) {
        String result;
        StringBuffer imgPath = new StringBuffer();

        imgPath.append("/images/");
        if( languageName.equals("java") )               imgPath.append("java-original.svg");
        else if( languageName.equals("javascript") )    imgPath.append("javascript-original.svg");
        else if( languageName.equals("html") )          imgPath.append("html5-original.svg");
        else if( languageName.equals("css") )           imgPath.append("css3-original.svg");
        else if( languageName.equals("kotlin") )        imgPath.append("kotlin-original.svg");
        else if( languageName.equals("python") )        imgPath.append("python-original.svg");
        else if( languageName.equals("swift") )         imgPath.append("swift-original.svg");
        else if( languageName.equals("spring") )        imgPath.append("spring-original.svg");

        if( imgPath.toString().equals("/images/") )     result = null;
        else                                            result = imgPath.toString();

        return result;
    }
}
