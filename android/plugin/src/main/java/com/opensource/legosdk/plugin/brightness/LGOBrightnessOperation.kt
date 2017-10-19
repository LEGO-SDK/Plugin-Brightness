package com.opensource.legosdk.plugin.brightness

import android.provider.Settings
import com.opensource.legosdk.core.LGORequestable
import com.opensource.legosdk.core.LGOResponse

/**
 * Created by cuiminghui on 2017/10/17.
 */
class LGOBrightnessOperation(val request: LGOBrightnessRequest): LGORequestable() {

    override fun requestSynchronize(): LGOResponse {
        request.brightness?.let { brightness ->
            request.context?.runOnMainThread {
                if (brightness <= 1.0 && brightness >= 0.0) {
                    request.context?.requestActivity()?.let { requestActivity ->
                        Settings.System.putInt(requestActivity.contentResolver,Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
                        Settings.System.putInt(requestActivity.contentResolver, Settings.System.SCREEN_BRIGHTNESS, (brightness * 255).toInt())
                    }
                }
            }
        }
        return LGOBrightnessResponse().accept(null)
    }

    override fun requestAsynchronize(callbackBlock: (LGOResponse) -> Unit) {
        callbackBlock.invoke(requestSynchronize())
    }

}