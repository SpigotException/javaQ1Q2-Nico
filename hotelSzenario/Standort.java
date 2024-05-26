package hotelSzenario;

import baeume.ComparableContent;
import linear.List;

public class Standort implements ComparableContent<Standort> {
    private String name;
    private String land;
    private List<Hotel> hotels;

    public Standort(String name, String land) {
        this.name = name;
        this.land = land;
        this.hotels = new List<Hotel>();
    }

    @Override
    public String toString() {
        return "" + name;
    }

    public String getName() {
        return name;
    }

    public String getLand() {
        return land;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public void addHotel(Hotel pHotel){
        this.hotels.append(pHotel);
    }

    public Hotel gibHotel(String pHotelName){
        for(this.hotels.toFirst();this.hotels.hasAccess();this.hotels.next()){
            if (this.hotels.getContent().getName().equals(pHotelName)){
                return this.hotels.getContent();
            }
        }
        return null;
    }

    public void loescheHotel(String pHotelName){
        for(this.hotels.toFirst();this.hotels.hasAccess();this.hotels.next()){
            if (this.hotels.getContent().getName().equals(pHotelName)){
                this.hotels.remove();
                return;
            }
        }
    }

    public boolean isGreater(Standort pStandort) {
        return this.getName().compareTo(pStandort.getName()) > 0;
    }

    public boolean isEqual(Standort pStandort) {
        return this.getName().compareTo(pStandort.getName()) == 0;
    }

    public boolean isLess(Standort pStandort) {
        return this.getName().compareTo(pStandort.getName()) < 0;
    }
}
