package com.dgdevelop.platzigram.login.repository

interface LoginRepository {
    fun signIn(username: String, password: String)
}