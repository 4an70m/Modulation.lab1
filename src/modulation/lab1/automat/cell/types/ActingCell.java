/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.automat.cell.types;

import java.util.List;
import modulation.lab1.automat.cell.Cell;

/**
 *
 * @author Admin
 */
public interface ActingCell {
    public List<Cell> act(List<Cell> suroundingCells);
}
