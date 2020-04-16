package ml.ytooo.string;

import java.util.Random;

/**
 * Created by ebiz on 2017/7/29.
 */
public class MobileUtil {

    public static String get6MobileValidateCode(){
        return  String.format("%06d",new Random().nextInt(999999));
    }
    public static String getMobileSub(String mobileNo){
        return mobileNo.substring(0,3)+"*****"+mobileNo.substring(8);
    }

}
