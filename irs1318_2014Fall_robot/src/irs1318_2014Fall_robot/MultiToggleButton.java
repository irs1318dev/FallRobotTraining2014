package irs1318_2014Fall_robot;

public class MultiToggleButton implements IMultiToggle
{
    private int currentStatePosition;
    private int[] possibleStates;

    public MultiToggleButton(int[] possibleStates)
    {
        this.possibleStates = possibleStates;
        this.currentStatePosition = 0;
    }

    public void toggle()
    {
        this.currentStatePosition++;
        
        // if we are going past the end of the list, go back to the beginning...
        if (this.currentStatePosition >= this.possibleStates.length)
        {
            this.currentStatePosition = 0;
        }
    }

    public int getToggledState()
    {
        return this.possibleStates[this.currentStatePosition];
    }
}
