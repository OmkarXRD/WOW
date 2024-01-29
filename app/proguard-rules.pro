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
