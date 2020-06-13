package com.barros.architecturecompare.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class RedditResponse(val data: RedditDataResponse)
class RedditDataResponse(val children: List<RedditChildrenResponse>)
class RedditChildrenResponse(val data: RedditItem)
@Parcelize
data class RedditItem(val title: String, val thumbnail: String) : Parcelable
