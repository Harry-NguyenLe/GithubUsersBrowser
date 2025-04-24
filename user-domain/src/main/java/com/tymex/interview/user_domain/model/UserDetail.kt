package com.tymex.interview.user_domain.model

import com.tymex.interview.core.model.DomainModel

data class UserDetail(
    val login: String?,
    val id: Int?,
    val avatarUrl: String?,
    val name: String?,
    val blog: String?,
    val location: String?,
    val followers: Int?,
    val following: Int?,
) : DomainModel
