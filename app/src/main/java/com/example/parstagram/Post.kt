package com.example.parstagram

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

//Description; String
//Image: File
// User: String
@ParseClassName("Post")
class Post: ParseObject() {
//--------------------Description------------------------------------
    fun getDescription(): String? {
        return getString(KEY_DESCRIPTION)
    }

    fun setDescription(description: String) {
        put(KEY_DESCRIPTION, description)
    }
//--------------------Image------------------------------------
    fun getImage(): ParseFile? {
        return getParseFile(KEY_IMAGE)
    }

    fun setImage(parseFile: ParseFile) {
        put(KEY_IMAGE, parseFile)
    }
//--------------------User------------------------------------
    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }
//--------------------End------------------------------------


    companion object {
            const val KEY_DESCRIPTION = "description"
            const val KEY_IMAGE = "image"
            const val KEY_USER = "user"
        }
}