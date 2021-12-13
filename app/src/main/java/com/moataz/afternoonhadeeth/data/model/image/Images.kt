package com.moataz.afternoonhadeeth.data.model.image

import androidx.annotation.Keep

@Keep
class Images {
    var imageUrl: String? = null

    constructor(imageUrl: String?) {
        this.imageUrl = imageUrl
    }

    constructor() {}
}