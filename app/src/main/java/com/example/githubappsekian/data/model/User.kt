package com.example.githubappsekian.data.model

data class User(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val following: Int,
    val followers: Int
)
