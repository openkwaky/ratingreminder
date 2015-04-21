package net.equasoft.ratingreminder.algo;

public interface IAlgo {
    /**
     * Check if the dialog is showable according to current class algo 
     * See list of algos in <code>et.equasoft.ratingreminder.type.AlgoType</code> class
     * 
     * @return boolean to indicate to show Rating dialog fragment
     */
    public boolean isDialogShowable();
}
