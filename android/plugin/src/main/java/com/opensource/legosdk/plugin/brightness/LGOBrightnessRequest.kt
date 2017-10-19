package com.opensource.legosdk.plugin.brightness

import com.opensource.legosdk.core.LGORequest
import com.opensource.legosdk.core.LGORequestContext

/**
 * Created by cuiminghui on 2017/10/17.
 */

class LGOBrightnessRequest(context: LGORequestContext?) : LGORequest(context) {

    var brightness: Float? = null

}