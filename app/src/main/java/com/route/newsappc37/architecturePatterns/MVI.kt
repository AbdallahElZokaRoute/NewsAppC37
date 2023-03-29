package com.route.newsappc37.architecturePatterns

/**
 *  Model - View - Intent
 *  MVVM ? MVC ?
 *
 *  Add-on Architecture pattern
 *  MVI :
 *  Model -> Data States -> Success ->
 *                          Error ->
 *                          Loading ->
 *                          Empty Results ->
 *  MVVM - MVC :-
 *  Model -> Data           Holds Data
 *
 *
 *  View :- On all architecture pattern are the same
 *
 *
 *  Intent :-  Idle
 *             SelectedTab
 *             Likes
 *             Swipe Intents
 *
 *      Add On Architecture Pattern helps you to handle states
 *
 *      2 Sealed Classes :-
 *          1- States (Result of processing intents)
 *          2- Intents (What user should do )
 *
 *
 *
 */