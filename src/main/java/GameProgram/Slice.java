package GameProgram;

import java.awt.*;

public class Slice
{
    private Color sliceColor;
    private Color textColor;
    private int prizeAmount;

    // Creates a slice with color c, and cash prize p
    public Slice(Color c, int p) 
    {
        sliceColor = c;
        prizeAmount = p;
        textColor = Color.BLACK;
    }

    public Slice(Color c, Color t, int p) 
    {
        sliceColor = c;
        prizeAmount = p;
        textColor = t;
    }

    // Returns the color of this slice as a string
    public Color getColor() 
    {
        return sliceColor;
    }

    public double getHueAngle(Color color) {
        double r = color.getRed() / 255.0;
        double g = color.getGreen() / 255.0;
        double b = color.getBlue() / 255.0;

        return Math.atan2(Math.sqrt(3) * (g - b), 2 * r - g - b);
    }

    // Receives an Color and updates the prize ammount to that value
    public void setColor(Color newColor)
    {
        sliceColor = newColor;
    }

    // Returns the cash prize in dollars for this slice
    public int getPrizeAmount() 
    {
        return prizeAmount;
    }

    // Receives an int and updates the prize ammount to that value
    public void setPrizeAmount(int newPrizeAmmount) 
    {
        prizeAmount = newPrizeAmmount;
    }

    // Returns the color of this slice as a string
    public Color getTextColor() 
    {
        return textColor;
    }

    // Receives an Color and updates the prize ammount to that value
    public void setTextColor(Color newColor)
    {
        textColor = newColor;
    }

    // Returns a string representation of the slice in the following format: "Color: red, prize amount: $50".
    public String toString() 
    {
        return "Color: " + sliceColor + ", Prize Amount: $" + prizeAmount;
    }
}
