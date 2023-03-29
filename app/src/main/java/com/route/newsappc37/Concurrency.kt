package com.route.newsappc37


/**
 *
 *      Kotlin Channel , flow , stateflow
 *
 *
 *      kotlin Channel is a way that Kotlin coroutines communicates - one -Time -Event
 *
 *      Network access -> when offline -> Toast
 *                          Live Data ->
 *
 *      Hot Stream -> Kotlin Channel
 *          Eager Initialized Data Stream
 *          Says Data Only once
 *          Send Data
 *
 *     Cold Stream -> Kotlin Flow
 *          1- Lazily Initialized Data Stream
 *          2- Says data whenever consumer arrives
 *
 *     Flow      <->  Channel
 *     StateFlow <->  LiveData
 *
 *     State Flow ->
 *            1- Fires(Triggers) only when there is update in data
 *            2- No null values
 *            3- Not LifeCycle Aware (Have a solution)
 *
 *     Live Data ->
 *           1- LifeCycle Aware
 *           2- doesn't care when there is update or not (fires even if send same value again)
 *           3- Null Values Approved
 *
 *    // MVI pattern -> Flow
 *
 */
