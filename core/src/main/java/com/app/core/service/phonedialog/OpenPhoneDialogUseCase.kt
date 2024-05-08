package com.app.core.service.phonedialog

import android.content.Context
import android.content.Intent
import android.net.Uri

class OpenPhoneDialogUseCase {
    operator fun invoke(
        context: Context,
        phone: String
    ) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phone")
        context.startActivity(intent)
    }
}