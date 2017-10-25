package com.gym.shancai.utils

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.Hashtable

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gym.shancai.R
//import com.google.zxing.BinaryBitmap
//import com.google.zxing.DecodeHintType
//import com.google.zxing.Result
//import com.google.zxing.common.HybridBinarizer
//import com.google.zxing.qrcode.QRCodeReader
import com.gym.shancai.activity.BasePhotoviewActivity
//import com.gym.shancai.customview.RGBLuminanceSource
import com.gym.shancai.glidetransformations.CropCircleTransformation
import com.gym.shancai.glidetransformations.CropCircleTransformationWithStork
import com.gym.shancai.glidetransformations.GlideRoundTransform


object ImageUtils {
    /**
     *
     * 将文件转成base64 字符串
     * @param path 文件路径
     * *
     * @return
     * *
     * @throws Exception
     */
    @Throws(Exception::class)
    fun encodeBase64File(path: String): String {
        val file = File(path)
        val inputFile = FileInputStream(file)
        val buffer = ByteArray(file.length().toInt())
        inputFile.read(buffer)
        inputFile.close()
        return Base64.encode(buffer)
    }

    /**
     * 通过Base32将Bitmap转换成Base64字符串
     * @param bit
     * *
     * @return

     */
    fun Bitmap2StrByBase64(bit: Bitmap): String {
        val bos = ByteArrayOutputStream()
        bit.compress(CompressFormat.JPEG, 80, bos)//参数100表示不压缩
        saveBitmap(bit)
        val bytes = bos.toByteArray()
        return Base64.encode(bytes)
    }

