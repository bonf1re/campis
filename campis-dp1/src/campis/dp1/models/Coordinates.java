package campis.dp1.models;

import static java.lang.System.out;

public class Coordinates
{
    public Coordinates(){
        super();
        
    }
    
    public Coordinates(Integer X, Integer Y) {
        this.X = X;
        this.Y = Y;
    }
    private Integer X;
    private Integer Y;

    public Integer getX()
    {
        return this.X;
    }

    public Integer getY()
    {
        return this.Y;
    }

    public void setX(Integer x)
    {
        this.X = x;
    }

    public void setY(Integer y)
    {
        this.Y = y;
    }

    public String stringFormat()
    {
        return this.getX() + ", " + this.getY();
    }
}
