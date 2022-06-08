package by.yukhnevich.carsharing.carsharing.model.entity.car;

import by.yukhnevich.carsharing.carsharing.model.entity.Entity;

import java.math.BigDecimal;

public class Car extends Entity {
    private int id;
    private String brand;
    private String model;
    private CarColor color;
    private int seats;
    private GearboxType gearbox;
    private String manufacturedYear;
    private EngineType engineType;
    private BigDecimal pricePerDay;
    private String vin;
    private String plate;
    private CarClass carClass;
    private String imagePath;

    public Car() {
    }

    public Car(int id, String brand, String model, CarColor color, int seats, GearboxType gearbox,
               String manufacturedYear, EngineType engineType, BigDecimal pricePerDay,
               String vin, String plate, CarClass carClass, String imagePath) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.seats = seats;
        this.gearbox = gearbox;
        this.manufacturedYear = manufacturedYear;
        this.engineType = engineType;
        this.pricePerDay = pricePerDay;
        this.vin = vin;
        this.plate = plate;
        this.carClass = carClass;
        this.imagePath = imagePath;
    }

    public Car(String brand, String model, CarColor color, int seats, GearboxType gearbox,
               String manufacturedYear, EngineType engineType, BigDecimal pricePerDay, String vin,
               String plate, CarClass carClass, String imagePath) {
        this(-1, brand, model, color, seats, gearbox,
                manufacturedYear, engineType, pricePerDay, vin, plate, carClass, imagePath);
    }


    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public CarColor getColor() {
        return color;
    }

    public Integer getSeats() {
        return seats;
    }

    public GearboxType getGearbox() {
        return gearbox;
    }

    public String getManufacturedYear() {
        return manufacturedYear;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public String getVin() {
        return vin;
    }

    public String getPlate() {
        return plate;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (seats != car.seats) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (color != car.color) return false;
        if (gearbox != car.gearbox) return false;
        if (manufacturedYear != null ? !manufacturedYear.equals(car.manufacturedYear) : car.manufacturedYear != null)
            return false;
        if (engineType != car.engineType) return false;
        if (pricePerDay != null ? !pricePerDay.equals(car.pricePerDay) : car.pricePerDay != null) return false;
        if (vin != null ? !vin.equals(car.vin) : car.vin != null) return false;
        if (plate != null ? !plate.equals(car.plate) : car.plate != null) return false;
        if (carClass != car.carClass) return false;
        return imagePath != null ? imagePath.equals(car.imagePath) : car.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + seats;
        result = 31 * result + (gearbox != null ? gearbox.hashCode() : 0);
        result = 31 * result + (manufacturedYear != null ? manufacturedYear.hashCode() : 0);
        result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
        result = 31 * result + (pricePerDay != null ? pricePerDay.hashCode() : 0);
        result = 31 * result + (vin != null ? vin.hashCode() : 0);
        result = 31 * result + (plate != null ? plate.hashCode() : 0);
        result = 31 * result + (carClass != null ? carClass.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", color=").append(color);
        sb.append(", seats=").append(seats);
        sb.append(", gearbox=").append(gearbox);
        sb.append(", manufacturedYear='").append(manufacturedYear).append('\'');
        sb.append(", engineType=").append(engineType);
        sb.append(", pricePerDay=").append(pricePerDay);
        sb.append(", vin='").append(vin).append('\'');
        sb.append(", plate='").append(plate).append('\'');
        sb.append(", carClass=").append(carClass);
        sb.append(", imagePath='").append(imagePath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
