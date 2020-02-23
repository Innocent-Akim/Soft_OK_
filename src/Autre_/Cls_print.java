/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autre_;

import static Autre_.Cls_preference.url_Barcode;
import java.awt.image.BufferedImage;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author Akim
 */
public class Cls_print {

    public static ResultSet rs;
    public static PreparedStatement ps;
    public static boolean createBarecode(String nameCode, String code) {
        boolean bool = false;
        try {
            Code128Bean code128 = new Code128Bean();
            code128.setHeight(15f);
            code128.setModuleWidth(0.3);
            code128.setQuietZone(10);
            code128.doQuietZone(true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 150, BufferedImage.TYPE_BYTE_BINARY, false, PAGE_EXISTS);
            code128.generateBarcode(canvas, code);
            canvas.finish();
            try (FileOutputStream fos = new FileOutputStream(url_Barcode() + nameCode)) {
                fos.write(baos.toByteArray());
                fos.flush();
            }
            bool = true;
        } catch (IOException ex) {
            bool = false;
        }
        return bool;
    }
}
