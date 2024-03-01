#new
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keepattributes JavascriptInterface

#old
-keepattributes *Annotation*
-dontwarn com.razorpay.**
-keep class com.razorpay.** {*;}
-optimizations !method/inlining/
-keepclasseswithmembers class * {
  public void onPayment*(...);
}


-keep class com.squareup.wire.* { }
-keep class com.opensource.svgaplayer.proto.* {  }

-keep class io.agora.**{*;}

### Keep the model classes and their fields
-keep class com.live.worldsocialintegrationapp.ModelClasses.** { *; }
-keepclassmembers class com.live.worldsocialintegrationapp.ModelClasses.** { *; }

-dontnote okhttp3.**, okio.**, retrofit2.**
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
