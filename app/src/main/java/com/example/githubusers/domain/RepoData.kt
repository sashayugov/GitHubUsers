package com.example.githubusers.domain

sealed class RepoData {

    data class Success(val repos: Array<RepoResponseModel>) : RepoData() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Success

            if (!repos.contentEquals(other.repos)) return false

            return true
        }

        override fun hashCode(): Int {
            return repos.contentHashCode()
        }
    }

    data class Error(val error: Throwable) : RepoData()

    data class Loading(val progress: Int?) : RepoData()
}