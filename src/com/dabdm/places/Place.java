package com.dabdm.places;

/**
 * Represent a place from the Google Places API
 * TODO complete it with the missing attributes
 */
public class Place {
    
    private String formatted_address;
    private String vicinity;
    private String icon;
    private String id;
    private String name;
    private float rating;
    private String reference;
    private String[] types;
    
    /**
     * @return a description message for that place
     */
    public String getSnippet() {
	// TODO build what should really be displayed in the marker bubble on the map
	return "test description for " + getName();
    }
    
    public String getFormatted_address() {
        return formatted_address;
    }
    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
    public String getVicinity() {
        return vicinity;
    }
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public String[] getTypes() {
        return types;
    }
    public void setTypes(String[] types) {
        this.types = types;
    }
}
