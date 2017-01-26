package com.example.mydiary.tool;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by lenovo on 2017/1/24.
 * 处理图片的工具类
 */
public class ImageTool {

    private Context context;//需要的上下文

    public ImageTool(Context context) {
        this.context = context;
    }

    public  Bitmap uriToBitmap(Uri uri){//Uri转Bitmap
        //通过uri找到图片地址并得到图片
        Cursor cursor=context.getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        int index=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        String path=cursor.getString(index);
        cursor.close();
        Bitmap bitmap= BitmapFactory.decodeFile(path);
        return bitmap;
    }

    public String uriToPath(Uri uri){
        //通过uri找到图片地址并得到图片
        Cursor cursor=context.getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        int index=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        String path=cursor.getString(index);
        cursor.close();
        return path;
    }

    public Drawable bitmapToDrawable(Bitmap bitmap){
        return new BitmapDrawable(bitmap);
    }

    public Bitmap drawableToBitmap(Drawable drawable){
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    //压缩图片到1M以内，主要利用compress这个方法,每次利用流判断图片大小，然后进行压缩
    public  Bitmap compressBitmap(String bitmapPath){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
////        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
////        int options = 100;
////        while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于1000kb,大于继续压缩
////            baos.reset();//重置baos即清空baos
////            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
////            options -= 10;//每次都减少10
////            if (options<=0)
////                options=1;
////        }
//
////        int options=baos.toByteArray().length / 1024/100;//压缩的比例
////        Log.v("tag","options"+options);
////        if (options==0) options=1;
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 1, baos);
//        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
//        Log.v("tag", String.valueOf(baos.toByteArray().length));
//        Bitmap bitmap2 = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
//        baos.reset();isBm.reset();
//
//        bitmap2.compress(Bitmap.CompressFormat.JPEG, 1, baos);
//        Log.v("tag", String.valueOf(baos.toByteArray().length));
//        isBm = new ByteArrayInputStream(baos.toByteArray());
//        Bitmap bitmap3 = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
//        baos.reset();isBm.reset();
//
//        try {
//            baos.close();
//            isBm.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bitmap3;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inSampleSize = 2;
        return BitmapFactory.decodeFile(bitmapPath, newOpts);
    }
}
