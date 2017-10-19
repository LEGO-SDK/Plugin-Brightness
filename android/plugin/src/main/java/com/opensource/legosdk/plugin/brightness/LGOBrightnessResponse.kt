package com.opensource.legosdk.plugin.brightness

import com.opensource.legosdk.core.LGOResponse

class LGOBrightnessResponse: LGOResponse() {

    var text: String? = null

    override fun resData(): HashMap<String, Any> {
        return hashMapOf(
            Pair("text", this.text ?: "")
        )
    }

}