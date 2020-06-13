package com.barros.architecturecompare.model

class RedditResponse(val data: RedditDataResponse)
class RedditDataResponse(val children: List<RedditChildrenResponse>)
class RedditChildrenResponse(val data: RedditItem)
data class RedditItem(val title: String, val thumbnail: String)
