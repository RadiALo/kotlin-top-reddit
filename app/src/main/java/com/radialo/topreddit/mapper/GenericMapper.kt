package com.radialo.topreddit.mapper

import org.json.JSONArray
import org.json.JSONObject

interface GenericMapper<M> {
    fun toModel(json : JSONArray): List<M> {
        val models: ArrayList<M> = ArrayList()
        for (i in 0 until json.length()) {
            models.add(toModel(json.getJSONObject(i).getJSONObject("data")))
        }
        return models
    }

    fun toModel(json : JSONObject) : M
}