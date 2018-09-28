package com.prashant.demowipro.model.bean;

import java.util.List;

/**
 * The type Response.
 */
public class Response {

    private String title;
    private List<RowsItem> rows;

    /**
     * Get title string.
     *
     * @return the string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get rows list.
     *
     * @return the list
     */
    public List<RowsItem> getRows() {
        return rows;
    }

    /**
     * Set rows.
     *
     * @param rows the rows
     */
    public void setRows(List<RowsItem> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "title = '" + title + '\'' +
                        ",rows = '" + rows + '\'' +
                        "}";
    }
}
