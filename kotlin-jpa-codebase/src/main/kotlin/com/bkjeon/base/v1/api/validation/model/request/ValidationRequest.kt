package com.bkjeon.base.v1.api.validation.model.request

import jakarta.validation.constraints.NotNull

data class ValidationRequest(
    @field:NotNull(message = "param1 을 입력하여 주시길 바랍니다.")
    val param1: String?
)
