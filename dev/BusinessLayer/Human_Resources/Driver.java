package BusinessLayer.Human_Resources;

import ServiceLayer.JsonService.JsonConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Driver extends Employee {
    @JsonDeserialize(contentUsing = JsonConverter.LocalDateDeserializer.class)
    @JsonSerialize(contentUsing = JsonConverter.LocalDateSerializer.class)
    private final LinkedList<LocalDate> deliveryDates;
    private final LinkedList<String> qualifications;
    private int licenses;

    public Driver(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, int _licenses) {
        super(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, 1);
        licenses = _licenses;
        qualifications = new LinkedList<>();
        deliveryDates = new LinkedList<>();
    }

    public Driver(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, int _licenses, boolean _isLogged,List<EmployeeConstraint> constraints,int _type) {
        super(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _isLogged,constraints,_type);
        licenses = _licenses;
        qualifications = new LinkedList<>();
        deliveryDates = new LinkedList<>();
    }

    public Driver(String _employeeId, String _firstName, String _lastName, String _password, int _accountNumber, int _branchBankNumber, int _salary, String _termsOfEmployment, String _statusOfEmployee, LocalDateTime _hireDate, String _phoneNumber, int _licenses, boolean _isLogged,LinkedList<String> _qualifications,LinkedList<LocalDate> _deliveryDates,List<EmployeeConstraint> constraints,int _type) {
        super(_employeeId, _firstName, _lastName, _password, _accountNumber, _branchBankNumber, _salary, _termsOfEmployment, _statusOfEmployee, _hireDate, _phoneNumber, _isLogged,constraints,_type);
        licenses = _licenses;
        qualifications = _qualifications;
        deliveryDates = _deliveryDates;
    }

    public int getLicenses() {
        return licenses;
    }

    public void setLicenses(int _licenses) {
        licenses = _licenses;
    }

    public LinkedList<String> getQualifications() {
        return qualifications;
    }

    public void addQualification(String qualification) {
        if (qualifications.contains(qualification))
            throw new IllegalArgumentException("qualification is already exist");
        this.qualifications.add(qualification);
    }

    public void removeQualification(String qualification) {
        if (!qualifications.contains(qualification))
            throw new NoSuchElementException("qualification doesn't exist");
        this.qualifications.remove(qualification);
    }

    public LinkedList<LocalDate> getDeliveryDates() {
        return deliveryDates;
    }

    public void addDeliveryDate(LocalDate deliveryDate) {
        if (deliveryDates.contains(deliveryDate))
            throw new IllegalArgumentException("deliveryDate is already exist");
        this.deliveryDates.add(deliveryDate);
    }

    public void removeDeliveryDate(LocalDate deliveryDate) {
        if (!deliveryDates.contains(deliveryDate))
            throw new NoSuchElementException("deliveryDate doesn't exist");
        this.deliveryDates.remove(deliveryDate);
    }

    public boolean hasQualification(String qualification) {
        return qualifications.contains(qualification);
    }

    public void upgardeLicense(int newLicense) {
        if (newLicense <= licenses)
            throw new IllegalArgumentException("illegal license");
        setLicense(newLicense);
    }

    private void setLicense(int newLicense) {
        licenses = newLicense;
    }

    public void checkDate(LocalDate date) {
        if (getDeliveryDates().contains(date))
            throw new IllegalArgumentException("Driver is unavailable");
    }

    public boolean isManagement() {
        return false;
    }

    public void setManagement(boolean _management) {
    }

    public boolean isCancellations() {
        return false;
    }

    public void setCancellations(boolean _cancellations) {
    }

    public List<String> getRoles() {
        List<String> driver = new LinkedList<>();
        driver.add("Driver");
        return driver;
    }

    public void removeRole(String role) {
    }

    public void addRole(String role) {
    }

}
