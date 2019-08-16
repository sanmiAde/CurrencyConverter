package com.sanmiaderibigbe.currencyconverter.model

data class Rating (val date : String,  val rating : String)

data class Ratings (val  currentratings : List<Rating>)