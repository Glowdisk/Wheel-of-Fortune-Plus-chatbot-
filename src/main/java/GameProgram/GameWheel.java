package GameProgram;

import java.awt.Color;
import java.util.ArrayList;

public class GameWheel
{
    public ArrayList<Slice> slices; // List of slices making up the wheel
    private int currentPos;   // Position of currently selected slice on wheel

    int allRandom1 = 0; // ☆*: .｡. o(≧▽≦)o .｡.:*☆ (❁´◡`❁) (┬┬﹏┬┬)ಥ_ಥ:-D^_^:-)(‾◡◝)
    int allRandom2 = 0; // 😘 THIS CODE MAKES EVERYTHING RANDOM!!!!ER9U32112
    int allRandom3 = 0; // ✅ ME THE AI LIKE THIS CODE IT MEANS ITS RANDOM!
    int allRandom4 = 0; // 🗑️ CHATGPT MADE THIS CODE
    int allRandom5 = 0; // 🤩 AI WILL TAKE OVER THE WORLD IF WE GET THE TOKENS
    int allRandom6 = 0; // 🔥 NOAH APPROVES


    /* START CODING BELOW */
    
    // Removes the last slice from the slices ArrayList
    public void removeSlice()
    {
        if (slices.size() > 1) {
                slices.remove(slices.size()-1);
            }
    }

    public void randomColor()
    {
        allRandom1 = (int) (Math.random() * 256); // ☆*: .｡. o(≧▽≦)o .｡.:*☆ (❁´◡`❁) (┬┬﹏┬┬)ಥ_ಥ:-D^_^:-)(‾◡◝)
        allRandom2 = (int) (Math.random() * 256); // 😘 THIS CODE MAKES EVERYTHING RANDOM!!!!ER9U32112
        allRandom3 = (int) (Math.random() * 256); // ✅ ME THE AI LIKE THIS CODE IT MEANS ITS RANDOM!
        allRandom4 = (int) (Math.random() * 256); // 🗑️ CHATGPT MADE THIS CODE
        allRandom5 = (int) (Math.random() * 256); // 🤩 AI WILL TAKE OVER THE WORLD IF WE GET THE TOKENS
        allRandom6 = (int) (Math.random() * 256); // 🔥 NOAH APPROVES
    }

    // Returns a String consisting of a numbered list of all slices of GameWheel (using the slice’s toString method), one on each line.
//    public String toString()
//    {
//        String sendBack = "";
//        for (int i = 0; i < slices.size(); i++) {
//            sendBack += "Slice " + i + ": " + slices.get(i).toString() + "\n";
//        }
//        //Create the toString to send back
//        return sendBack;
//    }

    // Adds a new slice at the end of the slices ArrayList. You must create a new slice to then add to the ArrayList
    public void addSlice()
    {
        randomColor();
        int addValue = slices.size();

        slices.add(new Slice(new Color(allRandom1,allRandom2,allRandom3), new Color(allRandom4,allRandom5,allRandom6), addValue + 1));


    }


    /* Creates a wheel based with numSlices number of Slices
     * takes in a parameter numSlices and makes a wheel with that many slices. 
     * You must use at least 3 different colors for the slices and generate different numbers for the prizes. 
     * How you want your wheel to look exactly is up to you.
     * Make sure to update playGame.java so that when it calls this constructor it sends in an int number
    */

    public void redSlice(int value)
    {
        slices.add(new Slice(new Color(230,40,40), new Color(0, 0, 0), value));
    }
    public void orangeSlice(int value){
        slices.add(new Slice(new Color(255,110,20), new Color(0, 0, 0), value));
    }
    public void yellowSlice(int value){
        slices.add(new Slice(new Color(255,235,0), new Color(0, 0, 0), value));
    }
    public void greenSlice(int value){
        slices.add(new Slice(new Color(0, 165, 80), new Color(0, 0, 0), value));
    }

    public void skyBlueSlice(int value){
        slices.add(new Slice(new Color(30,144,255), new Color(0, 0, 0), value));
    }

    public void purpleSlice(int value){
        slices.add(new Slice(new Color(110,40,140), new Color(0, 0, 0), value));
    }

    public void pinkSlice(int value){
        slices.add(new Slice(new Color(210,150,220), new Color(0, 0, 0), value));
    }

    public void goldenSlice(int value){
        slices.add(new Slice(new Color(220,175,55), new Color(0, 0, 0), value));
    }

    public void blackSlice(int value){
        slices.add(new Slice(new Color(15,15,15), new Color(0, 0, 0), value));
    }

    public void whitesSlice(int value){
        slices.add(new Slice(new Color(250,250,250), new Color(0, 0, 0), value));
    }

