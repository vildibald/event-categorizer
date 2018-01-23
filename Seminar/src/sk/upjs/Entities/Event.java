package sk.upjs.Entities;

import sk.upjs.Utilities.RegexUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Viliam on 22.3.2015.
 */

public abstract class Event {



    public enum Privacy {
        OPEN,
        FRIENDS,
        CLOSED
    }

    private long eid;
    private String name;
    private String startTime;
    private String endTime;
    private String endTimeComputed;
    private long creator;
    private Object creatorCategory;
    private String location;
    private String locationFilter;
    private double latitude;
    private double longitude;

    private String host;
    private Privacy privacy;
    private String type;
    private boolean valid;

    public Event(long eid, String name, String startTime, String endTime, String endTimeComputed, long creator, Object creatorCategory, String location, String locationFilter, double latitude, double longitude, String host, Privacy privacy, String type, boolean valid) {
        this.eid = eid;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endTimeComputed = endTimeComputed;
        this.creator = creator;
        this.creatorCategory = creatorCategory;
        this.location = location;
        this.locationFilter = locationFilter;
        this.latitude = latitude;
        this.longitude = longitude;
        this.host = host;
        this.privacy = privacy;
        this.type = type;
        this.valid = valid;
    }
    public Event(){

    }

    public Event(Event event){
       this(event.getEid(),
               event.getName(),
               event.getStartTime(),
               event.getEndTime(),
               event.getEndTimeComputed(),
               event.getCreator(),
               event.getCreatorCategory(),
               event.getLocation(),
               event.getLocationFilter(),
               event.getLatitude(),
               event.getLongitude(),
               event.getHost(),
               event.getPrivacy(),
               event.getType(),
               event.isValid());
    }

    //public abstract Event translate(Language toLanguage);

    public int occurencesCount(String word) {

        Pattern p = RegexUtils.compilePattern(word);

        Matcher m = p.matcher(name);
        return occurencesCount(m);
        //m = p.matcher()

    }

    protected int occurencesCount(Matcher matcher){
        int count = 0;
        while (matcher.find()){
            count +=1;
        }
        return count;
    }



    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeComputed() {
        return endTimeComputed;
    }

    public void setEndTimeComputed(String endTimeComputed) {
        this.endTimeComputed = endTimeComputed;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public Object getCreatorCategory() {
        return creatorCategory;
    }

    public void setCreatorCategory(Object creatorCategory) {
        this.creatorCategory = creatorCategory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationFilter() {
        return locationFilter;
    }

    public void setLocationFilter(String locationFilter) {
        this.locationFilter = locationFilter;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
