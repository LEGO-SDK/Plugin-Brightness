package com.opensource.legosdk.plugin.brightness

import com.opensource.legosdk.core.*
import org.json.JSONObject

class LGOBrightness: LGOModule() {

    override fun buildWithJSONObject(obj: JSONObject, context: LGORequestContext): LGORequestable? {
        val request = LGOBrightnessRequest(context)
        request.brightness = obj.optDouble("brightness", 0.0).toFloat()
        return LGOBrightnessOperation(request)
    }

}