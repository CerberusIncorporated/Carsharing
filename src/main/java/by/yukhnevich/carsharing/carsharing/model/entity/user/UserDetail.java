package by.yukhnevich.carsharing.carsharing.model.entity.user;

import by.yukhnevich.carsharing.carsharing.model.entity.Entity;

public class UserDetail extends Entity {
    private int id;
    private User user;
    private String passportNumber;
    private String phoneNumber;
    private String firstName;
    private String secondName;
    private String middleName;

    public UserDetail() {
    }

    public UserDetail(int id, User user, String passportNumber, String phoneNumber,
                      String firstName, String secondName, String middleName) {
        this.id = id;
        this.user = user;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
    }


    public UserDetail(User user, String passportNumber, String phoneNumber,
                      String firstName, String secondName, String middleName) {
        this(-1, user, passportNumber, phoneNumber, firstName, secondName, middleName);
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetail that = (UserDetail) o;

        if (id != that.id) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (passportNumber != null ? !passportNumber.equals(that.passportNumber) : that.passportNumber != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        return middleName != null ? middleName.equals(that.middleName) : that.middleName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDetail{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", passportNumber='").append(passportNumber).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
