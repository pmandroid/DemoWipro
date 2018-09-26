package com.prashant.demowipro.model.bean;

/**
 * The type Rows item.
 */
public class RowsItem {

    private String imageHref;
    private String description;
    private String title;

    /**
     * Set image href.
     *
     * @param imageHref the image href
     */
    public void setImageHref(String imageHref){
        this.imageHref = imageHref;
    }

    /**
     * Get image href string.
     *
     * @return the string
     */
    public String getImageHref(){
        return imageHref;
    }

    /**
     * Set description.
     *
     * @param description the description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Get description string.
     *
     * @return the string
     */
    public String getDescription(){
        return description;
    }

    /**
     * Set title.
     *
     * @param title the title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Get title string.
     *
     * @return the string
     */
    public String getTitle(){
        return title;
    }

    @Override
    public String toString(){
        return
                "RowsItem{" +
                        "imageHref = '" + imageHref + '\'' +
                        ",description = '" + description + '\'' +
                        ",title = '" + title + '\'' +
                        "}";
    }
}
