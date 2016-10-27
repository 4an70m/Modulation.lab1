/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulation.lab1.settings;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public final class WindGenerator {

    private static WindGenerator instance;
    public static WindVariant[] VARIANTS;
    public static final int[] VARIANT_ORDER;
    public final Map<TimeLimits, WindVariant> builtVariant;
    public final float totalTime;
    
    static {
        VARIANT_ORDER = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }
    
    private WindGenerator() {
        this(VARIANT_ORDER);
    }
    
    private WindGenerator(int[] order) {
        String str = null;
        try {
            str = this.readFile("C:\\Users\\Admin.4an70m\\Documents\\NetBeansProjects\\Modulation.lab1\\src\\modulation\\lab1\\settings\\WindSettings.json");
        } catch (FileNotFoundException ex) {
        Logger.getLogger(WindGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(WindGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(WindGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
            Gson gson = new Gson();
            WindVariant[] windVariants = gson.fromJson(str, WindVariant[].class);
            Map<Integer, WindVariant> sortedVariants = new HashMap<Integer, WindVariant>();
            for (WindVariant wind : windVariants) {
                sortedVariants.put(wind.variant, wind);
            }
            VARIANTS = new WindVariant[order.length];
            for (int i = 0; i < order.length; i++) {
                VARIANTS[i] = sortedVariants.get(order[i]);
            }
            this.totalTime = this.calculateTotalTime();
            this.builtVariant = this.calcualteVariant();
    }
    
    public static WindGenerator getInstance() {
        if (instance == null) {
            instance = new WindGenerator();
        }
        return instance;
    }
    
    public Map<TimeLimits, WindVariant> getBuiltVairiant () {
        return builtVariant;
    }
    
    private Map<TimeLimits, WindVariant> calcualteVariant() {
        Map<TimeLimits, WindVariant> result = new HashMap<>();
        float variantFinishTime = 0;
        float variantStartTime = 0;
        for (int i = 0; i < VARIANTS.length; i++) {
            variantFinishTime += VARIANTS[i].time * 60;
            result.put(new TimeLimits(variantStartTime, variantFinishTime), VARIANTS[i]);
            variantStartTime += VARIANTS[i].time * 60;
        }
        return result;
    }
    
    private float calculateTotalTime() {
        Float result = 0.0f;
        for (WindVariant wv : WindGenerator.VARIANTS) {
            result += wv.time;
        }
        return result * 60;
    }
    
    public WindVariant getWindVariantByTime(int time) {
        for (TimeLimits tl : this.builtVariant.keySet()) {
            if (tl.in(time)) {
                return this.builtVariant.get(tl);
            }
        }
        return null;
    }
    
    private String readFile(String filePath) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        File file = new File(filePath);
        FileInputStream fis;

        fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return new String(data, "UTF-8");
    }
    
    private class TimeLimits {
        public final float minTime;
        public final float maxTime;

        public TimeLimits(float minTime, float maxTime) {
            this.minTime = minTime;
            this.maxTime = maxTime;
        }
        
        public boolean in (float time) {
            return minTime <= time
                && time <= maxTime;
        }   
    }
}
