package com.example.ianshinbro.trackerbat.Implentation;

import com.example.ianshinbro.trackerbat.Implentation.Base;
import com.example.ianshinbro.trackerbat.Implentation.OutField;

/**
 * Created by ianshinbro on 2/19/2016.
 */
public interface iAtBat {

     void nextBase();
     void revertBase();

     void setInitialBase(Base base);
    void setFinalBase(Base base);
     String getInitialBase();
     String getFinalBase();
    String getBaseStats();
    OutField getInitialCatch();
    OutField getFinalCatch();
    void setInitialCatch(OutField initialCatch);
    void setFinalCatch(OutField finalCatch);
     void score();
     void revertScore();

}
