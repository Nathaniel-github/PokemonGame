import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FadeLabel extends JLabel {

        private float alpha;
        private float direction = 0.05f;
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float alpha = getAlpha();
                alpha += direction;
                if (alpha < 0) {
                    alpha = 0;
                    direction = 0.05f;
                } else if (alpha >= 1) {
                	alpha = 1;
                    timer.stop();
                } else {
                	alpha += direction;
                }
                setAlpha(alpha);
            }
        });

        public FadeLabel() {
        	super();
            setAlpha(0);
        }
        
        public FadeLabel(float fade) {
        	super();
        	direction = fade;
            setAlpha(0);
        }
        
        public FadeLabel(Icon icon) {
            super(icon);
            setAlpha(0);
        }
        
        public FadeLabel(Icon icon, float fade) {
            super(icon);
            direction = fade;
            setAlpha(0);
        }

        public void setAlpha(float value) {
        	if (value > 1) {
        		
        		value = 1;
        		
        	}
            if (alpha != value) {
                float old = alpha;
                alpha = value;
                firePropertyChange("alpha", old, alpha);
                repaint();
            }
        }
        
        public void startFade() {
        	
        	timer.start();
        	
        }
        
        public float getAlpha() {
            return alpha;
        }

        @Override
        public void paint(Graphics g) {
            // This is one of the few times I would directly override paint
            // This makes sure that the entire paint chain is now using
            // the alpha composite, including borders and child components
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha()));
            super.paint(g2d);
            g2d.dispose();
        }
    }
