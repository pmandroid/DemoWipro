package com.prashant.demowipro.model.bean;

import java.util.List;

/**
 * The type Response.
 */
public class Response {

    private String title;
    private List<RowsItem> rows;

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public List<RowsItem> getRows() {
        return rows;
    }

    /**
     * Sets rows.
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
