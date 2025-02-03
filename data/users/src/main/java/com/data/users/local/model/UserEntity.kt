package com.data.users.local.model

import com.data.users.model.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    @SerialName("unid")
    val unid: String? = null,
    @SerialName("createdAt")
    val createdAt: String? = null,
    @SerialName("updatedAt")
    val updatedAt: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("fullName")
    val fullName: String? = null,
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("profilePictureUrl")
    val profilePictureUrl: String? = null,
    @SerialName("onboardingComplete")
    val onboardingComplete: Boolean? = null,
    @SerialName("appBackgroundRefreshEnabled")
    val appBackgroundRefreshEnabled: Boolean? = null,
    @SerialName("enabledPushNotifications")
    val enabledPushNotifications: Boolean? = null,
    @SerialName("enabledHealthKit")
    val enabledHealthKit: Boolean? = null,
    @SerialName("gpsPermission")
    val gpsPermission: Int? = null,
    @SerialName("passwordSet")
    val passwordSet: Boolean? = null,
    @SerialName("fitbitLinked")
    val fitbitLinked: Boolean? = null,
    @SerialName("timeZoneText")
    val timeZoneText: String? = null,
    @SerialName("timeZoneOffsetMinutes")
    val timeZoneOffsetMinutes: Int? = null,
    @SerialName("stepGoal")
    val stepGoal: Int? = null,
    @SerialName("distanceUnits")
    val distanceUnits: String? = null,
    @SerialName("soundsEnabled")
    val soundsEnabled: Boolean? = null,
    @SerialName("teamsVisibility")
    val teamsVisibility: String? = null,
    @SerialName("lat")
    val lat: Double? = null,
    @SerialName("long")
    val long: Double? = null,
    @SerialName("autoGenerateStepGoal")
    val autoGenerateStepGoal: Boolean? = null,
    @SerialName("dailyChallengeStepGoal")
    val dailyChallengeStepGoal: Boolean? = null,
) {

    companion object {
        fun UserModel.toEntity(): UserEntity {
            return UserEntity(
                unid = unid,
                createdAt = createdAt,
                updatedAt = updatedAt,
                email = email,
                name = name,
                fullName = fullName,
                phone = phone,
                profilePictureUrl = profilePictureUrl,
                onboardingComplete = onboardingComplete,
                appBackgroundRefreshEnabled = appBackgroundRefreshEnabled,
                enabledPushNotifications = enabledPushNotifications,
                enabledHealthKit = enabledHealthKit,
                gpsPermission = gpsPermission,
                passwordSet = passwordSet,
                fitbitLinked = fitbitLinked,
                timeZoneText = timeZoneText,
                timeZoneOffsetMinutes = timeZoneOffsetMinutes,
                stepGoal = stepGoal,
                distanceUnits = distanceUnits,
                soundsEnabled = soundsEnabled,
                teamsVisibility = teamsVisibility,
                lat = lat,
                long = long,
                autoGenerateStepGoal = autoGenerateStepGoal,
                dailyChallengeStepGoal = dailyChallengeStepGoal
            )
        }
    }

    fun toExternal(): UserModel {
        return UserModel(
            unid = unid,
            createdAt = createdAt,
            updatedAt = updatedAt,
            email = email,
            name = name,
            fullName = fullName,
            phone = phone,
            profilePictureUrl = profilePictureUrl,
            onboardingComplete = onboardingComplete,
            appBackgroundRefreshEnabled = appBackgroundRefreshEnabled,
            enabledPushNotifications = enabledPushNotifications,
            enabledHealthKit = enabledHealthKit,
            gpsPermission = gpsPermission,
            passwordSet = passwordSet,
            fitbitLinked = fitbitLinked,
            timeZoneText = timeZoneText,
            timeZoneOffsetMinutes = timeZoneOffsetMinutes,
            stepGoal = stepGoal,
            distanceUnits = distanceUnits,
            soundsEnabled = soundsEnabled,
            teamsVisibility = teamsVisibility,
            lat = lat,
            long = long,
            autoGenerateStepGoal = autoGenerateStepGoal,
            dailyChallengeStepGoal = dailyChallengeStepGoal
        )
    }
}