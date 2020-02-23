/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autre_;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Akim
 */
public class Cls_date {

//    private final long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;
//    public void Cls_date1() {
//// 2006-05-01
//        Calendar calendar1 = new GregorianCalendar();
//        calendar1.set(Calendar.YEAR, 2006);
//        calendar1.set(Calendar.MONTH, 4);
//        calendar1.set(Calendar.DAY_OF_MONTH, 1);
//        Date date1 = (Date) calendar1.getTime();
////  2006-08-15
//        Calendar calendar2 = new GregorianCalendar();
//        calendar2.set(Calendar.YEAR, 2006);
//        calendar2.set(Calendar.MONTH, 8);
//        calendar2.set(Calendar.DAY_OF_MONTH, 15);
//        Date date2 = (Date) calendar2.getTime();
//// Différence
//        long diff = Math.abs(date2.getTime() - date1.getTime());
//        long numberOfDay = (long) diff / CONST_DURATION_OF_DAY;
//        System.err.println("Le nombre de jour est : " + numberOfDay);
//    }
//    public static boolean DateTest(String date) throws ParseException {
//        boolean Bien = true;
//
//        Calendar today = new GregorianCalendar();
//        DateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd");
//
//        Calendar userDate = new GregorianCalendar();
//        userDate.setTime(dateForma.parse(date));
//        System.out.println(userDate.before(today));
////
////
//        if (userDate.before(today)) {
//            System.out.println("Date correcte : ");
//            Bien = true;
//        } else {
//            System.out.println("Date incorrecte : 1");
//            Bien = false;
//        }
//        return Bien;
//    }
   public static Date date_expiration(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar.setTime(date);
        calendar.add(Calendar.DATE,40);
        Date d = (Date) calendar.getTime();
        return d;
    }
   
}
