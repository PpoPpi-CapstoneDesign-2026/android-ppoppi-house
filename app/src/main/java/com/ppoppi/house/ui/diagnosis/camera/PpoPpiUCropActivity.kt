package com.ppoppi.house.ui.diagnosis.camera

import android.os.Bundle
import androidx.core.view.WindowCompat
import com.yalantis.ucrop.UCropActivity

class PpoPpiUCropActivity : UCropActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // UCrop은 edge-to-edge를 지원하지 않으므로 상단바 inset을 직접 적용한다.
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
    }
}
