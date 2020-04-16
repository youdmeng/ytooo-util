package ml.ytooo.time;

import ml.ytooo.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 日期格式处理工具类
 * Created by Youdmeng on 2018/2/11 0011.
 */
public class DateFormatUtil{

    /**
     * 将日期字符串处理为日期
     * @param str
     * @return
     */
    public static Date getDateFromStr(String str){

        if(StringUtils.isBlank(str)){
            throw new ServiceException(1,null,"日期字符串转化为日期失败,入参不能为空");
        }

        Date dateReturn = null;
        try{
            //日期格式异常处理
            //正常样式
            if(DateUtil.parseDate(str,"yyyy年MM月") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy年MM月");
            }
            //特殊情况
            else if(DateUtil.parseDate(str,"yyyy年M月") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy年M月");
            }
            else if(DateUtil.parseDate(str,"yyyy年MM月d日") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy年MM月d日");
            }
            else if(DateUtil.parseDate(str,"yyyy年MM月d日 HH时mm分ss秒") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy年MM月d日 HH时mm分ss秒");
            }
            else if(DateUtil.parseDate(str,"yyyy年MM月dd日") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy年MM月dd日");
            }
            else if(DateUtil.parseDate(str,"yyyy年MM月dd日 HH时mm分ss秒") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy年MM月dd日 HH时mm分ss秒");
            }
            else if(DateUtil.parseDate(str,"yyyy年M月dd日") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy年M月dd日");
            }
            else if(DateUtil.parseDate(str,"yyyy年M月dd日 HH时mm分ss秒") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy年M月dd日");
            }
            else if(DateUtil.parseDate(str,"yyyy-M") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy-M");
            }
            else if(DateUtil.parseDate(str,"yyyy-MM") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy-MM");
            }
            else if(DateUtil.parseDate(str,"yyyy-MM-d") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy-MM-d");
            }
            else if(DateUtil.parseDate(str,"yyyy-MM-dd") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy-MM-dd");
            }
            else if(DateUtil.parseDate(str,"yyyy-MM-dd HH:mm:ss") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy-MM-dd HH:mm:ss");
            }
            else if(DateUtil.parseDate(str,"yyyy/MM/dd") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy-MM-dd");
            }
            else if(DateUtil.parseDate(str,"yyyy/MM/dd HH:mm:ss") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy-MM-dd HH:mm:ss");
            }
            else if(DateUtil.parseDate(str,"yyyy-MM-dd HH:mm") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy-MM-dd HH:mm");
            }
            else if(DateUtil.parseDate(str,"yyyy-MM-dd HH: mm") != null){
                dateReturn = DateUtil.parseDate(str,"yyyy-MM-dd HH: mm");
            }

        }catch (Exception e){
            throw new ServiceException(1,null,"["+str+"]日期格式异常");
        }
        return dateReturn;
    }

    /**
     * 获取指定月份第一天00:00:00
     * @return
     */
    public static Date getMonthStart(Date month){

        if(month == null){
            throw new ServiceException(1,null,"月度信息不能为空");
        }
        Date monthStart = month;
        monthStart = DateUtil.getFirstDayOfMonth(monthStart);
        monthStart = DateUtil.getStartOfDay(monthStart);
        return monthStart;
    }

    /**
     * 获取指定月份最后一天23:59:59
     * @return
     */
    public static Date getMonthEnd(Date month){

        if(month == null){
            throw new ServiceException(1,null,"月度信息不能为空");
        }

        Date monthEnd = month;
        monthEnd = DateUtil.getLastDayOfMonth(monthEnd);
        monthEnd = DateUtil.addDays(monthEnd,1);
        monthEnd = DateUtil.addSeconds(monthEnd,-1);
        return monthEnd;
    }


}
