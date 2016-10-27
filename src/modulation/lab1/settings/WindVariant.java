/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.settings;

/**
 *
 * @author Admin
 */
public class WindVariant {
    
    public enum Directions {N, NE, E, SE, S, SW, W, NW, NONE };
    
    public int variant;
    public Directions direction;
    public float time;
    public float speed;
    
    public WindVariant() {
        this.direction = Directions.NONE;
        this.variant = 0;
        this.speed = 0f;
        this.time = 0f;
    }
}