    /**
     * 测试方法保持bitmap到图片 sd根目录
     * @param bm
     */
    fun saveBitmap(bm: Bitmap) {
        val f = File("/sdcard/", "test.jpg")
        if (f.exists()) {
            f.delete()
        }
        try {
            val out = FileOutputStream(f)
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
            Log.i("a", "已经保存")
        } catch (e: FileNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }

    /**
     * 加载图片
     * @param mContext
     * *
     * @param path
     * *
     * @param target
     * *
     * @param isAvatar [0]：是否是加载头像  [1]：是否是加载本地
     */
    fun loadImage(mContext: Context?, path: String?, target: ImageView, vararg isAvatar: Boolean) {
        val avatar = isAvatar.size > 0 && isAvatar[0]
        val isLocal = isAvatar.size > 1 && isAvatar[1]
        if (TextUtils.isEmpty(path)) {
            if (avatar) {
                target.setImageResource(R.drawable.demo_little_icon)
            }
            return
        }
        var headPath = path
        if (!isLocal && headPath!![0] != 'h') {
            headPath = path
        }
        //        target.setTag(null);
        if (avatar)
            target.setImageResource(R.drawable.demo_little_icon)
        try {
            Glide.with(mContext).load(headPath).crossFade(800).into(target)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        target.visibility = View.VISIBLE
    }

    /**
     * 加载圆角边图片
     * @param mContext
     * *
     * @param path
     * *
     * @param target
     * *
     * @param isAvatar [0]：是否是加载头像  [1]：是否是加载本地
     */
    fun loadRoundImage(mContext: Context?, path: String, target: ImageView, round: Float, vararg isAvatar: Boolean) {
        val avatar = isAvatar.size > 0 && isAvatar[0]
        val isLocal = isAvatar.size > 1 && isAvatar[1]
        if (TextUtils.isEmpty(path)) {
            if (avatar) {
                target.setImageResource(R.drawable.demo_little_icon)
            }
            return
        }
        var headPath = path
        if (!isLocal && headPath[0] != 'h') {
            headPath = path
        }
        //        target.setTag(null);
        if (avatar)
            target.setImageResource(R.drawable.demo_little_icon)
        try {
            Glide.with(mContext).load(headPath).transform(GlideRoundTransform(mContext, MathUtils.dip2px(round))).crossFade(800).into(target)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        target.visibility = View.VISIBLE
    }

    /**
     * 加载圆形图片
     * @param mContext
     * *
     * @param path
     * *
     * @param target
     * *
     * @param isAvatar
     */
    fun loadImageCircle(mContext: Context?, path: String, target: ImageView, vararg isAvatar: Boolean) {
        val avatar = isAvatar.size > 0 && isAvatar[0]

        if (avatar) {
            try {
                Glide.with(mContext)//
                        .load(R.drawable.ic_launcher)//
                        .crossFade(0)//
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)//
                        .bitmapTransform(CropCircleTransformation(mContext))//
                        .into(target)//
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // target.setImageResource(R.drawable.common_avatar);
        }

        if (TextUtils.isEmpty(path)) {
            return
        }

        try {
            Glide.with(mContext)//
                    .load(path)//
                    .crossFade(0)//
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)//
                    .bitmapTransform(CropCircleTransformation(mContext))//
                    .into(target)//
        } catch (e: Exception) {
            e.printStackTrace()
        }

        target.visibility = View.VISIBLE
    }

    /**
     * 加载圆形图片
     * @param mContext
     * *
     * @param
     * *
     * @param target
     * *
     * @param isAvatar
     */
    fun loadImageCircle(mContext: Context?, byteFile: ByteArray?, target: ImageView, vararg isAvatar: Boolean) {
        val avatar = isAvatar.size > 0 && isAvatar[0]

        if (avatar) {
            try {
                Glide.with(mContext)//
                        .load(R.drawable.demo_little_icon)//
                        .crossFade(0)//
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)//
                        .bitmapTransform(CropCircleTransformation(mContext))//
                        .into(target)//
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // target.setImageResource(R.drawable.common_avatar);
        }

        if (byteFile == null) {
            return
        }

        try {
            Glide.with(mContext)//
                    .load(byteFile)
                    .centerCrop()//
                    .crossFade(0)//
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)//
                    .bitmapTransform(CropCircleTransformation(mContext))//
                    .into(target)//
        } catch (e: Exception) {
            e.printStackTrace()
        }

        target.visibility = View.VISIBLE
    }

    /**
     * 加载圆形图片带描边
     * @param mContext
     * *
     * @param
     * *
     * @param target
     * *
     * @param isAvatar
     */
    fun loadImageCircleWithStork(mContext: Context?, path: String, target: ImageView, with: Float, color: Int, vararg isAvatar: Boolean) {
        val avatar = isAvatar.size > 0 && isAvatar[0]

        if (avatar) {
            try {
                Glide.with(mContext)//
                        .load(R.drawable.ic_launcher)//
                        .crossFade(0)//
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)//
                        .bitmapTransform(CropCircleTransformationWithStork(mContext, color, with))//
                        .into(target)//
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // target.setImageResource(R.drawable.common_avatar);
        }

        if (TextUtils.isEmpty(path)) {
            return
        }

        try {
            Glide.with(mContext)//
                    .load(path)//
                    .crossFade(0)//
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)//
                    .bitmapTransform(CropCircleTransformationWithStork(mContext, color, with))//
                    .into(target)//
        } catch (e: Exception) {
            e.printStackTrace()
        }

        target.visibility = View.VISIBLE
    }

    /**
     * 根据id 获取drawable
     * @param context
     * *
     * @param maskId
     * *
     * @return
     */

    fun getMaskDrawable(context: Context?, maskId: Int): Drawable {
        val drawable: Drawable?
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //          drawable = context.getDrawable(maskId);
        //        } else {
        drawable = context?.resources?.getDrawable(maskId)
        //        }

        if (drawable == null) {
            throw IllegalArgumentException("maskId is invalid")
        }

        return drawable
    }

    fun Bitmap2Bytes(bm: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }

    /**
     * 根据路径获取图片资源（已缩放）
     * @param url 图片存储路径
     * *
     * @param width 缩放的宽度
     * *
     * @param height 缩放的高度
     * *
     * @return
     */
    fun getBitmapFromUrl(url: String, width: Double, height: Double): Bitmap {

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true // 设置了此属性一定要记得将值设置为false
        val bitmap = BitmapFactory.decodeFile(url)
        // 防止OOM发生
        options.inJustDecodeBounds = false
        val mWidth = bitmap.width
        val mHeight = bitmap.height
        val matrix = Matrix()
        var scaleWidth = 1f
        var scaleHeight = 1f
        //        try {
        //            ExifInterface exif = new ExifInterface(url);
        //            String model = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        // 按照固定宽高进行缩放
        // 这里希望知道照片是横屏拍摄还是竖屏拍摄
        // 因为两种方式宽高不同，缩放效果就会不同
        // 这里用了比较笨的方式
        if (mWidth <= mHeight) {
            scaleWidth = (width / mWidth).toFloat()
            scaleHeight = (height / mHeight).toFloat()
        } else {
            scaleWidth = (height / mWidth).toFloat()
            scaleHeight = (width / mHeight).toFloat()
        }
        //        matrix.postRotate(90); /* 翻转90度 */
        // 按照固定大小对图片进行缩放
        matrix.postScale(scaleWidth, scaleHeight)
        val newBitmap = Bitmap.createBitmap(bitmap, 0, 0, mWidth, mHeight, matrix, true)
        // 用完了记得回收
        bitmap.recycle()
        return newBitmap
    }

