/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.spacejunk.sprites;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.opengl.Texture;

/**
 * 
 * @author Techjar
 */
public interface TitleSprite extends Sprite {
    Texture getTexture();
    void setBounds(Shape bounds);
}
