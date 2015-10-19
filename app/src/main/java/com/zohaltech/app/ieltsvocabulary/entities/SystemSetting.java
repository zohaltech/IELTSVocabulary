package com.zohaltech.app.ieltsvocabulary.entities;


import com.zohaltech.app.ieltsvocabulary.classes.Helper;

public class SystemSetting {
    private int     id;
    private Boolean installed;
    private Boolean premium;
    private String  premiumVersion;
    private String  alarmRingingTone;
    private String  ringingToneUri;
    private Boolean vibrateInAlarms;
    private Boolean soundInAlarms;


    public SystemSetting(Boolean installed, Boolean premium, String premiumVersion, String ringingToneUri,
                         String alarmRingingTone, Boolean vibrateInAlarms, Boolean soundInAlarms) {
        setInstalled(installed);
        setPremium(premium);
        setPremiumVersion(premiumVersion);
        setAlarmRingingTone(alarmRingingTone);
        setRingingToneUri(ringingToneUri);
        setVibrateInAlarms(vibrateInAlarms);
        setSoundInAlarms(soundInAlarms);
    }

    public SystemSetting(int id, Boolean installed, Boolean premium, String premiumVersion, String ringingToneUri,
                         String alarmRingingTone, Boolean vibrateInAlarms, Boolean soundInAlarms) {
        this(installed, premium, premiumVersion, ringingToneUri, alarmRingingTone, vibrateInAlarms, soundInAlarms);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getInstalled() {
        return installed;
    }

    public void setInstalled(Boolean installed) {
        this.installed = installed;
    }

    public String getPremiumVersion() {
        return premiumVersion;
    }

    public void setPremiumVersion(String premiumVersion) {
        this.premiumVersion = premiumVersion;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public String getAlarmRingingTone() {
        return alarmRingingTone;
    }

    public void setAlarmRingingTone(String alarmRingingTone) {
        this.alarmRingingTone = alarmRingingTone;
    }

    public String getRingingToneUri() {
        return ringingToneUri;
    }

    public void setRingingToneUri(String ringingToneUri) {
        this.ringingToneUri = ringingToneUri;
    }

    public Boolean getVibrateInAlarms() {
        return vibrateInAlarms;
    }

    public void setVibrateInAlarms(Boolean vibrateInAlarms) {
        this.vibrateInAlarms = vibrateInAlarms;
    }

    public Boolean getSoundInAlarms() {
        return soundInAlarms;
    }

    public void setSoundInAlarms(Boolean soundInAlarms) {
        this.soundInAlarms = soundInAlarms;
    }

    public boolean isPremium() {
        // TODO: 2015/09/27
       return true;
       // return getPremiumVersion() != null && getPremiumVersion().equals(Helper.hashString(Helper.getDeviceId()));
    }
}
