#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------1.实体类---------------------------------
-verbose
-keepnames class com.gym.shancai.utils.PostCall.Mini$*{
public <fields> ;
public <methods> ;
}

-keep class com.gym.shancai.data.** { *; }
-keep class com.gym.shancai.utils.den.** { *; }
-keep class com.gym.shancai.service.** { *; }
-keep class com.gym.shancai.activity.** { *; }
#-keep class com.gym.shancai.annotation.** { *; }
-keep class com.unionpay.mobile.android.**{*;}
-keepattributes EnclosingMethod
#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------
#eventBus
#-libraryjars libs/eventbus-2.4.1.jar
-dontwarn de.greenrobot.event.**
-keep class  de.greenrobot.event.**{ *;}

-keep class  com.gym.shancai.APP
#butterknife
#-libraryjars libs/butterknife-annotations-8.4.0.jar
-dontwarn butterknife.**
-keep class  butterknife.**{ *;}

#gson-2.4
#-libraryjars libs/gson-2.4.jar
-dontwarn com.google.gson.**
-keep class  com.google.gson.**{ *;}

#umeng
#-libraryjars libs/umeng_social_sdk.jar
-dontwarn com.umeng.**
-keep class  com.umeng.**{ *;}

#-libraryjars libs/umeng_social_sdk.jar
-dontwarn com.squareup.**
-keep class  com.squareup.**{ *;}


-dontwarn com.facebook.**
-keep class  com.facebook.**{ *;}
#glide
#-libraryjars libs/glide-3.6.1.jar
-dontwarn com.bumptech.glide.**
-keep class  com.bumptech.glide.**{ *;}

#okio
#-libraryjars libs/okio-1.6.0.jar
-dontwarn okio.**
-keep class  okio.**{ *;}

#okio
#-libraryjars libs/okhttp-3.2.0.jar
-dontwarn okhttp3.**
-keep class  okhttp3.**{ *;}

#v4
#-libraryjars libs/android-support-v4.jar
-dontwarn android.support.**
-keep class  android.support.**{ *;}

#AMap_location_V2.6.0_20160628.jar
#-libraryjars libs/AMap_location_V2.6.0_20160628.jar
-dontwarn com.amap.api.**
-dontwarn com.autonavi.aps.**
-dontwarn com.loc.**
-keep class com.amap.api.**
-keep class com.autonavi.aps.**
-keep class com.loc.**

#alipaySdk-20160516
#-libraryjars libs/alipaySdk-20160516.jar
-dontwarn com.alipay.**
-dontwarn com.ta.utdid2.**
-dontwarn com.ut.device.**
-dontwarn org.json.alipay.**
-keep class com.alipay.**
-keep class com.ta.utdid2.**
-keep class com.ut.device.**
-keep class org.json.alipay.**

#jpush-android-2.1.9
#-libraryjars libs/jpush-android-2.1.9.jar
-dontwarn cn.jpush.**
-keep class cn.jpush.**{ *;}

#SocialSDK_WeiXin_1
#-libraryjars libs/SocialSDK_WeiXin_1.jar
-dontwarn com.tencent.**
-keep class com.tencent.**{ *;}

#core-3.0.0
#-libraryjars libs/core-3.0.0.jar
-dontwarn com.google.zxing.**
-keep class com.google.zxing.**{ *;}

#-------------------------------------------------------------------------

#---------------------------------3.与js互相调用的类------------------------



#-------------------------------------------------------------------------

#---------------------------------4.反射相关的类和方法-----------------------



#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------

#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------