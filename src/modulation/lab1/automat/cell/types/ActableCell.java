/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.cell.types;

import modulation.lab1.automat.cell.Cell;

/**
 *
 * @author Admin
 */
public interface ActableCell {
    
    public Cell changeState(Cell actor);    
}