    /**
     * h获取一个图片的base64编码
     * @param url
     * *
     * @return
     */
    fun getBitmapBase64(url: String): String {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true // 设置了此属性一定要记得将值设置为false
        val bitmap = BitmapFactory.decodeFile(url)
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bos)//参数100表示不压缩  
        val bytes = bos.toByteArray()
        return Base64.encode(bytes)
    }

//    /**
//     * 解析二维码图片,返回结果封装在Result对象中
//     */
//    fun parseQRcodeBitmap(bitmapPath: String): com.google.zxing.Result? {
//        //解析转换类型UTF-8
//        val hints = Hashtable<DecodeHintType, String>()
//        hints.put(DecodeHintType.CHARACTER_SET, "utf-8")
//        //获取到待解析的图片
//        val options = BitmapFactory.Options()
//        //如果我们把inJustDecodeBounds设为true，那么BitmapFactory.decodeFile(String path, Options opt)
//        //并不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你
//        options.inJustDecodeBounds = true
//        //此时的bitmap是null，这段代码之后，options.outWidth 和 options.outHeight就是我们想要的宽和高了
//        var bitmap = BitmapFactory.decodeFile(bitmapPath, options)
//        //我们现在想取出来的图片的边长（二维码图片是正方形的）设置为400像素
//        /**
//         * options.outHeight = 400;
//         * options.outWidth = 400;
//         * options.inJustDecodeBounds = false;
//         * bitmap = BitmapFactory.decodeFile(bitmapPath, options);
//         */
//        //以上这种做法，虽然把bitmap限定到了我们要的大小，但是并没有节约内存，如果要节约内存，我们还需要使用inSimpleSize这个属性
//        options.inSampleSize = options.outHeight / 400
//        if (options.inSampleSize <= 0) {
//            options.inSampleSize = 1 //防止其值小于或等于0
//        }
//        /**
//         * 辅助节约内存设置
//
//         * options.inPreferredConfig = Bitmap.Config.ARGB_4444;    // 默认是Bitmap.Config.ARGB_8888
//         * options.inPurgeable = true;
//         * options.inInputShareable = true;
//         */
//        options.inJustDecodeBounds = false
//        bitmap = BitmapFactory.decodeFile(bitmapPath, options)
//        //新建一个RGBLuminanceSource对象，将bitmap图片传给此对象
//        val rgbLuminanceSource = RGBLuminanceSource(bitmap)
//        //将图片转换成二进制图片
//        val binaryBitmap = BinaryBitmap(HybridBinarizer(rgbLuminanceSource))
//        //初始化解析对象
//        val reader = QRCodeReader()
//        //开始解析
//        var result: Result? = null
//        try {
//            result = reader.decode(binaryBitmap, hints)
//        } catch (e: Exception) {
//            // TODO: handle exception
//        }
//
//        return result
//    }

    fun lookPic(context: Context?, position: Int, imageUrls: Array<String>) {
        val intent = Intent(context, BasePhotoviewActivity::class.java)
        intent.putExtra("position", position)
        intent.putExtra("images", imageUrls)
        context?.startActivity(intent)

    }

    fun lookPic(context: Context?, position: Int, imageUrl: String) {
        val imageUrls = arrayOfNulls<String>(1)
        imageUrls[0] = imageUrl
        val intent = Intent(context, BasePhotoviewActivity::class.java)
        intent.putExtra("position", position)
        intent.putExtra("images", imageUrls)
        context?.startActivity(intent)
    }
}
