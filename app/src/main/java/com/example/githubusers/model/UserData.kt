package com.example.githubusers.model

sealed class UsersData {

    data class Success(val users: Array<UserResponseModel> ) : UsersData() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Success

            if (!users.contentEquals(other.users)) return false

            return true
        }

        override fun hashCode(): Int {
            return users.contentHashCode()
        }
    }

    data class Error(val error: Throwable) : UsersData()

    data class Loading(val progress: Int?) : UsersData()
}
