package br.unip.myapplication.managers;

import br.unip.myapplication.MyApplication;
import br.unip.myapplication.R;

public enum LocationType {

    BUS(1), SUBWAY(2);

    private int locationType;

    LocationType(int locationType){
        this.locationType = locationType;
    }

    public double getLocationType(){
        return this.locationType;
    }

}
