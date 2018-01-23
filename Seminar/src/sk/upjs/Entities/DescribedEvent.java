package sk.upjs.Entities;


import sk.upjs.Utilities.RegexUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Event with description.
 * 1 instance = 1 event from JSON
 */
public class DescribedEvent extends Event{
    private String description;

    private String picSmall;
    private String pic;
    private String picSquare;
    private String picBig;

    public DescribedEvent(){super();}
    public DescribedEvent(DescribedEvent event){
        super(event);
        description = event.getDescription();
    }


    public DescribedEvent(long eid, String name, String startTime, String endTime, String endTimeComputed, long creator, Object creatorCategory, String location, String locationFilter, double latitude, double longitude, String description, String host, Privacy privacy, String type, boolean valid, String picSmall, String pic, String picSquare, String picBig) {
        super(eid,name,startTime,endTime,endTimeComputed,creator,creatorCategory,location,locationFilter,latitude,longitude,host,privacy,type,valid);
        this.description = description;
        this.picSmall = picSmall;
        this.pic = pic;
        this.picSquare = picSquare;
        this.picBig = picBig;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPicSquare() {
        return picSquare;
    }

    public void setPicSquare(String picSquare) {
        this.picSquare = picSquare;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    @Override
    public int occurencesCount(String word) {
        int count = super.occurencesCount(word);
        Pattern p = RegexUtils.compilePattern(word);

        //Matcher m = p.matcher(getName());
       //occurencesCount(m);
        Matcher m = p.matcher(description);

        count += occurencesCount(m);
        return count;
    }

}


//package sk.upjs.Entities;
//
//public class DescribedEvent {
//
//    private Event event;
//
//    private String description;
//
//    private String picSmall;
//    private String pic;
//    private String picSquare;
//    private String picBig;
//
//    public DescribedEvent(){}
//
//    public DescribedEvent(long eid, String name, String startTime, String endTime, String endTimeComputed, long creator, Object creatorCategory, String location, String locationFilter, double latitude, double longitude, String description, String host, Privacy privacy, String type, boolean valid, String picSmall, String pic, String picSquare, String picBig) {
//        event = new Event(eid,name,startTime,endTime,endTimeComputed,creator,creatorCategory,location,locationFilter,latitude,longitude,host,privacy,type,valid);
//        this.description = description;
//
//        this.picSmall = picSmall;
//        this.pic = pic;
//        this.picSquare = picSquare;
//        this.picBig = picBig;
//    }
//
//    public String getLocationFilter() {
//        return event.getLocationFilter();
//    }
//
//    public void setLocationFilter(String locationFilter) {
//        event.setLocationFilter(locationFilter);
//    }
//
//
//
//    public long getEid() {
//        return event.getEid();
//    }
//
//    public void setEid(long eid) {
//        event.setEid(eid);
//    }
//
//    public void setName(String name) {
//        event.setName(name);
//    }
//
//    public void setStartTime(String startTime) {
//        event.setStartTime(startTime);
//    }
//
//    public void setEndTime(String endTime) {
//        event.setEndTime(endTime);
//    }
//
//    public void setEndTimeComputed(String endTimeComputed) {
//        event.setEndTimeComputed(endTimeComputed);
//    }
//
//    public void setCreator(long creator) {
//        event.setCreator(creator);
//    }
//
//    public void setCreatorCategory(Object creatorCategory) {
//        event.setCreatorCategory(creatorCategory);
//    }
//
//    public void setLocation(String location) {
//        event.setLocation(location);
//    }
//
//    public void setLatitude(double latitude) {
//        event.setLatitude(latitude);
//    }
//
//    public void setLongitude(double longitude) {
//        event.setLongitude(longitude);
//    }
//
//    public void setDescription(String description) {
//        this.description=description;
//    }
//
//    public void setHost(String host) {
//        event.setHost(host);
//    }
//
//    public void setPrivacy(Privacy privacy) {
//        event.setPrivacy(privacy);
//    }
//
//    public void setType(String type) {
//        event.setType(type);
//    }
//
//    public void setValid(boolean valid) {
//        event.setValid(valid);
//    }
//
//    public void setPicSmall(String picSmall) {
//        this.picSmall = picSmall;
//    }
//
//    public void setPic(String pic) {
//        this.pic = pic;
//    }
//
//    public void setPicSquare(String picSquare) {
//        this.picSquare = picSquare;
//    }
//
//    public void setPicBig(String picBig) {
//        this.picBig = picBig;
//    }
//
//    public String getName() {
//        return event.getName();
//    }
//
//    public String getStartTime() {
//        return event.getStartTime();
//    }
//
//    public String getEndTime() {
//        return event.getEndTime();
//    }
//
//    public String getEndTimeComputed() {
//        return event.getEndTimeComputed();
//    }
//
//    public long getCreator() {
//        return event.getCreator();
//    }
//
//    public Object getCreatorCategory() {
//        return event.getCreatorCategory();
//    }
//
//    public String getLocation() {
//        return event.getLocation();
//    }
//
//    public double getLatitude() {
//        return event.getLatitude();
//    }
//
//    public double getLongitude() {
//        return event.getLongitude();
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getHost() {
//        return event.getHost();
//    }
//
//    public Privacy getPrivacy() {
//        return event.getPrivacy();
//    }
//
//    public String getType() {
//        return event.getType();
//    }
//
//    public boolean isValid() {
//        return event.isValid();
//    }
//
//    public String getPicSmall() {
//        return picSmall;
//    }
//
//    public String getPic() {
//        return pic;
//    }
//
//    public String getPicSquare() {
//        return picSquare;
//    }
//
//    public String getPicBig() {
//        return picBig;
//    }
//
//    Event getEvent(){return event;}
//}
