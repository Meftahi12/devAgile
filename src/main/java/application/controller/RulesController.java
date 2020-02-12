package application.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class RulesController {

	private double tickUnit;
    private double tickDemiUnit;
    private double tickMiliUnit;
    private boolean rulerExisted=false;	
	 
	public void drawRuler(GraphicsContext g, double x1, double y1, double x2, double y2, boolean isVertical) {
		 double dx = x2 - x1;
         double dy = y2 - y1;
          double len = Math.sqrt(dx*dx + dy*dy);
          
          int j=0;
          if(!isVertical) {
        	  g.strokeLine(0, 0, (int) len, 0);
              for (double i = 0; i < len; i += tickUnit) {
                  g.strokeLine((int) i, -5, (int) i, 5);    
                  g.strokeText(Integer.toString(j-1),(int) ((j-1)*tickUnit+4), y1);
                  g.setStroke(Color.GRAY);
              		j++;
              }
              for (double i = 0; i < len; i += tickMiliUnit)
              		g.strokeLine((int) i, -1, (int) i, 1);
              for (double i = 0; i < len; i += tickDemiUnit)
            	  g.strokeLine((int) i,-3 , (int) i,3 );
            	 
          }
          else {
        	  g.strokeLine(0, 0,0 , (int) len);
              for (double i = 0; i < len; i += tickUnit) {
                  g.strokeLine(-5, (int) i,5 , (int) i);
              	g.strokeText(Integer.toString(j),y1-4, (int) ((j)*tickUnit+4));
              	g.setStroke(Color.GRAY);
              	j++;
              }
              for (double i = 0; i < len; i += tickMiliUnit)
              		g.strokeLine(-1, (int) i, 1, (int) i);
              for (double i = 0; i < len; i += tickDemiUnit)
            	  g.strokeLine(-3, (int) i, 3, (int) i);
          }
          
            }

	public void setUnits(double tickUnit,double tickMiliUnit,double tickDemiUnit) {
		this.tickUnit = tickUnit;
		this.tickMiliUnit = tickMiliUnit;
		this.tickDemiUnit = tickDemiUnit;
	}
	
	 
		public abstract void drawRules();
		public abstract void rulerResize();
		public boolean isRulerExisted() {
			return rulerExisted;
		}

		public void setRulerExisted(boolean rulerExisted) {
			this.rulerExisted = rulerExisted;
		}

}
