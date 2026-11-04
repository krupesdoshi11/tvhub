package com.example.tvhub

enum class Type { APP, YOUTUBE, STREAM }

/** target = package name (APP), URL (YOUTUBE), or .m3u8 URL (STREAM) */
data class Channel(val name: String, val type: Type, val target: String)

// EDIT THIS LIST. Only add streams you have the right to use.
val CHANNELS = listOf(
    // Official apps (use your own subscription)
    Channel("Sony LIV", Type.APP, "com.sonyliv"),
    Channel("JioHotstar", Type.APP, "in.startv.hotstar"),
    Channel("ZEE5", Type.APP, "com.graymatrix.did"),
    Channel("JioTV", Type.APP, "com.jio.jioplay.tv"),
    Channel("YouTube", Type.APP, "com.google.android.youtube.tv"),
    // Free official YouTube live streams
    Channel("Aaj Tak Live", Type.YOUTUBE, "https://www.youtube.com/@aajtak/live"),
    Channel("NDTV India Live", Type.YOUTUBE, "https://www.youtube.com/@NDTVIndia/live"),
    Channel("ABP News Live", Type.YOUTUBE, "https://www.youtube.com/@ABPNews/live"),
    Channel("India Today Live", Type.YOUTUBE, "https://www.youtube.com/@IndiaToday/live"),
    // Your own legal HLS streams (paste official/licensed .m3u8 URLs here)
    // Channel("My Channel", Type.STREAM, "https://example.com/live/index.m3u8"),
)
