package edu.osu.forevermetric;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.location.Location;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

/**
 * Overlay class used to draw circles on the locations
 * of each contact in the contact list, along with their 
 * name and a line connecting them to your current
 * position.
 * 
 * @author Reto Meier
 * Author of Professional Android Application Development
 * http://www.amazon.com/gp/product/0470344717?tag=interventione-20
 *
 */
public class DrawOverlay extends Overlay {

  private Context context;
  private Location location;
  private GeoPoint locationPoint;
  private Location destinationLocation;
  private GeoPoint destinationPoint;
  
  private Paint paint;
  private Paint backPaint;

  private static int markerRadius = 7;

  
  
  public DrawOverlay(Context _context) {
	    super();
	  }
  
  
  
  /** Get your current location */
	public Location getLocation() {
		return location;
	}
	
  /** Set your current location */
	public void setLocation(Location location, String[] destination) {
	  this.location = location;
	  
      Double latitude = location.getLatitude()*1E6;
      Double longitude = location.getLongitude()*1E6;
      
      Double destinationLatitude = Double.valueOf(destination[0])*1E6;
      Double destinationLongitude = Double.valueOf(destination[1])*1E6;
    
      locationPoint = new GeoPoint(latitude.intValue(),longitude.intValue());    
      destinationPoint = new GeoPoint(destinationLatitude.intValue(),destinationLongitude.intValue()); 
	}  
    
  


  
  @Override
  public void draw(Canvas canvas, MapView mapView, boolean shadow) {
	  super.draw(canvas, mapView, shadow);
	  // Create the paint objects
	  backPaint = new Paint();
	  backPaint.setARGB(200, 200, 200, 200);
	  backPaint.setAntiAlias(true);
	    
	  paint = new Paint();
	  paint.setARGB(255, 10, 10, 255);
	  paint.setAntiAlias(true);
	  paint.setFakeBoldText(true);
	  // Get the map projection to convert lat/long to screen coordinates
	  Projection projection = mapView.getProjection();
    
	  Point lPoint = new Point();
	  projection.toPixels(locationPoint, lPoint);
    
	  // get location point
	  Point point = new Point();
      projection.toPixels(destinationPoint, point);
		 
      //draw line connecting points
      canvas.drawLine(lPoint.x, lPoint.y, point.x, point.y, paint);
      
      //draw points
      RectF oval = new RectF(point.x-markerRadius,point.y-markerRadius,point.x+markerRadius,point.y+markerRadius);
      canvas.drawOval(oval, backPaint);
      oval.inset(2, 2);
      canvas.drawOval(oval, paint);
      //draw second point
      RectF lOval = new RectF(lPoint.x-markerRadius,lPoint.y-markerRadius,lPoint.x+markerRadius,lPoint.y+markerRadius);
      canvas.drawOval(lOval, backPaint);
      lOval.inset(2, 2);
      canvas.drawOval(lOval, paint);
  	}
	  
	@Override
	public boolean onTap(GeoPoint point, MapView mapView) {
	  // Do not react to screen taps.
	  return false;
	}
}