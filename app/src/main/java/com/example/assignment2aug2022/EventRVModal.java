package com.example.assignment2aug2022;

import android.os.Parcel;
import android.os.Parcelable;

public class EventRVModal implements Parcelable{
    // creating variables for our different fields
    private String eventName;
    private String eventOrganizer;
    private String eventImg;
    private String orgEmail;
    private String orgAddress;
    private String orgPhone;
    private String eventDateTime;

    public EventRVModal(){

    }

    public EventRVModal(String eventName, String eventOrganizer, String eventImg, String orgEmail, String orgAddress, String orgPhone, String eventDateTime) {
        this.eventName = eventName;
        this.eventOrganizer = eventOrganizer;
        this.eventImg = eventImg;
        this.orgEmail = orgEmail;
        this.orgAddress = orgAddress;
        this.orgPhone = orgPhone;
        this.eventDateTime = eventDateTime;
    }

    protected EventRVModal(Parcel in) {
        eventName = in.readString();
        eventOrganizer = in.readString();
        eventImg = in.readString();
        orgEmail = in.readString();
        orgAddress = in.readString();
        orgPhone = in.readString();
        eventDateTime = in.readString();
    }

    public static final Creator<EventRVModal> CREATOR = new Creator<EventRVModal>() {
        @Override
        public EventRVModal createFromParcel(Parcel in) {
            return new EventRVModal(in);
        }

        @Override
        public EventRVModal[] newArray(int size) {
            return new EventRVModal[size];
        }
    };

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(String eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(eventName);
        parcel.writeString(eventOrganizer);
        parcel.writeString(eventImg);
        parcel.writeString(orgEmail);
        parcel.writeString(orgAddress);
        parcel.writeString(orgPhone);
        parcel.writeString(eventDateTime);
    }
}