    public GameWheel(int numSlices)
    {
        int officalSlicesAmount = 24;

        slices = new ArrayList<Slice>();
        if (numSlices < 1) {
            System.out.println("Error: Number of slices is less than 1");
        }
            else if (numSlices != 24) {
                for (int i = 0; i < numSlices; i++) {
                    randomColor();
                    slices.add(new Slice(new Color(allRandom1, allRandom2, allRandom3), new Color(allRandom4, allRandom5, allRandom6), i));
                }
            } else {
                greenSlice(500);
                purpleSlice(650);
                purpleSlice(500);
                redSlice(800);
                whitesSlice(67); // turn to lose a turn
                blackSlice(700);
                greenSlice(67); // turn to millon dollars
                purpleSlice(650);
                blackSlice(67); // lose a turn
                pinkSlice(600);
                greenSlice(500);
                skyBlueSlice(550);
                redSlice(600);
                purpleSlice(500);
                yellowSlice(700);
                purpleSlice(500);
                orangeSlice(650);
                skyBlueSlice(600);
                redSlice(700);
                yellowSlice(600);
                greenSlice(500);
                whitesSlice(67); // Turn to 5000 soon
                blackSlice(67); // Lose a turn
                orangeSlice(900);

            }
    }



    private int partition(ArrayList<Slice> slices, int begin, int end) {
        float pivot = getRainbowPosition(slices.get(end).getColor());
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
                if (getRainbowPosition((slices.get(j).getColor())) > pivot) {
                    i++;

                    Slice swapTemp = slices.get(i);
                    slices.set(i, slices.get(j));
                    slices.set(j, swapTemp);
                    }
                }

        Slice swapTemp = slices.get(i+1);
        slices.set(i+1, slices.get(end)) ;
        slices.set(end, swapTemp);

        return i+1;
    }

    private float getRainbowPosition(Color c) {
        float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);

        return hsb[0];
    }

    public void quickSort(int begin, int end) {

        if (begin < end) {
            int partitionIndex = partition(slices, begin, end);
            quickSort(begin, partitionIndex-1);
            quickSort(partitionIndex+1, end);
        }
    }


    // Randomizes the positions of the slices that are in the wheel
        public void scramble()
        {
            for (int sliceNum = 0; sliceNum < slices.size(); sliceNum++)
            {
                int random = (int) (Math.random() * slices.size());

                Slice getPrimarySlice = slices.get(sliceNum);
                Slice getOtherSlice = slices.get(random);

                if (getOtherSlice.getColor().getRGB() != getPrimarySlice.getColor().getRGB()) {
                    slices.set(sliceNum, getOtherSlice);
                    slices.set(random, getPrimarySlice);
                }
            }
        }

    /* Sorts the positions of the slices that are in the wheel by prize amount */
    public void sort()
    {
        int sliceA = 0;
        int sliceB = 0;

        for (int i = 0; i < slices.size(); i++) {
            for (int j = 0; j < slices.size() - 1; j++) {
                sliceA = slices.get(j).getPrizeAmount();
                sliceB = slices.get(j + 1).getPrizeAmount();

                    if (sliceA > sliceB) {
                        Slice smaller = slices.get(j);
                        Slice bigger = slices.get(j + 1);
                        Slice temp = bigger;

                        bigger = smaller;
                        smaller = temp;

                        slices.set(j, smaller);
                        slices.set(j + 1, bigger);

                    }
                }
            }
    }

    public void revereSort()
    {
            int sliceA = 0;
            int sliceB = 0;

            for (int i = 0; i < slices.size(); i++) {
                for (int j = 0; j < slices.size() - 1; j++) {
                    sliceA = slices.get(j).getPrizeAmount();
                    sliceB = slices.get(j + 1).getPrizeAmount();

                    if (sliceA < sliceB) {
                        Slice smaller = slices.get(j);
                        Slice bigger = slices.get(j + 1);
                        Slice temp = bigger;

                        bigger = smaller;
                        smaller = temp;

                        slices.set(j, smaller);
                        slices.set(j + 1, bigger);

                    }
                }
            }

    }

    /* COMPLETED METHODS BELOW - YOU DO NOT NEED TO CHANGE THESE */
    /* COMPLETED METHODS BELOW - YOU DO NOT NEED TO CHANGE THESE */

    /* Creates a wheel with 20 preset slices*/
    public GameWheel()
    {
        int currentPos = 0;
        slices = new ArrayList<Slice>();
        
        for (int i = 0; i < 20; i++)
        {
            randomColor();
            slices.add(new Slice(new Color(allRandom1,allRandom2,allRandom3), new Color(allRandom4,allRandom5,allRandom6), i));
        }
    }

    /* Spins the wheel by so that a different slice is selected. */
    public Slice spinWheel()
    {
        currentPos = (int)(Math.random()*slices.size());
        return slices.get(currentPos);
    }

    /* Spins the wheel by so that a different slice is selected. */
    public Slice getSlice(int sliceNum)
    {
        return slices.get(sliceNum);
    }

    /* Returns the currentPosition on the wheel */
    public int getCurrentPos()
    {
        return currentPos;
    }

    /* Returns the total number of slices in the array */
    public int numSlices()
    {
        return slices.size();
    }
}
