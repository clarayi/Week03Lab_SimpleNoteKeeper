package domain;

import java.io.Serializable;

/**
 * This is Note class.
 * @author 810783
 */
public class Note implements Serializable
{
    private String title;
    private String content;
    
    public Note()
    {
        this.title = "";
        this.content = "";
    }
    
    public Note(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    public String getTitle()
    {
        return this.title;
    }
    
    public String getContent()
    {
        return this.content;
    }
}
